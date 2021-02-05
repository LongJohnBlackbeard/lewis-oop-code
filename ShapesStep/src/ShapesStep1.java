abstract class Shape {
    public Shape() {
        this(0,0,0,0);

    }

    public Shape(int widthIn, int heightIn) {
        this(widthIn, heightIn, 0, 0);
    }

    public Shape(int widthIn, int heightIn, int positionXIn, int positionYIn) {
        setWidth(widthIn);
        setHeight(heightIn);
        setPositionX(positionXIn);
        setPositionY(positionYIn);
    }

    private int width;
    public final void setWidth(int widthIn) {width = widthIn; }
    public final int getWidth() { return width;}

    private int height;
    public final void setHeight(int heightIn) {height = heightIn;}
    public final int getHeight() {return height;}


    private int positionX;
    public final void setPositionX(int positionXIn) {positionX = positionXIn;}
    public final int getPositionX() {return positionX;}

    private int positionY;
    public final void setPositionY(int positionYIn) {positionY = positionYIn;}
    public final int getPositionY() {return positionY;}


    abstract public double CalcArea();
}

class Rectangle extends Shape {
    public Rectangle() {
        this(0,0);
    }
    public Rectangle(int widthIn, int heightIn){
        super(widthIn, heightIn);
    }


    public double CalcArea() {
        return getWidth() * getHeight();
    }
}

public class ShapesStep1 {

    public static void main(String[] args) {
        System.out.println("ShapesStep1...");

        Rectangle myRectangle = new Rectangle(5,3);

        double area = myRectangle.CalcArea();
        System.out.format("Area = %.2f\n", area);


    }


}
