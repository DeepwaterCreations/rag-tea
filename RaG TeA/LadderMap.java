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


public class LadderMap<A, B> {

	ArrayList<A> listA;
	ArrayList<B> listB;
	
	public LadderMap(){
		listA = new ArrayList<A>();
		listB = new ArrayList<B>();
	}
	
	public void put(A first, B second){
		listA.add(first);
		listB.add(second);
		
		assert(listA.indexOf(first) == listB.indexOf(second));
	}
	
	public B getBof(A item){
		int index = listA.indexOf(item);
		return listB.get(index);
	}
	
	public A getAof(B item){
		int index = listB.indexOf(item);
		return listA.get(index);
	}
	
	public void removeA(A item){
		int index = listA.indexOf(item);
		listA.remove(index);
		listB.remove(index);	
	}
	
	public void removeB(B item){
		int index = listB.indexOf(item);
		listA.remove(index);
		listB.remove(index);	
	}

	public boolean containsA(A item){
		if(listA.contains(item)) return true;
		return false;
	}
	
	public boolean containsB(B item){
		if(listB.contains(item)) return true;
		return false;
	}
}
