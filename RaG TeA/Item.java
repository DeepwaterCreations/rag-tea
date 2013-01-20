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
 * Represents anything pickupable in the game world.
 * 
 * @author Dylan Craine
 *
 */
public class Item extends Entity {
	
	//Hmm... items could potentially be useable or equipable, or both.
	//Maybe that should just be a flag.
	//They could also act like characters, although maybe in that case they should just be characters. Characters can technically go into inventories.

	@Override
	public ArrayList<Action> getActions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLook() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
