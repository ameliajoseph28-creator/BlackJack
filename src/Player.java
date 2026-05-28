import java.sql.Array;
import java.util.ArrayList;

public class Player {
    private ArrayList<Card> hand;
    private int numberOfCards;



public ArrayList<Card> getHand(){
    return hand;
}
    public Player(Card card1, Card card2) {
        hand = new ArrayList<>();
        hand.add(card1);
        hand.add(card2);
        numberOfCards = hand.size();

    }

    public void hit(Card newCard) {
        numberOfCards = numberOfCards + 1;
        hand.add(newCard);
        System.out.println("===========");
        printInfo();
        if (getHandValue() > 21) {
            System.out.println("you lose");
        }

    }



    public void dealerInfo(){
        for (int i = 0; i < numberOfCards; i++) {
            if(i==0){
                hand.get(i).printInfo();
            }
            else{
                System.out.println("?");
            }
        }
    }

    public void stand() {
        numberOfCards = numberOfCards;

    }

    public void printInfo() {
        for (int i = 0; i < numberOfCards; i++) {
            hand.get(i).printInfo();

        }


    }

    public int getHandValue() {
        int sum = 0;
        for (int i = 0; i < numberOfCards; i++) {
            sum = sum+hand.get(i).getValue();

        }
    return sum;

    }



}
