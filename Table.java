import java.util.ArrayList;

public class Table {
	final static int MAXPLAYER = 4;
	Deck allCard;
	Player[] allPlayer= new Player[MAXPLAYER];
	Dealer dealer;
	int[] pos_betArray = new int[MAXPLAYER];
	
	Table(int nDeck){//Constructor
		Deck d = new Deck(nDeck);
		allCard = d;
	}
	
	public void set_player(int pos, Player p) {
		allPlayer[pos]  = p;
	}
	public Player[] get_player() {
		return allPlayer;
	}
	public void set_dealer(Dealer d) {
		dealer = d;
	}
	public Card get_face_up_card_of_dealer() {
		return dealer.getOneRoundCard().get(1);
	}

	private void ask_each_player_about_bets() {
		int i = 0;
		for(Player p : allPlayer) {
			p.sayHello();
			pos_betArray[i] = p.makeBet();	
			i++;
		}
	}
	private void distribute_cards_to_dealer_and_players() {		
		for(Player p : allPlayer) {
			ArrayList<Card> tem = new ArrayList<Card>();
			tem.add(allCard.getOneCard(true));
			tem.add(allCard.getOneCard(true));
			p.setOneRoundCard(tem);			
		}
		ArrayList<Card> tem = new ArrayList<Card>();
		tem.add(allCard.getOneCard(false));
		tem.add(allCard.getOneCard(true));
		dealer.setOneRoundCard(tem);
		get_face_up_card_of_dealer().printCard();//Dealer's face up card is ...
	}
	private void ask_each_player_about_hits() {
		 boolean hit = false;
		 for(Player p : allPlayer) {
			 do{				 	
				 	Table tbl = new Table(4);
					hit=p.hit_me(tbl);
					if(hit & p.getTotalValue() < 21){
						System.out.println("Hit!"+ p.getName()+ "¡¦s cards now");
						ArrayList<Card> tem = new ArrayList<Card>();
						tem = p.getOneRoundCard();
						tem.add(allCard.getOneCard(true));
						p.setOneRoundCard(tem);
						for(Card c : tem) {
							c.printCard();
						}
					}
					else{
						System.out.println(p.getName()+ "Pass hit!");
					}
				}while(hit);	
			}
	}
	private void ask_dealer_about_hits() {
		boolean hit = false;
		 do{				 	
			 	Table tbl = new Table(4);
				hit=dealer.hit_me(tbl);
				if(hit & dealer.getTotalValue() < 21){
					System.out.println("Hit!"+ "Dealer"+ "¡¦s cards now");
					ArrayList<Card> tem = new ArrayList<Card>();
					tem = dealer.getOneRoundCard();
					tem.add(allCard.getOneCard(true));
					dealer.setOneRoundCard(tem);
					for(Card c : tem) {
						c.printCard();
					}
				}
				else{
					System.out.println("Dealer"+ "Pass hit!");
				}
			}while(hit);
		 System.out.println("Dealer's hit is over!");
	}
	private void calculate_chips() {
		System.out.println("Dealer's card value is "+dealer.getTotalValue()+" ,Cards:");
		dealer.printAllCard();
		for(Player p : allPlayer) {
			System.out.print(p.getName()+" card value is "+p.getTotalValue());
			if((p.getTotalValue()>21 && dealer.getTotalValue()>21) || p.getTotalValue() == dealer.getTotalValue()){
				System.out.println(",chips have no change! The Chips now is: " + p.getCurrentChips());
			}else if((p.getTotalValue()<=21&&p.getTotalValue()>dealer.getTotalValue()) || (dealer.getTotalValue() > 21 && p.getTotalValue() < 21)){
				p.increaseChips(p.makeBet());
				System.out.println(",Get "+p.makeBet()+" Chips, the Chips now is: " + p.getCurrentChips());					
			}else {
				p.increaseChips(-p.makeBet());
				System.out.println(", Loss "+p.makeBet()+" Chips, the Chips now is: " + p.getCurrentChips());
			}
		}
	}
	public int[] get_players_bet() {
		int[] bet= new int[4];
		int i = 0;
		for(Player p : allPlayer) {
			bet[i] = p.makeBet();
			i++;
			}
		return bet;
		
	}
	public void play(){
		ask_each_player_about_bets();
		distribute_cards_to_dealer_and_players();
		ask_each_player_about_hits();
		ask_dealer_about_hits();
		calculate_chips();
	}

}
