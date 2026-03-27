import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import java.io.File;
import java.util.Arrays;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Player extends GameObject{
    private volatile boolean isParry = false;
    private volatile boolean cooldown = false;
    private ScheduledExecutorService scheduler;
    //creates player and centers to bottom of screen (gets paths from plrframes directory)
    public Player(){
        super(0,0,100,100, Arrays.stream(new File("assets/plrframes").list())
                .map(element ->  "assets/plrframes/" + element)
                .toArray(String[]::new));
        super.x = (double) (Launcher.gameWidth - width) / 2;
        scheduler = Executors.newScheduledThreadPool(1);
    }

    @Override
    public boolean getState(){
        return this.isParry;
    }

    @Override
    public void forceState(){ this.isParry = false; }

    private void playAnim(){
        for (int i = 0; i < getFrameCount(); i++){
            incrementFrames();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }
    }

    @Override
    public void move(double deltaTime) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.F) && !cooldown){
            isParry = true;
            cooldown = true;
            scheduler.schedule(() -> {
                playAnim();
                isParry = false;
                try {
                    Thread.sleep(1000); // 1 Second endlag to Parry
                } catch (InterruptedException e){}
                cooldown = false;
            }, 0, TimeUnit.MILLISECONDS);
        }
    }
}
