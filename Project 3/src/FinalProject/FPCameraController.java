/***************************************************************
* file: FPCameraController.Java
* author: Robert Gil, Albert Gil, Benjamin Krueper , Nathaniel Krueper
* class: CS 445 – Computer Graphics
*
* assignment: Quarter Project
* date last modified: 5/7/17
*
* purpose:  This class allows the player to control the camera through
*           keyboard input as well as rendering object in that cameras view
* 
* Controls:
    W:Walk Foward
    A:Strafe Left
    S:Walk Backwards
    D:Strafe right
    Space Bar:Increase Elevation
    Left Shift: Decrease Elevation
    Esc: Exit Game
*
****************************************************************/
package FinalProject;
import java.util.logging.Logger;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.Sys;


public class FPCameraController {
    private Vector3f position = null;
    private Vector3f lPosition = null;
    private float yaw = 0.0f; //the rotation around the Y axis of the camera
    private float pitch = 0.0f; //the rotation around the X axis of the camera
    private Vector3Float me;
    //method: FPCameraController
    //purpose: constructor
    public FPCameraController(float x, float y, float z){ //instantiate position Vector3f to the x y z params.
        position = new Vector3f(x, y, z);
        lPosition = new Vector3f(x,y,z);
        lPosition.x = 0f;
        lPosition.y = 15f;
        lPosition.z = 0f;
    }
    //method: yaw
    //purpose: increment the camera's current yaw rotation
    public void yaw(float amount){
    //increment the yaw by the amount param
        yaw += amount;
    }
    
    //method: pitch
    //purpose: increment the camera's current pitch rotation
    public void pitch(float amount){
    //increment the pitch by the amount param
        pitch -= amount;
    }
    //method: walkForard
    //purpose: moves the camera forward relative to its current rotation (yaw)
    public void walkForward(float distance){
        float xOffset = distance * (float)Math.sin(Math.toRadians(yaw));
        float zOffset = distance * (float)Math.cos(Math.toRadians(yaw));
        position.x -= xOffset;
        position.z += zOffset;
    }
    
    //method: walkBackwards
    //purpose: moves the camera backward relative to its current rotation (yaw)
    public void walkBackwards(float distance){
        float xOffset = distance * (float)Math.sin(Math.toRadians(yaw));
        float zOffset = distance * (float)Math.cos(Math.toRadians(yaw));
        position.x += xOffset;
        position.z -= zOffset;
    }
    
    //method: strafeLeft
    //purpose: moves the camera left relative to its current rotation (yaw)
    public void strafeLeft(float distance){
        float xOffset = distance * (float)Math.sin(Math.toRadians(yaw-90));
        float zOffset = distance * (float)Math.cos(Math.toRadians(yaw-90));
        position.x -= xOffset;
        position.z += zOffset;
    }
    
    //method: strafeRight
    //purpose: moves the camera right relative to its current rotation (yaw)
    public void strafeRight(float distance){
        float xOffset = distance * (float)Math.sin(Math.toRadians(yaw+90));
        float zOffset = distance * (float)Math.cos(Math.toRadians(yaw+90));
        position.x -= xOffset;
        position.z += zOffset;
    }
    
    //method: moveUp
    //purpose: moves the camera up relative to its current rotation (yaw)
    public void moveUp(float distance){
        position.y -= distance;
    }
    
    //method: moveDown
    //purpose: moves the camera down relative to its current rotation (yaw)
    public void moveDown(float distance){
     position.y += distance;
    }
    
    //method: lookThrough
    //translates and rotate the matrix so that it looks through the camera
    //this does basically what gluLookAt() does
    public void lookThrough(){
    //roatate the pitch around the X axis
        glRotatef(pitch, 1.0f, 0.0f, 0.0f);
    //roatate the yaw around the Y axis
        glRotatef(yaw, 0.0f, 1.0f, 0.0f);
    //translate to the position vector's location
        glTranslatef(position.x, position.y, position.z);
    }
    
