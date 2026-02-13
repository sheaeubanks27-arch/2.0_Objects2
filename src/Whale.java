import java.awt.*;

public class Whale {

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

    public Whale (int pXpos, int pYpos){
        xpos = pXpos;
        ypos = pYpos;
        width = 300;
        height = 300;
        isAlive = true;
        dx = 1;
        dy = 1;
        hitbox = new Rectangle(xpos,ypos,width,height);


    }
    //method that makes the whale move
    public void move() {


        if(xpos >= 1000){//wrap when hits right wall
            xpos = 1;

        }

        if (xpos <= 0){//wrap when hits left wall
            xpos = 999;

        }

        if (ypos >= 700){//wrap when hits bottom wall
            ypos = 1;
        }

        if (ypos < 0){//wrap when hits top wall
            ypos = 699-height;
        }


        xpos = xpos + dx;
        ypos = ypos + dy;
        //makes the boundary around the whale
        hitbox = new Rectangle(xpos,ypos,width,height);

    }
}
