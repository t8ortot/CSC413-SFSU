/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author James Clark
 */
public class Projectile extends Animation {

    private Game game;
    private Player player;
    private Paddle paddle;
    private ImageParser frames;
    private int angle = 90;
    private int speed = 30;
    private Walls wallLayout;
    private int starWidth = 35;
    private int starHeight = 35;
    private Sound sound = new Sound();

    public Projectile(Game game, Paddle paddle, Player player, ImageParser item, int x, int y, int delay, Walls wall, int angle) {
        super(item, x, y, delay, true);
        this.game = game;
        this.player = player;
        this.frames = item;
        this.wallLayout = wall;
        this.paddle = paddle;
        this.angle = angle;
    }

    @Override
    public void doDrawing(Graphics graphics) {

        //if animation is not done
        if (!super.isDone()) {

            this.forward();

            this.checkCollision();
            this.normalizeAngle();
            //System.out.println(this.angle);
            this.checkPaddle();
            this.normalizeAngle();
            this.checkBounds();
            this.normalizeAngle();

            super.doDrawing(graphics);

        }
    }

    private void normalizeAngle() {

        while (this.angle < 0) {
            this.angle += 360;
        }
        while (this.angle >= 360) {
            this.angle -= 360;
        }

    }

    private void checkBounds() {
        Rectangle star = new Rectangle(super.X, super.Y, this.starWidth, this.starHeight);
        Rectangle sBound = new Rectangle(0, 480, 640, 100);
        Rectangle nBound = new Rectangle(0, 0, 640, 25);
        Rectangle eBound = new Rectangle(595, 0, 40, 480);
        Rectangle wBound = new Rectangle(0, 0, 45, 480);

        if (star.intersects(sBound)) {
            if (this.game.stars <= 1) {
                player.lifeLost();
                this.game.newStart();
            }

            this.sound.playOnce("Resources/Sound_lost.wav");
            this.setDone();
            this.game.stars--;
        }

        if (star.intersects(nBound)) {
            this.angle = 360 - this.angle;
            sound.playOnce("Resources/Sound_wall.wav");
        }
        if (star.intersects(wBound)) {
            this.angle = 540 - this.angle;
            sound.playOnce("Resources/Sound_wall.wav");

        }
        if (star.intersects(eBound)) {
            this.angle = 540 - this.angle;
            sound.playOnce("Resources/Sound_wall.wav");

        }

    }

    private void checkPaddle() {
        Rectangle star = new Rectangle(super.X, super.Y, this.starWidth, this.starHeight);
        Rectangle lPaddle = new Rectangle(this.paddle.getImageX(), this.paddle.getImageY(), this.paddle.getWidth() - 70, this.paddle.getHeight());
        Rectangle mPaddle = new Rectangle(this.paddle.getImageX() + 10, this.paddle.getImageY(), this.paddle.getWidth() - 20, this.paddle.getHeight());
        Rectangle rPaddle = new Rectangle(this.paddle.getImageX() + 70, this.paddle.getImageY(), this.paddle.getWidth() - 70, this.paddle.getHeight());

        if (star.intersects(lPaddle)) {
            this.angle = 130;
            sound.playOnce("Resources/Sound_katch.wav");
        } else if (star.intersects(rPaddle)) {
            this.angle = 50;
            sound.playOnce("Resources/Sound_katch.wav");
        } else if (star.intersects(mPaddle)) {
            this.angle = 360 - this.angle;
            sound.playOnce("Resources/Sound_katch.wav");
        }
    }

    private void checkCollision() {
        Rectangle star = new Rectangle(super.X, super.Y, this.starWidth, this.starHeight);
        boolean hit = false;

        for (int row = 0; row < this.wallLayout.getMapHeight(); row++) {

            for (int col = 0; col < this.wallLayout.getMapWidth(); col++) {

                if (!hit && !this.wallLayout.getWallLayout()[row][col].equals(" ")) {

                    Rectangle brick = new Rectangle(col * 20, row * 20,
                            this.wallLayout.getWidth(this.wallLayout.getWallLayout()[row][col]), this.wallLayout.getHeight());
                    if (star.intersects(brick)) {

                        this.hitWall(row, col);
                        hit = true;

                    }

                }

            }

        }

    }

