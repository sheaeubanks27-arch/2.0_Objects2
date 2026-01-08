public class Whale {

    public String name;
    public int xpos;
    public int ypos;
    public int width;
    public int height;
    public boolean isAlive;
    public int dx;
    public int dy;

    public Whale (int pXpos, int pYpos){
        xpos = pXpos;
        ypos = pYpos;
        width = 300;
        height = 300;
        isAlive = true;
        dx = 1;
        dy = 0;

    }

    public void move() {
        xpos = xpos + dx;
        ypos = ypos + dy;

    }
}
