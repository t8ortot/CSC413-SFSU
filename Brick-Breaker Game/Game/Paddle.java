/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import java.awt.Graphics;
import java.io.IOException;

/**
 *
 * @author James Clark
 */
public class Paddle extends ImageObject{
    
    private final ImageParser PADDLE_IMAGES;
    private final int SPEED;
    private int index;
    
    public Paddle(String image) throws IOException {
        super(image);
        this.PADDLE_IMAGES = new ImageParser(image);
        super.setImageX(305);
        super.setImageY(445);
        this.SPEED = 7;
        this.index = 0;
    }
    
    public void upadateImage() {

        if (this.index == 23) {

            this.index = 0;
        } else {

            this.index++;
        }

        try {

            super.image = this.PADDLE_IMAGES.getFrame(this.index);
        } catch (Exception e) {

            this.index = 0;
            super.image = this.PADDLE_IMAGES.getFrame(this.index);
        }

    }
    
      /**
     * Draws tank
     *
     * @param graphic to be drawn on
     */
    @Override
    public void doDrawing(Graphics graphic) {

        graphic.drawImage(this.PADDLE_IMAGES.getFrame(this.index), super.x, super.y, null);
    }
    /**
     * Moves tank backwards
     */
    public void leftMovement() {

        if(super.getImageX() > 45){
        super.setImageX(super.getImageX() - SPEED);
        }
    }

    /**
     * Moves tank forward
     */
    public void rightMovement() {

        if (super.getImageX() < 515)
         super.setImageX(super.getImageX() + SPEED);
    }

    //Gets updated x-position
    public int getX() {

        return super.getImageX();
    }

    //Gets updated y-position
    public int getY() {

        return super.getImageY();
    }
}
