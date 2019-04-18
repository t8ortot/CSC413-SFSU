package Game.Mechanics;

import java.awt.event.*;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import Game.Moving.Projectile;

/**
 * Controls key-press and key-release actions
 *
 * @author Zachary Martin & James Clark
 */
public class Controls extends Game implements KeyListener {

    //Bullet image path
    private final String BULLET_IMG = "Resources/Shell.gif";
    //Small explosion image path
    private final String S_EXPLOSION_IMG = "Resources/Explosion_small.gif";
    //Small explosion sound effect
    private final String S_EXPLOSION_SFX = "Resources/Explosion_small.wav";
    //Explosion object
    private ImageParser smallExplosion;
    //Bullet object
    private ImageParser bullet;
    //Used to play theme music and explosions
    private final Sound SOUND = new Sound();
    //Limits shot frequency
    private int shotClockA = 0;
    private int shotClockB = 0;
    //Player 1 controls
    private boolean wPressed, sPressed, aPressed, dPressed, cPressed;
    //Player 2 controls
    private boolean iPressed, kPressed, jPressed, lPressed, nPressed;

    /**
     * Constructor
     */
    public Controls() {

        //Game
        super();

        //Starts Key Listener Thread
        this.startListening();

        //starts theme music
        SOUND.playLoop("Resources/MOP.mid");
        try {

            //sets image of bullet
            this.bullet = new ImageParser(BULLET_IMG);
            //sets image of explosion
            this.smallExplosion = new ImageParser(S_EXPLOSION_IMG);

        } catch (IOException e) {

            System.out.println("Shell.gif and Explosion_small.gif not found");
        }

    }

    /**
     * Executes methods corresponding to keyboard inputs
     */
    @Override
    public void run() {

        //sets degrees of both tanks
        this.tankA.setDegrees();
        this.tankB.setDegrees();

        do {

            //if the game has not ended
            if (!this.playerA.gameEnd() && !this.playerB.gameEnd()) {

                //Player A's Buttons:
                //Player A's up (forawrd)
                if (this.wPressed) {

                    this.tankA.forwardMovement();

                    //if tank is not colliding with anything
                    if (!((this.tankA.wallCollision(this.walls)) || (this.tankA.tankCollision(this.tankB)))) {

                        this.tankA.setImageX(this.tankA.updateX());
                        this.tankA.setImageY(this.tankA.updateY());
                    }

                    //if tank is colliding with power up (health pack)
                    if (this.tankA.powerUpCollision(this.walls, this.playerA)) {

                        //the player can only pick up a health pack when damaged
                        if (this.playerA.getHealth() != 100) {

                            this.playerA.healthPack();
                        }

                    }

                }

                //Player A's down (backward)
                if (this.sPressed) {

                    this.tankA.reverseMovement();

                    //if tank is not colliding with anything
                    if (!((this.tankA.wallCollision(this.walls)) || (this.tankA.tankCollision(this.tankB)))) {

                        this.tankA.setImageX(this.tankA.updateX());
                        this.tankA.setImageY(this.tankA.updateY());
                    }

                    //if tank is colliding with power up (health pack)
                    if (this.tankA.powerUpCollision(this.walls, this.playerA)) {

                        if (this.playerA.getHealth() != 100) {

                            this.playerA.healthPack();
                        }

                    }

                }

                //Player A's left (rotate counter-clockwise)
                if (this.aPressed) {

                    this.tankA.rotateCounterClockwise();
                }

                //Player A's right (rotate clockwise)
                if (this.dPressed) {

                    this.tankA.rotateClockwise();
                }

                //Player A's fire button
                if (this.cPressed && this.shotClockA <= 0) {

                    //explosion animation
                    super.addAnimation(new Animation(this.smallExplosion, this.tankA.flashX(), this.tankA.flashY(), 0));

                    //bullet animation
                    super.addAnimation(new Projectile(this, this.tankA, this.tankB, this.playerA, this.playerB, this.walls, this.bullet, this.tankA.fireX(), this.tankA.fireY()));

                    //plays shooting sound
                    this.SOUND.playOnce(S_EXPLOSION_SFX);

                    //player cannot shoot for specific number of ticks
                    this.shotClockA = 15;
                }

                //Player B's Buttons:
                //Player B's up (forawrd)
                if (this.iPressed) {

                    this.tankB.forwardMovement();

                    //if tank is not colliding with anything
                    if (!((this.tankB.wallCollision(this.walls)) || (this.tankB.tankCollision(this.tankA)))) {

                        this.tankB.setImageX(this.tankB.updateX());
                        this.tankB.setImageY(this.tankB.updateY());
                    }

                    //if tank is colliding with power up (health pack)
                    if (this.tankB.powerUpCollision(this.walls, this.playerB)) {

                        if (this.playerB.getHealth() != 100) {

                            this.playerB.healthPack();
                        }

                    }

                }

                //Player B's down (backward) 
                if (this.kPressed) {

                    this.tankB.reverseMovement();

                    //if tank is not colliding with anything
                    if (!((this.tankB.wallCollision(this.walls)) || (this.tankB.tankCollision(this.tankA)))) {

                        this.tankB.setImageX(this.tankB.updateX());
                        this.tankB.setImageY(this.tankB.updateY());
                    }

                    //if tank is colliding with power up (health pack)
                    if (this.tankB.powerUpCollision(this.walls, this.playerB)) {

                        if (this.playerB.getHealth() != 100) {

                            this.playerB.healthPack();
                        }

                    }

                }

                //Player B's left (rotate counter-clockwise)
                if (this.jPressed) {

                    this.tankB.rotateCounterClockwise();
                }

                //Player B's right (rotate clockwise)
                if (this.lPressed) {

                    this.tankB.rotateClockwise();
                }

                //Player B's fire button
                if (this.nPressed && this.shotClockB <= 0) {

                    //explosion animation
                    super.addAnimation(new Animation(this.smallExplosion, this.tankB.fireX(), this.tankB.fireY(), 0));

                    //bullet animation
                    super.addAnimation(new Projectile(this, this.tankB, this.tankA, this.playerB, this.playerA, this.walls, this.bullet, this.tankB.fireX(), this.tankB.fireY()));

                    //plays shooting sound
                    this.SOUND.playOnce(S_EXPLOSION_SFX);

                    //player cannot shoot for specific number of ticks
                    this.shotClockB = 15;

                }

            }

            //decrements shot clocks
            this.shotClockA--;
            this.shotClockB--;

            //next frame
            super.repaint();

            //makes while-loop wait for .05 seconds (controls refresh rate)
            try {

                TimeUnit.MILLISECONDS.sleep(50);
            } catch (InterruptedException e) {

            }

        } while (true);
    }

