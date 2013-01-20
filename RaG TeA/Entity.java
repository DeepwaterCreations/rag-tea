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
 * Superclass that defines objects in the game world. 
 * 
 * @author Dylan Craine
 *
 */
public abstract class Entity{
	protected String name;
	protected String adjective;
	protected String shortdesc;
	protected String longdesc; //Longdesc is for examining items. Short is for "There is a small fountain here, bubbling away."
	
	//The point of this nested class is to have a way to pass Entity descriptions around without passing actual Entities.
	//Benefits include that I can have a constructor that ensures all the fields get filled.
	//I've no idea if this structure really makes sense in the long run. It's kind of an experiment.
	//Also, I haven't used nested classes much yet, so I'm trying 'em out.
	public static class EntityData{
		public String name;
		public String adjective;
		public String shortdesc;
		public String longdesc;
		
		public EntityData(String _name, String _adjective, String _shortdesc, String _longdesc){
			name = _name;
			adjective = _adjective;
			shortdesc = _shortdesc;
			longdesc = _longdesc;
		}
	}	
	
	protected ArrayList<Entity> contents; //An entity can contain anything - so rooms can have items in them, but items can also have rooms.
								//Serves as the player's inventory.
	protected Entity location; //Should refer to whatever entity has this entity listed in "contents". For nested entities, that should be the innermost above this one.
    				//Each entity should only be in one other entity's "contents". I'll have to figure out if I want a "deepContains" method, in case having the
					//magic pebble in your pocket when you enter the grotto is important. (Or having it in the small velvet bag in the locked chest at the bottom of
					//the vault in your pocket dimension hidden behind the gem in the ring you're wearing when you enter the grotto.)
					//Note to self: Ultimately, all of that should be possible in this game. Because that's just too awesome not to happen.

	public Entity(){
		name = "";
		adjective = "";
		shortdesc = "";
		longdesc = "";
		contents = new ArrayList<Entity>();
	}
	
	/**
	 * If a string is given, that's the entity's name. This might not ultimately get used, I'm just including it
	 * so I can do some simple testing.
	 * 
	 * @param n The name of the new entity.
	 */
	public Entity(String n){
		name = n;
		adjective = "";
		shortdesc = "";
		longdesc = "";
		contents = new ArrayList<Entity>();
	}
	
	public Entity(EntityData data){
		name = data.name;
		adjective = data.adjective;
		shortdesc = data.shortdesc;
		longdesc = data.longdesc;
		contents = new ArrayList<Entity>();
	}
	
	public abstract ArrayList<Action> getActions();
	
	public abstract String getLook();
	
	private void addContent(Entity cont){ //DON'T USE THIS! 
		contents.add(cont);
	}
	
	private void remContent(Entity cont){//Ditto for this one. They should only be called from setLocation.
		contents.remove(cont);
	}
	
	public void setLocation(Entity newloc){
		if(location != null) location.remContent(this); //This is the only place where these two methods should be called.
		location = newloc;
		location.addContent(this);
	}
	
	public Entity getLocation(){
		return location;
	}
	

	public void setName(String n){
		name = n;
	}
	
	public String getName(){
		return name;
	} 
	
	public void setAdj(String adj){
		adjective = adj;
	}
	
	public String getAdj(){
		return adjective;
	}
	
	public void setsDesc(String sdesc){
		shortdesc = sdesc;
	}
	
	public String getsDesc(){
		return shortdesc;
	}
	
	public void setlDesc(String ldesc){
		longdesc = ldesc;
	}
	
	public String getlDesc(){
		return longdesc;
	}
	
	
	
}