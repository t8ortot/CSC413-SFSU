package Game.Mechanics;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;
import Game.Moving.Tank;
import Game.Static.*;

/**
 * Game class that handles output
 *
 * @author Zachary Martin & James Clark
 */
public abstract class Game extends JPanel implements Runnable {

    //Tank objects
    protected Tank tankA, tankB;
    //Wall objects
    protected Walls walls;
    //Player objects
    protected Player playerA;
    protected Player playerB;
    //paths for tank images
    private final String TANKA_IMG = "Resources/Tank1.gif";
    private final String TANKB_IMG = "Resources/Tank2.gif";
    //path for background image
    private final String BG_IMG = "Resources/Background.bmp";
    //Map width and height
    private final int MAP_WIDTH = 1280;
    private final int MAP_HEIGHT = 1280;
    //Tanks' panel height
    private final int PANEL_HEIGHT = 700;
    //Background
    private ImageObject background;
    //Array of animations
    private final ArrayList<Animation> ANIMATION = new ArrayList<>();
    //Full map
    private BufferedImage map = new BufferedImage(this.MAP_WIDTH, this.MAP_HEIGHT, BufferedImage.TYPE_INT_RGB);
    //Tank A view (left-side)
    private BufferedImage tankAView = new BufferedImage(this.MAP_WIDTH, this.MAP_HEIGHT, BufferedImage.TYPE_INT_RGB);
    //Tank B view (right side)
    private BufferedImage tankBView = new BufferedImage(this.MAP_WIDTH, this.MAP_HEIGHT, BufferedImage.TYPE_INT_RGB);
    //Window's display
    private Graphics display;
    //Health pack object
    private HealthPack healthPack;

    /**
     * Constructor
     */
    public Game() {

        try {

            //creates background
            this.background = new ImageObject(this.BG_IMG);
            //Gives path to breakbale wall image
            this.walls = new Walls();
            //builds the wall layout
            this.walls.buildWallLayout();
            //gives image paths to tanks
            this.tankA = new Tank(this.TANKA_IMG);
            this.tankB = new Tank(this.TANKB_IMG);
            //creates healthpacks
            this.healthPack = new HealthPack(this.walls);

        } catch (IOException e) {

            System.err.println("Error loading images.");
        }

        //initializes players
        playerA = new Player(this.tankA, this.walls, "3");
        playerB = new Player(this.tankB, this.walls, "4");
        //sets player positions
        this.playerA.setPosition();
        this.playerB.setPosition();
    }

    /**
     * Used for packing window
     *
     * @return dimensions of the window
     */
    @Override
    public Dimension getPreferredSize() {

        return new Dimension(this.MAP_WIDTH, this.PANEL_HEIGHT);
    }

    /**
     * Paints visible components of the window
     *
     * @param graphic
     */
    @Override
    public void paintComponent(Graphics graphic) {

        //JComponent
        super.paintComponent(graphic);
        //Creates graphic of map
        this.display = this.map.createGraphics();

        //Moves background image
        for (int i = 0; i < this.MAP_WIDTH; i += this.background.getWidth()) {

            for (int j = 0; j < this.MAP_HEIGHT; j += this.background.getHeight()) {

                this.background.setImageX(i);
                this.background.setImageY(j);
                this.background.doDrawing(this.display);
            }

        }

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

        //draws walls
        this.walls.doDrawing(this.display);

        //draws health pack
        this.healthPack.doDrawing(this.display);

        //draws tanks
        if (!this.playerA.gameEnd()) {

            this.tankA.doDrawing(this.display);
        }

        if (!this.playerB.gameEnd()) {

            this.tankB.doDrawing(this.display);
        }

        this.display = graphic;

        //Creates tank views
        this.tankAView = this.map.getSubimage(this.tankA.getTankXPosition(this.MAP_WIDTH), this.tankA.getTankYPosition(this.MAP_HEIGHT, this.PANEL_HEIGHT), this.MAP_WIDTH / 2, this.PANEL_HEIGHT);
        this.tankBView = this.map.getSubimage(this.tankB.getTankXPosition(this.MAP_WIDTH), this.tankB.getTankYPosition(this.MAP_HEIGHT, this.PANEL_HEIGHT), this.MAP_WIDTH / 2, this.PANEL_HEIGHT);

        //Draws tank views
        this.display.drawImage(this.tankAView, 0, 0, this.MAP_WIDTH / 2 - 2, this.PANEL_HEIGHT, this);
        this.display.drawImage(this.tankBView, this.MAP_WIDTH / 2, 0, this.MAP_WIDTH / 2, this.PANEL_HEIGHT, this);

        //Draws black areas
        this.display.setColor(Color.BLACK);
        this.display.fillRect(this.MAP_WIDTH / 2 - 150, 400, 300, 300);
        this.display.fillRect(this.MAP_WIDTH / 2 - 5, 0, 10, 400);

        //Draw minimap
        this.display.drawImage(this.map, this.MAP_WIDTH / 2 - 150, 360, 300, 300, this);

        //Draw health bars
        if (!this.playerA.gameEnd() && !this.playerB.gameEnd()) {

            this.playerA.showHealthBar(this.display, this.MAP_WIDTH / 2 - 145, this.PANEL_HEIGHT - 35);
            this.playerB.showHealthBar(this.display, this.MAP_WIDTH / 2 + 45, this.PANEL_HEIGHT - 35);

        } else if (this.playerA.gameEnd()) {

            this.playerB.showHealthBar(this.display, this.MAP_WIDTH / 2 + 45, this.PANEL_HEIGHT - 35);

        } else if (this.playerB.gameEnd()) {

            this.playerA.showHealthBar(this.display, this.MAP_WIDTH / 2 - 145, this.PANEL_HEIGHT - 35);
        }

        //Draw lives
        this.playerA.showLives(this.display, this.MAP_WIDTH / 2 - 35, this.PANEL_HEIGHT - 25);
        this.playerB.showLives(this.display, this.MAP_WIDTH / 2 + 5, this.PANEL_HEIGHT - 25);

        //if game is over, display "Game Over" and who won
        if (this.playerA.gameEnd() || this.playerB.gameEnd()) {

            graphic.setColor(Color.GRAY);
            graphic.fillRect(350, 110, 580, 230);
            graphic.setColor(Color.BLACK);
            graphic.fillRect(360, 120, 560, 210);
            graphic.setColor(Color.DARK_GRAY);
            graphic.fillRect(365, 125, 550, 200);

            graphic.setColor(Color.RED);
            Font font = graphic.getFont().deriveFont(70.0f);
            graphic.setFont(font);
            graphic.drawString("GAME OVER!", 430, 200);
            graphic.setColor(Color.WHITE);
            graphic.fillRect(400, 225, 475, 2);

            if (this.playerA.gameEnd()) {

                graphic.drawString("PLAYER 2 WINS!", 375, 300);
            } else if (this.playerB.gameEnd()) {

                graphic.drawString("PLAYER 1 WINS!", 375, 300);
            }

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

}