    /**
     * Obligatory function call. Does nothing.
     *
     * @param key
     */
    @Override
    public void keyTyped(KeyEvent key) {

    }

    /**
     * Changes the corresponding boolean to true for key that is pressed
     *
     * @param key pressed key
     */
    @Override
    public void keyPressed(KeyEvent key) {

        switch (key.getKeyCode()) {

            //W key pressed
            case KeyEvent.VK_W:
                this.wPressed = true;
                break;

            //A key pressed
            case KeyEvent.VK_A:
                this.aPressed = true;
                break;

            //S key pressed
            case KeyEvent.VK_S:
                this.sPressed = true;
                break;

            //D key pressed
            case KeyEvent.VK_D:
                this.dPressed = true;
                break;

            //C key pressed
            case KeyEvent.VK_C:
                this.cPressed = true;
                break;

            //I key pressed
            case KeyEvent.VK_I:
                this.iPressed = true;
                break;

            //J key pressed
            case KeyEvent.VK_J:
                this.jPressed = true;
                break;

            //K key pressed
            case KeyEvent.VK_K:
                this.kPressed = true;
                break;

            //L key pressed
            case KeyEvent.VK_L:
                this.lPressed = true;
                break;

            //N key pressed
            case KeyEvent.VK_N:
                this.nPressed = true;
                break;

            //Anything else pressed
            default:
                break;
        }

    }

    /**
     * Changes the corresponding boolean to false for key that is released
     *
     * @param key released key
     */
    @Override
    public void keyReleased(KeyEvent key) {

        switch (key.getKeyCode()) {

            //W key released
            case KeyEvent.VK_W:
                this.wPressed = false;
                break;

            //A key released
            case KeyEvent.VK_A:
                this.aPressed = false;
                break;

            //S key released
            case KeyEvent.VK_S:
                this.sPressed = false;
                break;

            //D key released
            case KeyEvent.VK_D:
                this.dPressed = false;
                break;

            //C key released
            case KeyEvent.VK_C:
                this.cPressed = false;
                break;

            //I key released
            case KeyEvent.VK_I:
                this.iPressed = false;
                break;

            //J key released
            case KeyEvent.VK_J:
                this.jPressed = false;
                break;

            //K key released
            case KeyEvent.VK_K:
                this.kPressed = false;
                break;

            //L key released
            case KeyEvent.VK_L:
                this.lPressed = false;
                break;

            //N key released
            case KeyEvent.VK_N:
                this.nPressed = false;
                break;

            //Anything else released
            default:
                break;
        }

    }

    /**
     * Starts key listening thread
     */
    private void startListening() {

        //makes ready to accept input
        super.setFocusable(true);
        //adds listener
        super.addKeyListener(this);
    }

}
