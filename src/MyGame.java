import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.audio.Mp3.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class MyGame extends ApplicationAdapter {
  
    Sound parrySound = (Sound) Gdx.audio.newSound(new FileHandle("assets/sounds/parry.mp3"));

    private SpriteBatch batch;
    private ArrayList<GameObject> activeObjects;
    public static int score = 0;

    @Override
    public void create() {
        batch = new SpriteBatch();
        activeObjects = new ArrayList<GameObject>();

        Player plr = new Player();
        activeObjects.add(plr);

        Enemy enemy = new Enemy();
        activeObjects.add(enemy);
        
    }

    //render() is the game loop, called approx 60 times per second
    @Override
    public void render() {
        // Boilerplate: Clear the screen to black each frame
        Gdx.gl.glClearColor(.25f, .25f, .25f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //double for sum bs reason
        double deltaTime = (double) Gdx.graphics.getDeltaTime();

        //Updates (kept name move for simplicity each object
        for (GameObject e : activeObjects){
            e.move(deltaTime);
        }

        
        //Draw all objects
        batch.begin();

        for (GameObject e : activeObjects){
            e.draw(batch);
        }

        batch.end();

       if (activeObjects.get(0).getState() && activeObjects.get(1).getState()){
                activeObjects.get(1).forceState();
                activeObjects.get(0).forceState();
                parrySound.play(); 
                score++;
                System.out.println(score);
       } else if (activeObjects.get(1).getState()) {
           activeObjects.get(1).forceState();
           activeObjects.get(0).forceState();
           System.out.println("You died");
           score = 0;
       }

    }
    
    @Override
    public void dispose() {
        batch.dispose();
    }
}