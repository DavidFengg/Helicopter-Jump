package david.feng.com.myfirstgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Player extends GameObject {
    private Bitmap spritesheet;
    private int score;
    private boolean up;
    private boolean playing;
    private Animation animation = new Animation();
    private long startTime;

    public Player(Bitmap res, int w, int h, int numFrames) {
        x = 100;
        y = (int)GamePanel.HEIGHT/2;
        dy = 0;
        height = h;
        width = w;

        Bitmap[] image = new Bitmap[numFrames];
        spritesheet = res;

        for (int x = 0; x < image.length; x++) {
            image[x] = Bitmap.createBitmap(spritesheet, x*width, 0, width, height);
        }

        animation.setFrames(image);
        animation.setDelay(10);
        startTime = System.nanoTime();
    }

    public void setUp(boolean b){up = b;}

    public void update() {
        long elapsed = (System.nanoTime()-startTime)/1000000;
        if (elapsed > 100) {
            score++;
            startTime = System.nanoTime();
        }
        animation.update();

        if (up) {
            dy -= 1;
        }
        else {
            dy += 1;
        }

        if (dy > 4) {
            dy = 4;
        }

        if (dx < -4) {
            dx = -4;
        }

        y += dy*2;

        if (y < 0  || y > 640) {
            setPlaying(false);
        }
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(animation.getImage(), x, y, null);
    }

    public int getScore() {
        return score;
    }

    public boolean getPlaying() {
        return playing;
    }

    public void setPlaying(boolean b) {
        playing = b;
    }

    public void resetDY() {
        dy = 0;
    }
    public void resetScore() {
        score = 0;
    }
}
