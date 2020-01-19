import java.util.*;

public class GamerStrategy2 extends YahtzeeComputerStrategy {

	private AbstractYahtzeeCombination[] availableCombinations;

	public String playerName() {
		return "Gamer";
	}

	/*
	 * NOTES (What y'all should do): 1) Look into PlayerRecord, maybe see if you can
	 * use the availableCombinations class to make the strategy not look for stuff
	 * we've already found. 2) Fill in each case you guys think matters, I'll lay
	 * each case out so that you know where it is. 3) So the priority list is
	 * Yahtzee->Large Straight->Small Straight->Full House->Four of a Kind->Three of
	 * a Kind->Single Numbers (6, 5, 4, 3, 2, 1)->Chance. 4) REMEMBER, if you get a
	 * score greater than 63 in the upper layer, you get 35 bonus points. 5) I don't
	 * think two of a kind is very important, because there is no scoring order for
	 * it, but if you wanna check for single numbers if the amount is greater than
	 * 2, I think that would be good. 6) If we could get to like 170, I think that's
	 * pretty much A-range, and I would be satisfied with that, but if we could get
	 * higher, that would be rad.
	 */

	public void reroll(int[] dice, int rollNumber, PlayerRecord record, boolean[] reroll) {
		int[] diceval = { 0, 0, 0, 0, 0 };
		int ones = 0;
		int sixes = 0;
		int twos = 0;
		int threes = 0;
		int fours = 0;
		int fives = 0;
		int holder = 6;
		int holder2 = 6;
		int holder3 = 6;
		int holder4 = 6;
		int holder5 = 6;
		int holder6 = 6;
		int place1 = 0;
		int place2 = 0;
		int place3 = 0;

		for (int i = 0; i < dice.length; i++) {
			holder = dice[i];
			if (holder == 1) {
				ones++;
			} else if (holder == 2) {
				twos++;
			} else if (holder == 3) {
				threes++;
			} else if (holder == 4) {
				fours++;
			} else if (holder == 5) {
				fives++;
			} else if (holder == 6) {
				sixes++;
			}
		}

		if (Yahtzee(ones, twos, threes, fours, fives, sixes))
			setBool(reroll, false, false, false, false, false);
		else if (LargeStraight(ones, twos, threes, fours, fives, sixes))
			setBool(reroll, false, false, false, false, false);
		else if (SmallStraight(ones, twos, threes, fours, fives, sixes) != -1) {
			int val = SmallStraight(ones, twos, threes, fours, fives, sixes);
			boolean[] recorder = { false, false, false, false, false };
			if (val == 0) {
				if (ones > 1)
					recorder[getIndex(dice, 1, 0)] = true;
				else if (twos > 1)
					recorder[getIndex(dice, 2, 0)] = true;
				else if (threes > 1)
					recorder[getIndex(dice, 3, 0)] = true;
				else if (fours > 1)
					recorder[getIndex(dice, 4, 0)] = true;
				else if (isIn(dice, 5))
					recorder[getIndex(dice, 5, 0)] = true;
				else if (isIn(dice, 6))
					recorder[getIndex(dice, 6, 0)] = true;
			} else if (val == 1) {
				if (isIn(dice, 1))
					recorder[getIndex(dice, 1, 0)] = true;
				else if (twos > 1)
					recorder[getIndex(dice, 2, 0)] = true;
				else if (threes > 1)
					recorder[getIndex(dice, 3, 0)] = true;
				else if (fours > 1)
					recorder[getIndex(dice, 4, 0)] = true;
				else if (fives > 1)
					recorder[getIndex(dice, 5, 0)] = true;
				else if (isIn(dice, 6))
					recorder[getIndex(dice, 6, 0)] = true;
			} else if (val == 2) {
				if (isIn(dice, 1))
					recorder[getIndex(dice, 1, 0)] = true;
				else if (isIn(dice, 2))
					recorder[getIndex(dice, 2, 0)] = true;
				else if (threes > 1)
					recorder[getIndex(dice, 3, 0)] = true;
				else if (fours > 1)
					recorder[getIndex(dice, 4, 0)] = true;
				else if (fives > 1)
					recorder[getIndex(dice, 5, 0)] = true;
				else if (sixes > 1)
					recorder[getIndex(dice, 6, 0)] = true;
			}

			reroll = recorder;
		} else if (FullHouse(dice)) {
			setBool(reroll, false, false, false, false, false);
		} else if (FourOfAKind(ones, twos, threes, fours, fives, sixes)) {
			if (ones == 1) {
				holder = 1;
				if (isIn(dice, 1)) {
					holder = getIndex(dice, 1, 0);
				}
			} else if (twos == 1) {
				holder = 2;
				if (isIn(dice, 2)) {
					holder = getIndex(dice, 2, 0);
				}
			} else if (threes == 1) {
				holder = 3;
				if (isIn(dice, 3)) {
					holder = getIndex(dice, 3, 0);
				}
			} else if (fours == 1) {
				holder = 4;
				if (isIn(dice, 4)) {
					holder = getIndex(dice, 4, 0);
				}
			} else if (fives == 1) {
				holder = 5;
				if (isIn(dice, 5)) {
					holder = getIndex(dice, 5, 0);
				}
			} else if (sixes == 1) {
				holder = 6;
				if (isIn(dice, 6)) {
					holder = getIndex(dice, 6, 0);
				}
			}

			if (holder == 0) {
				setBool(reroll, true, false, false, false, false);
			} else if (holder == 1) {
				setBool(reroll, false, true, false, false, false);
			} else if (holder == 2) {
				setBool(reroll, false, false, true, false, false);
			} else if (holder == 3) {
				setBool(reroll, false, false, false, true, false);
			} else if (holder == 4) {
				setBool(reroll, false, false, false, false, true);
			}
		} else if (ThreeOfAKind(ones, twos, threes, fours, fives, sixes)) {
			place1 = 0;
			place2 = 0;
			place3 = 0;
			holder = 6;
			holder2 = 6;
			holder3 = 6;
			holder4 = 6;
			holder5 = 6;
			holder6 = 6;
			if (ones == 3) {
				place1 = getIndex(dice, 1, 0);
				place2 = getIndex(dice, 1, place1);
				place3 = getIndex(dice, 1, place2);
				if ((twos == 2) || (threes == 2) || (fours == 2) || (fives == 2) || (sixes == 2)) {
					setBool(reroll, false, false, false, false, false);
				} else {
					if (twos == 1) {
						holder = getIndex(dice, 2, 0);
					} else if (threes == 1) {
						holder2 = getIndex(dice, 3, 0);
					} else if (fours == 1) {
						holder3 = getIndex(dice, 4, 0);
					} else if (fives == 1) {
						holder4 = getIndex(dice, 5, 0);
					} else if (sixes == 1) {
						holder5 = getIndex(dice, 6, 0);
					}

					int[] stuff = setMax(place1, place2, place3, holder, holder2, holder3, holder4, holder5);
					setBool(reroll, stuff[0] == 6, stuff[1] == 6, stuff[2] == 6, stuff[3] == 6, stuff[4] == 6);
				}
			} else if (twos == 3) {
				place1 = getIndex(dice, 2, 0);
				place2 = getIndex(dice, 2, place1);
				place3 = getIndex(dice, 2, place2);
				setBool(reroll, true, true, true, true, true);
				reroll[place1] = false;
				reroll[place2] = false;
				reroll[place3] = false;

				if ((ones == 2) || (threes == 2) || (fours == 2) || (fives == 2) || (sixes == 2)) {
					setBool(reroll, false, false, false, false, false);
				}
			} else if (threes == 3) {
				place1 = getIndex(dice, 3, 0);
				place2 = getIndex(dice, 3, place1);
				place3 = getIndex(dice, 3, place2);
				setBool(reroll, true, true, true, true, true);
				reroll[place1] = false;
				reroll[place2] = false;
				reroll[place3] = false;
				if ((twos == 2) || (ones == 2) || (fours == 2) || (fives == 2) || (sixes == 2)) {
					setBool(reroll, false, false, false, false, false);
				}
			} else if (fours == 3) {
				place1 = getIndex(dice, 4, 0);
				place2 = getIndex(dice, 4, place1);
				place3 = getIndex(dice, 4, place2);
				setBool(reroll, true, true, true, true, true);
				reroll[place1] = false;
				reroll[place2] = false;
				reroll[place3] = false;
				if ((twos == 2) || (threes == 2) || (ones == 2) || (fives == 2) || (sixes == 2)) {
					setBool(reroll, false, false, false, false, false);
				}
			} else if (fives == 3) {
				place1 = getIndex(dice, 5, 0);
				place2 = getIndex(dice, 5, place1);
				place3 = getIndex(dice, 5, place2);
				setBool(reroll, true, true, true, true, true);
				reroll[place1] = false;
				reroll[place2] = false;
				reroll[place3] = false;
				if ((twos == 2) || (threes == 2) || (fours == 2) || (ones == 2) || (sixes == 2)) {
					setBool(reroll, false, false, false, false, false);
				}
			} else if (sixes == 3) {
				place1 = getIndex(dice, 6, 0);
				place2 = getIndex(dice, 6, place1);
				place3 = getIndex(dice, 6, place2);
				setBool(reroll, true, true, true, true, true);
				reroll[place1] = false;
				reroll[place2] = false;
				reroll[place3] = false;
				if ((twos == 2) || (threes == 2) || (fours == 2) || (fives == 2) || (ones == 2)) {
					setBool(reroll, false, false, false, false, false);
				}
			}
		}

		/*
		 * In this space, you guys should define the cases 1-6, and see if there are 2-3
		 * of one number. Then, you should make it choose the corresponding combination.
		 * If you find something you think is better, go with that.
		 */

		else if (Chance(dice)) {
			setBool(reroll, false, false, false, false, false);
		} else {
			setBool(reroll, true, true, true, true, true);
		}
	}

