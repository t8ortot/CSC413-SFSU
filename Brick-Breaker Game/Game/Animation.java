package Game;

import java.awt.Graphics;

/**
 * Animation object
 *
 * @author Zachary Martin & James Clark
 */
public class Animation {

    //position to be drawn at
    protected int X, Y;
    //Object to be animated
    private final ImageParser OBJECT;
    private final boolean LOOP;
    //current frame taken from the animation source
    protected int currFrame = 0;
    //delay between frames
    private final int FRAME_DELAY;
    //ticker to determine delay
    private int delayTicker = 0;
    //total duration
    private int duration = 0;
    //Boolean for if the animation is done
    private boolean done = false;

    /**
     * Constructor
     *
     * @param item to be animated
     * @param x-position to be animated at
     * @param y-position to be animated at
     * @param delay
     * @param loop
     */
    public Animation(ImageParser item, int x, int y, int delay, boolean loop) {

        this.OBJECT = item;
        this.X = x;
        this.Y = y;
        this.FRAME_DELAY = delay;
        this.LOOP = loop;
    }

    /**
     * Draws the animation
     *
     * @param graphic to be drawn on
     */
    public void doDrawing(Graphics graphic) {

        if(!done){
            
        //increment total duration and delay ticker
        this.duration++;
        this.delayTicker++;

        //if ticker has reached delay time
        if (delayTicker > FRAME_DELAY) {

            //reset ticker
            delayTicker = 0;
            
            //done is true if the total time has elapsed and not in a loop
            this.done = (this.currFrame > this.OBJECT.frameCount() - 1) && !LOOP;
            
            //increment current frame
            this.currFrame = (currFrame +1) % this.OBJECT.frameCount();

            
        }

        //draw the current frame in the animation
        graphic.drawImage(this.OBJECT.getFrame(this.currFrame), this.X, this.Y, null);
    }
    }

    /**
     * Sets done to true
     */
    protected void setDone() {

        this.done = true;
    }

    /**
     * Returns true when the animation is complete
     *
     * @return animation completed
     */
    protected boolean isDone() {

        return this.done;
    }

}
