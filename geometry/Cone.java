package geometry;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;

/* Vincent Testagrossa
 * CMSC 335 - Inheritance with Shapes
 * 
 * Class Cone - 
 * A right circular cone has a height and a radius. Whenever the setRadius() or setHeight() methods are called, they are validated
 * for numbers greater than 0 and setVolume() is called to update the volume field.
 */
public class Cone extends ThreeDimensionalShape {
    private double radius, height;

    public Cone(){
        this(0.5, 7);
    }
    public Cone(double radius, double height){
        super((Math.PI * Math.pow(radius, 2) * (height/3.0)), "Cone");
        setRadius(radius);
        setHeight(height);
    }
    private void setVolume(double radius, double height){
        volume = Math.PI * Math.pow(radius, 2) * (height/3.0);
    }
    @Override
    public double getVolume() {
        return volume;
    }
    public double getRadius() {
        return radius;
    }
    public double getHeight() {
        return height;
    }
    public void setRadius(double radius) throws IllegalArgumentException {
        if (radius <= 0){
            String err = "ERROR: Radius must be positive.";
            throw new IllegalArgumentException(err);
        }
        else{
            this.radius = radius;
            setVolume(this.radius, this.height);
        }
    }
    public void setHeight(double height) throws IllegalArgumentException {
        if (height <= 0){
            String err = "ERROR: Height must be positive.";
            throw new IllegalArgumentException(err);
        }
        else{
            this.height = height;
            setVolume(this.radius, this.height);
        }
    }

    /*
     * Uses GLUquadric to draw a wire mesh cone. When looking for an option that could be rotated I came across using GLU and 
     * quadrics to draw more complex objects. Also used in the Cylinder class. Scaled and rotated to fit better in the GLJPanel.
     */
    public void drawShape(GL2 gl, GLU glu){
        drawShape(gl,glu, 32, 32);
    }
    public void drawShape(GL2 gl, GLU glu, int slices, int stacks){
        gl.glScaled(0.5, 0.5,0.5);
        gl.glRotated(-15,1,0,1);
        gl.glColor3d(0.5,0.1,0.1);
        GLUquadric cone = glu.gluNewQuadric();
        glu.gluQuadricDrawStyle(cone, GLU.GLU_LINE);
        glu.gluQuadricNormals(cone, GLU.GLU_SMOOTH);
        glu.gluCylinder(cone, radius, 0, height, slices, stacks);
    }
}
