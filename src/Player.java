public class Player extends GameObject{
    private boolean isParry = false;
    //creates player and centers to bottom of screen
    public Player(){
        super(0,0,100,100,"assets/fish_blue.png");
        super.x = (double) (Launcher.gameWidth - width) / 2;
    }

    @Override
    public void move(double deltaTime) {
        super.move(deltaTime);
    }
}
