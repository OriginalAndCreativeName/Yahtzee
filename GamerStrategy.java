public class GamerStrategy extends YahtzeeComputerStrategy {

	private AbstractYahtzeeCombination[] availableCombinations;
	@Override
	public String playerName() {
		// TODO Auto-generated method stub
		return "Gamer";
	}

	@Override
	public void reroll(int[] dice, int rollNumber, PlayerRecord record, boolean[] reroll) {
		int[] diceval = {0,0,0,0,0};
		int ones = 0;
		int sixes = 0;
		int twos = 0;
		int threes = 0;
		int fours = 0;
		int fives = 0;
		int holder = 0;
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
		holder = 0;
		int holder2 = 0;
		if((ones == 5) || (twos == 5) || (threes == 5) || (fours == 5) || (fives == 5) || (sixes == 5)) {
			setBool(reroll, false, false, false, false, false);
		}else if (((ones == 1) && (twos == 1) && (threes == 1) && (fours == 1) && (fives == 1)) || ((twos == 1) && (threes == 1) && (fours == 1) && (fives == 1) && (sixes == 1))) {
			setBool(reroll, false, false, false, false, false);
		}else if((ones == 4) || (twos == 4) || (threes == 4) || (fours == 4) || (fives == 4) || (sixes == 4)) {
			if(ones == 1) {
				holder = 1;
				if(isIn(dice,1)) {
					holder = getIndex(dice,1);
				}
			}else if(twos == 1) {
				holder = 2;
				if(isIn(dice,2)) {
					holder = getIndex(dice,2);
				}
			}else if(threes == 1) {
				holder = 3;
				if(isIn(dice,3)) {
					holder = getIndex(dice,3);
				}
			}else if(fours ==  1) {
				holder = 4;
				if(isIn(dice,4)) {
					holder = getIndex(dice,4);
				}
			}else if(fives == 1) {
				holder = 5;
				if(isIn(dice,5)) {
					holder = getIndex(dice,5);
				}
			}else if(sixes == 1) {
				holder = 6;
				if(isIn(dice,6)) {
					holder = getIndex(dice,6);
				}
			}
			if(holder == 0) {
				setBool(reroll, true, false, false, false, false);
			}else if(holder == 1) {
				setBool(reroll, false, true, false, false, false);
			}else if (holder == 2) {
				setBool(reroll, false, false, true, false, false);
			} else if (holder == 3) {
				setBool(reroll, false, false, false, true, false);
			} else if (holder == 4) {
				setBool(reroll, false, false, false, false, true);
			}
		}else if((ones == 3) || (twos == 3) || (threes == 3) || (fours == 3) || (fives == 3) || (sixes == 3)) {
			holder = 6; 
			holder2 = 6;
			if(ones == 3) {
				if((twos ==2)||(threes == 2)|| (fours == 2)||(fives == 2)||(sixes == 2)) {
					setBool(reroll, false, false, false, false, false);
				}
			}else if(twos ==3) {
				if((ones ==2)||(threes == 2)|| (fours == 2)||(fives == 2)||(sixes == 2)) {
					setBool(reroll, false, false, false, false, false);
				}
			}else if(threes == 3) {
				if((twos ==2)||(ones == 2)|| (fours == 2)||(fives == 2)||(sixes == 2)) {
					setBool(reroll, false, false, false, false, false);
				}
			}else if(fours == 3) {
				if((twos ==2)||(threes == 2)|| (ones == 2)||(fives == 2)||(sixes == 2)) {
					setBool(reroll, false, false, false, false, false);
				}
			}else if(fives == 3) {
				if((twos ==2)||(threes == 2)|| (fours == 2)||(ones == 2)||(sixes == 2)) {
					setBool(reroll, false, false, false, false, false);
				}
			}else if(sixes == 3) {
				if((twos ==2)||(threes == 2)|| (fours == 2)||(fives == 2)||(ones == 2)) {
					setBool(reroll, false, false, false, false, false);
				}else {
					
				}
			}
		}
		
		// TODO Auto-generated method stub

	}
	private boolean isIn(int[] dice, int desired) {
		for(int i = 0; i < dice.length; i++) {
			if(dice[i]== desired) {
				return true;
			}
		}
		return false;
	}
	private int getIndex(int[] dice, int desired) {
		int target = 0;
		for(int i = 0; i < dice.length; i++) {
			if(dice[i] == desired) {
				target = i;
				return target;
			}
		}
		//unreachable
		return -1;
	}
	private void setBool(boolean[] reroll, boolean a, boolean b, boolean c, boolean d, boolean e) {
		reroll[0] = a;
		reroll[1] = b;
		reroll[2] = c;
		reroll[3] = d;
		reroll[4] = e;
	}

	@Override
	public int chooseCombination(int[] dice, PlayerRecord record) {
		// TODO Auto-generated method stub
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