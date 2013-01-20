

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
import java.util.*;


public abstract class Block {

	int openDoors;
	LadderMap<Room, Point> roomList;
	
	
	//ArrayList<Point> mapList = new ArrayList<Point>();
	//HashMap<Point, Room> mapGrid = new HashMap<Point, Room>();

	public Block(){
		roomList = new LadderMap<Room, Point>();
		openDoors = 0;
	}
	
	
//	public boolean containsRoom(Point rloc){
//		for(Point p : mapList){
//			if(p.equals(rloc)) return true;
//		}
//		return false;
//	}
	
	public boolean containsRoom(Point rloc){
		return roomList.containsB(rloc);
	}
	
	
//	public void addRoom(Room r, Point rloc){
//		if(!containsRoom(rloc)){
//			mapList.add(rloc);
//			mapGrid.put(rloc, r);
//		}
//		else{
//			System.err.println("Tried to add a room where there already was one! Coordinates: " + rloc.toString());
//		}
//		
//	}
	
	//TODO What about rooms with no coordinates?
	public void addRoom(Room r, Point rloc){
		if(!containsRoom(rloc)){
			roomList.put(r, rloc);
		}
		else System.err.println("Tried to add a room where there already was one! Coordinates: " + rloc.toString()); 
	}
	
	
//	public Room getRoom(Point rloc){
//		return mapGrid.get(rloc);
//	}
	
	public Room getRoomAtPoint(Point rloc){
		if(roomList.containsB(rloc))
			return roomList.getAof(rloc);
		else
			return null;
	}
	
	public Point getPointOfRoom(Room r){
		if(roomList.containsA(r))
			return roomList.getBof(r);
		else
			return null;
	}
	
	
	public abstract Room getNewRoom(Room source, Exit sourceexit);
	
}
