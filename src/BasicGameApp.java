//Basic Game Application
//Version 2
// Basic Object, Image, Movement
// Astronaut moves to the right.
// Threaded

//K. Chun 8/2018

//*******************************************************************************
//Import Section
//Add Java libraries needed for the game
//import java.awt.Canvas;

//Graphics Libraries
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;


//*******************************************************************************
// Class Definition Section

public class BasicGameApp implements Runnable {

    //Variable Definition Section
    //Declare the variables used in the program
    //You can set their initial values too

    //Sets the width and height of the program window
    final int WIDTH = 1000;
    final int HEIGHT = 700;

    //Declare the variables needed for the graphics
    public JFrame frame;
    public Canvas canvas;
    public JPanel panel;

    public BufferStrategy bufferStrategy;
    public Image astroPic;
    public Image fishPic;
    public Image octopusPic;
    public Image whalePic;
    public Image backgroundPic;
    public Image netPic;
    public Image fishPic2;
    public Image whalePic2;

    //Declare the objects used in the program
    //These are things that are made up of more than one variable type
    private Astronaut astro;
    private Fish flip;
    private Whale whl;
    private Octopus octo;
    private Net n;
    public int print = 0;



    // Main method definition
    // This is the code that runs first and automatically
    public static void main(String[] args) {
        BasicGameApp ex = new BasicGameApp();   //creates a new instance of the game
        new Thread(ex).start();                 //creates a threads & starts up the code in the run( ) method
    }

    // Constructor Method
    // This has the same name as the class
    // This section is the setup portion of the program
    // Initialize your variables and construct your program objects here.
    public BasicGameApp() {
        int randx = (int) (Math.random() * 999) + 1;
        int randy = (int) (Math.random() * 699) + 1;
        setUpGraphics();

        //variable and objects
        //create (construct) the objects needed for the game and load up
        astroPic = Toolkit.getDefaultToolkit().getImage("astronaut.png");//load the picture
        fishPic = Toolkit.getDefaultToolkit().getImage("bluefish.png");
        octopusPic = Toolkit.getDefaultToolkit().getImage("octopus.png");
        whalePic = Toolkit.getDefaultToolkit().getImage("whale.png");
        backgroundPic = Toolkit.getDefaultToolkit().getImage("ocean.jpg");
        netPic = Toolkit.getDefaultToolkit().getImage("net.png");
        //astro = new Astronaut(10,100);
        flip = new Fish(700, 200);
        whl = new Whale(0, 300);
        octo = new Octopus(randx, randy);
        n = new Net(100, 70);
        fishPic2 = Toolkit.getDefaultToolkit().getImage("blackfish.png");
        whalePic2 = Toolkit.getDefaultToolkit().getImage("blackwhale.png");


    }// BasicGameApp()


//*******************************************************************************
//User Method Section
//
// put your code to do things here.

    // main thread
    // this is the code that plays the game after you set things up
    public void run() {

        //for the moment we will loop things forever.
        while (true) {

            moveThings();  //move all the game objects
            render();  // paint the graphics
            pause(20); // sleep for 10 ms
        }
    }


    public void moveThings() {
        //calls the move( ) code in the objects
        flip.move();
        whl.move();
        octo.move();
        n.move();
        catching();
        spray();
        eating();
        endMessage();


    }

    //Pauses or sleeps the computer for the amount specified in milliseconds
    public void pause(int time) {
        //sleep
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {

        }
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


    //paints things on the screen using bufferStrategy
    private void render() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);
        //draws the background image
        g.drawImage(backgroundPic, 0, 0, WIDTH, HEIGHT, null);
        //draw the image of the net
        g.drawImage(netPic, n.xpos, n.ypos, n.width, n.height, null);
        //draws the fish image only when the fish is alive(disapears when eaten)
        if (flip.isAlive == true) {
            g.drawImage(fishPic, flip.xpos, flip.ypos, flip.width, flip.height, null);
        }       //draws whale image
        g.drawImage(whalePic, whl.xpos, whl.ypos, whl.width, whl.height, null);
        //draws the octopus image only when the octopus is alive(disapears when eaten)
        if (octo.isAlive == true) {
            g.drawImage(octopusPic, octo.xpos, octo.ypos, octo.width, octo.height, null);
        }
        g.dispose();

        bufferStrategy.show();
    }

    //a method that makes it look like the octopus sprayed ink and turned the whale and fish black
    public void spray() {

        if (octo.hitbox.intersects(flip.hitbox)) {
            fishPic = fishPic2;
            if( print == 1) {
                System.out.println("The octopus sprayed the fish");
            }
        }



        if (octo.hitbox.intersects(whl.hitbox)) {
            whalePic = whalePic2;
            if(print == 1) {
                System.out.println("The octopus sprayed the whale");
            }
            }

    }

    public void catching() {
        if (n.hitbox.intersects(flip.hitbox) ) {
            flip.xpos = n.xpos;
            flip.ypos = n.ypos;
            flip.isCaught = true;
            if(print == 1) {
                System.out.println("The fish has been caught");
            }
            }

//        if (!n.hitbox.intersects(flip.hitbox)) {
//            flip.isCaught = false;
//        }

        if (n.hitbox.intersects(octo.hitbox) && octo.isCaught == false) {
            octo.xpos = n.xpos;
            octo.ypos = n.ypos;
            octo.isCaught = true;
           if(print == 1) {
               System.out.println("The octopus has been caught");
           }
        }

       // if (!n.hitbox.intersects(octo.hitbox)) {
            //octo.isCaught = false;

        if (n.hitbox.intersects(whl.hitbox) ) {

            whl.xpos = n.xpos;
            whl.ypos = n.ypos;
            whl.isCaught = true;
            print++;
            if (print==1){
                System.out.println("The whale has been caught");

            }
        }

//        if (!n.hitbox.intersects(whl.hitbox)) {
//            whl.isCaught = false;
//        }
    }

        public void eating() {
            if (whl.hitbox.intersects(flip.hitbox)) {
                whl.dy = -whl.dy;
                flip.dy = -flip.dy;
                flip.isAlive = false;
                if (print == 1) {
                    System.out.println("The whale ate the fish");
                }
            }


            if (whl.hitbox.intersects(octo.hitbox)) {
                whl.dy = -whl.dy;
                octo.dy = -octo.dy;
                octo.isAlive = false;
            }
        }

        public void endMessage () {
            if (octo.isAlive == false && flip.isAlive == false && whl.isCaught == true) {
                if (print == 1) {
                    System.out.println("The life cycle has been completed");
                }
                }
        }
    }
