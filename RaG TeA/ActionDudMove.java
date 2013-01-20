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
 * This Action is for movement in directions that don't have exits. Rather than actually move the player,
 * it will inform them that they have picked an invalid direction.
 * Since local Actions are checked and assigned after global ones, the presence of a legitimate exit in the player's vicinity
 * will override this Action in the command parser.
 * 
 * @author Dylan Craine
 *
 */
public class ActionDudMove extends Action{

	static final String[] verblist = {"north", "n", 
									  "south", "s", 
									  "east", "e", 
									  "west", "w", 
									  "northeast", "ne", 
									  "southeast", "se", 
									  "northwest", "nw", 
									  "southwest", "sw", 
									  "up", "down", "u", "d"};
	
	public ActionDudMove(){
		super(verblist);
	}
	
	public void act(ArrayList<Entity> cargs) {
		System.out.println("You can't go that way!");	
	}
	

}
