package geometry;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;

/* Vincent Testagrossa
 * CMSC 335 - Inheritance with Shapes
 * 
 * Class Sphere -
 * A sphere has a radius and a volume. The volume calculation is done within setVolume() which is called by the setRadius() method. 
 * Since setRadius() is validated, both the overloaded constructor and the getRadius() method are validated
 * for numbers greater than 0.
 * 
 * Added a Draw method that can be called by the DisplayPanel to draw the Sphere.
 */
public class Sphere extends ThreeDimensionalShape {
    private double radius;
    public Sphere(){
        this(5);
    }
    public Sphere(double radius){
        super((4.0/3.0 * Math.PI * Math.pow(radius, 3)), "Sphere");
        setRadius(radius);
    }
    private void setVolume(double radius){
        volume = 4.0/3.0 * Math.PI * Math.pow(radius, 3);
    }
    @Override
    public double getVolume() {
        return volume;
    }
    public double getRadius() {
        return radius;
    }
    public void setRadius(double radius) throws IllegalArgumentException {
        if (radius <= 0){
            String err = "ERROR: Radius must be positive.";
            throw new IllegalArgumentException(err);
        }
        else{
            this.radius = radius;
            setVolume(this.radius);
        }
    }

    /*
     * Draws a sphere as stacks made from slices of triangle strips. 
     * 
     * The calculations come from the demo for TexturedShapes from the CMSC 405 textbook, Introduction to Computer 
     * Graphics - David J. Eck. The scaling, coloring and some of the simplification are my own additions. 
     */
    public void drawShape(GL2 gl, GLU glu){
        drawShape(gl, 64, 128);
    }
    public void drawShape(GL2 gl, int stacks, int slices){
        gl.glScaled(0.3,0.3,0.3);
        for (int stack = 0; stack < stacks; stack++){
            double lat1 = (Math.PI/stacks) * stack - Math.PI/2;
            double lat2 = (Math.PI/stacks) * (stack+1) - Math.PI/2;
            double sinLat1 = Math.sin(lat1);
            double cosLat1 = Math.cos(lat1);
            double sinLat2 = Math.sin(lat2);
            double cosLat2 = Math.cos(lat2);
            if (stack % 2 == 0){
                gl.glColor3d(0.2, 0.8, 0.2);
            }
            else{
                gl.glColor3d(0, 0.5, 0.5);
            }
            gl.glBegin(GL2.GL_TRIANGLE_STRIP);
            gl.glRotatef(-15, 1f, 1f, 0f);
            for (int slice = 0; slice <= slices; slice++){
                double lon = (2*Math.PI/slices) * slice;
                double sinLong = Math.sin(lon);
                double cosLong = Math.cos(lon);
                double x1 = cosLong * cosLat1;
                double y1 = sinLong * cosLat1;
                double z1 = sinLat1;
                double x2 = cosLong * cosLat2;
                double y2 = sinLong * cosLat2;
                double z2 = sinLat2;
                gl.glNormal3d(x2, y2, z2);
                gl.glVertex3d(radius*x2, radius*y2, radius*z2);
                gl.glNormal3d(x1, y1, z1);
                gl.glVertex3d(radius*x1, radius*y1, radius*z1);
            }
            gl.glEnd();
        }
    }
}
