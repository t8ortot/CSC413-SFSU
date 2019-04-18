# Tank Game
# Team 34
James Clark & Zachary Martin

Our project was completed using JDK 8 on NetBeans IDE 8.2.

# How to Set Up (Quick Way)

On Mac:

1. Download this repository (keep track of where it has been downloaded to)
2. Navigate to the downloaded .zip called "csc413-03-tankgame-Team34-master" and open it
3. Double-click the "TankGame.jar" file
4. The game window should appear and you can start playing

On Windows:

1. Download this repository (keep track of where it has been downloaded to)
2. Navigate to the downloaded .zip called "csc413-03-tankgame-Team34-master" un-zip the contents
3. Open the folder that was unzipped in the command prompt
4. List the items in the folder by using the command "dir", if you can see an item named "TankGame.jar", continue. If not, you are in the wrong directory.
5. Use the command "java -jar TankGame.jar"
6. The game window should appear, and you can start playing. 

(If the game does not open/work properly with .jar file, open with the next set of instructions below to set up in the NetBeans IDE.)

# How to Set Up in NetBeans IDE 8.2:
1. Download this repository (keep track of where it has been downloaded to)
2. Un-zip the contents of the downloaded .zip
2. Open NetBeans IDE 8.2
3. In the menu bar, select File -> New Project...
4. A pop-up will appear. On the left side, "Java" should be highlighted and on the right, select "Java Project with Existing Sources" and then hit "Next."
5. Name the project and hit "Next"
6. Next to the "Source Package Folders" box select "Add Folder..."
7. Navigate to the un-zipped repository folder named "csc413-03-tankgame-Team34-master", open it, and then hit "Finish."
8. At this point, the project should be open in the NetBeans IDE.
9. In the menu bar, select File -> Project Properties (...)
10. On the left pane, select "Run"
11. To the right of the "Working Directory" box, select "Browse" and navigate to the same "csc413-03-tankgame-Team34-master" folder you downloaded and open it.
12. Click "Ok"
13. Then, in the menu bar, select Run -> Run Project (...)
14. The game window should appear and you can start playing.


# How to Play:
Upon start, both tanks will spawn on a generated map with 3 lives and 100 health points per life. The goal of the game is to shoot the the other players tank with your bullets, which inflict 25 points of damage each, until to you deduct their life points to 0. You will do this until all three lives of the enemy tank have been depleted. There are indestructible walls (red) and destructible walls (orange). The destructible walls can be destroyed by shooting them. Colliding your tank with objects on the map will NOT inflict damage. Health packs are placed around the map to be picked up and will heal your tank to full health in the current life of the tank.

Player 1 (left side):
- W: Move forward
- S: Move backward
- A: Rotate clockwise
- D: Rotate counter-clockwise
- C: Fire

Player 2 (right side):
- I: Move forward
- K: Move backward
- J: Rotate clockwise
- L: Rotate counter-clockwise
- N: Fire
