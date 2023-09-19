import java.awt.Dimension;
import javax.swing.*;
import gui.ShapeViewerGUI;

/*
 * Vincent Testagrossa
 * CMSC 335 - Drawing with Shapes
 * 
 * The initComponents method adds the ShapeViewerGUI to a JFrame using a boxlayout. 
 */

public class App {
    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                initComponents(new ShapeViewerGUI("Shape Viewer"));
            }
        });
    }
    private static void initComponents(ShapeViewerGUI panel){
        JFrame frame = new JFrame(panel.getTitle());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setJMenuBar(panel.getMenu());
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.add(panel);
        frame.add(Box.createVerticalGlue());
        frame.setPreferredSize(new Dimension(800, 800));
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
