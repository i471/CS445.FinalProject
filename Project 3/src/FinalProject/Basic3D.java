/***************************************************************
* file: Basic3D.java
* author: Robert Gil, Albert Gil, Benjamin Krueper , Nathaniel Krueper
* class: CS 445 – Computer Graphics
*
* assignment: Quarter Project
* date last modified: 5/7/17
*
* purpose: This class is hold the main method and is the starting point of the program.
* 
*           This program uses the LWJGL to render a Minecraft like
 *          scene and allows the player to navigate the scene using
 *          keyboard commands.
****************************************************************/
package FinalProject;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.util.glu.GLU;


public class Basic3D {
    private FPCameraController fp;
    private DisplayMode displayMode;
    
    //method: start
    //purpose: creates window
    public void start() {
        try {
            
            createWindow();
            initGL();
            fp = new FPCameraController(0f,0f,0f);
            fp.gameLoop();//render();
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    //method: createWindow
    //purpose: sets up window parameters
    private void createWindow() throws Exception{
        Display.setFullscreen(false);
        DisplayMode d[] =
        Display.getAvailableDisplayModes();
        for (int i = 0; i < d.length; i++) {
            if (d[i].getWidth() == 640 && d[i].getHeight() == 480 && d[i].getBitsPerPixel() == 32) {
                displayMode = d[i];
                break;
            }
        }
        Display.setDisplayMode(displayMode);
        Display.setTitle("Minecraft???");
        Display.create();
    }
    //method: initGL
    //purpose: initiates metrices and other specifications
    private void initGL() {
        glEnable(GL_TEXTURE_2D);
        glEnableClientState (GL_TEXTURE_COORD_ARRAY);
        glEnableClientState(GL_VERTEX_ARRAY);
        glEnableClientState(GL_COLOR_ARRAY);
        glEnable(GL_DEPTH_TEST);

        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        GLU.gluPerspective(100.0f, (float)displayMode.getWidth()/(float)
        displayMode.getHeight(), 0.1f, 300.0f);
        glMatrixMode(GL_MODELVIEW);
        glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
    }
    //method: main
    //purpose: driver
    public static void main(String[] args) {
        Basic3D basic = new Basic3D();
        basic.start();
    }
    
}
