/*Umar Khan
 * ICS3U
 * June 14th 2021
 * Culminating Game
 * President Card Game
 */

package PresidentCardGame;
import java.util.*;
public class President {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//VARIABLE TABLE
		int cardToPlay=0, amountToPlay;//card that User plays and amount
		int compToPlay1=0, compAmountToPlay1;//card that Computer 1 plays and amount
		int compToPlay2=0, compAmountToPlay2;//card that Computer 2 plays and amount
		int compToPlay3=0, compAmountToPlay3;//card that Computer 3 plays and amount
		int set=0, currentCard=0;//set (number of cards picked at the beginning of the round) and previous card played by player
		int userTurn=0, comp1Turn=0, comp2Turn=0, comp3Turn=0;//turn counters
		int USERTurn=0, COMP1Turn=1, COMP2Turn=2, COMP3Turn=3;//turn counters
		int pass=0;//pass counter

		ArrayList<Integer> deck = new ArrayList<>();//ArrayList for all 52 cards in a deck (4 of each number)
		ArrayList<Integer> cards = new ArrayList<>();//all distinct cards (numbers 2 to 14)
		ArrayList<Integer> UserHand = new ArrayList<>();//User cards/hand
		ArrayList<Integer> Comp1Hand = new ArrayList<>();//Computer 1 cards/hand
		ArrayList<Integer> Comp2Hand = new ArrayList<>();//Computer 2 cards/hand
		ArrayList<Integer> Comp3Hand = new ArrayList<>();//Computer 3 cards/hand
		
		//add all distinct cards in game (numbers 2 to 14)
		cards.add(2);
		cards.add(3);
		cards.add(4);
		cards.add(5);
		cards.add(6);
		cards.add(7);
		cards.add(8);
		cards.add(9);
		cards.add(10);
		cards.add(11);
		cards.add(12);
		cards.add(13);
		cards.add(14);

		Rules();//method call for rules (output rules of the game)
		
		//add 52 cards into the deck ArrayList (4 of each card - 52 cards total)
		for(int k=0; k<4; k++) {
			for(int i=0; i<cards.size(); i++) {
				deck.add(cards.get(i));//ArrayList.get(index) finds the element at the particular index
			}
		}
		Collections.shuffle(deck);//randomly shuffle deck
		for(int u=0; u<13; u++) {//add 13 cards into User's hand
			UserHand.add(deck.get(u));
			deck.remove(u);//remove the cards put into User's hand
		}
		for(int c=0; c<13; c++) {//add 13 cards into Computer 1's hand
			Comp1Hand.add(deck.get(c));
			deck.remove(c);//remove the cards put into Computer 2's hand
		}
		for(int c=0; c<13; c++) {//add 13 cards into Computer 2's hand
			Comp2Hand.add(deck.get(c));
			deck.remove(c);//remove the cards put into Computer 2's hand
		}
		for(int c=0; c<13; c++) {//add the rest of the 13 cards into Computer 3's hand
			Comp3Hand.add(deck.get(c));
		}
		deck.clear();//deck is empty at this point
		
		System.out.println("\nSTARTING DECKS");
		//sort all the hands so that they appear in least to greatest order 
		Collections.sort(UserHand);
		Collections.sort(Comp1Hand);
		Collections.sort(Comp2Hand);
		Collections.sort(Comp3Hand);
		//output all players' starting hands to the User
		System.out.print("User Cards: "+UserHand);
		System.out.print("\nComputer 1's Cards: "+Comp1Hand);
		System.out.print("\nComputer 2's Cards: "+Comp2Hand);
		System.out.print("\nComputer 3's Cards: "+Comp3Hand);

