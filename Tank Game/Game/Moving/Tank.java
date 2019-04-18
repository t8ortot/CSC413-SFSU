package Game.Moving;

import java.awt.*;
import java.io.IOException;
import Game.Mechanics.*;
import Game.Static.Walls;

/**
 * Tank object
 *
 * @author Zachary Martin & James Clark
 */
public class Tank extends ImageObject {

    //degree of rotation
    private int degree = 0;
    //frame being accessed if gif
    private int index = 0;
    //new x-position
    private int updateX = 0;
    //new y-position
    private int updateY = 0;
    //tank movement speed
    private final int SPEED = 10;
    //array of angles
    private final int[] ANGLE = new int[60];
    //Images of tank angles
    private final ImageParser TANK_IMAGES;

    /**
     * Constructor
     *
     * @param img
     * @throws java.io.IOException
     */
    public Tank(String img) throws IOException {

        super(img);
        this.TANK_IMAGES = new ImageParser(img);
    }

    /**
     * Checks if colliding with wall
     *
     * @param wall layout
     * @return true if colliding
     */
    public boolean wallCollision(Walls wall) {

        try {

            wall.buildWallLayout();
        } catch (IOException e) {

        }

        //makes tank rectangle
        Rectangle tank = new Rectangle(this.updateX, this.updateY, super.getWidth() - 15, super.getHeight() - 15);
        for (int row = 0; row < wall.getMapHeight(); row++) {

            for (int col = 0; col < wall.getMapWidth(); col++) {

                if (wall.getWallLayout()[row][col].equals("1") || wall.getWallLayout()[row][col].equals("2")) {

                    //make wall rectangle
                    Rectangle walls = new Rectangle(col * wall.getWidth(), row * wall.getHeight(),
                            wall.getWidth() - 5, wall.getHeight() - 5);

                    //if the two intersect
                    if (tank.intersects(walls)) {

                        return true;
                    }

                }

            }

        }

        return false;
    }

    /**
     * Checks if colliding with another tank
     *
     * @param tank2 other tank
     * @return true if colliding
     */
    public boolean tankCollision(Tank tank2) {

        //tank rectangle
        Rectangle tank = new Rectangle(this.updateX, this.updateY, super.getWidth() - 15, super.getHeight() - 15);

        //second tank rectangle
        Rectangle secondTank = new Rectangle(tank2.getImageX(), tank2.getImageY(),
                tank2.getWidth() - 15, tank2.getHeight() - 15);

        //return if intersecting
        return tank.intersects(secondTank);
    }

    /**
     * determines if colliding with power up
     *
     * @param wall layout
     * @param player player being checked
     * @return colliding
     */
    public boolean powerUpCollision(Walls wall, Player player) {

        try {

            wall.buildWallLayout();
        } catch (IOException e) {

        }

        Rectangle tank = new Rectangle(this.updateX, this.updateY, getWidth() - 10, getHeight() - 15);
        for (int row = 0; row < wall.getMapHeight(); row++) {

            for (int col = 0; col < wall.getMapWidth(); col++) {

                if (wall.getWallLayout()[row][col].equals("5")) {

                    Rectangle walls = new Rectangle(col * wall.getWidth(), row * wall.getHeight(),
                            wall.getWidth() - 15, wall.getHeight() - 15);

                    if (tank.intersects(walls) && player.getHealth() != 100) {

                        wall.getWallLayout()[row][col] = "0";

                        wall.setUpdatedWallMap(wall.getWallLayout());
                        return true;
                    }

                }

            }

        }

        return false;
    }

    /**
     * Rotates tank image clockwise
     */
    public void rotateClockwise() {

        if (this.index == 0) {

            this.index = 59;
        } else {

            this.index--;
        }

        try {

            super.image = this.TANK_IMAGES.getFrame(this.index);
        } catch (Exception e) {

            this.index = 59;
            super.image = this.TANK_IMAGES.getFrame(this.index);
        }

    }

