package Game.Mechanics;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import javax.imageio.*;
import javax.imageio.stream.ImageInputStream;

/**
 * Splits GIF images into individual frames
 *
 * @author Zachary Martin & James Clark
 */
public class ImageParser {

    //path of image file
    private final String FILE_PATH;
    //array of frames in image
    private final ArrayList<BufferedImage> frames = new ArrayList<>();

    /**
     * Constructor
     *
     * @param file path of image
     * @throws IOException
     */
    public ImageParser(String file) throws IOException {

        this.FILE_PATH = file;

        //loads images of gif into frames array
        this.loadImages();
    }

    /**
     * Gets specified frame from array
     *
     * @param frame frame number to get
     * @return frame requested
     */
    public BufferedImage getFrame(int frame) {

        return this.frames.get(frame);
    }

    /**
     * Gets number of frames in image
     *
     * @return number of frames
     */
    protected int frameCount() {

        return this.frames.size();
    }

    /**
     * Method to split GIFs into individual frames Algorithm found here:
     * https://stackoverflow.com/a/32119229
     *
     * @throws IOException
     */
    private void loadImages() throws IOException {

        try {

            ImageReader reader = ImageIO.getImageReadersByFormatName("gif").next();
            File input = new File(this.FILE_PATH);
            ImageInputStream stream = ImageIO.createImageInputStream(input);
            reader.setInput(stream);

            int count = reader.getNumImages(true);
            for (int index = 0; index < count; index++) {

                BufferedImage frame = reader.read(index);

                //adds frame into array of all frames
                this.frames.add(frame);
            }

        } catch (IOException e) {

        }

    }

}
