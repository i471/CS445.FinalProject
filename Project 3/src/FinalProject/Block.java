/***************************************************************
* file: Basic3D.java
* author: Robert Gil, Albert Gil, Benjamin Krueper , Nathaniel Krueper
* class: CS 445 – Computer Graphics
*
* assignment: Quarter Project
* date last modified: 5/12/17
*
* purpose: This class stores data for the blocks in the game.
* 
****************************************************************/
package FinalProject;


public class Block {
    private boolean IsActive;
    private BlockType Type;
    private float x,y,z;
    public enum BlockType
    {
        BlockType_Grass(0),
        BlockType_Sand(1),
        BlockType_Water(2),
        BlockType_Dirt(3),
        BlockType_Stone(4),
        BlockType_Bedrock(5),
        BlockType_Default(6);
        private int BlockID;
        
        BlockType(int i) {
            BlockID=i;
        }
        //method: GetID
        //purpose: getter for ID
        public int GetID(){
            return BlockID;
        }
        //method: SetID
        //purpose: setter for ID
        public void SetID(int i){
            BlockID = i;
        }
    }
    //method: Block
    //purpose: constructor
    public Block(BlockType type){
        Type= type;
    }
    //method: setCoords
    //purpose: sets the blocks coorodinates
    public void setCoords(float x, float y, float z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
    //method: IsActive
    //purpose: getter for IsActive
    public boolean IsActive() {
        return IsActive;
    }
    //method: SetActive
    //purpose: setter for IsActive
    public void SetActive(boolean active){
        IsActive=active;
    }
    //method: GetID
    //purpose: getter for ID
    public int GetID(){
        return Type.GetID();
    }


    
}
