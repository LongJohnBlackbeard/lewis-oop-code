import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Graphics;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


// Face class that creates a face object when called.
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


    // default constructor
    public Face() {
        leftEyePositionX = 0;
        leftEyePositionY = 0;
        rightEyePositionX = 0;
        rightEyePositionY = 0;
    }
    // constructor the sets attributes
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
    //paint component that draws the face
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



        // smiling if statement that sets what smile the face uses
        if (getSmiling() == 0) {
            g.drawArc(23, 30, 30, 20, 220, 100);
        } else if (getSmiling() == 1) {
            g.drawArc(23, 45, 30, 20, 45, 90);
        } else {
            g.drawArc(23, 45, 30, 0, 220, 100);
        }
    }
}

// Container class that contains generate random methods
class generateRandom {
    public static int getRandomValue(int Min, int Max) {

        return ThreadLocalRandom.current().nextInt(Min, Max + 1);
    }
    public static float getRandomValue(float Min, float Max) { return ThreadLocalRandom.current().nextFloat(); }
}

// Lettetile class the create letter tile object, implements mouse listener
class LetterTile extends JPanel implements MouseListener{
    private int red, green, blue;
    private String letter;
    private int shape;
    Face myFace = new Face(getWidth(),getHeight());
    private boolean condition = false;

    // LetterTile constructor that sets all attributes
    LetterTile() {
        super();
        SetRandomRGB();
        SetRandomLetter();
        SetRandomShape();
        toStringMeth();
        this.addMouseListener(this);
    }
    // To string method that prints to terminal the tile attributes
    final public void toStringMeth(){
        String letterShape;
        if (shape == 1) {
            letterShape = "Square";
        } else {
            letterShape = "Circle";
        }
        String sf=String.format("LetterTile Letter: %s, Shape: %s, R: %d, G: %d, B: %d", letter,
                                letterShape, red, green, blue );
        System.out.println(sf);

    }
    // method that sets random shape for the tile.
    final public void SetRandomShape() {
        shape = ThreadLocalRandom.current().nextInt(0,2);
    }
    // method that sets random letter for the tile
    final public void SetRandomLetter() {
        Random rnd = new Random();
        char c = (char) ('A' + rnd.nextInt(26));
        letter = String.valueOf(c);
    }
    // method that sets random rgb values for the tile
    final public void SetRandomRGB() {
        red = RandomRGB(0, 255);
        green = RandomRGB(0, 255);
        blue = RandomRGB(0, 255);
    }
    // method that sets random rgb value for letter in the tile
    private static int ContrastRGB(int colorIn) {
        return ((colorIn + 128) % 256);
    }
    // method that calulcates random values for set rgb values.
    private static int RandomRGB(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max +1);
    }
    // paint component for letter tile
    public void paintComponent(Graphics g) {
        super.paintComponent(g);


        int panelWidth = getWidth();
        int panelHeight = getHeight();

        // uses if statement on a condition that is set from the mouselistener
        // condition false sets the tile as a lettertile, true sets it to a face.
        if (!condition) {
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

        } else {
            myFace.paintComponent(g);

        }
        repaint();
    }

    // mouse listeners
    @Override
    public void mouseClicked(MouseEvent e) {
        // uses mouseclicked event as a toggle for condition
        condition = !condition;

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
// JFrame class
class MosaicFrame extends JFrame implements ActionListener {
    private ArrayList<LetterTile> letterList;

    // constructor for the Frame
    public MosaicFrame() {
        setBounds(50,50,972,972);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //creates container for panels
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        // creates a jpanel button pannel with a border layer
        JPanel buttonPanel = new JPanel();
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
        // creates a jbutton for randomize and adds it to the buttonpanel
        JButton randomize = new JButton("Randomize!");
        buttonPanel.add(randomize);
        randomize.addActionListener(this);
        // creates panel for lettertile as a grid layer 12x12
        JPanel letterGridPanel = new JPanel();
        contentPane.add(letterGridPanel, BorderLayout.CENTER);
        letterGridPanel.setLayout(new GridLayout(12,12));

        // creates arraylist to hold lettertiles
        letterList = new ArrayList<LetterTile>();
        // for loop that generates tiles and adds them to arraylist
        for(int i=1; i<145; i++) {

            LetterTile tile = new LetterTile();
            letterGridPanel.add(tile);
            letterList.add(tile);
        }
    }
    // actionevent that is performed when randomize button is clicked
    public void actionPerformed(ActionEvent e) {
        for (LetterTile tile:letterList) {
            tile.SetRandomRGB();
            tile.SetRandomLetter();
            tile.SetRandomShape();
            tile.toStringMeth();
        }
        repaint();
    }
}
// main class that creates Jframe object and sets visibility
public class Mosaic {
    public static void main(String[] args) {
        System.out.println("Mosaic Starting...");

        MosaicFrame myMosaicFrame = new MosaicFrame();
        myMosaicFrame.setVisible(true);

    }
}
