

public class YahtzeePlayerEvaluator {

	
	public static double evaluatePlayer(YahtzeePlayer player, int games)
	{
		double totalScore=0;
		YahtzeeGame game = new YahtzeeGame();
		for(int x=0; x<games;x++)
		{
			//This is just so people can see how much longer they have to wait lol
			if(x%(int)(games/20) == 0)
				System.out.println((100*x/games) + "%");
			totalScore += game.yahtzeeGame(player);
		}
		return (totalScore / games);
	}
}
