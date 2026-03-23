import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Player extends GameObject{
    private volatile boolean isParry = false;
    private volatile boolean cooldown = false;
    private ScheduledExecutorService scheduler;
    //creates player and centers to bottom of screen
    public Player(){
        super(0,0,100,100,"assets/fish_blue.png");
        super.x = (double) (Launcher.gameWidth - width) / 2;
        scheduler = Executors.newScheduledThreadPool(1);
    }

    @Override
    public void move(double deltaTime) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.F) && !cooldown){
            isParry = true;
            cooldown = true;
            scheduler.schedule(() -> {
                isParry = false;
                cooldown = false;
            }, 3, TimeUnit.SECONDS);
        }
        System.out.println(isParry);
    }
}
