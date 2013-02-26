package edu.vserver.exercises.mathpath;

import java.util.ArrayList;
import java.util.Random;

public class PathModel {

	private ArrayList<Integer> list;
	
	/*
	 * Luo uuden correctpathmodelin, joka generoi oikeat vastaukset min-max väliltä
	 * 
	 * length: polun pituus
	 */
	public PathModel(int min, int max, int length) {
		list = new ArrayList<Integer>();
		generateAnswers(min, max, length);
	}

	private void generateAnswers(int min, int max, int length) {
		
		Random rnd = new Random();	
		for (int i=0; i < length; i++) {
			
			int answer = rnd.nextInt(max-min+1) + min;
			list.add(i, answer);		
		}	
	}
	
	public int getNode(int index) {
		return list.get(index);
	}
	
	public int getLength() {
		return list.size();
	}

}
