

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
 * Displays descriptions of the specified Entities. If no Entity is specified, defaults to the Room that the player is in.  
 * 
 * @author Dylan Craine
 *
 */
public class ActionLook extends Action{

	static final String[] verblist = {"look", "examine", "x"}; //Aah, but do I want these all to be synonymous?
	
	public ActionLook(){
		super(verblist);
	}
	
	public void act(ArrayList<Entity> cargs) {
		if(cargs.size() == 1){ //If there's only one argument, we look at the room. 			
			System.out.println(cargs.get(0).getLocation().getLook());
		}
		else{
			System.out.println(cargs.get(1).getLook());
		}
		
	}
	
	
	
}
