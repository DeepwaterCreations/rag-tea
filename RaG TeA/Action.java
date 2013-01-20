

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

import java.util.ArrayList;

/**
 * 
 * An abstract class that acts as a template for various game actions. Whenever a player does anything, it is a subclass of Action that gets invoked. 
 * 
 * @author Dylan Craine
 *
 */
public abstract class Action{
	ArrayList<String> verblist;	 //Synonyms for the verb used to invoke.
	
	/**
	 * Initializes the array of verbs that can be used to invoke the action. 
	 */
	public Action(){
		verblist = new ArrayList<String>();
	}
	
	/**
	 * 
	 * Puts the given Strings into the array of verbs that can be used to invoke the action. 
	 *  
	 * @param list A list of verbs to be placed in the verblist.
	 */
	public Action(String[] list){
		verblist = new ArrayList<String>();
		for(String s : list){
			verblist.add(s);
		}
	}
	
	/**
	 * 
	 * @param s A verb that might match this Action. 
	 * @return True if "s" is in the verblist.
	 */
	public boolean equals(String s){
		for(String v : verblist){
			if(v == s)
				return true;
		}
		return false;
 	}
	
	/**
	 * 
	 * @return True if there are no Strings in verblist. 
	 */
	public boolean isEmpty(){
		if(verblist.size() > 0)
			return false;
		return true;
	}
	
	
	/**
	 * 
	 * @return the list of verbs that refer to this action.
	 */
	public ArrayList<String> getVerbs(){
		return verblist;
	}
	
	/**
	 * 
	 * When the Action is performed, this method is invoked.
	 * 
	 * @param cargs A list of entities that this action is meant to affect. The first entry in the list should always be the PlayerCharacter who is the source of the command.
	 */
	public abstract void act(ArrayList<Entity> cargs);
	
	/**
	 * 
	 * actionFactory creates and returns instances of several Actions that are likely to be relevant in most circumstances. Actions that are dependent on
	 * the performing player being in a certain room, or in possession of a certain item or ability, are not returned.
	 * 
	 * @return A list of instances of default Action subclasses.
	 */
	static public ArrayList<Action> actionFactory(){
		ArrayList<Action> alist = new ArrayList<Action>();
		alist.add(new ActionDance());
		alist.add(new ActionQuit());
		alist.add(new ActionLook());
		alist.add(new ActionDudMove());

		return alist;
	}

	
}
