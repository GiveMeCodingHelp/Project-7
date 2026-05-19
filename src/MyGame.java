import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.io.FileWriter;
import java.util.ArrayList;
import java.io.File;
import java.io.Writer;
import java.util.Scanner;

public class MyGame extends ApplicationAdapter {

    private SpriteBatch batch;
    private BitmapFont scoreFont;
    private ArrayList<GameObject> activeObjects;
    public static int score = 0;
    private int bestScore = getHighScore();
    private static String action = "";
    private Sound parrySound;
    private Sound deathSound;

    private int getHighScore() {
        try {
            Scanner scan = new Scanner(new File("src/BestScore.txt"));
            int gotten = Integer.parseInt(scan.nextLine());
            scan.close();
           // System.out.println(gotten);
            return gotten;
        } catch (Exception e) {
            System.out.println("error");
            return -1;
        }
    }

    private void saveHighScore(int newScore) {
        try {
            Writer write = new FileWriter("src/BestScore.txt");
            write.write(Integer.toString(newScore));
            write.close();
        } catch (Exception e) {

        }
    }


    @Override
    public void create() {
        parrySound = Gdx.audio.newSound(Gdx.files.internal("assets/sounds/parry.ogg"));
        deathSound = Gdx.audio.newSound(Gdx.files.internal("assets/sounds/death.ogg"));

        scoreFont = new BitmapFont(); 

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
        
        scoreFont.draw(batch, "Score: " + score, 50, 450);
        scoreFont.draw(batch, action, 375, 200);
        scoreFont.draw(batch, "Best Score: " + bestScore, 650, 450);
       
        batch.end();

       if (activeObjects.get(0).getState() && activeObjects.get(1).getState()){
                activeObjects.get(1).forceState();
                activeObjects.get(0).forceState();
                parrySound.play(); 
                score++;
                action = "Parry! x" + score;
                //System.out.println(score);
       } else if (activeObjects.get(1).getState()) {
           activeObjects.get(1).forceState();
           activeObjects.get(0).forceState();
           action = "You died";
           //System.out.println("You died");
           deathSound.play();
           if (score > bestScore){
               bestScore = score;
               saveHighScore(bestScore);
           }
           score = 0;
       }

    }
    
    @Override
    public void dispose() {
        batch.dispose();
    }
}