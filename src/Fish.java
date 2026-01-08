public class Fish{

    public String name;
    public int xpos;
    public int ypos;
    public int width;
    public int height;
    public boolean isAlive;
    public int dx;
    public int dy;

    public Fish (int pXpos, int pYpos){
        xpos = pXpos;
        ypos = pYpos;
        width = 60;
        height = 60;
        isAlive = true;
        dx = 7;
        dy = 7;

    }

    public void move() {
        xpos = xpos + dx;
        ypos = ypos + dy;

    }

}