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
 * Represents an exit from a room.
 * 
 * @author Dylan Craine
 *
 */

public class Exit {

	String name;
	Room source;
	Room dest;
	ActionMove move;
	
	
	public Exit(String[] names, Room s, Room d){
		name = names[0];
		source = s;	
		dest = d;
		
		move = new ActionMove(names, dest);
	}
	
	public Exit(Direction dir, Room s, Room d){
		name = dir.getName();
		source = s;	
		dest = d;
		
		move = new ActionMove(dir.getSynonyms(), dest);
	}
	
	public Exit(String[] names, Room s){
		name = names[0];
		source = s;	
		
		move = new ActionEMove(names, this);
	}
	
	public Exit(Direction dir, Room s){
		name = dir.getName();
		source = s;
		
		move = new ActionEMove(dir.getSynonyms(), this);
	}	

	public void set(){
		set(source.getBlock().getNewRoom(source, this));
	}
	
	public void set(Room r){
		dest = r;
		move = move.removeTheE(dest);
	}
	
	public Action getAction(){
		return move;
	}
	
	public String getName(){
		return name;
	}
	
	public Room getDest(){
		return dest;
	}
	
	public boolean hasDest(){
		if(dest != null) return true;
		return false;
	}
	
	public void sendMove(ArrayList<Entity> cargs){
		move.act(cargs);
	}
}
