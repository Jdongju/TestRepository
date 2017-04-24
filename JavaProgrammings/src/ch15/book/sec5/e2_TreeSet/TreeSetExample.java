package ch15.book.sec5.e2_TreeSet;

import java.util.NavigableSet;
import java.util.TreeSet;

public class TreeSetExample {

	public static void main(String[] args) {
		TreeSet<Integer> scores=new TreeSet<Integer>();
		scores.add(new Integer(87));
		scores.add(new Integer(98));
		scores.add(new Integer(75));
		scores.add(new Integer(95));
		scores.add(new Integer(80));

		NavigableSet<Integer> descendigSet=scores.descendingSet();
		for(Integer score:descendigSet){
			System.out.print(score+" ");
		}
		System.out.println();
		
		NavigableSet<Integer> ascendingSet=descendigSet.descendingSet();
		for(Integer score:ascendingSet){
			System.out.print(score+" ");
		}
		System.out.println();
	}

}
