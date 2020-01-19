
public class TestThings {
	public static void main(String args[]) {
		System.out.println(YahtzeePlayerEvaluator.evaluatePlayer(new GamerStrategy(), 100000));
		System.out.println(YahtzeePlayerEvaluator.evaluatePlayer(new GamerStrategy2(), 100000));
	}
}
