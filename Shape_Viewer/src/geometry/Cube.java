package geometry;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;

/* Vincent Testagrossa
 * CMSC 335 - Inheritance with Shapes
 * 
 * Class Cube - 
 * A cube has a volume and an edge length, which is represented by edge. setEdge() is validated to ensure the edge is always greater 
 * than 0, and is used in the constructors as well, so the volume calculation is called every time setEdge() is called, keeping it
 * updated.
 */
public class Cube extends ThreeDimensionalShape {
    private double edge;
    public Cube(){
        this(1);
    }
    public Cube(double edge){
        super(Math.pow(edge, 3), "Cube");
        setEdge(edge);
    }
    private void setVolume(double edge){
        volume = Math.pow(edge, 3);
    }
    @Override
    public double getVolume() {
        return volume;
    }
    public double getEdge() {
        return edge;
    }
    public void setEdge(double edge) throws IllegalArgumentException {
        if (edge <= 0){
            String err = "ERROR: Edge must be positive.";
            throw new IllegalArgumentException(err);
        }
        else{
            this.edge = edge;
            setVolume(this.edge);
        }
    }
    public void drawShape(GL2 gl, GLU glu){
        gl.glRotatef(-15, 1f, 1f, 0f);
        geometry.Square square = new Square(edge);
        gl.glPushMatrix();
        gl.glTranslated(0,0,edge/2);
        square.drawShape(gl, 0.5, 0, 0);
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glRotatef(90,0,1,0);
        gl.glTranslated(0,0,edge/2);
        square.drawShape(gl, 0.5,0.5,0);
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glRotatef(180,0,1,0);
        gl.glTranslated(0,0,edge/2);
        square.drawShape(gl, 0, 0.5, 0.5);
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glRotatef(270,0,1,0);
        gl.glTranslated(0,0,edge/2);
        square.drawShape(gl, 0, 0, 0.5);
        gl.glPopMatrix();

        
        gl.glPushMatrix();
        gl.glRotatef(90, -1, 0,0);
        gl.glTranslated(0,0,edge/2);
        square.drawShape(gl, 0.5, 0, 0.5);
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glRotatef(-90, -1, 0,0);
        gl.glTranslated(0,0,edge/2);
        square.drawShape(gl, 0, 0.5, 0);
        gl.glPopMatrix();
    }
}
