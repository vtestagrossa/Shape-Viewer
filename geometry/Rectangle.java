package geometry;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;

/* Vincent Testagrossa
 * CMSC 335 - Inheritance with Shapes
 * 
 * Class Rectangle -
 * Rectangle contains length and width, which are used in the area calculation. Since setLength and setWidth check for positive
 * doubles, setArea(length, width) are called during each step. The default constructor passes valid values each time, whereas
 * the overloaded constructor uses the setLength() and setWidth() methods, which both throw exceptions in the case that a number
 * of 0 or less is entered. The inherited field area is kept updated whenever setLength() and setWidth() are called.
 */
public class Rectangle extends TwoDimensionalShape {
    private double length, width;
    public Rectangle(){
        this(1, 1);
    }
    public Rectangle(double length, double width){
        this(length, width, "Rectangle");
        setLength(length);
        setWidth(width);
    }
    public Rectangle(double length, double width, String name){
        super(length * width, name);
        setLength(length);
        setWidth(width);
    }
    public double getArea(){
        return area;
    }
    private void setArea(double length, double width){
        area = length * width;
    }
    public void setLength(double length) throws IllegalArgumentException{
        if (length <= 0){
            String err = "ERROR: Length must be greater than 0.";
            throw new IllegalArgumentException(err);
        }
        else{
            this.length = length;
            setArea(this.length, this.width);
        }
    }
    public void setWidth(double width) throws IllegalArgumentException{
        if (width <= 0){
            String err = "ERROR: Width must be greater than 0.";
            throw new IllegalArgumentException(err);
        }
        else{
            this.width = width;
            setArea(this.length, this.width);
        }
    }
    public double getLength() {
        return length;
    }
    public double getWidth() {
        return width;
    }
    public void drawShape(GL2 gl, GLU glu){
        drawShape(gl, 0.5, 0, 0);
    }

    /*
     * Draws the rectangle with TRIANGLES, then traces the outside with a line loop. Useful since the square is the basis for
     * the cube, which benefits from the outline without lighting enabled.
     */
    public void drawShape(GL2 gl, double red, double green, double blue){
        gl.glColor3d(red, green, blue);
        gl.glBegin(GL2.GL_TRIANGLES);
        gl.glVertex3d(-(length/2),-(width/2),0);
        gl.glVertex3d((length/2),-(width/2),0);
        gl.glVertex3d(-(length/2),(width/2),0);
        gl.glVertex3d((length/2),-(width/2),0);
        gl.glVertex3d(-(length/2),(width/2),0);
        gl.glVertex3d((length/2),(width/2),0);
        gl.glEnd();
        drawOutline(gl);
    }
    private void drawOutline(GL2 gl){
        gl.glColor3d(1, 1, 1);
        gl.glBegin(GL2.GL_LINE_LOOP);
        gl.glVertex3d(-(length/2),-(width/2),0);
        gl.glVertex3d((length/2),-(width/2),0);
        gl.glVertex3d((length/2),(width/2),0);
        gl.glVertex3d(-(length/2),(width/2),0);
        gl.glEnd();

    }
}
