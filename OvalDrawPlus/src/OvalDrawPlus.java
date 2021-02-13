import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;




class Oval extends JPanel {
    private Color myColor;
    private Color myRectColor;

    public void setRectColor(int red, int green, int blue) {
        myRectColor = new Color(red,green,blue);
    }
    public Color getRectColor() {
        return myRectColor;
    }


    public void setColor(int red, int green, int blue) {
        myColor = new Color(red,green,blue);
    }
    public Color getColor() {
        return myColor;
    }




    Oval() {
        setColor(255,0,0);
        setRectColor(0,0,255);
    }

    Oval(int red, int green, int blue, int redTwo, int greenTwo, int blueTwo) {
        setColor(red,green,blue);
        setRectColor(redTwo, greenTwo, blueTwo);
    }

    public void paintComponent(Graphics g) {


        super.paintComponent(g);

        int panelWidth = getWidth();
        int panelHeight = getHeight();


        g.setColor(myRectColor);
        g.fillRect(0,0,panelWidth,panelHeight);
        g.setColor(myColor);
        g.fillOval(0,0,panelWidth,panelHeight);

    }
}

class OvalFrame extends JFrame {
    OvalFrame() {
        setTitle("OvalDrawPlus");
        setBounds(250,150,400,500);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);


        Oval myOval = new Oval(92,54,133,0,0,255);
        Container contentPane = getContentPane();
        contentPane.add(myOval);

    }
}

public class OvalDrawPlus {
    public static void main(String[] args) {
        System.out.println("Starting OvalDrawPlus...");
        OvalFrame myFrame = new OvalFrame();
        myFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                System.out.println("Closing OvalDrawPlus...");
                System.exit(0);
            }
        });

        myFrame.setVisible(true);

    }
}
