package geometry;
/* Vincent Testagrossa
 * CMSC 335 - Inheritance with Shapes
 * 
 * Abstract class TwoDimensionalShape -
 * Calls the Parent constructor and ensures there are two dimensions. Introduces the abstract method getArea() and the field
 * double area, which all 2D shapes will have.
 */
public class TwoDimensionalShape extends Shape {
    double area;
    public TwoDimensionalShape(){
        this(1, "2D Shape");
    }
    public TwoDimensionalShape(double area, String name){
        super(2, name);
        this.area = area;
    }
    public double getArea(){
        return area;
    }
}
