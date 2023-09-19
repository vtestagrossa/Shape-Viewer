package geometry;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;

/* Vincent Testagrossa
 * CMSC 335 - Inheritance with Shapes
 * 
 * Class Cylinder - 
 * A cylinder has a radius and a height, which are updated by setRadius() and setHeight(). Each of those methods are validated for
 * numbers greater than 0, and then setVolume() is called to keep the field volume updated.
 */
public class Cylinder extends ThreeDimensionalShape {
    private double radius, height;

    public Cylinder(){
        this(0.5, 6.5);
    }
    public Cylinder(double radius, double height){
        super((Math.PI * Math.pow(radius, 2) * height), "Cylinder");
        setRadius(radius);
        setHeight(height);
    }

    private void setVolume(double radius, double height){
        volume = Math.PI * Math.pow(radius, 2) * height;
    }
    @Override
    public double getVolume() {
        return volume;
    }
    public double getHeight() {
        return height;
    }
    public double getRadius() {
        return radius;
    }
    public void setHeight(double height) throws IllegalArgumentException {
        if (height <= 0){
            String err = "ERROR: Radius must be positive.";
            throw new IllegalArgumentException(err);
        }
        else{
            this.height = height;
            setVolume(this.radius, this.height);
        }
    }
    public void setRadius(double radius) throws IllegalArgumentException {
        if (radius <= 0){
            String err = "ERROR: Height must be positive.";
            throw new IllegalArgumentException(err);
        }
        else{
            this.radius = radius;
            setVolume(this.radius, this.height);
        }
    }
    public void drawShape(GL2 gl, GLU glu){
        drawShape(gl,glu, 32, 10);
    }

    /*
     * Uses GLUquadric to draw the outside of the cylinder as a wire mesh, and places solid caps on top and bottom. The code for the
     * caps comes from the CMSC 405 textbook, Introduction to Computer Graphics - David J. Eck.
     */
    public void drawShape(GL2 gl, GLU glu, int slices, int stacks){
        gl.glRotated(-15,1,0,1);
        gl.glScaled(0.5, 0.5, 0.5);
        GLUquadric cylinder = glu.gluNewQuadric();
        gl.glColor3d(0.1,0.9,0.1);
        glu.gluQuadricDrawStyle(cylinder, GLU.GLU_LINE);
        glu.gluQuadricNormals(cylinder, GLU.GLU_SMOOTH);
        glu.gluCylinder(cylinder, radius, radius, height, slices, stacks);


        double[] x = new double[slices];
        double[] y = new double[slices];
        double angleDelta = Math.PI * 2 / slices;
        double angle = 0;
        for (int i = 0; i < slices; i++) {
            angle = i * angleDelta;
            x[i] = Math.cos(angle) * radius;
            y[i] = Math.sin(angle) * radius;
        }

        // Draw bottom cap
        gl.glColor3d(0.5,0,0);
        gl.glBegin(GL2.GL_TRIANGLE_FAN);
        gl.glNormal3d(0, 0, -1);
        gl.glVertex3d(0, 0, 0);
        for (int i = 0; i < slices; i++) {
            gl.glVertex3d(x[i], y[i], 0);
        }
        gl.glVertex3d(x[0], y[0], 0);
        gl.glEnd();

        // Draw top cap
        gl.glColor3d(0.5,0,0);
        gl.glBegin(GL2.GL_TRIANGLE_FAN);
        gl.glNormal3d(0, 0, 1);
        gl.glVertex3d(0, 0, height);
        for (int i = 0; i < slices; i++) {
            gl.glVertex3d(x[i], y[i], height);
        }
        gl.glVertex3d(x[0], y[0], height);
        gl.glEnd();
    }
}
