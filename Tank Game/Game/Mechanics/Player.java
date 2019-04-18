package Game.Mechanics;

import java.awt.*;
import Game.Moving.Tank;
import Game.Static.Walls;

/**
 * Handles all information about the player
 *
 * @author Zachary Martin & James Clark
 */
public class Player {

    //Location to put tank on map (3 or 4)
    private final String MAP_LOCATION;
    //Health of player
    private int health = 100;
    //Number of lives
    private int lives = 3;
    //True if player has lost
    private boolean endGame = false;
    //Tank object
    private final Tank TANK;
    //Wall object
    private final Walls WALL_LAYOUT;

    /**
     * Constructor
     *
     * @param tank tank object of the player
     * @param walls wall layout
     * @param tankLocation spawn location
     */
    public Player(Tank tank, Walls walls, String tankLocation) {

        this.TANK = tank;
        this.WALL_LAYOUT = walls;
        this.MAP_LOCATION = tankLocation;
    }

    /**
     * Deduct health by 10 when hit
     */
    public void hitTank() {

        this.health -= 25;
    }

    public int getHealth() {

        return this.health;
    }

    /**
     * Returns if tank is dead
     *
     * @return if player is dead
     */
    public boolean dead() {

        return this.health == 0;
    }

    /**
     * Sets initial position of the tank
     */
    public void setPosition() {

        for (int row = 0; row < this.WALL_LAYOUT.getMapHeight(); row++) {

            for (int column = 0; column < this.WALL_LAYOUT.getMapWidth(); column++) {

                // Set the initial position to location indicated in map file (3 or 4)
                if (this.WALL_LAYOUT.getWallLayout()[row][column].equals(this.MAP_LOCATION)) {

                    this.TANK.setImageX(column * this.WALL_LAYOUT.getWidth());
                    this.TANK.setImageY(row * this.WALL_LAYOUT.getHeight());
                }

            }

        }

    }

    /**
     * Draws life dots
     *
     * @param graphic to be drawn on
     * @param x-position
     * @param y-position
     */
    protected void showLives(Graphics graphic, int x, int y) {

        //width between dots
        int width = 10;
        //color of dots
        graphic.setColor(Color.YELLOW);

        //draws all dots
        for (int i = 0; i < this.lives; i++) {

            graphic.fillOval(x + i * width, y, 8, 8);
        }

        //if no lives are left, end game
        if (this.lives == 0) {

            this.endGame = true;

        }

    }

    /**
     * Draws health bar
     *
     * @param graphic to be drawn on
     * @param x-position
     * @param y-position
     */
    protected void showHealthBar(Graphics graphic, int x, int y) {

        //if health is above 50, bar is green
        if (this.health > 50) {

            graphic.setColor(Color.green);
            graphic.fillRect(x, y, this.health, 30);

            //if health is between 20 and 50, bar is yellow
        } else if (this.health > 25 && this.health <= 50) {

            graphic.setColor(Color.yellow);
            graphic.fillRect(x, y, this.health, 30);

            //if health is between 0 and 20, bar is red
        } else if (this.health > 0 && this.health <= 25) {

            graphic.setColor(Color.red);
            graphic.fillRect(x, y, this.health, 30);

            //if health is less than or equal to 0
        } else {

            //deduct a life
            this.lives--;
            //reset health
            this.health = 100;
        }

    }

    /**
     * When health pack is picked up, heal tank back to 100
     */
    protected void healthPack() {

        this.health = 100;
    }

    /**
     * Returns if game has ended
     *
     * @return if game ends
     */
    protected boolean gameEnd() {

        return this.endGame;
    }

}
