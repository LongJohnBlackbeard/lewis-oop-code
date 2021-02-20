import javax.swing.*;
import java.awt.Graphics;
import java.awt.Color;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;

class OvalDraw extends Oval {

    public OvalDraw() {
        super(0,0,0,0);
    }

    public OvalDraw(int positionXIn, int positionYIn, int widthIn, int heightIn) {
        super(positionXIn, positionYIn,widthIn, heightIn);
    }

    public void painComponent(Graphics g) {
        g.drawOval(getPositionX(), getPositionY(), getWidth(), getHeight());
    }


}

class Face extends OvalDraw{
    public final void setAppHeight(int appHeightIn) { appHeight = appHeightIn;}
    public final int getAppHeight() { return appHeight;}
    private int appHeight;

    public final void setAppWidth(int appWidthIn) { appWidth = appWidthIn;}
    public final int getAppWidth() { return appWidth;}
    private int appWidth;

    public final void setSmiling(int smileIn) { smile = smileIn;}
    public final int getSmiling() { return smile;}
    private int smile;

    private OvalDraw leftEye;
    private OvalDraw rightEye;

    public Face() {
        super(0,0,0,0);
        leftEye = new OvalDraw(0,0,0,0);
        rightEye = new OvalDraw(0,0,0,0);

    }

    public Face(int positionXIn, int positionYIn, int widthIn, int heightIn, int smileIn) {
        super(positionXIn, positionYIn, widthIn, heightIn);
        setSmiling(smileIn);

        int eyeHeight = heightIn / 5;
        int eyeWidth = widthIn / 4;
        int leftEyePositionX = positionXIn + (widthIn / 5) - (eyeHeight / 7);
        int eyePositionY = positionYIn + (heightIn / 3) - (eyeHeight / 2);
        int rightEyePositionX = positionXIn + (widthIn / 2) + (eyeHeight / 5);
        leftEye = new OvalDraw(leftEyePositionX, eyePositionY, eyeWidth, eyeHeight);
        rightEye = new OvalDraw(rightEyePositionX, eyePositionY, eyeWidth, eyeHeight);


    }


    public void paintComponent(Graphics g) {
        super.painComponent(g);
        leftEye.painComponent(g);
        rightEye.painComponent(g);
        if (smile == 0) {
            g.drawArc(getPositionX(), getPositionY() - (getHeight()/6), getWidth()-10,
                    getHeight()-10, 220, 100);
        } else if (smile == 1) {
            g.drawArc(getPositionX(),getPositionY()+(getHeight()/2), getWidth()-10,getHeight()-10,
                    45, 90);
        } else {
            g.drawArc(getPositionX(), getPositionY() + (getHeight()/ 2), getWidth()-10,
                    0, 220, 100);
        }
        g.setColor(Color.black);

    }
}

class generateRandom {
    public static int getRandomValue(int Min, int Max){
        return ThreadLocalRandom.current().nextInt(Min, Max + 1);
    }
}

class FacePanel extends JPanel {
    private Face myFace;

    int faceNumber = generateRandom.getRandomValue(3,10);
    ArrayList<Face> FaceList = new ArrayList<Face>();

    public FacePanel() {
        for (int i=0; i < faceNumber; i++)

            myFace = new Face(generateRandom.getRandomValue(50,700),generateRandom.getRandomValue(50,700),
                    generateRandom.getRandomValue(50,500),generateRandom.getRandomValue(50,500),
                    generateRandom.getRandomValue(0,3));
            System.out.println(myFace);

            FaceList.add(myFace);


    }

    public void paintComponent(Graphics g) {

        for(int i=0; i < FaceList.size(); i++){
            super.paintComponent(g);
            FaceList.get(i).paintComponent(g);
        }
    }
}

public class FaceDraw {
    public static void main(String[] args) {
        System.out.println("FaceDraw");

        JFrame myFrame = new JFrame("Random Faces FaceDraw");
        myFrame.setBounds(100,100, 900,900);
        myFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        FacePanel myFace = new FacePanel();
        myFrame.add(myFace);
        myFrame.setVisible(true);
    }
}
