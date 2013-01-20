

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
 * Transports a player to the specified destination, then prints a description of it. 
 * 
 * @author Dylan Craine
 *
 */
public class ActionMove extends Action{

	Room destination;
	
	public ActionMove(String[] moveVerbs) {
		super(moveVerbs);
	}
	
	public ActionMove(String[] moveVerbs, Room d){
		super(moveVerbs);
		destination = d;
	}

	public void act(ArrayList<Entity> cargs) {
		Room r;  
		if(destination == null)
			r = (Room) cargs.get(1);
		else
			r = destination;
		cargs.get(0).setLocation(r);
		new ActionLook().act(new ArrayList<Entity>(cargs.subList(0, 1))); //Verbose/Terse mode will have to hook up here. Also, what an awful way to pass a single Entity.
	}																			//TODO I really ought to add non-array-based versions of act() to the various sorts of action,
																				//and have the ArrayList versions of the methods call them. 

	//I really shouldn't ever even get this.
	public ActionMove removeTheE(Room dest) {
		return this;
	}
	
	
}
