import java.awt.*;

public class Net {

    public String name;
    public int xpos;
    public int ypos;
    public int width;
    public int height;
    public boolean isAlive;
    public int dx;
    public int dy;
    public Rectangle hitbox;


    public Net(int pXpos, int pYpos) {

        xpos = pXpos;
        ypos = pYpos;
        width = 350;
        height = 350;
        isAlive = true;
        dx = 0;
        dy = 1;
        hitbox = new Rectangle(xpos, ypos, width, height);


    }
    //method that makes the net move
    public void move() {

        if (ypos >= 700 - height) {//bounce off bottom wall
            dy = -dy;
        }

        if (ypos <= 0) {//bounce off top wall
            dy = -dy;
        }
        xpos = xpos + dx;
        ypos = ypos + dy;
        //makes the boundary around the net
        hitbox = new Rectangle(xpos, ypos, width, height);
    }
}
