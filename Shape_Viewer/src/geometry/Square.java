package geometry;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;

/* Vincent Testagrossa
 * CMSC 335 - Inheritance with Shapes
 * 
 * Class Square -
 * Extends the Rectangle class, which is a child of TwoDimensionalShape, so a Square is a TwoDimensionalShape. Since setLength() and
 * setWidth() are inherited, both need to be implemented with both parent methods called for each with the length as the parameter.
 * 
 * drawShape() also included in this class. Just calls the super.drawShape() and changes the color.
 * 
 */
public class Square extends Rectangle{
    public Square(){
        this(1);
    }
    public Square(double length){
        super(length, length, "Square");
    }
    public void setLength(double length){
        super.setLength(length);
        super.setWidth(length);
    }
    public void setWidth(double length){
        super.setLength(length);
        super.setWidth(length);
    }
    public void drawShape(GL2 gl, GLU glu){
        super.drawShape(gl, 0.6, 0, 0.6);
    }
}
