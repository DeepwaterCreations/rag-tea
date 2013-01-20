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
 * Informs an Exit that the player is attempting to move in a given direction without actually moving them.
 * Used for Exits that haven't got destination Rooms yet. Once such a Room is in place, ActionEMove will be replaced
 * with a regular ActionMove.  
 * 
 * @author Dylan Craine
 *
 */
public class ActionEMove extends ActionMove{

	Exit exit;
	
	public ActionEMove(String[] moveVerbs, Exit e){
		super(moveVerbs);
		exit = e;
	}
	
	public void act(ArrayList<Entity> cargs){
		exit.set();
		exit.sendMove(cargs);
	}
	
	public ActionMove removeTheE(Room d){
		int s = this.verblist.size();
		String[] verbs = new String[s];
		return new ActionMove(this.verblist.toArray(verbs), d);
	}
	
}