    public void hitWall(int row, int col) {

        Rectangle nBrick = new Rectangle((col * 20) + 5, row * 20, this.wallLayout.getWidth(this.wallLayout.getWallLayout()[row][col]) - 10, this.wallLayout.getHeight() - 15);
        Rectangle sBrick = new Rectangle((col * 20) + 5, (row * 20) + 15, this.wallLayout.getWidth(this.wallLayout.getWallLayout()[row][col]) - 10, this.wallLayout.getHeight() - 15);
        Rectangle eBrick = new Rectangle((col * 20) + 35, (row * 20) + 5, this.wallLayout.getWidth(this.wallLayout.getWallLayout()[row][col]) - 35, this.wallLayout.getHeight() - 10);
        Rectangle wBrick = new Rectangle((col * 20), (row * 20) + 5, this.wallLayout.getWidth(this.wallLayout.getWallLayout()[row][col]) - 35, this.wallLayout.getHeight() - 10);

        Rectangle nStar = new Rectangle(super.X, super.Y, this.starWidth, this.starHeight);
//        Rectangle sStar = new Rectangle(super.X + 7, super.Y + 28, this.starWidth - 14, this.starHeight / 7);
//        Rectangle wStar = new Rectangle(super.X, super.Y + 7, this.starWidth / 7, this.starHeight - 28);
//        Rectangle eStar = new Rectangle(super.X + 28, super.Y + 7, this.starWidth / 7, this.starHeight - 28);

        boolean react = false;

        if (nStar.intersects(sBrick)) {
            react = true;
            if (this.angle == 90) {
                this.angle = 270;
            } else if (this.angle > 0 && this.angle < 90) {
                this.angle = 360 - this.angle;

            } else if (this.angle > 90 && this.angle < 180) {
                this.angle = 360 - this.angle;
            }
        } else if (nStar.intersects(nBrick)) {
            react = true;
            if (this.angle == 270) {
                this.angle = 90;
            } else if (this.angle > 180 && this.angle < 270) {

                this.angle = 360 - this.angle;
            } else if (this.angle > 270 && this.angle < 360) {

                this.angle = 360 - angle;
            }

        } else if (nStar.intersects(wBrick)) {
            react = true;
            if (this.angle == 0 || this.angle == 360) {
                this.angle = 180;
            } else if (this.angle > 0 && this.angle < 90) {

                this.angle = 180 - this.angle;

            } else if (this.angle > 270 && this.angle < 360) {
                this.angle = 540 - this.angle;
            }

        } else if (nStar.intersects(eBrick)) {
            react = true;
            if (this.angle == 180) {
                this.angle = 0;
            } else if (this.angle > 90 && this.angle < 180) {
                this.angle = 180 - this.angle;

            } else if (this.angle > 180 && this.angle < 270) {

                this.angle = 540 - this.angle;
            }
        }

        if (react) {
            if (this.speed < 50) {
                this.speed++;
            }
            switch (this.wallLayout.getWallLayout()[row][col]) {
                case "0":
                    break;
                case "1":
                    this.wallLayout.getWallLayout()[row][col] = " ";
                    this.wallLayout.setUpdatedWallMap(this.wallLayout.getWallLayout());
                    player.addScore(100);
                    this.sound.playOnce("Resources/Sound_block.wav");
                    break;
                case "2":
                    this.wallLayout.getWallLayout()[row][col] = "1";
                    this.wallLayout.setUpdatedWallMap(this.wallLayout.getWallLayout());
                    player.addScore(100);
                    this.sound.playOnce("Resources/Sound_block.wav");
                    break;
                case "3":
                    this.wallLayout.getWallLayout()[row][col] = "2";
                    this.wallLayout.setUpdatedWallMap(this.wallLayout.getWallLayout());
                    player.addScore(100);
                    this.sound.playOnce("Resources/Sound_block.wav");
                    break;
                case "4":
                    this.wallLayout.getWallLayout()[row][col] = "3";
                    this.wallLayout.setUpdatedWallMap(this.wallLayout.getWallLayout());
                    player.addScore(100);
                    this.sound.playOnce("Resources/Sound_block.wav");
                    break;
                case "5":
                    this.wallLayout.getWallLayout()[row][col] = "4";
                    this.wallLayout.setUpdatedWallMap(this.wallLayout.getWallLayout());
                    player.addScore(100);
                    this.sound.playOnce("Resources/Sound_block.wav");
                    break;
                case "6":
                    this.wallLayout.getWallLayout()[row][col] = "5";
                    this.wallLayout.setUpdatedWallMap(this.wallLayout.getWallLayout());
                    player.addScore(100);
                    this.sound.playOnce("Resources/Sound_block.wav");
                    break;
                case "7":
                    this.wallLayout.getWallLayout()[row][col] = "6";
                    this.wallLayout.setUpdatedWallMap(this.wallLayout.getWallLayout());
                    player.addScore(100);
                    this.sound.playOnce("Resources/Sound_block.wav");
                    break;
                case "S":
                    this.wallLayout.getWallLayout()[row][col] = " ";
                    this.wallLayout.setUpdatedWallMap(this.wallLayout.getWallLayout());
                    this.game.addAnimation(new Projectile(this.game, this.paddle, this.player, this.frames, this.X, this.Y, 0, this.wallLayout, 540 - this.angle));
                    this.game.stars++;
                    this.sound.playOnce("Resources/Sound_split.wav");
                    break;
                case "B":
                    break;
                case "L":
                    this.wallLayout.getWallLayout()[row][col] = " ";
                    this.wallLayout.setUpdatedWallMap(this.wallLayout.getWallLayout());
                    this.player.lifeAdded();
                    sound.playOnce("Resources/Sound_life.wav");
                    break;
                case " ":
                    break;
                default:
                    System.out.println("Character not recognized in mapLayout!");
                    break;
            }
        }

    }

    /**
     * forward movement
     */
    private void forward() {

        super.X += (int) (Math.cos(Math.toRadians(angle)) * this.speed / 10);
        super.Y -= (int) (Math.sin(Math.toRadians(angle)) * this.speed / 10);
    }
}
