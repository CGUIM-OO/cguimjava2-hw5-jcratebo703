

public class Card {
	enum esuit {Clubs , Diamonds , Hearts , Spades}; //Definition: 1~4, Clubs=1, Diamonds=2, Hearts=3, Spades=4
	private int rank; //1~13
	//private int suit;
	private esuit thisCardSuit = esuit.Clubs;
	/**
	 * @param s suit
	 * @param r rank
	 */
	public Card(esuit s,int r){
		thisCardSuit=s;
		rank=r;
	}	
	//TODO: 1. Please implement the printCard method (20 points, 10 for suit, 10 for rank)
	public void printCard(){
		//Hint: print (System.out.println) card as suit,rank, for example: print 1,1 as Clubs Ace
		String rankary[] = {"Ace","Two","Three","Four","Five","Six","Seven","Eight","Night","Ten","Jack","Queen","King"};
		
		//String suitary[] = {"Clubs","Diamonds","Hearts","Spades"};
		
		System.out.println(thisCardSuit+ " " + rankary[rank-1]);

	}
	public esuit getSuit(){
		return thisCardSuit;
	}
	public int getRank(){
		return rank;
	}
}
