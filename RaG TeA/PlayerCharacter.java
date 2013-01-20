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
 * Represents a player in the game.
 * 
 * @author Dylan Craine
 *
 */
public class PlayerCharacter extends Character{
	//I'm not actually sure what might go here that other characters won't have access to. But I'm sure there's something.
	//...right?
	//At the very least, NPCs will have NPC-only qualities, so it's worth drawing the distinction. 

	//Here's something, at least:
	boolean shouldQuit;
	
	public PlayerCharacter(){
		super();
		shouldQuit = false;
	}
	
	public PlayerCharacter(String n){
		super(n);
		shouldQuit = false;
	}
	
	public void setQuit(){
		shouldQuit = true;
	}

	//Any special powers the PC has should go here.
	//If I do races and classes, those abilities will need to be retrieved from their objects.
	//Also, this is where the list will start. Everything in the list generated here will be returned to CommandReader,
	//and anything CommandReader needs had better end up being in this list.
	public ArrayList<Action> getActions() { 
		return location.getActions();
	}

	public String getLook() {
		return longdesc;
	} 
	
}
