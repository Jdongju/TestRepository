package ch15.book.sec2.e2_vector;

import java.util.List;
import java.util.Vector;

public class VectorExample {

	public static void main(String[] args) {
		List<Board> list=new Vector<Board>();
		
		list.add(new Board("����1","����1","�۾���1"));
		list.add(new Board("����1","����1","�۾���1"));
		list.add(new Board("����1","����1","�۾���1"));
		list.add(new Board("����1","����1","�۾���1"));
		list.add(new Board("����1","����1","�۾���1"));
		
		list.remove(2);
		list.remove(3);
		
		for(int i=0; i<list.size();i++){
			Board board=list.get(i);
			System.out.println();
		}
	}

}
