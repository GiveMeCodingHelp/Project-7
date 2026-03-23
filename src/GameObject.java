import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class GameObject {
    protected double x;
    protected double y;
    protected int width;
    protected int height;
    protected ArrayList<Texture> frames;
    protected int frame = 0;

    public GameObject(double x, double y, int width, int height, String[] imagePaths) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.frames = new ArrayList<>();
        for (String path : imagePaths){
            frames.add(new Texture(path));
        }
    }

    public int getFrameCount(){
        return frames.size();
    }

    public void incrementFrames(){
        if (frame < frames.size() - 1){
            frame++;
        } else {
            frame = 0;
        }
    }

    //to be overwritten by subclasses
    public boolean getState(){ return false; }

    public void forceState(){}


    public void move(double deltaTime){

    }

    public void draw(SpriteBatch batch) {
        batch.draw(frames.get(frame), (int) x, (int) y, width, height);
    }
}