		//PRESIDENT CARD GAME
		System.out.println("\n\nLET THE GAME BEGIN!");//start the game
		while(UserHand.isEmpty()==false && Comp1Hand.isEmpty()==false && Comp2Hand.isEmpty()==false && Comp3Hand.isEmpty()==false) {//if any player's hand is not empty, keep playing the game

			if(userTurn==0) {//if user starts the round (the user begins the game)
				Collections.sort(UserHand);
				if(pass==3) {//if passes equal 3
					System.out.print("\nUSER TURN\nUser Cards: "+UserHand);
				}

				cardToPlay=userCard(UserHand, currentCard, set);//call userCard method and determine what card user would like to play
				currentCard=cardToPlay;//the currentCard now becomes what the user plays
				amountToPlay=userAmount(UserHand, cardToPlay, set);//call userAmount method and determine how many cards they would like to play
				set=amountToPlay;//user starts the round so they determine the set for the round
				System.out.println("THE SET FOR THIS ROUND IS : "+set);
				UserHand=remove(UserHand, cardToPlay, amountToPlay);//call the remove method and remove the played cards from the user's hand
				USERTurn=USERTurn+4;//used to determine turns
				userTurn++;
				comp1Turn++;
				comp2Turn++;
				comp3Turn++;
				pass=0;//reset passes to 0

			}
			else if(USERTurn==userTurn) {//when the user plays regularly (does not begin the round)
				cardToPlay=0;
				Collections.sort(UserHand);
				System.out.print("\nUSER TURN\nUser Cards: "+UserHand);
				if(pass==3) {//if the 3 Computers pass, the user begins round
					userTurn=-1; comp1Turn=-1; comp2Turn=-1; comp3Turn=-1;
					USERTurn=-4; COMP1Turn=1; COMP2Turn=2; COMP3Turn=3;
					set=0; currentCard=0;
					pass=0;//reset passes
					cardToPlay=-1;//skips regular procedure when user begins round
				}
				if(cardToPlay!=-1) {//regular round (user does not begin round)
					if(pass<3) {
						cardToPlay=userCard(UserHand, currentCard, set);//call userCard method and determine what card user would like to play
					}
					if(cardToPlay==0) {//if user passes their turn
						pass++;
					}
					else if(cardToPlay!=0) {//if user plays (does not pass)
						currentCard=cardToPlay;
						amountToPlay=userAmount(UserHand, cardToPlay, set);//call userAmount method and determine how many cards they would like to play
						UserHand=remove(UserHand, cardToPlay, amountToPlay);//call the remove method and remove the played cards from the user's hand

						pass=0;//reset passes to 0
					}
				}
				USERTurn=USERTurn+4;//used to determine turns
				userTurn++;
				comp1Turn++;
				comp2Turn++;
				comp3Turn++;

			}

			else if(COMP1Turn==comp1Turn) {//when it is Computer 1's turn
				compToPlay1=0;
				Collections.sort(Comp1Hand);
				System.out.print("\nCOMPUTER 1'S TURN\nComputer Cards: "+Comp1Hand);
				if(pass==3) {//if Computer 2, 3 and the User all pass their turns
					userTurn=1; comp1Turn=1; comp2Turn=1; comp3Turn=1;
					USERTurn=4; COMP1Turn=1; COMP2Turn=2; COMP3Turn=3;
					set=0; currentCard=0;
					pass=0;//reset passes

					currentCard=compPlaysFirst(Comp1Hand);//call compPlaysFirst method as Computer 1 starts the round
					set=compPlaysFirstAmount(Comp1Hand, currentCard);//call compPlaysFirstAmount as Computer 1 determines set
					System.out.println("\nThe table is clear, Computer 1 plays "+currentCard+", "+set+" times");
					System.out.println("THE SET FOR THIS ROUND IS : "+set);
					Comp1Hand=remove(Comp1Hand, currentCard, set);//remove cards played from Computer 1's hand
					compToPlay1=-1;
				}
				if(compToPlay1!=-1) {//regular round (Computer 1 does not begin round)
					if(pass<3) {
						compToPlay1=compCard(Comp1Hand, currentCard, set);
					}

					if(compToPlay1==0) {//if computer passes it's turn
						System.out.println("\nComputer 1 is passing this turn");
						pass++;
					}
					else if(compToPlay1!=0) {//if Computer 2 plays (does not pass)
						currentCard=compToPlay1;
						compAmountToPlay1=compAmount(Comp1Hand, compToPlay1, set);
						Comp1Hand=remove(Comp1Hand, compToPlay1, compAmountToPlay1);
						System.out.println("\nThe computer played the card "+compToPlay1+", "+compAmountToPlay1+" times");

						pass=0;//reset passes
					}
				}
				COMP1Turn=COMP1Turn+4;//used to determine turns
				userTurn++;
				comp1Turn++;
				comp2Turn++;
				comp3Turn++;

			}


			else if(COMP2Turn==comp2Turn) {//when it is Computer 2's turn
				compToPlay2=0;
				Collections.sort(Comp2Hand);
				System.out.print("\nCOMPUTER 2'S TURN\nComputer Cards: "+Comp2Hand);
				if(pass==3) {//if Computer 3, 1 and the User all pass their turn
					userTurn=2; comp1Turn=2; comp2Turn=2; comp3Turn=2;
					USERTurn=4; COMP1Turn=5; COMP2Turn=2; COMP3Turn=3;
					set=0; currentCard=0;
					pass=0;//reset passes

					currentCard=compPlaysFirst(Comp2Hand);//call compPlaysFirst method as Computer 2 starts the round
					set=compPlaysFirstAmount(Comp2Hand, currentCard);//call compPlaysFirstAmount as Computer 2 determines set
					System.out.println("\nThe table is clear, Computer 2 plays "+currentCard+", "+set+" times");
					System.out.println("THE SET FOR THIS ROUND IS : "+set);
					Comp2Hand=remove(Comp2Hand, currentCard, set);//remove cards played from Computer 2's hand
					compToPlay2=-1;

				}
				if(compToPlay2!=-1) {//regular round (Computer 2 does not begin round
					if(pass<3) {
						compToPlay2=compCard(Comp2Hand, currentCard, set);
					}

					if(compToPlay2==0) {//if Computer 2 passes it's turn
						System.out.println("\nComputer 2 is passing this turn");
						pass++;
					}
					else if(compToPlay2!=0) {//if computer plays (does not pass)
						currentCard=compToPlay2;
						compAmountToPlay2=compAmount(Comp2Hand, compToPlay2, set);
						Comp2Hand=remove(Comp2Hand, compToPlay2, compAmountToPlay2);
						System.out.println("\nThe computer played the card "+compToPlay2+", "+compAmountToPlay2+" times");

						pass=0;//reset passes
					}
				}
				COMP2Turn=COMP2Turn+4;//used to determine turns
				userTurn++;
				comp1Turn++;
				comp2Turn++;
				comp3Turn++;

			}


			else if(COMP3Turn==comp3Turn) {//When it is Computer 3's turn
				compToPlay3=0;
				Collections.sort(Comp3Hand);
				System.out.print("\nCOMPUTER 3'S TURN\nComputer Cards: "+Comp3Hand);
				if(pass==3) {//if Computer 1, 2 and the User all pass their turn
					userTurn=3; comp1Turn=3; comp2Turn=3; comp3Turn=3;
					USERTurn=4; COMP1Turn=5; COMP2Turn=6; COMP3Turn=3;
					set=0; currentCard=0;
					pass=0;//reset passes

					currentCard=compPlaysFirst(Comp3Hand);//call compPlaysFirst method as Computer 3 starts the round
					set=compPlaysFirstAmount(Comp3Hand, currentCard);//call compPlaysFirstAmount as Computer 3 determines set
					System.out.println("\nThe table is clear, Computer 3 plays "+currentCard+", "+set+" times");
					System.out.println("THE SET FOR THIS ROUND IS : "+set);
					Comp3Hand=remove(Comp3Hand, currentCard, set);//remove cards played from Computer 3's hand
					compToPlay3=-1;

				}
				if(compToPlay3!=-1) {//regular round (Computer 3 does not begin round
					if(pass<3) {
						compToPlay3=compCard(Comp3Hand, currentCard, set);
					}

					if(compToPlay3==0) {//if Computer 3 passes it's turn
						System.out.println("\nComputer 3 is passing this turn");
						pass++;
					}
					else if(compToPlay3!=0) {//if computer plays (does not pass)
						currentCard=compToPlay3;
						compAmountToPlay3=compAmount(Comp3Hand, compToPlay3, set);
						Comp3Hand=remove(Comp3Hand, compToPlay3, compAmountToPlay3);
						System.out.println("\nThe computer played the card "+compToPlay3+", "+compAmountToPlay3+" times");

						pass=0;//reset passes
					}
				}
				COMP3Turn=COMP3Turn+4;//used to determine turns
				userTurn++;
				comp1Turn++;
				comp2Turn++;
				comp3Turn++;

			}

		}
		winner(UserHand, Comp1Hand, Comp2Hand, Comp3Hand);//if one of the player's hands are empty (they got rid of all their cards) call the winner method
	}
	//Rules
	/*Purpose: Output the rules of the President Card Game
	 * Parameters: N/A
	 * Data Returned: N/A
	 */
	public static void Rules() {
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ WELCOME TO PRESIDENT ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("1. The user will begin by placing one card, or a set which is two or more cards of the same rank. For example, 2 Kings, or 3 7’s.\r\n"
				+ "2. Any player after that can either play or pass. \r\n"
				+ "\t- If they choose to play, they must match the quantity of the cards and the rank of the card(s) must be equal to or greater \n\t  than the rank of the previously played card. \r\n"
				+ "\t- The card hierarchy is as follows: 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K, A\r\n"
				+ "\t- A player cannot play a card that is less than the card previously played or a set that has more or less cards than the \n\t  number of cards in the set of the starting player. If they cannot do this, they are forced to pass.\r\n"
				+ "3. A player may choose to pass one round and play on another round. However if 3 players consecutively pass their turn and it returns \n   to the player that just played a card, the card pile is “flushed” or removed, and this player will play the new starting card or set.\r\n"
				+ "4. This process repeats until one player is able to discard all the cards in their hand. They are now the President or the winner!\r\n"
				+ "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	}
	
	//userCard
	/*Purpose: Determine the card that the user would like to play and check if input is valid
	 * Parameters: ArrayList<Integer> userHand, int currentCard (card played by previous player), int set (set of the round)
	 * Data Retuned: int card, card that user chooses to play
	 */
	public static int userCard(ArrayList<Integer> userHand, int currentCard, int set) {
		int card=0;
		int occurrences=0;
		Scanner input=new Scanner(System.in);
		if(currentCard==0 && set==0) {
			System.out.println("\nThe table is clear, you may play any card: ");
			card=input.nextInt();

			while(card<=0 || card>14 || card==1) {
				System.out.println("Invalid input! Please try again. You may not pass at this time!");
				card=input.nextInt();
			}
		}
		else if(currentCard!=0){
			System.out.println("\nWhat card would you like to play (Enter 0 to pass): ");
			card=input.nextInt();
			if(card==0) {
				return card;
			}
		}
		occurrences=(Collections.frequency(userHand, card));
		while(userHand.contains(card)==false || currentCard>card || occurrences<set && set!=0) {//ArrayList.contains() determines if a particular number occurs in the ArrayList
			System.out.println("Invalid input! Please try again (Enter 0 to pass): ");
			card=input.nextInt();
			occurrences=(Collections.frequency(userHand, card));//Collections.frequency finds the number of times a chosen number occurs in the ArrayList
			if(card==0) {
				return card;
			}
		}
		return card;
	}
	
	//userAmount
	/*Purpose: Determine the amount of cards that the user would like to play and check if input is valid
	 * Parameters: ArrayList<Integer> userHand, int card (card chosen by user), int set
	 * Data Returned: int amountPlayed, amount user wishes to play
	 */
	public static int userAmount(ArrayList<Integer> userHand, int card, int set) {
		int a=0;
		int amountPlayed=0;
		int occurrences=0;
		Scanner input=new Scanner(System.in);
		System.out.println("How many cards would you like to play: ");
		a=input.nextInt(); 
		occurrences=(Collections.frequency(userHand, card));

		while(occurrences<a || set!=0 && a!=set || a<=0){
			System.out.println("Invalid input! Please try again: ");
			a=input.nextInt();
		}
		if(occurrences>=a) {
			amountPlayed=a;
		}
		return amountPlayed;
	}
	
	//compCard
	/*Purpose: Determine the card that the computer plays at random and check if it is valid
	 * Parameters: ArrayList<Integer> compHand, int currentCard (card played by previous player), int set (set of the round)
	 * Data Returned: int card, card that computer randomly picks to play
	 */
	public static int compCard(ArrayList<Integer> compHand, int currentCard, int set) {
		int a=0;
		int card=0;
		int occurrences=0;
		a=(int)(Math.random()*compHand.size());
		occurrences=(Collections.frequency(compHand, compHand.get(a)));
		while(compHand.contains(compHand.get(a))==false || currentCard>compHand.get(a) || occurrences<set) {

			if (!compareCards(compHand, currentCard, set)) {
				card=0;
				return card;
			}
			a=(int)(Math.random()*compHand.size());
			occurrences=(Collections.frequency(compHand, compHand.get(a)));

		}
		card=compHand.get(a);
		return card;
	}
	
	//compareCards
	/*Purpose: Determine if card chosen to play is valid
	 * Parameters: ArrayList<Integer> compHand, int card (card played by previous user), int set (set of the round)
	 * Data Returned: boolean flag, true or false
	 */
	public static boolean compareCards(ArrayList<Integer> compHand, int card, int set) {
		boolean flag = false;
		if (compHand.contains(card) && Collections.frequency(compHand, card) == set) {
			flag = true;
		}
		return flag;
	}
	
	//compAmount
	/*Purpose: Determine the amount of cards that the computer would like to play and check if input is valid
	 * Parameters: ArrayList<Integer> userHand, int card (card chosen by user), int set (set of the round
	 * Data Returned: int amountPlayed, amount of cards computer wishes to play
	 */
	public static int compAmount(ArrayList<Integer> compHand, int card, int set) {
		int a=0;
		int amountPlayed=0;
		int occurances=0;
		occurances=(Collections.frequency(compHand, card));

		while(occurances<a || set!=0 && a!=set){
			a++;
		}
		if(occurances>=a) {
			amountPlayed=a;
		}
		return amountPlayed;
	}
	
	//compPlaysFirst
	/*Purpose: What will occur if the computer begins the round, they can play any card they want (randomly picked)
	 * Parameters: ArrayList<Integer> compHand
	 * Data Returned: int card, the random card that they choose to play
	 */
	public static int compPlaysFirst(ArrayList<Integer> compHand) {
		int a=0;
		int card=0;
		a=(int)(Math.random()*compHand.size());
		card=compHand.get(a);

		return card;
	}
	
	//compPlaysFirstAmount
	/*Purpose: Determine how many cards they would like to play if the computer starts the round
	 * Parameters: ArrayList<Integer> compHand, int currentCard (card computer randomly chose right now)
	 * Data Returned: int occurrences, play the maximum amount of the card that the computer chose 
	 */
	public static int compPlaysFirstAmount(ArrayList<Integer> compHand, int currentCard) {
		int occurrences=0;

		occurrences=(Collections.frequency(compHand, currentCard));
		return occurrences;
	}
	
	//remove
	/*Purpose: Remove the cards that the player player from their hand
	 * Parameters: ArrayList<Integer> hand, int card (card they chose to play), int amount (amount of cards they chose to play)
	 * Data Returned: ArrayList<Integer> newHand, player's new hand with the cards they played removed 
	 */
	public static ArrayList<Integer> remove (ArrayList<Integer> hand, int card, int amount) {
		ArrayList newHand = new ArrayList<Integer>();
		int a=amount;
		int occurrences=0;
		for (int i = 0; i<hand.size(); i++) {
			if (hand.get(i)!=card) {
				newHand.add(hand.get(i));
			}
		}
		occurrences=(Collections.frequency(hand, card));
		int diff=(occurrences-amount);
		for(int l=0; l<diff; l++) {
			newHand.add(card);
		}
		return newHand; 
	}
	
	//winner
	/*Purpose: Output the winner of the game and winning message
	 * Parameters: ArrayList<Integer> Uhand, ArrayList<Integer> C1hand, ArrayList<Integer> C2hand, ArrayList<Integer> C3hand
	 * Data Returned: N/A
	 */
	public static void winner(ArrayList<Integer> Uhand, ArrayList<Integer> C1hand, ArrayList<Integer> C2hand, ArrayList<Integer> C3hand) {
		System.out.println();
		if(Uhand.isEmpty()) {
			System.out.println("The User is the President! YOU WON!");
		}
		else if(C1hand.isEmpty()) {
			System.out.println("Computer 1 is the President!");
		}
		else if(C2hand.isEmpty()) {
			System.out.println("Computer 2 is the President!");
		}
		else if(C3hand.isEmpty()) {
			System.out.println("Computer 3 is the President!");
		}
	}
	//THE GAME IS OVER
}