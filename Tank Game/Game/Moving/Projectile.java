package Game.Moving;

import java.awt.*;
import java.io.IOException;
import java.util.logging.*;
import Game.Mechanics.*;
import Game.Static.Walls;

/**
 * Projectile object
 *
 * @author Zachary Martin & James Clark
 */
public class Projectile extends Animation {

    //Large explosion image path
    private final String L_EXPLOSION_IMG = "Resources/Explosion_large.gif";
    //Large explosion sound effect path
    private final String L_EXPLOSION_SFX = "Resources/Explosion_large.wav";
    //frame that is accessed
    private int index;
    //speed of projectile
    private int speed = 25;
    //dimensions of bullet
    private int bulletWidth = 10;
    private int bulletHeight = 10;
    //large explosion frames
    private ImageParser largeExplosion;
    //bullet frames
    private ImageParser bulletFrames;
    //tanks
    private Tank tank1, tank2;
    //wall layout
    private Walls wall;
    //game object
    private Game game;
    //players
    private Player player1;
    private Player player2;
    //sound player
    private Sound sound = new Sound();

    /**
     * Constructor
     *
     * @param game game object to add animation
     * @param tank1 shooting tank
     * @param tank2 enemy tank
     * @param player1 shooting player
     * @param player2 enemy player
     * @param wall wall layout
     * @param item object being animated
     * @param x-position
     * @param y-position
     */
    public Projectile(Game game, Tank tank1, Tank tank2, Player player1, Player player2,
            Walls wall, ImageParser item, int x, int y) {

        super(item, x, y, 0);
        this.game = game;
        this.player1 = player1;
        this.player2 = player2;
        this.bulletFrames = item;
        this.index = tank1.getIndex();
        this.tank1 = tank1;
        this.tank2 = tank2;
        this.wall = wall;

        try {

            this.largeExplosion = new ImageParser(this.L_EXPLOSION_IMG);
        } catch (IOException ex) {

        }

    }

    /**
     * draws projectile
     *
     * @param graphics
     */
    @Override
    public void doDrawing(Graphics graphics) {

        //if animation is not done
        if (!this.isDone()) {

            //update coordinates
            this.forwardMove();
            //draw bullet
            graphics.drawImage(this.bulletFrames.getFrame(this.index), super.X, super.Y, null);
            try {

                //if it collides with a wall
                if (this.wallCollision()) {

                    this.hitWall();
                    this.setDone();
                }

            } catch (IOException ex) {

                Logger.getLogger(Projectile.class.getName()).log(Level.SEVERE, null, ex);
            }

            //if it collides with tank
            if (this.collideWithTank()) {

                this.hitTank();
                this.setDone();
            }

        }

    }

    /**
     * determines if hitting wall
     *
     * @return hitting wall
     * @throws IOException
     */
    private boolean wallCollision() throws IOException {

        this.wall.buildWallLayout();
        Rectangle bullets = new Rectangle(super.X, super.Y, this.bulletWidth, this.bulletHeight);
        for (int row = 0; row < this.wall.getMapHeight(); row++) {

            for (int col = 0; col < this.wall.getMapWidth(); col++) {

                if (this.wall.getWallLayout()[row][col].equals("1") || this.wall.getWallLayout()[row][col].equals("2")) {

                    Rectangle walls = new Rectangle(col * this.wall.getWidth(), row * this.wall.getHeight(),
                            this.wall.getWidth(), this.wall.getHeight());
                    if (bullets.intersects(walls)) {

                        return true;
                    }

                }

            }

        }

        return false;
    }

    /**
     * destroys walls
     */
    private void hitWall() {

        try {

            this.wall.buildWallLayout();
        } catch (IOException e) {

        }

        Rectangle bullets = new Rectangle(super.X, super.Y, this.bulletWidth, this.bulletHeight);

        for (int row = 0; row < this.wall.getMapHeight(); row++) {

            for (int col = 0; col < this.wall.getMapWidth(); col++) {

                if (this.wall.getWallLayout()[row][col].equals("2")) {

                    Rectangle walls = new Rectangle(col * this.wall.getWidth(), row * this.wall.getHeight(),
                            this.wall.getWidth(), this.wall.getHeight());

                    if (bullets.intersects(walls)) {

                        this.wall.getWallLayout()[row][col] = "0";

                        this.wall.setUpdatedWallMap(this.wall.getWallLayout());
                    }

                }

            }

        }

    }

    /**
     * determines if colliding with tank
     *
     * @return colliding
     */
    private boolean collideWithTank() {

        Rectangle bullets = new Rectangle(super.X, super.Y, this.bulletWidth, this.bulletHeight);
        Rectangle tank_aRec = new Rectangle(this.tank1.getImageX(), this.tank1.getImageY(), this.tank1.getWidth() - 10, this.tank1.getHeight() - 10);
        Rectangle tank_bRec = new Rectangle(this.tank2.getImageX(), this.tank2.getImageY(), this.tank2.getWidth() - 10, this.tank2.getHeight() - 10);

        return (bullets.intersects(tank_aRec) || bullets.intersects(tank_bRec));
    }

    /**
     * tank hit actions
     */
    private void hitTank() {

        Rectangle bullets = new Rectangle(super.X, super.Y, this.bulletWidth, this.bulletHeight);
        Rectangle tank_aRec = new Rectangle(this.tank1.getImageX(), this.tank1.getImageY(), this.tank1.getWidth() - 10, this.tank1.getHeight() - 10);
        Rectangle tank_bRec = new Rectangle(this.tank2.getImageX(), this.tank2.getImageY(), this.tank2.getWidth() - 10, this.tank2.getHeight() - 10);

        if (bullets.intersects(tank_aRec)) {

            this.player1.hitTank();
            if (this.player1.dead()) {

                explosion(this.tank1);
                this.sound.playOnce(this.L_EXPLOSION_SFX);
                this.player1.setPosition();
                this.tank1.setIndex(0);
            }

        }

        if (bullets.intersects(tank_bRec)) {

            this.player2.hitTank();
            if (this.player2.dead()) {

                explosion(this.tank2);
                this.sound.playOnce(this.L_EXPLOSION_SFX);
                this.player2.setPosition();
                this.tank2.setIndex(0);
            }

        }

    }

    /**
     * forward movement
     */
    private void forwardMove() {

        super.X += (int) (Math.cos(Math.toRadians(this.tank1.getAngle()[this.index])) * this.speed);
        super.Y -= (int) (Math.sin(Math.toRadians(this.tank1.getAngle()[this.index])) * this.speed);
    }

    /**
     * explosion when tank dies
     *
     * @param deadTank
     */
    private void explosion(Tank deadTank) {

        Animation temp = new Animation(this.largeExplosion, deadTank.getImageX(), deadTank.getImageY(), 3);
        this.game.addAnimation(temp);
    }

}
