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
 * Represents anything in the game world that can't be picked up - an ornate mosaic built into the wall, the sky, a peculiar smell, remorse...
 * Probably should be used only for things that can conceptually be in rooms, too.
 * (Technically, they can go in inventories. It might even be simpler just to use the Item class and flag it as non-pickupable. We'll see.) 
 * 
 * @author Dylan Craine
 *
 */

public class Feature extends Entity {

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
