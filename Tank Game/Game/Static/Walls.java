package Game.Static;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import Game.Mechanics.ImageObject;

/**
 * Wall Layout
 *
 * @author Zachary Martin & James Clark
 */
public class Walls extends ImageObject {

    //Map layout file path
    private final String MAP_LAYOUT = "Resources/Map.txt";
    //Indestructible wall image path
    private final String INDESTRUCTIBLE_WALL_IMG = "Resources/Wall2.gif";
    //Map layout reader
    private static BufferedReader mapReader;
    //Indestructible wall image
    private final BufferedImage INDESTRUCTIBLE_WALL;
    //Map layout dimensions
    private final int MAP_HEIGHT = 40;
    private final int MAP_WIDTH = 40;
    //Map layout array
    private String[][] wallLayout = new String[this.MAP_HEIGHT][this.MAP_WIDTH];

    /**
     * Constructor
     *
     * @throws IOException
     */
    public Walls() throws IOException {

        super("Resources/Wall1.gif");

        //map layout
        Walls.mapReader = new BufferedReader(new FileReader(this.MAP_LAYOUT));
        //industructable wall image
        this.INDESTRUCTIBLE_WALL = ImageIO.read(new File(this.INDESTRUCTIBLE_WALL_IMG));
    }

    /**
     * Builds wall layout array
     *
     * @throws IOException
     */
    public void buildWallLayout() throws IOException {

        int row = 0;
        String index;
        index = Walls.mapReader.readLine();
        while (index != null) {

            for (int i = 0; i < this.wallLayout[row].length; i++) {

                this.wallLayout[row][i] = String.valueOf(index.charAt(i));
            }

            row++;
            index = Walls.mapReader.readLine();
        }

    }

    /**
     * Gets wall layout
     *
     * @return layout array
     */
    public String[][] getWallLayout() {

        return this.wallLayout;
    }

    /**
     * gets map height
     *
     * @return map height
     */
    public int getMapHeight() {

        return this.MAP_HEIGHT;
    }

    /**
     * gets map width
     *
     * @return map width
     */
    public int getMapWidth() {

        return this.MAP_WIDTH;
    }

    /**
     * Sets updated map
     *
     * @param newWallMap new map
     */
    public void setUpdatedWallMap(String[][] newWallMap) {

        this.wallLayout = newWallMap;
    }

    /**
     * draws walls
     *
     * @param graphic drawn onto
     */
    @Override
    public void doDrawing(Graphics graphic) {

        for (super.x = 0; super.x < this.MAP_WIDTH; super.x++) {

            for (super.y = 0; super.y < this.MAP_HEIGHT; super.y++) {

                if (this.wallLayout[super.y][super.x].equals("1")) {

                    graphic.drawImage(this.INDESTRUCTIBLE_WALL, super.x * this.INDESTRUCTIBLE_WALL.getWidth(), super.y * this.INDESTRUCTIBLE_WALL.getHeight(), null);
                } else if (this.wallLayout[super.y][super.x].equals("2")) {

                    graphic.drawImage(super.image, this.x * super.image.getWidth(), this.y * super.image.getHeight(), null);
                }

            }

        }

    }

}
