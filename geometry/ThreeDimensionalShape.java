package geometry;
/* Vincent Testagrossa
 * CMSC 335 - Inheritance with Shapes
 * 
 * Abstract class ThreeDimensionalShape -
 * Calls the Parent constructor and ensures there are now three dimensions. Introduces the abstract method getVolume(), and the
 * field double volume, which all 3D shapes will have.
 */
public class ThreeDimensionalShape extends Shape {
    double volume;
    public ThreeDimensionalShape(){
        this(1, "3D Shape");
    }
    public ThreeDimensionalShape(double volume, String name){
        super(3, name);
        this.volume = volume;
    }
    public double getVolume(){
        return volume;
    }
}
