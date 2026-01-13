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

    public Octopus (int pXpos, int pYpos){
        xpos = pXpos;
        ypos = pYpos;
        width = 60;
        height = 60;
        isAlive = true;
        dx = 5;
        dy = 5;
        hitbox = new Rectangle(xpos,ypos,width,height);

    }

    public void move() {
        //xpos = xpos + dx;
       // ypos = ypos + dy;
        if(xpos >= 1000-width){//bounce off the right wall
            dx = -dx;

        }


        if(xpos <=0){
            dx = -dx;
        }


        if(ypos >= 700-height){
            dy = -dy;
        }

        if(ypos <=0){
            dy = -dy;
        }
        xpos = xpos + dx;
        ypos = ypos + dy;
        hitbox = new Rectangle(xpos,ypos,width,height);

    }
}
