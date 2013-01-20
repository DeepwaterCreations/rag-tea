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


public class Direction {

	String[] names;
	Point addvals;
	//Direction opposite;
	
	public Direction(String n, Point p){
		names = new String[] {n};
		addvals = p;
	}
	
	public Direction(String[] n, Point p){
		names = n;
		addvals = p;
	}
	
//	public Direction(String n, Point p, Direction o){
//		this(n, p);
//		opposite = o;
//	}
//	
//	public Direction(String[] n, Point p, Direction o){
//		this(n, p);
//		opposite = o;
//	}
	
	//You put in the starting Point, and it spits out the Point one step in this Direction.  
	public Point addDirection(Point p){
		Point ret = new Point(p);
		ret.translate(addvals.x, addvals.y);
		return ret;
	}
	
	public String getName(){
		return names[0];
	}
	
	public String[] getSynonyms(){
		return names;
	}
	
}
