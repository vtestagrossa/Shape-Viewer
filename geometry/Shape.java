package geometry;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;

/* Vincent Testagrossa
 * CMSC 335 - Inheritance with Shapes
 * 
 * Public class Shape -
 * A shape has a number of dimensions and the default number of dimensions is 2, as shown in the constructor.
 * 
 * drawShape() has been added so that different shapes can be swapped into the Shape field in DisplayPanel.
 */
public class Shape {
    int numberOfDimensions;
    protected Double translateX, translateY, translateZ, scaleX, scaleY, scaleZ, rotateX, rotateY, rotateZ;
    final String name;
    public int getNumberOfDimensions() {
        return numberOfDimensions;
    }
    public Shape(){
        this(2, "Shape");
    }
    public Shape(int numberOfDimensions, String name){
        this.numberOfDimensions = numberOfDimensions;
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public void drawShape(GL2 gl, GLU glu){
        setTransform(scaleX, scaleY, scaleZ, rotateX, rotateY, rotateZ, translateX, translateY, translateZ);
        gl.glBegin(GL2.GL_TRIANGLES);
        gl.glEnd();
    }

    public void setTransform(double sX, double sY, double sZ, double rX, double rY, double rZ, double tX, double tY, double tZ){
        scaleX = sX;
        scaleY = sY;
        scaleZ = sZ;

        rotateX = rX;
        rotateY = rY;
        rotateZ = rZ;
        
        translateX = tX;
        translateY = tY;
        translateZ = tZ;
    }
    public void setTransform(double rX, double rY, double rZ, double tX, double tY, double tZ){
        this.translateX(1, 1, 1, rX, rY, rZ, tX, tY, tZ);
    }
}
