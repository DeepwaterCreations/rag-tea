/*
 * Copyright 2012 Dylan Craine
 * 
    This file is part of RaG TeA, the Randomly Generated Text Adventure.

    RaG TeA is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    RaG TeA is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with RaG TeA.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */

import java.util.*;



/**
 * Reads and interprets commands from the command line. 
 * 
 * @author Dylan Craine
 *
 */

public class CommandReader{
	//HashMap<String, ???> exits;  //I still need to work out just what exits are - probably a subclass of Action.
		//Wait, it's been a while since I wrote that comment. Aren't exits working now? What does this mean?
	HashMap<String, Action> localVerbs;
	HashMap<String, Action> defaultVerbs;
	ArrayList<Entity> localEntities;
	
	PlayerCharacter thisPlayer;
	World world;
	
	Scanner ears;
		
	public CommandReader(String cname){
		world = new World();
		localVerbs = new HashMap<String, Action>();
		defaultVerbs = new HashMap<String, Action>();
		localEntities = new ArrayList<Entity>();
		
		ears = new Scanner(System.in);
		ArrayList<Action> dVerbList = Action.actionFactory();
		for(Action a:dVerbList){
			for(String verb:a.getVerbs()){
				defaultVerbs.put(verb, a);
			}
		}
		thisPlayer = world.playerConnect(cname);
		ArrayList<Entity> lookinput = new ArrayList<Entity>();
		lookinput.add(thisPlayer);
		defaultVerbs.get("look").act(lookinput);
	}
	
	public void interpret(String[] command){
		
		updateLocal();
		
		Action actmatch;
		ArrayList<Entity> objectmatches = new ArrayList<Entity>();
		objectmatches.add(thisPlayer); //Actions get the calling player as a default argument.
				
		//Check to make sure there's actually text in the command.
		if(command.length < 1 || command[0] == ""){
			System.out.println("You didn't actually say anything. It's difficult for me to help you if you behave like this.");
			return;
		}
		
		if(localVerbs.containsKey(command[0])){
			actmatch = localVerbs.get(command[0]);
		}
		else if(defaultVerbs.containsKey(command[0])){
			actmatch = defaultVerbs.get(command[0]);
		}	
		else{
			System.out.println("You don't know how to " + command[0] + "!");
			return;
		}
		
		if(command.length > 1){
			int index; //This bit does nothing but skip "the".
			if(command[1] == "the")
				index = 2;
			else
				index = 1;
			
			while(index < command.length){ //Takes words from the command and tries to match them to objects, both individually and in pairs.
											//Matches are added to the array.
				Entity obj = new Item();
				obj = matchObj(command[index]);
				if(obj.getName().equals("") && index + 1 < command.length){
					obj = matchObj(command[index], command[index + 1]);
				}
				
				if(!obj.getName().equals(""))
					objectmatches.add(obj);
				
				index ++;
			}
		}
		if(command.length > 1 && objectmatches.size() < 2){ //This bit is to report to the player that we have no idea what he's referring to.
			int index;
			if(command[1] == "the" && command.length > 2)
				index = 2;
			else
				index = 1;
			String words = "";
			for(int i = index; i < command.length; i++){
				words += (command[i] + " ");
			}			
			System.out.println("You don't see any \'" + words + "\' here!");
			
			
		}
		else actmatch.act(objectmatches);
		
		
	}
	

	/**
	 * 
	 * Takes a name of an entity and returns a reference to such an entity, if one is in the player's vicinity (as determined
	 * by the contents of localentities). 
	 * 
	 * @param s The name or a prefix of the name of the entity that is being sought.
	 * @return  An entity with the given name and adjective, or a blank Item if no such entity is found.
	 */
	private Entity matchObj(String s){
		return matchObj(s, "*");
	}
	
