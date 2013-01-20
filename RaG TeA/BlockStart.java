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

import java.awt.Point;


public class BlockStart extends Block {
	
	public BlockStart(PlayerCharacter pc){
		super();
		Room startRoom = new Room(this);
		Room testRoom = new Room(this);
		
		startRoom.setName("Chamber of the Crystal Podium");
		startRoom.setsDesc("A room with a crystal podium in it.");
		startRoom.setlDesc("This natural stone cavern is dominated by an immense pentagonal podium of blue crystal, perhaps sapphire, around which a spiral stairway gracefully winds. A distant beam of light shines forlornly down onto the podium from above, illuminating the remains of the torn, frayed rope that you climbed down to enter this place. There is no way to return to the surface now - the only way out is through. To the north there is a carved marble archway styled to look like a dragon. The dragon's claws are stretched out in front of him, but it is not a threatening gesture so much as it is a warning. The dragon entreats you to turn back. If only that were an option!");
		
		/*Obviously, this is just for testing.*/
		testRoom.setName("Pizza Box");
		testRoom.setsDesc("A greasy and flat cardboard box.");
		testRoom.setlDesc("Instead of the expected dungeon, you are in a pizza box. It is flat, greasy and smells strongly of pizza. Alas, the pizza is gone, save for a handful of crumbs.");
		
		String[] northNames = {"north", "n"};		
		startRoom.addExit(new Exit(northNames, startRoom, testRoom));
		String[] southNames = {"south", "s"};		
		testRoom.addExit(new Exit(southNames, testRoom, startRoom));
		
		
		this.addRoom(startRoom, new Point(0,0));
		this.addRoom(testRoom, new Point(0,-1));
		
		pc.setLocation(startRoom);
		
	}

	
	public Room getNewRoom(Room r, Exit e) {
		return null;
	}

}
