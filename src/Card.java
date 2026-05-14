public class Card {
    int value;
    String suite;
    String name;



    public Card(String nameInput, String suitInput) {
        name = nameInput;
        suite = suitInput;
        if (nameInput == "King") {
            value = 10;
        } else if (nameInput == "Queen") {
            value = 10;
        } else if (nameInput == "Jack") {
            value = 10;
        } else if (nameInput == "Ace") {
            value = 1;
        } else {
            value = Integer.parseInt(name);
        }
    }


    public void printInfo(){
        System.out.println(name+ " of " +suite);
    }

    public String getInfo(){
        return  name+" of "+suite;
    }

    public static void main(String[] args) {

    }



}
