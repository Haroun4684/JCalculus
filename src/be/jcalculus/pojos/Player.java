package be.jcalculus.pojos;

public class Player {

	private String name;
	private String eventKey;
	private int score;

	private Player() {
	}

	public Player(String name, String key) {
		if (name == null || "".equals(name)) {
			throw new RuntimeException("Your player must have a name !");
		}
		this.name = name;
		if (key == null || key.length() != 1) {
			throw new RuntimeException("Your player " + name + " must have a key length of 1 then no " + key + " !");
		}
		this.eventKey = key;
		this.score = 0;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEventKey() {
		return eventKey;
	}

	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return String.format("Player %s (key %s) have a score of %d", this.name, this.eventKey, this.score);
	}

	public static void main(String[] args) {
		Player player1 = new Player();
		player1.setName("Haroun");
		player1.setEventKey("h");
		player1.setScore(0);
		System.out.println("creation of " + player1);

		Player player2 = new Player("Raph", "r");
		System.out.println("creation of " + player2);
		player2.setScore(10);
		System.out.println("creation of " + player2);

		try {
			Player player3 = new Player("Daoud", "dd");
			System.out.println("creation of " + player3);
		} catch (Exception e) {
			System.err.println("Player creation error : " + e.getMessage());
		}

		try {
			Player player3 = new Player("", "dd");
			System.out.println("creation of " + player3);
		} catch (Exception e) {
			System.err.println("Player creation error : " + e.getMessage());
		}

	}

}
