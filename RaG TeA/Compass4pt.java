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


public class Compass4pt extends Compass{

	public Compass4pt(){
		super();
		
		String[] northnames = new String[] {"north", "n"};
		String[] southnames = new String[] {"south", "s"};
		String[] eastnames = new String[] {"east", "e"};
		String[] westnames = new String[] {"west", "w"};
		
		Direction north = new Direction(northnames, new Point(0, -1));
		Direction south = new Direction(southnames, new Point(0, 1));
		Direction east = new Direction(eastnames, new Point(1, 0));
		Direction west = new Direction(westnames, new Point(-1, 0));
		
		add(north);
		add(south);
		add(east);
		add(west);
		
		setOpposites(north, south);
		setOpposites(east, west);
		
	}
	
	
	protected void setOpposites(Direction a, Direction b) {
		opposites.put(a, b);
		opposites.put(b, a);
		
	}

}
