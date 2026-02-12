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

    public void move() {
        // xpos = xpos + dx;
        //ypos = ypos + dy;
        if (xpos >= 1000 - width) {//bounce off the right wall
            dx = -dx;

        }


        if (xpos <= 0) {
            dx = -dx;
        }


        if (ypos >= 700 - height) {
            dy = -dy;
        }

        if (ypos <= 0) {
            dy = -dy;
        }
        xpos = xpos + dx;
        ypos = ypos + dy;
        hitbox = new Rectangle(xpos, ypos, width, height);
    }
}
