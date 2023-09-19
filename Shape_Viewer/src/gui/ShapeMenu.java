package gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/*
 * Vincent Testagrossa
 * CMSC 335 - Drawing with Shapes - 
 * 
 * Class ShapeMenu -
 * Was used in testing only to get the menu's events correct. Menu is now generated within ShapeViewerGUI.
 */

public class ShapeMenu extends JMenuBar implements ActionListener{
    JMenu shapes2d;
    JMenuItem triangle, rectangle;

    //can create an overloaded constructor that takes a list of shapes
    public ShapeMenu(){
        shapes2d = new JMenu("2D Shapes");
        triangle = new JMenuItem("Triangle");
        rectangle = new JMenuItem("Rectangle");
        //Create the menu bar
        this.setOpaque(true);
        this.setBackground(Color.darkGray);
        this.setPreferredSize(new Dimension(200, 25));

        triangle.addActionListener(this);
        rectangle.addActionListener(this);
        shapes2d.add(triangle);
        shapes2d.add(rectangle);
        this.add(shapes2d);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(triangle)){
            System.out.println("triangle");
        }
        else if (e.getSource().equals(rectangle)){
            System.out.println("rectangle");
        }
    }
    
}
