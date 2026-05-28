//vvvvvvvvvvvvvvvvvvvvvvvvvvvvvv DON'T CHANGE! vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
// Graphics Libraries
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
public class BasicGameApp implements Runnable, KeyListener {
    //Sets the width and height of the program window
    final int WIDTH = 1000;
    final int HEIGHT = 700;
    Card[] deck;
    Player player;
    Player dealer;
    int indexInDeck = 0;
    Image background;
    boolean stood = false;
    boolean overLimit;


    //Variable Definition Section
    //You can set their initial values too
    // Like Mario mario = new Mario(); //


    // Initialize your variables and construct your program objects here.
    public BasicGameApp() { // BasicGameApp constructor
        setUpGraphics();
        background = Toolkit.getDefaultToolkit().getImage("CasinoTable.jpg");
        deck = getShuffledDeck();
        for (int i = 0; i < deck.length; i++) {
            // deck[i].printInfo();
        }
        player = new Player(deck[0], deck[1]);
        System.out.println("Your Cards:");
        player.printInfo();
        System.out.println("==============");
        indexInDeck = indexInDeck + 2;
        dealer = new Player(deck[2], deck[3]);
        System.out.println("Dealer's cards:");
        dealer.dealerInfo();
        System.out.println("===========");

        indexInDeck = indexInDeck + 2;
        if(player.getHandValue()<=21){
            overLimit = false;
        }
        else{
            overLimit = true;
        }
        if(overLimit == true){
            for(int i=0; i < player.getHand().size(); i++){
                if(player.getHand().get(i).getName() == "Ace"){
                    player.getHand().get(i).setValue(1);
                }
            }
        }
        //  player.stand();
        // player.hit(deck[indexInDeck], deck[indexInDeck+1]);
        //indexInDeck = indexInDeck+2;

    }

    //variable and objects
    //create (construct) the objects needed for the game


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


    // end BasicGameApp constructor

    public void moveThings() {

        //call the move() code for each object  -

    }

    //Paints things on the screen using bufferStrategy
    private void render() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);
        g.drawImage(background, 0, 0, WIDTH, HEIGHT, null);
        g.setFont(new Font("SansSerif", Font.BOLD, 20));
        for(int i = 0; i < player.getHand().size(); i++) {
            g.drawString("Player:",100,150);
            g.drawString(player.getHand().get(i).getInfo(), 200, 200+50*i);
            g.drawString(""+player.getHandValue(), 290, 150);
           // g.drawString("");
        }

        for (int i = 0; i < dealer.getHand().size(); i++) {
            if (i > 0) {
                g.drawString("Dealer:", 500, 150);
                g.drawString(dealer.getHand().get(i).getInfo(), 600, 200 + 50 * i);
            } else {
                g.drawString("????????", 600, 200 + 200 * i);
            }
        }

        // game end info

        if (player.getHandValue() > 21){
            g.drawString("Player Busts - Dealer Wins", 500, 550);
        }
        if (dealer.getHandValue()>21){
            g.drawString("Dealer Busts - Player Wins",500,550);
        }
        if (stood) {
            if (player.getHandValue() > dealer.getHandValue() && player.getHandValue() <= 21) {
                g.drawString("Player Wins", 500, 550);
            }
            if (player.getHandValue() < dealer.getHandValue() && dealer.getHandValue() <= 21) {
                g.drawString("Dealer Wins", 500, 550);
            }
            // CHANGE BELOW FOR PUSH
            if (player.getHandValue() == dealer.getHandValue() ) {
                g.drawString("Draw", 500, 550);
            }
        }




        //draw the images
        // Signature: drawImage(Image img, int x, int y, int width, int height, ImageObserver observer)


        // Keep the code below at the end of render()
        g.dispose();
        bufferStrategy.show();
    }


    //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
//vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv DON'T CHANGE! vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
    //Declare the variables needed for the graphics
    public JFrame frame;
    public Canvas canvas;
    public JPanel panel;
    public BufferStrategy bufferStrategy;

    // PSVM: This is the code that runs first and automatically
    public static void main(String[] args) {
        BasicGameApp ex = new BasicGameApp();   //creates a new instance of the game
        new Thread(ex).start();                 //creates a threads & starts up the code in the run( ) method
    }

    // main thread
    // this is the code that plays the game after you set things up
    public void run() {
        //for the moment we will loop things forever.
        while (true) {
            moveThings();  //move all the game objects
            render();  // paint the graphics
            pause(10); // sleep for 10 ms
        }
    }

    //Pauses or sleeps the computer for the amount specified in milliseconds
    public void pause(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
        }
    }

    private Image getImage(String filename) {
        return Toolkit.getDefaultToolkit().getImage(filename);
    }

    //Graphics setup method
    private void setUpGraphics() {
        frame = new JFrame("Application Template");   //Create the program window or frame.  Names it.

        panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
        panel.setLayout(null);   //set the layout

        // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
        // and trap input events (Mouse and Keyboard events)
        canvas = new Canvas();
        canvas.setBounds(0, 0, WIDTH, HEIGHT);
        canvas.setIgnoreRepaint(true);
        canvas.addKeyListener(this);

        panel.add(canvas);  // adds the canvas to the panel.

        // frame operations
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
        frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
        frame.setResizable(false);   //makes it so the frame cannot be resized
        frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!

        // sets up things so the screen displays images nicely.
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        canvas.requestFocus();
        System.out.println("DONE graphic setup");
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
       // System.out.println(e.getKeyCode());
        int key = e.getKeyCode();
        if (key == 83){ // s
            stood = true;
            while(dealer.getHandValue()<=17){
                System.out.println("Dealer Hits");
                dealer.hit(deck[indexInDeck++]);
            }

            if(dealer.getHandValue()>17){ // was 15
                System.out.println("Dealer Stands");
                dealer.stand();
            }
            if(player.getHandValue()==dealer.getHandValue()){
                System.out.println("Push");
            }

            System.out.println("Dealer's cards:");
            dealer.printInfo();
            System.out.println("=============");
            if(player.getHandValue()>dealer.getHandValue()){
                System.out.println("Player Wins");
            }

            else if(player.getHandValue()<dealer.getHandValue()){
                System.out.println("Dealer Wins");
            }
        }
        else if (key == 72){
            player.hit(deck[indexInDeck++]);
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();


    }
}
//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

























