/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.Static;

import java.awt.Graphics;
import java.io.IOException;
import Game.Mechanics.ImageObject;

/**
 * Health pack object
 *
 * @author Zachary Martin & James Clark
 */
public class HealthPack extends ImageObject {

    //wall layout
    private final Walls walls;

    /**
     * Constructor
     *
     * @param wall layout
     * @throws IOException
     */
    public HealthPack(Walls wall) throws IOException {

        super("Resources/HealthPack.gif");
        this.walls = wall;
    }

    /**
     * Draws health pack
     *
     * @param graphic to be drawn on
     */
    @Override
    public void doDrawing(Graphics graphic) {

        for (int row = 0; row < this.walls.getMapHeight(); row++) {

            for (int column = 0; column < this.walls.getMapWidth(); column++) {

                if (this.walls.getWallLayout()[column][row].equals("5")) {

                    graphic.drawImage(super.image, row * super.image.getWidth(), column * super.image.getHeight(), null);
                }

            }

        }

    }

}
