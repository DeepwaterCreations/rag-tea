
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
import java.util.HashMap;
import java.util.Random;




public class BlockClassic extends Block {
	
	static final int NEW_EXIT_ODDS = 3;
	
	//HashMap<String, Point> compass; //The points represent directions. One step in that direction means 0.x += p.x  and 0.y += p.y,
									//where 0 is the source location and p is the point returned by the HashMap.
	Compass4pt compass;
	
	public BlockClassic(){
		super();
		compass = new Compass4pt();
	}
	
	public BlockClassic(PlayerCharacter pc){
		this();
		
		/* SETUP START ROOM FOR TESTING PURPOSES */
		Room startRoom = new Room(this);
		startRoom.setName("Chamber of the Crystal Podium");
		startRoom.setsDesc("A room with a crystal podium in it.");
		startRoom.setlDesc("This natural stone cavern is dominated by an immense pentagonal podium of blue crystal, perhaps sapphire, around which a spiral stairway gracefully winds. A distant beam of light shines forlornly down onto the podium from above, illuminating the remains of the torn, frayed rope that you climbed down to enter this place. There is no way to return to the surface now - the only way out is through. To the north there is a carved marble archway styled to look like a dragon. The dragon's claws are stretched out in front of him, but it is not a threatening gesture so much as it is a warning. The dragon entreats you to turn back. If only that were an option!");
		startRoom.addExit(new Exit(compass.get("north"), startRoom));
		this.addRoom(startRoom, new Point(0,0));
		
		pc.setLocation(startRoom);
	}
		
	
	public Room getNewRoom(Room source, Exit sourceexit) {
		Room newroom;
		openDoors --;
		
		if(compass.has(sourceexit.getName())){
			Point origpoint = getPointOfRoom(source);
			Direction dir = compass.get(sourceexit.getName());
			Point checkpoint = dir.addDirection(origpoint);
			if(containsRoom(checkpoint)){
				newroom = getRoomAtPoint(checkpoint);
				//Checking if the other room already has an exit leading back to this exit,
				String opname = compass.getOpposite(dir).getName();
				if(newroom.hasExitNamed(opname)){
					//and if so, checking that it doesn't already have a room attached.
					if(!newroom.getExitNamed(opname).hasDest()){
						newroom.setExit(opname, source); //We set the link between the existing room and the source room.
						return newroom; //Then we return the existing room, so the source room can link back to it.
					}
					else{ //Other room links back in this direction, but to something else. 
						System.err.println("Tried to link Exit " + opname + " in Room called " + newroom.getName() + " to Room called " + source.getName() + " when the exit was already defined!");
						return null;
					}
				}
				else{ //Other room doesn't link back in this direction.
					System.err.println("Tried to link from room " + source.getName() + " in direction " + sourceexit.getName() + " but there was a wall in the way!");
					return null;
				}
				
			}
			else{ //If the direction doesn't already contain a room, we'll make a new one.
				return makeNewRoom(source, sourceexit, checkpoint);
			}
			
		}
		else{ //If the exit isn't part of the compass directions, just add a generic room, I guess?
			String name = "A Boring, Generic Room";
			String adjective = "";
			String longdesc = "This room doesn't look like much of anything. The room description generator hasn't been written yet - come back when it's finished!";
			String shortdesc = "A room.";
				
			Entity.EntityData data = new Entity.EntityData(name, adjective, shortdesc, longdesc); //Normally, a generator will provide the EntityData. 
			 newroom = new RoomClassic(data, this);
			//TODO Add this room to the block, numbnuts!
		
			return newroom;

		}
		
	}