	private boolean isIn(int[] dice, int desired) {
		for (int i = 0; i < dice.length; i++) {
			if (dice[i] == desired) {
				return true;
			}
		}
		return false;
	}

	private int getIndex(int[] dice, int desired, int start) {
		int target = 0;
		for (int i = start; i < dice.length; i++) {
			if (dice[i] == desired) {
				target = i;
				return target;
			}
		}
		// unreachable
		return -1;
	}

	private int[] setMax(int one2, int two2, int three2, int four2, int five2, int six2, int seven2, int eight2) {
		int[] returnVal = { one2, two2, three2, four2, five2, six2, seven2, eight2 };
		Arrays.sort(returnVal);
		return returnVal;
	}

	private void setBool(boolean[] reroll, boolean a, boolean b, boolean c, boolean d, boolean e) {
		reroll[0] = a;
		reroll[1] = b;
		reroll[2] = c;
		reroll[3] = d;
		reroll[4] = e;
	}

	public int chooseCombination(int[] dice, PlayerRecord record) {
		availableCombinations = record.availableCombinations();
		int maxScore = 0;
		int indexOfMaxScore = -1;
		for (int x = 0; x < availableCombinations.length; x++) {
			int score = availableCombinations[x].score(dice);
			if (score > maxScore) {
				maxScore = score;
				indexOfMaxScore = x;
			}
		}
		if (maxScore == 0) {
			if (availableCombinations[availableCombinations.length - 1].upperSection()) {
				return 0;
			} else {
				return availableCombinations.length - 1;
			}
		} else {
			return indexOfMaxScore;
		}
	}

