import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameObject {
    private double x;
    private double y;
    private int width;
    private int height;
    private Texture image;

    public GameObject(double x, double y, int width, int height, String imagePath) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.image = new Texture(imagePath);
    }

    public void move(double deltaTime){

    }

    public void draw(SpriteBatch batch) {
        batch.draw(image, (int) x, (int) y, width, height);
    }
}