	private Room makeNewRoom(Room source, Exit sourceexit, Point location) {
		Room newroom;
		
		/*For testing*/
		String name = "You are in a maze of twisty little passages, all alike";
		String adjective = "";
		String longdesc = "(This description intentionally left blank)";
		String shortdesc = "A room.";
			
		Entity.EntityData data = new Entity.EntityData(name, adjective, shortdesc, longdesc); //Normally, a generator will provide the EntityData. 
		newroom = new RoomClassic(data, this);
		addRoom(newroom, location);
		
		Direction dir = compass.getOpposite(compass.get(sourceexit.getName())); //This bit adds an exit back to the source room.
		newroom.addExit(new Exit(dir, newroom, source)); 				
		
		ArrayList<Direction> adjroomdirs = findAdjacentRooms(newroom);
		linkExits(newroom, adjroomdirs, sourceexit);
		giveRandomExits(newroom, adjroomdirs);
		
		return newroom;
		
	}
	
	/**
	 * 
	 * Gets a list of directions that lead to adjourning cells with rooms in them. 
	 * 
	 * @param r the room to check
	 * @return An array of directions that point to rooms.
	 */
	private ArrayList<Direction> findAdjacentRooms(Room r){
		ArrayList<Direction> returndirs = new ArrayList<Direction>();
		Point checkpoint;
		Point roompoint = getPointOfRoom(r);
		for(Direction d : compass.getAll()){
			checkpoint = d.addDirection(roompoint);
			if(containsRoom(checkpoint)){
				returndirs.add(d);
				//System.err.println("Direction " + d.getName() + " found to have a room in it."); //DEBUG
			}
		}
		return returndirs;
	}
	
	/**
	 * 
	 * Links to and from adjacent rooms with exits leading back to the argument room. 
	 * 
	 * @param r the room to link back to
	 * @param adjroomdirs An array of directions in which lie rooms.
	 */
	private void linkExits(Room r, ArrayList<Direction> adjroomdirs, Exit sourceexit){
		Point checkpoint;
		Point roompoint = getPointOfRoom(r);
		//For each direction in which lies a room,
		for(Direction d : adjroomdirs){
			 checkpoint = d.addDirection(roompoint);
			 Room otherroom = getRoomAtPoint(checkpoint);
			 //if the room in that direction has the opposite exit,
			 if(otherroom.hasExitNamed(compass.getOpposite(d).getName())){
				 //System.err.println("The room in Direction " + d.getName() + " at point " + checkpoint.toString() + " has a link back to the new room's cell."); //DEBUG
				 //connect it to r if it's unset.
				 Exit opexit = otherroom.getExitNamed(compass.getOpposite(d).getName());
				 if(!opexit.hasDest() && opexit != sourceexit){
					 //System.err.println("Connecting it to the new room..."); //DEBUG
					 opexit.set(r);
					 r.addExit(new Exit(d, r, otherroom));
				 }
				 else if(opexit.dest == r || opexit == sourceexit){
					 //System.err.println("It already links here."); //DEBUG
				 }
				 else{
					 System.err.println("Tried to connect room " + r.getName() + " to room " + otherroom.getName() + " but it was already linked in that direction!");
				 } 
			 }
			 else{} //If the other room has no exit, this one should also get a wall.
		}
		
	}
	
	private void giveRandomExits(Room r, ArrayList<Direction> adjroomdirs){
		int exitOdds;
		Random dicebag = new Random(); //TODO Make sure this gets a seed consistent with the whole gameworld?
		for(Direction dir : compass.getAll()){		
			if(openDoors <= 1){
				exitOdds = 1;
			}
			else{
				exitOdds = NEW_EXIT_ODDS;
			}
			//there is a 1/NEW_EXIT_ODDS chance that there will be a door in this direction,
			//unless there aren't enough undefined doors on the map, in which case
			//there is a 1/1 chance that there will be a door in this direction.
			if(dicebag.nextInt(exitOdds) == 0 && !adjroomdirs.contains(dir)){ //TODO: Checking the whole list every single time is a crummy way to do this.
				r.addExit(new Exit(dir, r));
				openDoors ++;
			}
		}
		
	}

}
