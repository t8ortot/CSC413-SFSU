package Game.Mechanics;

import java.awt.Graphics;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;

/**
 * Handles images that are game-objects
 *
 * @author Zachary Martin & James Clark
 */
public class ImageObject {

    //position to be drawn at
    protected int x = 0;
    protected int y = 0;
    //image to be drawn
    protected BufferedImage image;

    /**
     * Constructor
     *
     * @param fileLoc path of image
     * @throws IOException
     */
    public ImageObject(String fileLoc) throws IOException {

        this.image = ImageIO.read(new FileInputStream(fileLoc));
    }

    /**
     * Get x-position to be drawn at
     *
     * @return x-position
     */
    public int getImageX() {

        return this.x;
    }

    /**
     * Get y-position to be drawn at
     *
     * @return y-position
     */
    public int getImageY() {

        return this.y;
    }

    /**
     * Gets width of image
     *
     * @return width
     */
    public int getWidth() {

        return this.image.getWidth();
    }

    /**
     * Gets height of image
     *
     * @return height
     */
    public int getHeight() {

        return this.image.getHeight();
    }

    /**
     * draw image
     *
     * @param graphics drawn onto
     */
    public void doDrawing(Graphics graphics) {

        graphics.drawImage(this.image, this.x, this.y, null);
    }

    /**
     * Sets x-position to be drawn at
     *
     * @param x-position
     */
    protected void setImageX(int x) {

        this.x = x;
    }

    /**
     * Sets y-position to be drawn at
     *
     * @param y-position
     */
    protected void setImageY(int y) {

        this.y = y;
    }

}
