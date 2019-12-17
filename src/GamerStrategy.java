
public class GamerStrategy extends YahtzeeComputerStrategy {

	@Override
	public String playerName() {
		// TODO Auto-generated method stub
		return "Gamer";
	}

	@Override
	public void reroll(int[] dice, int rollNumber, PlayerRecord record, boolean[] reroll) {
		int[] dieCounts = { 0, 0, 0, 0, 0, 0 };
		for (int i = 0; i < dice.length; i++) {
			dieCounts[dice[i]]++;
		}
		
		// TODO Auto-generated method stub

	}

	@Override
	public int chooseCombination(int[] dice, PlayerRecord record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void finalResults(int[] dice, PlayerRecord record) {
		// TODO Auto-generated method stub

	}

	@Override
	public String author() {
		// TODO Auto-generated method stub
		return "GaMeR gAnG";
	}

}
