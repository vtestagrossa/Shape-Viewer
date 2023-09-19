package gui;

import java.awt.*;

import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.*;
import com.jogamp.opengl.glu.GLU;

import geometry.ThreeDimensionalShape;

/*
 * Vincent Testagrossa
 * CMSC 335 - Drawing with Shapes
 * 
 * GLJPanel for drawing the shape passed to it with OpenGL.
 */

public class DisplayPanel extends GLJPanel implements GLEventListener{
    geometry.Shape shape;
    private GLU glu;
    public DisplayPanel(geometry.Shape shape){
        super(new GLCapabilities(null));
        setPreferredSize(new Dimension(600, 600));
        addGLEventListener(this);
        this.shape = shape;
        glu = new GLU();
    }
    @Override
    public void display(GLAutoDrawable arg0) {
        final GL2 gl = arg0.getGL().getGL2();
        
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();
        if(ThreeDimensionalShape.class.isAssignableFrom(shape.getClass()))
        {
            gl.glRotatef(-15, 1f, 1f, 0f);
        }

        gl.glScaled(0.3, 0.3,0.3);
        gl.glPushMatrix();
        shape.drawShape(gl, glu);
        gl.glPopMatrix();

    }

    @Override
    public void dispose(GLAutoDrawable arg0) {
        
    }

    @Override
    public void init(GLAutoDrawable drawable) {
        GL2 gl2 = drawable.getGL().getGL2();
        gl2.glMatrixMode(GL2.GL_PROJECTION);
        gl2.glOrtho(-1, 1, -1, 1, -1, 1);
        gl2.glMatrixMode(GL2.GL_MODELVIEW);
        gl2.glClearColor(0, 0, 0, 1);
        gl2.glEnable(GL2.GL_DEPTH_TEST);
        gl2.glLineWidth(2);
    }

    @Override
    public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {
        
    }
    public void setShape(geometry.Shape shape){
        this.shape = shape;
    }
}
