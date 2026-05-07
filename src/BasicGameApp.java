public class BlackJackGame {
   // public boolean cardDrawn[];
    Card[] deck;


    public BlackJackGame() {
        deck = getShuffledDeck();
        for (int i = 0; i < deck.length; i++) {
            deck[i].printInfo();

        }

    }

    public Card[] getShuffledDeck() {
        String[] suites = {"Hearts", "Clubs", "Diamonds", "Spades"};
        String[] names = {"King", "Queen", "Jack", "Ace", "10", "9", "8", "7", "6", "5", "4", "3", "2"};
        Card[] gameDeck = new Card[52];
        int placeInDeck = 0;


    for (int i = 0; i < names.length; i++) {
        for (int j = 0; j < suites.length; j++) {
            gameDeck[placeInDeck] = new Card(names[i], suites[j]);
            placeInDeck = placeInDeck + 1;
        }
    }


        for (int i = 0; i < gameDeck.length; i++) {
            Card temp = gameDeck[i];
            int randomIndex = (int) (Math.random() * 52);
            gameDeck[i] = gameDeck[randomIndex];
            gameDeck[randomIndex] = temp;

        }



        return gameDeck;


    }





    public static void main(String[] args) {
        BlackJackGame main = new BlackJackGame();
    }
    }


