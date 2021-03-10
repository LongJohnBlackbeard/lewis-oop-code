import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;



class Face extends JPanel {

    //setter and getter for smiling
    public final void setSmiling(int smileIn) { smile = smileIn; }
    public final int getSmiling() { return smile; }
    private int smile;
    private int leftEyePositionX;
    private int leftEyePositionY;
    private int rightEyePositionX;
    private int rightEyePositionY;
    private int red;
    private int green;
    private int blue;


    // constructor that sets attributes
    public Face() {
        leftEyePositionX = 0;
        leftEyePositionY = 0;
        rightEyePositionX = 0;
        rightEyePositionY = 0;
    }

    public Face(int width, int height) {
        leftEyePositionX = 23;
        leftEyePositionY = 20;
        rightEyePositionX = 43;
        rightEyePositionY = 20;
        red = generateRandom.getRandomValue(0, 255);
        green = generateRandom.getRandomValue(0,255);
        blue = generateRandom.getRandomValue(0, 255);
        smile = generateRandom.getRandomValue(0,3);

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLACK);
        g.drawOval(5,5,65,65);
        g.setColor(new Color(generateRandom.getRandomValue(0, 255),
                generateRandom.getRandomValue(0, 255),
                generateRandom.getRandomValue(0, 255)));
        g.fillOval(5,5,65,65);
        g.setColor(Color.BLACK);
        g.drawOval(leftEyePositionX, leftEyePositionY, 10, 10);
        g.setColor(new Color(red,green,blue));
        g.fillOval(leftEyePositionX, rightEyePositionY, 10, 10);

        g.setColor(Color.BLACK);
        g.drawOval(rightEyePositionX, rightEyePositionY, 10, 10);
        g.setColor(new Color(red, green, blue));
        g.fillOval(rightEyePositionX, rightEyePositionY, 10, 10);




        if (getSmiling() == 0) {
            g.drawArc(23, 30, 30, 20, 220, 100);
        } else if (getSmiling() == 1) {
            g.drawArc(23, 45, 30, 20, 45, 90);
        } else {
            g.drawArc(23, 45, 30, 0, 220, 100);
        }
    }
}

class generateRandom {
    public static int getRandomValue(int Min, int Max) {

        return ThreadLocalRandom.current().nextInt(Min, Max + 1);
    }
    public static float getRandomValue(float Min, float Max) { return ThreadLocalRandom.current().nextFloat(); }
}

class LetterTile extends JPanel implements MouseListener{
    private int red, green, blue;
    private String letter;
    private int shape;
    Face myFace = new Face(getWidth(),getHeight());
    private boolean condition = false;


    LetterTile() {
        super();
        SetRandomRGB();
        SetRandomLetter();
        SetRandomShape();
        this.addMouseListener(this);

    }

    final public void SetRandomShape() {
        shape = ThreadLocalRandom.current().nextInt(0,2);

    }

    final public void SetRandomLetter() {
        Random rnd = new Random();
        char c = (char) ('A' + rnd.nextInt(26));
        letter = String.valueOf(c);


    }
    final public void SetRandomRGB() {
        red = RandomRGB(0, 255);
        green = RandomRGB(0, 255);
        blue = RandomRGB(0, 255);

    }

    private static int ContrastRGB(int colorIn) {
        return ((colorIn + 128) % 256);
    }

    private static int RandomRGB(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max +1);

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);


        int panelWidth = getWidth();
        int panelHeight = getHeight();

        if (condition == false) {
            g.setColor(new Color(red, green, blue));
            if (shape == 1) {
                g.fillRect(0, 0, panelWidth, panelHeight);
            } else {
                g.fillOval(0, 0, panelWidth, panelHeight);
            }
            g.setColor(new Color(ContrastRGB(red), ContrastRGB(green), ContrastRGB(blue)));

            final int fontsize = 30;
            g.setFont(new Font("Times New Roman", Font.BOLD, fontsize));
            FontRenderContext context = g.getFontMetrics().getFontRenderContext();
            Font messageTextFont = new Font("Times New Roman", Font.BOLD, fontsize);
            TextLayout txt = new TextLayout(letter, messageTextFont, context);
            Rectangle2D bounds = txt.getBounds();
            int stringX = (int) ((getWidth() - (int) bounds.getWidth()) / 2);
            int stringY = (int) ((getHeight() / 2) + (int) (bounds.getHeight() / 2));
            g.drawString(letter, stringX, stringY);
            repaint();
        } else {
            myFace.paintComponent(g);
            repaint();

        }
    }


    @Override
    public void mouseClicked(MouseEvent e) {

        if (condition == false) {
            condition = true;
        } else {
            condition = false;
        }
        System.out.println(condition);





    }

    @Override
    public void mousePressed(MouseEvent r) {

    }

    @Override
    public void mouseReleased(MouseEvent r) {

    }

    @Override
    public void mouseEntered(MouseEvent r) {

    }

    @Override
    public void mouseExited(MouseEvent r) {

    }
}

class MosaicFrame extends JFrame implements ActionListener {
    private ArrayList<LetterTile> letterList;
    private ArrayList<Face> faceList;
    private LetterTile Tilling[][];


    public MosaicFrame() {
        setBounds(50,50,972,972);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        contentPane.add(buttonPanel, BorderLayout.SOUTH);

        JButton randomize = new JButton("Randomize!");
        buttonPanel.add(randomize);
        randomize.addActionListener(this);

        JPanel faceGridPanel = new JPanel();
        contentPane.add(faceGridPanel, BorderLayout.CENTER);
        faceGridPanel.setLayout(new GridLayout(12,12));

        JPanel letterGridPanel = new JPanel();
        contentPane.add(letterGridPanel, BorderLayout.CENTER);
        letterGridPanel.setLayout(new GridLayout(12,12));

        faceList = new ArrayList<Face>();
        letterList = new ArrayList<LetterTile>();

        for(int i=1; i<145; i++) {

            LetterTile tile = new LetterTile();
            letterGridPanel.add(tile);
            letterList.add(tile);
        }
    }

    public void actionPerformed(ActionEvent e) {
        for (LetterTile tile:letterList) {
            tile.SetRandomRGB();
            tile.SetRandomLetter();
            tile.SetRandomShape();
        }
        repaint();
    }

}

public class Mosaic {
    public static void main(String[] args) {
        System.out.println("Mosaic Starting...");

        MosaicFrame myMosaicFrame = new MosaicFrame();
        myMosaicFrame.setVisible(true);

    }
}
