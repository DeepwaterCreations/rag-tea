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


/*
 * A class to split lines of input into arrays of words.
 * Fun Fact: The original version of this class was an assignment for Computer Science 1 at the beginning of my Freshman year of college.
 * It has changed very little since then. The moral is, save everything! (Not that I couldn't rewrite this in my sleep, but still.)
 */

import java.util.Scanner;
public class Listener {
	
	Scanner ears = new Scanner(System.in);
	String input;
			
	public String[] listen(){
		//Gets the user input, makes it lower case, splits it at the spaces and stores each word
		//in an array. 
		input = ears.nextLine();
		input = input.toLowerCase();
		String[] temp = input.split(" ");
		String[] sentenceReturn = new String[temp.length]; 
		//Puts the words into the entries of the return array
		for (int i = 0; i < temp.length; i++){
			sentenceReturn[i] = temp[i];
		}
		
		return sentenceReturn;
	}
	
	
	
	
}