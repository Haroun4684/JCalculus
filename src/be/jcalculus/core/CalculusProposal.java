package be.jcalculus.core;

import java.util.Random;

public class CalculusProposal {

	private int a;
	private int b;

	public CalculusProposal() {
		a = getRandomInt();
		b = getRandomInt();
	}

	public String getCalcul() {
		return String.format("%d + %d = ???", a, b);
	}

	public int getResponse() {
		return a + b;
	}

	public boolean isResponseCorrect(String responseToTest) {
		boolean ret = false;
		Integer responseToTestInt = null;
		try {
			responseToTestInt = Integer.parseInt(responseToTest);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		if (responseToTestInt != null && getResponse() == responseToTestInt) {
			ret = true;
		}
		return ret;
	}

	private int getRandomInt() {
		return new Random(System.nanoTime()).nextInt(11);
	}

	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			CalculusProposal c = new CalculusProposal();
			System.out.println(c.getCalcul());
			if (c.isResponseCorrect("2")) {
				System.out.println("Correct :)");
			} else {
				System.out.println("Bad :(");
			}
			System.out.println("-------");
		}
	}

}
