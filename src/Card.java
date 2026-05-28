public class Card {
    private int value;
    private String suite;
    private String name;


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
            value = 11;
        } else {
            value = Integer.parseInt(name);
        }
    }

    public int getValue() {
        return value;
    }
    public void setValue(int newValue){
        value = newValue;
    }

    public String getName(){
        return name;
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
