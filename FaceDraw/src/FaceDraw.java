import javax.swing.*;
import java.awt.Graphics;
import java.awt.Color;
import java.util.concurrent.ThreadLocalRandom;

class Face extends Oval {

    public final void setAppHeight(int appHeightIn) { appHeight = appHeightIn;}
    public final int getAppHeight() { return appHeight;}
    private int appHeight;

    public final void setAppWidth(int appWidthIn) { appWidth = appWidthIn;}
    public final int getAppWidth() { return appWidth;}
    private int appWidth;


    public Face () {
        super(0,0,0,0,0) ;
    }

    public Face(int positionXIn, int positionYIn, int widthIn, int heightIn, int smileIn) {
        super(positionXIn, positionYIn,widthIn, heightIn, smileIn);
    }

    public Face (int appHeightIn, int appWidthIn) {
        setAppHeight(appHeightIn);
        setAppWidth(appWidthIn);
    }

    public void painComponent(Graphics g) {
        g.drawOval(getPositionX(), getPositionY(), getWidth(), getHeight());
    }


}

class generateRandom {
    public static int getRandomValue(int Min, int Max){
        return ThreadLocalRandom.current().nextInt(Min, Max + 1);
    }
}

class FacePanel extends JPanel {
    private Face myOvalDraw;

    public FacePanel() {
        myOvalDraw = new Face(200,200,160,240, 0);
    }

    public void paintComponent(Graphics g) {
        myOvalDraw.painComponent(g);

    }

}

public class FaceDraw {
    public static void main(String[] args) {
        System.out.println("FaceDraw");

        JFrame myFrame = new JFrame("Random Faces FaceDraw");
        myFrame.setBounds(100,100,900,900);
        myFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        FacePanel myFace = new FacePanel();
        myFrame.add(myFace);
        myFrame.setVisible(true);
    }
}