    /**
     * Rotates tank image counter-clockwise
     */
    public void rotateCounterClockwise() {

        if (this.index == 59) {

            this.index = 0;
        } else {

            this.index++;
        }

        try {

            super.image = this.TANK_IMAGES.getFrame(this.index);
        } catch (Exception e) {

            this.index = 0;
            super.image = this.TANK_IMAGES.getFrame(this.index);
        }

    }

    /**
     * Moves tank backwards
     */
    public void reverseMovement() {

        this.updateX = this.getImageX() - (int) (Math.cos(Math.toRadians(this.ANGLE[this.index])) * SPEED);
        this.updateY = this.getImageY() + (int) (Math.sin(Math.toRadians(this.ANGLE[this.index])) * SPEED);
    }

    /**
     * Moves tank forward
     */
    public void forwardMovement() {

        this.updateX = this.getImageX() + (int) (Math.cos(Math.toRadians(this.ANGLE[this.index])) * SPEED);
        this.updateY = this.getImageY() - (int) (Math.sin(Math.toRadians(this.ANGLE[this.index])) * SPEED);
    }

    /**
     * Initializes array of degrees
     */
    public void setDegrees() {

        for (int i = 0; i < this.ANGLE.length; i++) {

            this.ANGLE[i] = this.degree;
            this.degree += 6;
        }

    }

    /**
     * Draws tank
     *
     * @param graphic to be drawn on
     */
    @Override
    public void doDrawing(Graphics graphic) {

        graphic.drawImage(this.TANK_IMAGES.getFrame(this.index), super.x, super.y, null);
    }

    /**
     * Gets tanks x-position
     *
     * @param width of window
     * @return x-position
     */
    public int getTankXPosition(int width) {

        int xPos = this.getImageX() - width / 4;

        if (xPos < 0) {

            xPos = 0;
        }

        if (xPos > (width / 2)) {

            xPos = (width / 2);
        }

        return xPos;
    }

    /**
     * Gets tanks y-position
     *
     * @param height window height
     * @param pHeight panel height
     * @return y-position
     */
    public int getTankYPosition(int height, int pHeight) {

        int yPos = this.getImageY() - pHeight / 2;

        if (yPos < 0) {

            yPos = 0;
        }

        if (yPos > (height - pHeight)) {

            yPos = (height - pHeight);
        }

        return yPos;
    }

    //Gets updated x-position
    public int updateX() {

        return this.updateX;
    }

    //Gets updated y-position
    public int updateY() {

        return this.updateY;
    }

    //Gets x-position to be fired from
    public int flashX() {

        return super.x - 15 + this.getWidth() / 2 + (int) ((this.getWidth() * (Math.cos(Math.toRadians(50)))) * (Math.cos(Math.toRadians(this.ANGLE[this.index]))));

    }

    //Gets y-position to be fired from
    public int flashY() {

        return super.y - 15 + this.getHeight() / 2 - (int) ((this.getHeight() * (Math.cos(Math.toRadians(50)))) * (Math.sin(Math.toRadians(this.ANGLE[this.index]))));

    }

    //Gets x-position to be fired from
    public int fireX() {

        return super.x - 15 + this.getWidth() / 2 + (int) ((this.getWidth() * .75 * (Math.cos(Math.toRadians(50)))) * (Math.cos(Math.toRadians(this.ANGLE[this.index]))));

    }

    //Gets y-position to be fired from
    public int fireY() {

        return super.y - 15 + this.getHeight() / 2 - (int) ((this.getHeight() * .75 * (Math.cos(Math.toRadians(50)))) * (Math.sin(Math.toRadians(this.ANGLE[this.index]))));

    }

    /**
     * Gets frame being accessed in tank GIF
     *
     * @return frame number
     */
    protected int getIndex() {

        return this.index;
    }

    /**
     * Sets frame to be accessed
     *
     * @param index frame to access in GIF
     * @return number
     */
    protected int setIndex(int index) {

        this.index = index;
        return index;
    }

    /**
     * Gets angle tank is in
     *
     * @return angle tank is in
     */
    protected int[] getAngle() {

        return this.ANGLE;
    }

}