	/**
	 * 
	 * Takes a written description (name and adjective) of an entity and returns a reference to such an entity, if one is in the player's vicinity (as determined
	 * by the contents of localentities). 
	 * 
	 * @param s The name or a prefix of the name of the entity that is being sought. 
	 * @param adj The adjective describing the entity, or "*" to match any adjective.
	 * @return An entity with the given name and adjective, or a blank Item if no such entity is found.
	 */
	private Entity matchObj(String s, String adj){
		Entity retentity = new Item();
		ArrayList<Entity> possibles = new ArrayList<Entity>();
		ArrayList<Entity> closematches = new ArrayList<Entity>();
		for(Entity e : localEntities){
			if(e.getName().startsWith(s)){
				if(adj.equals("*") || e.getAdj().equals(adj))
					possibles.add(e);
				else
					closematches.add(e);
			}
		}
		
		//If we have only one match, we're done. 
		//Zero matches, we're still done, just with a message to tell the player. 
		//Otherwise, we'll have to have the player clarify.  
		if(possibles.size() == 1)
			retentity = possibles.get(0);
		else if(possibles.size() < 1){
			if(closematches.size() != 0){
				String message = "I don't see one of those. Perhaps you meant ";
				for(Entity cm : closematches){
					message += "the " + cm.getAdj() + " " + cm.getName() + ", or ";
				}
				message += "something else entirely?";
				System.out.println(message);
			}
		}
		else{
			String message = "I see " + possibles.size() + " of those here!";
			//If no adjective was specified, or if the names are different, they get a chance to clarify.
			boolean allsame = true;
			for(Entity pm : possibles){
				if(!pm.getName().equals(possibles.get(0).getName())) //Check each name against the first. Set the flag if one of them doesn't match.
					allsame = false;
			}
			if(adj.equals("*") || !allsame){
				message += " Which did you mean? Was it ";
				for(Entity pm : possibles){
					message += "the " + pm.getName() + " " + pm.getAdj() + ", or ";
				}
				message += "something else entirely?";
				System.out.println(message);
				String[] specification = ears.nextLine().toLowerCase().split(" "); //One-line version of listen().
				//They just might have used "the", so I'm screwing around with array indices to check for that.
				//(Whatever happened to KISS? Oh well.)
				int i = 0;
				if(specification[i].equals("the") )
					i++;
				if(specification.length - i > 1)
					retentity = matchObj(specification[i], specification[i + 1]); //Calls itself with the new argument.
				else if(specification.length - i > 0)
					retentity = matchObj(specification[i]);
				else
					System.out.println("The what? THE WHAT? Finish the phrase, numbskull!");
			}
			else{ //This is what happens if there are multiple items with the exact same name.
					//We just ask them for a number and return whatever that index point to in the possible options.
				message += " Which one did you want?";
				int choice = ears.nextInt();
				while(choice <= 0 || choice > possibles.size()){
					System.out.println("There aren't that many. There are only " + possibles.size() + ".");
					choice = ears.nextInt();
				}
				retentity = possibles.get(choice);				
			}
			
		}
		return retentity;
	}
	
	/**
	 * Updates the list of actions to reflect the player's current circumstances.
	 */
	private void updateLocal(){
		HashMap<String, Action> newVerbs = new HashMap<String, Action>();
		for(Action a : thisPlayer.getActions()){
			for(String verb:a.getVerbs()){
				newVerbs.put(verb, a);
			}
		}
		localVerbs = newVerbs;
	}
	
	
	
	public void addLocalVerb(Action newact){
		for(String verb:newact.getVerbs()){
			localVerbs.put(verb, newact);
		}
	}
	
	public void addLocalVerb(ArrayList<Action> newacts){
		for(Action act:newacts)
			addLocalVerb(act);
	}
	
	public void remLocalVerb(Action remact){
		for(String verb:remact.getVerbs()){
			localVerbs.remove(verb);
		}
	}
	
	public void remLocalVerb(ArrayList<Action> remacts){
		for(Action act:remacts){
			remLocalVerb(act);
		}
	}
	
	public void addLocalEntity(Entity newent){
		localEntities.add(newent);
	}
	
	public void addLocalEntity(ArrayList<Entity> newents){
		localEntities.addAll(newents);
	}
	
	public void remLocalEntity(Entity rement){
		localEntities.remove(rement);
	}
	
	public void remLocalEntity(ArrayList<Entity> rements){
		for(Entity ent:rements){
			localEntities.remove(ent);
		}
	}
	
	
}

