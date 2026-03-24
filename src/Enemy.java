import java.io.File;
import java.util.Arrays;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Enemy extends GameObject{
    private volatile boolean isAttacking = false;
    private ScheduledExecutorService scheduler;
    //creates player and centers to bottom of screen (gets paths from plrframes directory)
    public Enemy(){
        super(0,0,100,100, Arrays.stream(new File("assets/enemyframes").list())
                .map(element ->  "assets/enemyframes/" + element)
                .toArray(String[]::new));
        super.x = (double) (Launcher.gameWidth - width) / 2;
        super.y = Launcher.gameHeight - 200;
        scheduler = Executors.newScheduledThreadPool(2);
        loop();
    }

    private void loop(){
        scheduler.schedule(() -> {
            playAnim();
            loop();
        }, (long) ((Math.random() * 3000) / (1 + (double) MyGame.score / 100)), TimeUnit.MILLISECONDS);
    }

    @Override
    public boolean getState(){
        return this.isAttacking;
    }

    @Override
    public void forceState(){ isAttacking = false; }

    private void playAnim(){
        for (int i = 0; i < getFrameCount(); i++){
            incrementFrames();
            if (i == 3){
                isAttacking = true;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }
        isAttacking = false;
    }

}
