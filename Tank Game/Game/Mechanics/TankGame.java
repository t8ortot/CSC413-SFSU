package Game.Mechanics;

/**
 * Upon start, both tanks will spawn on a generated map with 3 lives and 100
 * health points per life. The goal of the game is to shoot the the other
 * players tank with your bullets, which inflict 25 points of damage each, until
 * to you deduct their life points to 0. You will do this until all three lives
 * of the enemy tank have been depleted. There are indestructible walls (red)
 * and destructible walls (orange). The destructible walls can be destroyed by
 * shooting them. Colliding your tank with objects on the map will NOT inflict
 * damage. Health packs are placed around the map to be picked up and will heal
 * your tank to full health in the current life of the tank.
 *
 * Player 1 (left side):
 *
 * W: Move forward S: Move backward A: Rotate clockwise D: Rotate
 * counter-clockwise C: Fire
 *
 * Player 2 (right side):
 *
 * I: Move forward K: Move backward J: Rotate clockwise L: Rotate
 * counter-clockwise N: Fire
 *
 * @author Zachary Martin & James Clark
 */
public class TankGame {

    public static void main(String[] args) {

        //Creates game window
        Window window = new Window("Tank Game", new Controls());
    }
    
}
