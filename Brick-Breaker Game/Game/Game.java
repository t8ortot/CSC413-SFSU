package Game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 * Game class that handles output
 *
 * @author Zachary Martin & James Clark
 */
public abstract class Game extends JPanel implements Runnable {

    protected Player player;
    private ImageObject background;
    protected Walls wallLayout;
    private final String LVL1_BG_IMG = "Resources/Background1.bmp";
    private final String LVL2_BG_IMG = "Resources/Background2.bmp";
    private final int WINDOW_WIDTH = 640;
    private final int WINDOW_HEIGHT = 480;
    private Graphics display;
    protected Paddle paddle;
    protected boolean start;
    private final String PADDLE_IMG = "Resources/Katch.gif";
    //Array of animations
    private final ArrayList<Animation> ANIMATION = new ArrayList<>();
    //Full map
    private BufferedImage map = new BufferedImage(this.WINDOW_WIDTH, this.WINDOW_HEIGHT, BufferedImage.TYPE_INT_RGB);
    protected int stars = 0;
    private int level = 1;
    
    
    public Game() {

        this.start = false;
        try {

            //creates background
            this.background = new ImageObject(this.LVL1_BG_IMG);
            //creates walls
            this.wallLayout = new Walls();
            //creates paddle
            this.paddle = new Paddle(PADDLE_IMG);
        } catch (IOException e) {

        }

        this.player = new Player();

    }

    public void nextLevel() {
        try {
            this.background = new ImageObject(LVL2_BG_IMG);
            this.wallLayout.changeLevel("2");
            this.stars = 0;
            this.ANIMATION.clear();
            
            this.newStart();
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(this.WINDOW_WIDTH, this.WINDOW_HEIGHT);
    }

    @Override
    public void paintComponent(Graphics graphic) {
        
        if (this.wallLayout.isEmpty() && level == 1) {
            level++;
            nextLevel();
        }
        
        //JComponent
        super.paintComponent(graphic);
        //Creates graphic of map
        this.display = this.map.createGraphics();

        //Draws background
        this.background.doDrawing(this.display);

        //Updates stored animations
        Animation tempAnimation;
        for (int i = 0; i < this.ANIMATION.size(); i++) {

            tempAnimation = this.ANIMATION.get(i);
            if (tempAnimation.isDone()) {

                this.ANIMATION.remove(i);
            } else {

                tempAnimation.doDrawing(this.display);
            }
        }

        this.wallLayout.doDrawing(this.display);

        this.paddle.doDrawing(this.display);

        this.display = graphic;

        //draws layout
        this.display.drawImage(map, 0, 0, this.WINDOW_WIDTH, this.WINDOW_HEIGHT, this);

        this.player.showLives(this.display, this.WINDOW_WIDTH - 150, 50);
        this.player.showScore(this.display, 50, 50);

        if (!this.start) {
            graphic.setColor(Color.BLUE);
            Font font = graphic.getFont().deriveFont(35.0f);
            graphic.setFont(font);
            graphic.drawString("Press Space to Start", 150, 400);

        }
        
        if (player.gameEnd()) {
             graphic.setColor(Color.GRAY);
            graphic.fillRect(80, 240, 475, 200);
            graphic.setColor(Color.BLACK);
            graphic.fillRect(85, 245, 465, 190);
            graphic.setColor(Color.DARK_GRAY);
            graphic.fillRect(90, 250, 455, 180);

            
            graphic.setColor(Color.RED);
            Font font = graphic.getFont().deriveFont(70.0f);
            graphic.setFont(font);
            graphic.drawString("GAME OVER!", 107, 360);
            graphic.setColor(Color.WHITE);
            graphic.fillRect(110, 370, 420, 2);
        }
        
        if (this.wallLayout.isEmpty() && level == 2) {
             graphic.setColor(Color.GREEN);
            graphic.fillRect(80, 240, 475, 200);
            graphic.setColor(Color.BLACK);
            graphic.fillRect(85, 245, 465, 190);
            graphic.setColor(Color.CYAN);
            graphic.fillRect(90, 250, 455, 180);

            graphic.setColor(Color.PINK);
            Font font = graphic.getFont().deriveFont(70.0f);
            graphic.setFont(font);
            graphic.drawString("YOU WIN!", 160, 360);
            graphic.setColor(Color.WHITE);
            graphic.fillRect(110, 370, 420, 2);
        }

    }

    /**
     * Adds animation into array
     *
     * @param temp Animation to be added
     */
    public void addAnimation(Animation temp) {

        this.ANIMATION.add(temp);
    }

    protected void newStart() {
        this.start = false;
    }
}