    //method: gameLoop
    //purpose: processes controls and graphics every frame
    public void gameLoop(){
    
        FPCameraController camera = new FPCameraController(0, 0, 0);
        float dx;
        float dy;
        //float dt = 0.0f; //length of frame
        //float lastTime = 0.0f; // when the last frame was
        //long time = 0;
        float mouseSensitivity = 0.09f;
        float movementSpeed = .35f;
        
   
        Mouse.setGrabbed(true); //Hide mouse in program

       
        while (!Display.isCloseRequested() && !Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
        //time = Sys.getTime();
        //lastTime = time;
        dx = Mouse.getDX(); //distance in mouse movement
        dy = Mouse.getDY();//distance in mouse movement
        camera.yaw(dx * mouseSensitivity);    //controll camera yaw from x movement fromt the mouse
        camera.pitch(dy * mouseSensitivity); //controll camera pitch from y movement fromt the mouse

        //when passing in the distance to move
        //we times the movementSpeed with dt this is a time scale
        //so if its a slow frame u move more then a fast frame
        //so on a slow computer you move just as fast as on a fast computer
            if (Keyboard.isKeyDown(Keyboard.KEY_W)){
                camera.walkForward(movementSpeed);
            }

            if (Keyboard.isKeyDown(Keyboard.KEY_S)){
                camera.walkBackwards(movementSpeed);
            }

            if (Keyboard.isKeyDown(Keyboard.KEY_A)){
                camera.strafeLeft(movementSpeed);
            }

            if (Keyboard.isKeyDown(Keyboard.KEY_D)){
                camera.strafeRight(movementSpeed);
            }

            if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
                camera.moveUp(movementSpeed);
            }

            if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
                camera.moveDown(movementSpeed);
            }
           


        glLoadIdentity();//set the modelview matrix back to the identity
        camera.lookThrough(); //look through the camera before you draw anything
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        renderCube();
        Display.update();
        Display.sync(60);
        }
    Display.destroy();
    }
    
    //method: render
    //purpose: renders a square
    private void render() {
    try{
        glBegin(GL_QUADS);
        glColor3f(2.0f,0.0f,2.0f);
        glVertex3f( 1.0f,-2.0f,-1.0f);
        glVertex3f(-1.0f,-2.0f,-1.0f);
        glVertex3f(-1.0f, 2.0f,-1.0f);
        glVertex3f( 1.0f, 2.0f,-1.0f);
        glEnd();
        }
    catch(Exception e){}
    }
    //method: renderCube
    //purpose: renders a cube
    private void renderCube() {
        try {
            //Top Side
            glBegin(GL_QUADS);
            glColor3f(0.098f, 0.098f, 0.439f); 
            glVertex3f(1.0f, 1.0f, -1.0f);
            glVertex3f(-1.0f, 1.0f, -1.0f);
            glVertex3f(-1.0f, 1.0f, 1.0f);
            glVertex3f(1.0f, 1.0f, 1.0f);

            //Bottom Side
            glColor3f(0.373f, 0.620f, 0.627f); 
            glVertex3f(1.0f, -1.0f, 1.0f);
            glVertex3f(-1.0f, -1.0f, 1.0f);
            glVertex3f(-1.0f, -1.0f, -1.0f);
            glVertex3f(1.0f, -1.0f, -1.0f);

            //Front Side
            glColor3f(0.000f, 0.502f, 0.502f); 
            glVertex3f(1.0f, 1.0f, 1.0f);
            glVertex3f(-1.0f, 1.0f, 1.0f);
            glVertex3f(-1.0f, -1.0f, 1.0f);
            glVertex3f(1.0f, -1.0f, 1.0f);

            //Back Side
            glColor3f(0.400f, 0.804f, 0.667f);
            glVertex3f(1.0f, -1.0f, -1.0f);
            glVertex3f(-1.0f, -1.0f, -1.0f);
            glVertex3f(-1.0f, 1.0f, -1.0f);
            glVertex3f(1.0f, 1.0f, -1.0f);

            //Left Side
            glColor3f(1.000f, 0.000f, 1.000f); 
            glVertex3f(-1.0f, 1.0f, 1.0f);
            glVertex3f(-1.0f, 1.0f, -1.0f);
            glVertex3f(-1.0f, -1.0f, -1.0f);
            glVertex3f(-1.0f, -1.0f, 1.0f);

            //Right Side
            glColor3f(1.000f, 0.647f, 0.000f); 
            glVertex3f(1.0f, 1.0f, -1.0f);
            glVertex3f(1.0f, 1.0f, 1.0f);
            glVertex3f(1.0f, -1.0f, 1.0f);
            glVertex3f(1.0f, -1.0f, -1.0f);
            glEnd();

  
        } catch (Exception e) {

        }
    }
    private static final Logger LOG = Logger.getLogger(FPCameraController.class.getName());
    }

