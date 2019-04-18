package Game;

import java.awt.event.*;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Controls key-press and key-release actions
 *
 * @author Zachary Martin & James Clark
 */
public class Controls extends Game implements KeyListener {

   //player controls
    private boolean leftPressed, rightPressed, spacePressed;
    private final String PROJ_IMG = "Resources/Pop.gif";
    private ImageParser projectile;
    private Sound song = new Sound();
    

    /**
     * Constructor
     */
    public Controls() {

        //Game
        super();

        //Starts Key Listener Thread
        this.startListening();
        
        song.playLoop("Resources/Journey.wav");
        
        
        try{
            this.projectile = new ImageParser(this.PROJ_IMG);
        } catch (IOException ex) {
        }

    }

    /**
     * Executes methods corresponding to keyboard inputs
     */
    @Override
    public void run() {

         while (!this.player.gameEnd()){

            
            
                //Player's Buttons:
                //Player's start (shoots star)
                if (this.spacePressed && !super.start) {
                    
                    super.start = true;
                    super.addAnimation(new Projectile(this, super.paddle, super.player, this.projectile, super.paddle.getX() + 22, super.paddle.getY() - 35, 0, super.wallLayout, 90));
                    this.stars++;
                }


                //Player A's left (paddle moves left)
                if (this.leftPressed) {
                    super.paddle.leftMovement();
                }

                //Player's right (paddle moves right)
                if (this.rightPressed) {
                    super.paddle.rightMovement();
                }

            super.paddle.upadateImage();
            //next frame
            super.repaint();

            //makes while-loop wait for .05 seconds (controls refresh rate)
            try {

                TimeUnit.MILLISECONDS.sleep(25);
            } catch (InterruptedException e) {

            }

           
        }
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


            //A key pressed
            case KeyEvent.VK_LEFT:
                this.leftPressed = true;
                break;

            //D key pressed
            case KeyEvent.VK_RIGHT:
                this.rightPressed = true;
                break;

            //Space key pressed
            case KeyEvent.VK_SPACE:
                this.spacePressed = true;
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


            //A key released
            case KeyEvent.VK_LEFT:
                this.leftPressed = false;
                break;

           

            //D key released
            case KeyEvent.VK_RIGHT:
                this.rightPressed = false;
                break;
                
            //Space key released
            case KeyEvent.VK_SPACE:
                this.spacePressed = false;
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