	public boolean Yahtzee(int ones, int twos, int threes, int fours, int fives, int sixes) {
		if ((ones == 5) || (twos == 5) || (threes == 5) || (fours == 5) || (fives == 5) || (sixes == 5))
			return true;
		else
			return false;
	}

	public boolean LargeStraight(int ones, int twos, int threes, int fours, int fives, int sixes) {
		if (((ones == 1) && (twos == 1) && (threes == 1) && (fours == 1) && (fives == 1))
				|| ((twos == 1) && (threes == 1) && (fours == 1) && (fives == 1) && (sixes == 1)))
			return true;
		else
			return false;
	}

	public int SmallStraight(int ones, int twos, int threes, int fours, int fives, int sixes) {
		if ((ones == 1) && (twos == 1) && (threes == 1) && (fours == 1))
			return 0;
		else if ((twos == 1) && (threes == 1) && (fours == 1) && (fives == 1))
			return 1;
		else if ((threes == 1) && (fours == 1) && (fives == 1) && (sixes == 1))
			return 2;
		else
			return -1;
	}

	public boolean FullHouse(int[] dice) {
		if (dice[0] == dice[1] && dice[2] == dice[3] && dice[3] == dice[4] && (dice[1] != dice[2])) {
			return true;
		} else if (dice[0] == dice[1] && dice[1] == dice[2] && dice[3] == dice[4] && (dice[2] != dice[3])) {
			return true;
		} else
			return false;
	}

	public boolean FourOfAKind(int ones, int twos, int threes, int fours, int fives, int sixes) {
		if ((ones == 4) || (twos == 4) || (threes == 4) || (fours == 4) || (fives == 4) || (sixes == 4))
			return true;
		else
			return false;
	}

	public boolean ThreeOfAKind(int ones, int twos, int threes, int fours, int fives, int sixes) {
		if ((ones == 3) || (twos == 3) || (threes == 3) || (fours == 3) || (fives == 3) || (sixes == 3))
			return true;
		else
			return false;
	}

	public boolean Chance(int[] dice) {
		int total = 0;
		for (int i = 0; i < dice.length; i++) {
			total += dice[i];
		}
		if (total > 30)
			return true;
		return false;
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