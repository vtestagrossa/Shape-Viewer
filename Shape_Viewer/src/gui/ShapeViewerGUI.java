package gui;
import java.awt.event.*;
import java.util.HashMap;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.Dimension;

import geometry.*;
import geometry.Rectangle;

/*
 * Vincent Testagrossa
 * CMSC 335 - Drawing with Shapes
 * 
 * ShapeViewGUI - Builds the components and layouts for the top panel, which are used to input values based on whatever shape is
 * selected from the JMenuBar JMenus. The textfields are used to enter different values for the shapes and the update "shape" button
 * is used to make the changes via the "ShapeButton" ActionCommand. The enum availableShapes is used in currentShape to store the state
 * of the program, which is tested for in various methods. Each shape uses the "name" field as its actioncommand.
 */

public class ShapeViewerGUI extends JPanel implements ActionListener{
    private String title;
    HashMap<String, TwoDimensionalShape> shapeMap2D = new HashMap<String, TwoDimensionalShape>();
    HashMap<String, ThreeDimensionalShape> shapeMap3D = new HashMap<String, ThreeDimensionalShape>();
    

    //Components for the top panel
    private JMenuBar menuBar;
    private JButton shapeButton; 
    private JLabel areaVolumeLabel, inputLabel1, inputLabel2;
    private JTextField areaVolumeTextField, input1TextField, input2TextField;

    //JOGL panel to draw each shape
    private DisplayPanel bottomPanel;

    private enum availableShapes {
        TRIANGLE, RECTANGLE, CIRCLE, SQUARE, SPHERE, CONE, CYLINDER, CUBE, TORUS
    }
    availableShapes currentShape;

