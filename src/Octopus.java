import java.awt.*;

public class Octopus {
    public String name;
    public int xpos;
    public int ypos;
    public int width;
    public int height;
    public boolean isAlive;
    public int dx;
    public int dy;
    public Rectangle hitbox;
    public boolean isCaught;

    public Octopus (int pXpos, int pYpos){
        xpos = pXpos;
        ypos = pYpos;
        width = 60;
        height = 60;
        isAlive = true;
        dx = 1;
        dy = 1;
        hitbox = new Rectangle(xpos,ypos,width,height);

    }
    //method that makes the octopus move
    public void move() {
        //xpos = xpos + dx;
       // ypos = ypos + dy;
        if(xpos >= 1000-width){//bounce off the right wall
            dx = -dx;

        }


        if(xpos <=0){//bounce off the left wall
            dx = -dx;
        }


        if(ypos >= 700-height){//bounce off the bottom wall
            dy = -dy;
        }

        if(ypos <=0){//bounce off the top wall
            dy = -dy;
        }
        xpos = xpos + dx;
        ypos = ypos + dy;
        hitbox = new Rectangle(xpos,ypos,width,height);

    }
}
