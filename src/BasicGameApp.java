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
        int randx = (int)(Math.random()*999)+1;
        int randy = (int)(Math.random()*699)+1;
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
        flip = new Fish(randx,randy);
        whl = new Whale(randx,randy);
        octo = new Octopus(randx,randy);
        n = new Net(500,350);
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


	public void moveThings()
	{
      //calls the move( ) code in the objects
		//astro.move();
        flip.move();
        whl.move();
        octo.move();
        n.move();
        catching();
        spray();


	}
	
   //Pauses or sleeps the computer for the amount specified in milliseconds
   public void pause(int time ){
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
        g.drawImage(backgroundPic,0, 0, WIDTH, HEIGHT, null);

      //draw the image of the astronaut
		//g.drawImage(astroPic, astro.xpos, astro.ypos, astro.width, astro.height, null);
        g.drawImage(netPic, n.xpos, n.ypos, n.width, n.height, null);

        g.drawImage(fishPic, flip.xpos, flip.ypos, flip.width, flip.height ,null);
        g.drawImage(whalePic, whl.xpos, whl.ypos, whl.width, whl.height, null);
        g.drawImage(octopusPic, octo.xpos, octo.ypos, octo.width, octo.height, null);

		g.dispose();

		bufferStrategy.show();
	}

    public void spray(){

        if(octo.hitbox.intersects(flip.hitbox)){
            fishPic = fishPic2;

        }

        if (octo.hitbox.intersects(whl.hitbox)){
            whalePic = whalePic2;

        }

    }

    public void catching(){
        if(flip.isCaught=true){
            n.xpos = flip.xpos;
            n.ypos = flip.ypos;
        }

        if(octo.isCaught=true){
            n.xpos = octo.xpos;
            n.ypos = octo.ypos;
        }

        if(whl.isCaught=true){
            n.xpos= whl.xpos;
            n.ypos = whl.ypos;
        }
    }

    public void eating(){
        if(whl.hitbox.intersects(flip.hitbox)){

        }
    }
}