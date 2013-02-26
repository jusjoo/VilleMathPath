package edu.vserver.exercises.mathpath;

import java.util.Random;

public class AdditionGenerator implements ArithmeticsInterface {

	@Override
	public String calculate(int result) {

		Random rnd = new Random();

		// Jakaa kahtia
		int slicer = rnd.nextInt(result - 1) + 1;
		String equation = "" + slicer + " + " + (result - slicer);

		return equation;
	}

}
