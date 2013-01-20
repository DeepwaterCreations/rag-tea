
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

import java.util.Scanner;

/**
 * 
 * This is a testing class for initializing game sessions.
 * Will probably be replaced with something more polished, or at least
 * expanded/renamed to something more polished.  
 * 
 * @author Dylan Craine
 *
 */
public class CommandTester {

	public static void main(String[] args) {

		
		Listener listener = new Listener();
		CommandReader reader = new CommandReader("Dylboz the Bizzare"); //Obviously, I'll want to handle player name input at some point.
		
		
		while(!reader.thisPlayer.shouldQuit){ //This is also going to have to change. 
			String[] command = listener.listen();
			reader.interpret(command);
			
		}

	}

}
