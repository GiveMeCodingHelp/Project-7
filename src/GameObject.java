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

    public int getWidth(){
        return width;
    }

    public void move(double deltaTime){

    }

    public void draw(SpriteBatch batch) {
        batch.draw(frames.get(frame), (int) x, (int) y, width, height);
    }
}
