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
import java.util.HashMap;

/**
 * 
 * Creates and stores Directions. Subclasses will be Compass4pt, Compass8pt.
 * Note that not all Exits will use Directions. The point is to help manage rooms on a grid
 * by establishing their relative locations.
 * 
 * @author Dylan Craine
 *
 */

//TODO This whole thing seems a bit bulky. I should see if I can simplify this mess later.

public abstract class Compass {

	ArrayList<Direction> compassrose;
	HashMap<Direction, Direction> opposites; //There's probably a better way to do this. I miss Python's tuples!
			//Yeah, come to think of it, why the heck didn't I write this project in Python? It seems like a better language for this sort
			//of thing than Java, and it would be good practice.
	
	/**
	 * Subclasses should set up Directions in the constructor.
	 */
	public Compass(){
		compassrose = new ArrayList<Direction>();	
		opposites = new HashMap<Direction, Direction>();
	}
	
	protected void add(Direction d){
		compassrose.add(d);
	}
	
	/**
	 * Finds a direction from its name.
	 * 
	 * @param A name referring to the direction being sought.
	 * @return The first direction found that uses that name.
	 */
	public Direction get(String name){
		for(Direction d : compassrose){
			String[] syns = d.getSynonyms();
			for(int i = 0; i < syns.length; i++){
				if(name.equals(syns[i]))
					return d;
			}
		}
		return null;
	}
	
	public ArrayList<Direction> getAll(){
		return new ArrayList<Direction> (compassrose);
	}
	
	public boolean has(String name){
		if(get(name) != null)
			return true;
		return false;
	}
	
	/**
	 * 
	 * Abstract so that weird compasses with non-reflective opposites can be created if desired.
	 * 
	 * @param a The first direction.
	 * @param b The second direction.
	 */
	protected abstract void setOpposites(Direction a, Direction b); 

	
	public Direction getOpposite(Direction dir){
		if(opposites.containsKey(dir))
			return opposites.get(dir);
		return null;
	}
	
	
}
