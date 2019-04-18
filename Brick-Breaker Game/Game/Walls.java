/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author James Clark
 */
public class Walls {

    //Map layout file path
    private String mapLayout;
    //Map reader
    private static BufferedReader mapReader;
    //Block 1
    private final ImageObject block1;
    //Block 2
    private final ImageObject block2;
    //Block 3
    private final ImageObject block3;
    //Block 4
    private final ImageObject block4;
    //Block 5
    private final ImageObject block5;
    //Block 6
    private final ImageObject block6;
    //Block 7
    private final ImageObject block7;
    //Block that gives life
    private final ImageObject lifeBlock;
    //Unbreakable block
    private final ImageObject solidBlock;
    //Block that splits projectile into two
    private final ImageObject splitterBlock;
    //Wall
    private final ImageObject wall;

    //Map layout dimensions
    private final int MAP_WIDTH = 32;
    private final int MAP_HEIGHT = 24;

    //Map layout array
    private String[][] mapArray = new String[this.MAP_HEIGHT][this.MAP_WIDTH];

    public Walls() throws IOException {

        //Creates all block objects
        this.block1 = new ImageObject("Resources/Block1.gif");
        this.block2 = new ImageObject("Resources/Block2.gif");
        this.block3 = new ImageObject("Resources/Block3.gif");
        this.block4 = new ImageObject("Resources/Block4.gif");
        this.block5 = new ImageObject("Resources/Block5.gif");
        this.block6 = new ImageObject("Resources/Block6.gif");
        this.block7 = new ImageObject("Resources/Block7.gif");
        this.lifeBlock = new ImageObject("Resources/Block_life.gif");
        this.solidBlock = new ImageObject("Resources/Block_solid.gif");
        this.splitterBlock = new ImageObject("Resources/Block_split.gif");
        this.wall = new ImageObject("Resources/Wall.gif");

        //sets map
        this.mapLayout = "Resources/Map1.txt";
        Walls.mapReader = new BufferedReader(new FileReader(this.mapLayout));

        //creates map
        this.buildWallLayout();

    }

    public void changeLevel(String level) throws FileNotFoundException, IOException {
        this.mapLayout = "Resources/Map" + level + ".txt";
        Walls.mapReader = null;
        Walls.mapReader = new BufferedReader(new FileReader(this.mapLayout));
        this.buildWallLayout();
        this.setUpdatedWallMap(mapArray);
    }

    private void buildWallLayout() throws IOException {

        int row = 0;
        String index;
        index = Walls.mapReader.readLine();
        while (index != null) {

            for (int i = 0; i < this.mapArray[row].length; i++) {

                this.mapArray[row][i] = String.valueOf(index.charAt(i));
            }

            row++;
            index = Walls.mapReader.readLine();
        }

    }

    public int getWidth(String brick) {
        if (brick.equals("0") || brick.equals(" ")) {
            return 20;
        } else {
            return 40;
        }

    }

    public boolean isEmpty() {
        for (int i = 0; i < this.MAP_HEIGHT; i++) {
            for (int j = 0; j < this.MAP_WIDTH; j++) {
                if (!((this.mapArray[i][j].equals(" ")) || (this.mapArray[i][j].equals("B")))) {
                    return false;
                }
            }
        }
        return true;
    }

    public int getHeight() {
        return 20;
    }

    /**
     * Gets wall layout
     *
     * @return layout array
     */
    public String[][] getWallLayout() {

        return this.mapArray;
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

        this.mapArray = newWallMap;
    }

    /**
     * draws walls
     *
     * @param graphic drawn onto
     */
    public void doDrawing(Graphics graphic) {

        for (int i = 0; i < this.MAP_WIDTH; i++) {

            for (int j = 0; j < this.MAP_HEIGHT; j++) {

                switch (this.mapArray[j][i]) {
                    case "0":
                        graphic.drawImage(this.wall.image, i * this.wall.getWidth(), j * this.wall.getHeight(), null);
                        break;
                    case "1":
                        graphic.drawImage(this.block1.image, i * this.block1.getWidth() / 2, j * this.block1.getHeight(), null);
                        break;
                    case "2":
                        graphic.drawImage(this.block2.image, i * this.block2.getWidth() / 2, j * this.block2.getHeight(), null);
                        break;
                    case "3":
                        graphic.drawImage(this.block3.image, i * this.block3.getWidth() / 2, j * this.block3.getHeight(), null);
                        break;
                    case "4":
                        graphic.drawImage(this.block4.image, i * this.block4.getWidth() / 2, j * this.block4.getHeight(), null);
                        break;
                    case "5":
                        graphic.drawImage(this.block5.image, i * this.block5.getWidth() / 2, j * this.block5.getHeight(), null);
                        break;
                    case "6":
                        graphic.drawImage(this.block6.image, i * this.block6.getWidth() / 2, j * this.block6.getHeight(), null);
                        break;
                    case "7":
                        graphic.drawImage(this.block7.image, i * this.block7.getWidth() / 2, j * this.block7.getHeight(), null);
                        break;
                    case "S":
                        graphic.drawImage(this.splitterBlock.image, i * this.block6.getWidth() / 2, j * this.block6.getHeight(), null);
                        break;
                    case "B":
                        graphic.drawImage(this.solidBlock.image, i * this.solidBlock.getWidth() / 2, j * this.solidBlock.getHeight(), null);
                        break;
                    case "L":
                        graphic.drawImage(this.lifeBlock.image, i * this.block6.getWidth() / 2, j * this.block6.getHeight(), null);
                        break;
                    case " ":
                        break;
                    default:
                        System.out.println("Character not recognized in mapLayout!");
                        break;
                }

            }

        }

    }

}
