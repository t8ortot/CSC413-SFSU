package Game;

import javax.swing.JFrame;

/**
 *
 * @author Zachary Martin & James Clark
 */
public class Window extends JFrame {

    private Thread thread;

    /**
     * Window constructor
     *
     * @param title title of the window
     * @param game game object to play
     */
    public Window(String title, Game game) {
        
        //JFrame
        super();
        //creates window
        this.openWindow(title, game);

    }

    /**
     * Called by the constructor to create the window
     *
     * @param title title of the window passed from constructor
     * @param game game object to play passed from constructor
     */
    private void openWindow(String title, Game game) {
        
        //sets title
        super.setTitle(title);
        //sets to end when closed
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //disbales resizing window
        super.setResizable(false);
        //adds game object 
        super.add(game);
        //packs the window to the preferred size
        super.pack();
        //makes window visible
        super.setVisible(true);
        //creates new thread with game
        this.thread = new Thread(game);
        //starts thread
        this.thread.start();
    }
    
}
