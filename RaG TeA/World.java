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
import java.util.ArrayList;

/**
 * 
 * Manages the creation and structure of game world content.
 * 
 * @author Dylan Craine
 *
 */
public class World {
	
	ArrayList<Block> blocklist; //Might turn into a HashMap or something later, but I think an ArrayList will do for now.
	
	PlayerCharacter pc; //Eventually, I'll want a database - probably a whole class for storing player accounts.
						//I'm keeping things simple for now, though.
	
	public World(){
		blocklist = new ArrayList<Block>();
	}
	
	public PlayerCharacter playerConnect(String cname){
		pc = new PlayerCharacter(cname);
		if(blocklist.size() < 1) blocklist.add(new BlockClassic(pc));
		else pc.setLocation(blocklist.get(0).getRoomAtPoint(new Point(0, 0))); //TODO: I'll have to properly define a starting room later.
		return pc;
	}
	
	/**
	 * NOTE: This is just a placeholder until I get around to multiplayer/multiple characters in the same world.  
	 * Allows a new player to connect to the game. This version of the method takes a character name and password - if they match the player
	 * database, connect to the matching character. Otherwise, send a rejection message.   
	 * 
	 * @param cname The name of the player's character. 
	 * @param password A password linked to the character to prevent strangers from using it.
	 * @return a reference to the character. 
	 */
	public PlayerCharacter playerConnect(String cname, String password){
		return new PlayerCharacter();
	} 	
	
}
