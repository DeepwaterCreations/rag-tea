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
 * Represents a room, in the text adventure sense. Could be a small chamber, could be the corner of a meadow or the summit of a mountain or the inside
 * of an acorn. Anywhere the player could be, which, with a bit of magic, could be anywhere, I guess. (Maybe I'll need a way to turn items into rooms?)    
 * 
 * @author Dylan Craine
 *
 */

public class Room extends Entity{

	ArrayList<Exit> exits;
	Block block;
	

	public Room(Block b){
		super();
		exits = new ArrayList<Exit>();
		block = b;
	}
	
	public Room(EntityData data, Block b){
		super(data);
		exits = new ArrayList<Exit>();
		block = b;
	}
	
	public Block getBlock(){
		return block;
	}
	
	public void addExit(Exit e){
		exits.add(e);
	}
	
	public void removeExit(Exit e){
		if(exits.contains(e))
			exits.remove(e);
	}
	
	public void setExit(String ename, Room r){
		Exit setexit = getExitNamed(ename);
		if(setexit != null)
			setexit.set(r);
		else
			System.err.println("Tried to set a non-existent exit called " + ename + " in Room " + name);
	}
	
	public Exit getExitNamed(String ename){
		for(Exit e : exits){
			if(e.getName().equals(ename)) return e;
		}
		return null;
	}
	
	public boolean hasExitNamed(String ename){
		if(getExitNamed(ename) != null)
			return true;
		return false;
	}
	
	//I've got to be careful with this. I want recursion, but not infinite loops. Particularly if PC's version calls the containing room -
	//which it will have to - and then that room calls its contents.
	//I'll leave it at exits for now, since I haven't gotten to features yet. I might want to filter out items, or at least distinguish between
	//actions for items that are held and items that are just nearby. 
	public ArrayList<Action> getActions(){ 
		ArrayList<Action> returnlist = new ArrayList<Action>();
		for(Exit e: exits)
			returnlist.add(e.getAction());
		return returnlist;
	}


	public String getLook() {
		String lstring = new String();
		System.err.println(getBlock().getPointOfRoom(this).toString()); //DEBUG
		lstring += name;
		lstring += '\n';
		lstring += longdesc;
		lstring += '\n';
		if(exits.size() > 0){
			lstring += "Visible exits: ";
			for(Exit e: exits)
				lstring += e.name + " ";
		}
		return lstring;
	}
	
}
