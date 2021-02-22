import javax.swing.*;
import java.awt.Graphics;
import java.awt.Color;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;
import java.awt.geom.Line2D;
import java.awt.*;

class OvalDraw extends Oval {

    public OvalDraw() {
        super(0, 0, 0, 0);
    }

    public OvalDraw(int positionXIn, int positionYIn, int widthIn, int heightIn) {
        super(positionXIn, positionYIn, widthIn, heightIn);
    }

    public void painComponent(Graphics g) {

        g.drawOval(getPositionX(), getPositionY(), getWidth(), getHeight());
        g.setColor(Color.getHSBColor(generateRandom.getRandomValue((float) 0,(float) 360),
                generateRandom.getRandomValue((float) 0.50,(float) 1.0),
                generateRandom.getRandomValue((float) 0.50,(float) 1.0)));
        g.fillOval(getPositionX(),getPositionY(),getWidth(),getHeight());

    }


}

class Face extends OvalDraw {
    public final void setAppHeight(int appHeightIn) { appHeight = appHeightIn; }
    public final int getAppHeight() { return appHeight; }
    private int appHeight;

    public final void setAppWidth(int appWidthIn) { appWidth = appWidthIn; }
    public final int getAppWidth() { return appWidth; }
    private int appWidth;

    public final void setSmiling(int smileIn) { smile = smileIn; }
    public final int getSmiling() { return smile; }
    private int smile;

    private final OvalDraw leftEye;
    private final OvalDraw rightEye;





    public Face(int positionXIn, int positionYIn, int widthIn, int heightIn, int smileIn) {
        super(0, 0, 0, 0);
        leftEye = new OvalDraw(0, 0, 0, 0);
        rightEye = new OvalDraw(0, 0, 0, 0);

    }

    public Face() {
        setPositionX(generateRandom.getRandomValue(50, 700));
        setPositionY(generateRandom.getRandomValue(50, 700));
        setWidth(generateRandom.getRandomValue(100, 500));
        setHeight(generateRandom.getRandomValue(100, 500));
        setSmiling(generateRandom.getRandomValue(0, 3));

        setAppWidth(900);
        setAppHeight(900);


        int eyeHeight = getHeight() / 4 ;
        int eyeWidth = getWidth() / 5;
        int leftEyePositionX = getPositionX() + (getWidth() / 5) - (eyeHeight / 7);
        int eyePositionY = getPositionY() + (getHeight() / 3) - (eyeHeight / 2);
        int rightEyePositionX = getPositionX() + (getWidth() / 2) + (eyeHeight / 5);
        leftEye = new OvalDraw(leftEyePositionX, eyePositionY, eyeWidth, eyeHeight);
        rightEye = new OvalDraw(rightEyePositionX, eyePositionY, eyeWidth, eyeHeight);


    }


    public void paintComponent(Graphics g) {
        super.painComponent(g);
        leftEye.painComponent(g);
        rightEye.painComponent(g);
        if (getSmiling() == 0) {
            g.drawArc(getPositionX(), getPositionY() - (getHeight() / 6), getWidth() - 10,
                    getHeight() - 10, 220, 100);
        } else if (getSmiling() == 1) {
            g.drawArc(getPositionX(), getPositionY() + (getHeight() / 2), getWidth() - 10, getHeight() - 10,
                    45, 90);
        } else {
            g.drawArc(getPositionX(), getPositionY() + (getHeight() / 2), getWidth() - 10,
                    0, 220, 100);
        }


    }
}

class generateRandom {
    public static int getRandomValue(int Min, int Max) {
        return ThreadLocalRandom.current().nextInt(Min, Max + 1);
    }
    public static float getRandomValue(float Min, float Max) { return ThreadLocalRandom.current().nextFloat(); }
}

class FacePanel extends JPanel {

    ArrayList<Face> FaceList = new ArrayList<Face>();
    public FacePanel() {
        int faceNumber = generateRandom.getRandomValue(3, 10);

        for (int i = 0; i < faceNumber; i++) {

            Face myFace = new Face();
            String face = myFace.toString();
            System.out.println(face);
            FaceList.add(myFace);

        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Face face : FaceList) {

            face.paintComponent(g);
        }
    }
}

public class FaceDraw {
    public static void main(String[] args) {
        System.out.println("FaceDraw");

        JFrame myFrame = new JFrame("Random Faces FaceDraw");
        myFrame.setBounds(100, 100, 900, 900);
        myFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        FacePanel myFace = new FacePanel();
        myFrame.add(myFace);
        myFrame.setVisible(true);
    }
}
