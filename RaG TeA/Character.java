
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

/**
 * Represents living creatures (or other independently active agents) in the game world. 
 * Includes PCs and NPCs
 * 
 * @author Dylan Craine
 *
 */
public abstract class Character extends Entity {
	int hp; //Hit points? I might want a more Burning Wheel-esque combat system, but this is a filler until I get to fighting mechanics.
	//I also need to work out my system for limbs and equipping stuff.
	//Remember - anything defined here applies to PCs and NPCs alike!
	
	public Character(){
		super();
	}
	
	public Character(String n){
		super(n);
	}
	
}
