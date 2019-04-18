/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 *
 * @author James Clark
 */
public class Player {
    
    //Number of lives
    private int lives;
    //Score
    private int score;
    //If player has lost
    private boolean endGame;
    
    public Player(){
        this.lives = 3;
        this.score = 0;
        this.endGame = false;
    }
    
    public void lifeLost(){
        this.lives--;
        
        if (lives <= 0){
            endGame = true;
        }
    }
    
    public void lifeAdded(){
        this.lives++;
    }
    
    public void addScore(int add){
        this.score += add;
    }
    
    protected void showLives(Graphics graphic, int x, int y){
        
        graphic.setColor(Color.BLUE);
        Font font = graphic.getFont().deriveFont(25.0f);
        graphic.setFont(font);
        graphic.drawString("Lives: " + this.lives, x, y);
        
    }
    
    protected void showScore(Graphics graphic, int x, int y){
        
        graphic.setColor(Color.BLUE);
        Font font = graphic.getFont().deriveFont(25.0f);
        graphic.setFont(font);
        graphic.drawString("Score: " + this.score, x, y);
        
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