    public ShapeViewerGUI(String title){
        //adds shapes to each HashMap. Passed into the menu initializer to generate the menu items.
        shapeMap2D = initialize2DMap();
        shapeMap3D = initialize3DMap();
        menuBar = initMenu(shapeMap2D,shapeMap3D);
        bottomPanel = new DisplayPanel(shapeMap2D.get("Triangle"));

        bottomPanel.setPreferredSize(new Dimension(800,800));

        this.title = title;
        this.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        //Displays the current shape selected
        shapeButton = new JButton("");
        shapeButton.addActionListener(this);
        shapeButton.setActionCommand("shapeButton");

        //create the area/volume label and textfield, disable editing
        areaVolumeLabel = new JLabel();
        areaVolumeTextField = new JTextField();
        areaVolumeTextField.setEditable(false);

        //add the label for the first input and its field
        inputLabel1 = new JLabel();
        input1TextField = new JTextField();

        //add the final input label and its text field
        inputLabel2 = new JLabel();
        input2TextField = new JTextField();

        //Set up the groupLayout and add components to the topPanel
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        //add horizontal and vertical groups
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(areaVolumeLabel)
                    .addComponent(inputLabel1)
                    .addComponent(inputLabel2))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(areaVolumeTextField)
                    .addComponent(input1TextField)
                    .addComponent(input2TextField)))
            .addComponent(shapeButton)
            .addComponent(bottomPanel)
        );
        layout.setVerticalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(areaVolumeLabel)
                .addComponent(areaVolumeTextField))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(inputLabel1)
                .addComponent(input1TextField))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(inputLabel2)
                .addComponent(input2TextField))
            .addComponent(shapeButton)
            .addComponent(bottomPanel)
        );

        currentShape = availableShapes.TRIANGLE;
        updateComponents(currentShape);
    }

    /*
     * Uses getActionCommand to set the state of currentShape and then calls updateComponents to reset the labels, textfields,
     * and button. When "shapeButton" is recieved by the method, updateShape() is called on the current shape.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //First menu of the menuBar is 2D Shapes
        //First menuItem is triangle:
        switch(e.getActionCommand()){
            case "Triangle":
                currentShape = availableShapes.TRIANGLE;
                updateComponents(currentShape);
                break;
            case "Rectangle":
                currentShape = availableShapes.RECTANGLE;
                updateComponents(currentShape);
                break;
            case "Square":
                currentShape = availableShapes.SQUARE;
                updateComponents(currentShape);
                break;
            case "Circle":
                currentShape = availableShapes.CIRCLE;
                updateComponents(currentShape);
                break;
            case "Sphere":
                currentShape = availableShapes.SPHERE;
                updateComponents(currentShape);
                break;
            case "Cube":
                currentShape = availableShapes.CUBE;
                updateComponents(currentShape);
                break;
            case "Cone":
                currentShape = availableShapes.CONE;
                updateComponents(currentShape);
                break;
            case "Cylinder":
                currentShape = availableShapes.CYLINDER;
                updateComponents(currentShape);
                break;
            case "Torus":
                currentShape = availableShapes.TORUS;
                updateComponents(currentShape);
                break;
            case "shapeButton":
                updateShape(currentShape);
                break;
            default:
                break;
        }
    }

    /*
     * Resets all of the components and set's the shape of the bottomPanel to the selected shape.
     */
    //keeps the actionPerformed method a little shorter.
    private void updateComponents(availableShapes currentShape){
        switch(currentShape){
            case TRIANGLE:
                input1TextField.setText("");
                input2TextField.setText("");
                areaVolumeTextField.setText("");
                areaVolumeLabel.setText("Area");
                inputLabel1.setText("Base");
                inputLabel2.setText("Height");
                inputLabel2.setVisible(true);
                input2TextField.setVisible(true);
                shapeButton.setText("Update Triangle");
                bottomPanel.setShape(shapeMap2D.get("Triangle"));
                break;
            case SQUARE:
                input1TextField.setText("");
                input2TextField.setText("");
                areaVolumeTextField.setText("");
                areaVolumeLabel.setText("Area");
                inputLabel1.setText("Length");
                inputLabel2.setText("");
                inputLabel2.setVisible(false);
                input2TextField.setVisible(false);
                shapeButton.setText("Update Square");
                bottomPanel.setShape(shapeMap2D.get("Square"));
                break;
            case RECTANGLE:
                input1TextField.setText("");
                input2TextField.setText("");
                areaVolumeTextField.setText("");
                areaVolumeLabel.setText("Area");
                inputLabel1.setText("Length");
                inputLabel2.setText("Width");
                inputLabel2.setVisible(true);
                input2TextField.setVisible(true);
                shapeButton.setText("Update Rectangle");
                bottomPanel.setShape(shapeMap2D.get("Rectangle"));
                break;
            case CIRCLE:
                input1TextField.setText("");
                input2TextField.setText("");
                areaVolumeTextField.setText("");
                areaVolumeLabel.setText("Area");
                inputLabel1.setText("Radius");
                inputLabel2.setText("");
                inputLabel2.setVisible(false);
                input2TextField.setVisible(false);
                shapeButton.setText("Update Circle");
                bottomPanel.setShape(shapeMap2D.get("Circle"));
                break;
            case SPHERE:
                input1TextField.setText("");
                input2TextField.setText("");
                areaVolumeTextField.setText("");
                areaVolumeLabel.setText("Volume");
                inputLabel1.setText("Radius");
                inputLabel2.setVisible(false);
                input2TextField.setVisible(false);
                shapeButton.setText("Update Sphere");
                bottomPanel.setShape(shapeMap3D.get("Sphere"));
                break;
            case CUBE:
                input1TextField.setText("");
                input2TextField.setText("");
                areaVolumeTextField.setText("");
                areaVolumeLabel.setText("Volume");
                inputLabel1.setText("Edge");
                inputLabel2.setVisible(false);
                input2TextField.setVisible(false);
                shapeButton.setText("Update Cube");
                bottomPanel.setShape(shapeMap3D.get("Cube"));
                break;
            case CONE:
                input1TextField.setText("");
                input2TextField.setText("");
                areaVolumeTextField.setText("");
                areaVolumeLabel.setText("Volume");
                inputLabel1.setText("Radius");
                inputLabel2.setText("Height");
                inputLabel2.setVisible(true);
                input2TextField.setVisible(true);
                shapeButton.setText("Update Cone");
                bottomPanel.setShape(shapeMap3D.get("Cone"));
                break;
            case CYLINDER:
                input1TextField.setText("");
                input2TextField.setText("");
                areaVolumeTextField.setText("");
                areaVolumeLabel.setText("Volume");
                inputLabel1.setText("Radius");
                inputLabel2.setText("Height");
                inputLabel2.setVisible(true);
                input2TextField.setVisible(true);
                shapeButton.setText("Update Cylinder");
                bottomPanel.setShape(shapeMap3D.get("Cylinder"));
                break;
            case TORUS:
                input1TextField.setText("");
                input2TextField.setText("");
                areaVolumeTextField.setText("");
                areaVolumeLabel.setText("Volume");
                inputLabel1.setText("Major Radius");
                inputLabel2.setText("Minor Radius");
                inputLabel2.setVisible(true);
                input2TextField.setVisible(true);
                shapeButton.setText("Update Torus");
                bottomPanel.setShape(shapeMap3D.get("Torus"));
                break;
            default:
                break;
        }
        this.repaint(); //Was having issues with draw operations not being called. This forces the redraw.
    }

    //Adds all of the items from the Keys of each map to the appropriate JMenu
    private JMenuBar initMenu(HashMap<String, TwoDimensionalShape> shapes2D, HashMap<String, ThreeDimensionalShape> shapes3D){
        JMenuBar menuBar = new JMenuBar();
        JMenu shapeMenu2D = new JMenu("2D Shapes");
        JMenu shapeMenu3D = new JMenu("3D Shapes");
        if (!shapes2D.isEmpty()){
            for(int i = 0; i < shapes2D.size(); i++){
                String key = shapes2D.keySet().toArray()[i].toString();
                shapeMenu2D.add(new JMenuItem(key));
                shapeMenu2D.getItem(i).addActionListener(this);
                shapeMenu2D.getItem(i).setActionCommand(key);
            }
        }
        if (!shapes3D.isEmpty()){
            for(int i = 0; i < shapes3D.size(); i++){
                String key = shapes3D.keySet().toArray()[i].toString();
                shapeMenu3D.add(new JMenuItem(key));
                shapeMenu3D.getItem(i).addActionListener(this);
                shapeMenu3D.getItem(i).setActionCommand(key);
            }
        }
        menuBar.add(shapeMenu2D);
        menuBar.add(shapeMenu3D);
        return menuBar;
    }
    
    /*
     * Uses a switch on the currentShape state and validates the appropriate textfields for positive numbers. The try/catch
     * blocks are to catch any IllegalArgumentExceptions and send the message to a JOptionPane (only really necessary for
     * the Torus, but used in all of them just in case I missed something).
     * 
     * Once the validation checks pass, calls the constructor for currentShape and replaces it's old value in the appropriate
     * map, then gets and displays the area.
     */
    private void updateShape(availableShapes currentShape){
        switch(currentShape){
            case TRIANGLE:
                if (validateInput(input1TextField.getText()) && validateInput(input2TextField.getText())){
                    double base = Double.valueOf(input1TextField.getText());
                    double height = Double.valueOf(input2TextField.getText());
                    try{
                        shapeMap2D.replace("Triangle", new Triangle(base, height));
                        bottomPanel.setShape(shapeMap2D.get("Triangle"));
                        areaVolumeTextField.setText(String.valueOf(shapeMap2D.get("Triangle").getArea()));
                    }
                    catch(IllegalArgumentException ex){
                        JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                break;
            case RECTANGLE:
                if (validateInput(input1TextField.getText()) && validateInput(input2TextField.getText())){
                    double width = Double.valueOf(input1TextField.getText());
                    double length = Double.valueOf(input2TextField.getText());
                    try{
                        shapeMap2D.replace("Rectangle", new Rectangle(length, width));
                        bottomPanel.setShape(shapeMap2D.get("Rectangle"));
                        areaVolumeTextField.setText(String.valueOf(shapeMap2D.get("Rectangle").getArea()));
                    }
                    catch(IllegalArgumentException ex){
                        JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                break;
            case SQUARE:
                if (validateInput(input1TextField.getText())){
                    double length = Double.valueOf(input1TextField.getText());
                    try {
                        shapeMap2D.replace("Square", new Square(length));
                        bottomPanel.setShape(shapeMap2D.get("Square"));
                        areaVolumeTextField.setText(String.valueOf(shapeMap2D.get("Square").getArea()));
                        }
                        catch(IllegalArgumentException ex){
                            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }
                }
                break;
            case CIRCLE:
                if (validateInput(input1TextField.getText())){
                    double radius = Double.valueOf(input1TextField.getText());
                    try {
                            shapeMap2D.replace("Circle", new Circle(radius));
                            bottomPanel.setShape(shapeMap2D.get("Circle"));
                            areaVolumeTextField.setText(String.valueOf(shapeMap2D.get("Circle").getArea()));
                        }
                        catch(IllegalArgumentException ex){
                            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }
                }
                break;
            case SPHERE:
                if (validateInput(input1TextField.getText())){
                    double radius = Double.valueOf(input1TextField.getText());
                    try{
                        shapeMap3D.replace("Sphere", new Sphere(radius));
                        bottomPanel.setShape(shapeMap3D.get("Sphere"));
                        areaVolumeTextField.setText(String.valueOf(shapeMap3D.get("Sphere").getVolume()));
                    }
                    catch(IllegalArgumentException ex){
                        JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                break;
            case CUBE:
                if (validateInput(input1TextField.getText())){
                    double edge = Double.valueOf(input1TextField.getText());
                    try{
                        shapeMap3D.replace("Cube", new Cube(edge));
                        bottomPanel.setShape(shapeMap3D.get("Cube"));
                        areaVolumeTextField.setText(String.valueOf(shapeMap3D.get("Cube").getVolume()));
                    }catch(IllegalArgumentException ex){
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                
                }
                break;
            case CYLINDER:
                if (validateInput(input1TextField.getText()) && validateInput(input2TextField.getText())){
                    double radius= Double.valueOf(input1TextField.getText());
                    double height = Double.valueOf(input2TextField.getText());
                    try{
                        shapeMap3D.replace("Cylinder", new Cylinder(radius, height));
                        bottomPanel.setShape(shapeMap3D.get("Cylinder"));
                        areaVolumeTextField.setText(String.valueOf(shapeMap3D.get("Cylinder").getVolume()));
                    }
                    catch(IllegalArgumentException ex){
                        JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                break;
            case CONE:
                if (validateInput(input1TextField.getText()) && validateInput(input2TextField.getText())){
                    double radius= Double.valueOf(input1TextField.getText());
                    double height = Double.valueOf(input2TextField.getText());
                    try{
                        shapeMap3D.replace("Cone", new Cone(radius, height));
                        bottomPanel.setShape(shapeMap3D.get("Cone"));
                        areaVolumeTextField.setText(String.valueOf(shapeMap3D.get("Cone").getVolume()));
                    }
                    catch(IllegalArgumentException ex){
                        JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                break;
            case TORUS:
                if (validateInput(input1TextField.getText()) && validateInput(input2TextField.getText())){
                    double minRad= Double.valueOf(input1TextField.getText());
                    double maxRad = Double.valueOf(input2TextField.getText());
                    try{
                        shapeMap3D.replace("Torus", new Torus(minRad, maxRad));
                        bottomPanel.setShape(shapeMap3D.get("Torus"));
                        areaVolumeTextField.setText(String.valueOf(shapeMap3D.get("Torus").getVolume()));
                    }
                    catch(IllegalArgumentException ex){
                        JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                break;
            default:
                break;
        }
        this.repaint(); //ensures a redraw occurs whenever the shapes are updated
    }

    //Checks for positive numbers and outputs a message if they aren't.
    private boolean validateInput(String input){
        double output = 0;
        String message = "";
        try{
            output = Double.valueOf(input);
            if (output <= 0){
                message = "Input must be a positive value greater than 0.";
                JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }catch(NumberFormatException ex){
            message = "Input must be a number.";
            JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    //used to initialize the hashmaps for 2d and 3d shapes.
    private HashMap<String, TwoDimensionalShape> initialize2DMap(){
        HashMap<String, TwoDimensionalShape> output = new HashMap<String, TwoDimensionalShape>();

        Triangle triangle = new Triangle();
        Circle circle = new Circle();
        Rectangle rect = new Rectangle();
        Square square = new Square();
        
        output.put(triangle.getName(), triangle);
        output.put(circle.getName(), circle);
        output.put(rect.getName(), rect);
        output.put(square.getName(), square);
        return output;
    }
    private HashMap<String, ThreeDimensionalShape> initialize3DMap(){
        HashMap<String, ThreeDimensionalShape> output = new HashMap<String, ThreeDimensionalShape>();
        Sphere sphere = new Sphere();
        Cube cube = new Cube();
        Cylinder cylinder = new Cylinder();
        Cone cone = new Cone();
        Torus torus = new Torus();

        output.put(sphere.getName(), sphere);
        output.put(cube.getName(), cube);
        output.put(cylinder.getName(), cylinder);
        output.put(cone.getName(), cone);
        output.put(torus.getName(), torus);

        return output;
    }
    public String getTitle(){
        return this.title;
    }
    public JMenuBar getMenu(){
        return menuBar;
    }
}
