/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Element;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel; 
import javax.swing.JSlider;
import javax.swing.border.TitledBorder;
/**
 *
 * @author gracesubianto 16946441
 */
public class HotplateGUI extends JPanel implements MouseListener
{
    private JSlider temperature;
    private JSlider heatConstant;
    private DrawPanel drawPanel;
    private int mousePx = 0;
    private int mousePy = 0;
    Element element = new Element(25);
    Element[][] elements = new Element[20][20];
    
    /**
     * Constructor, initializes all elements of the GUI
     */
    public HotplateGUI() 
    {
        super(new BorderLayout());
        drawPanel = new DrawPanel();
        
        //Temperature slider
        this.temperature = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
        this.temperature.setLocation(10, 40);
        this.temperature.setMajorTickSpacing(10);
        this.temperature.setMinorTickSpacing(2);
        this.temperature.setPaintTicks(true);
        //this.temperature.addChangeListener(l);
        
        //HeatConstant slider
        this.heatConstant = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
        this.heatConstant.setLocation(10, 40);
        this.heatConstant.setMajorTickSpacing(10);
        this.heatConstant.setMinorTickSpacing(2);
        this.heatConstant.setPaintTicks(true);
        
        JPanel sliderPanel1 = new JPanel();
        JPanel sliderPanel2 = new JPanel();
        sliderPanel1.add(temperature);
        sliderPanel1.setPreferredSize(new Dimension(600, 80));
        sliderPanel1.setBorder(BorderFactory.createTitledBorder(null, "Temperature slider 0 - 1000", TitledBorder.LEFT, TitledBorder.CENTER));
        sliderPanel2.add(heatConstant);
        sliderPanel2.setPreferredSize(new Dimension(600, 90));
        sliderPanel2.setBorder(BorderFactory.createTitledBorder(null, "HeatConstant slider 0.01 - 1.0", TitledBorder.LEFT, TitledBorder.CENTER));
        
        add(drawPanel, BorderLayout.NORTH);
        add(sliderPanel1, BorderLayout.CENTER);
        add(sliderPanel2, BorderLayout.SOUTH);
        addMouseListener(this);
        
        for(int i = 0; i <= elements.length; i++)
        {
            for(int j = 0; j <= elements.length; j++)
            {
                elements[0][0] = new Element(0);
            }
        }
                
    }

    /**
     * When the mouse is clicked on the panel, x and y coordinates are printed to the console
     * @param e 
     */
    @Override
    public void mouseClicked(MouseEvent e) 
    {
        mousePx = e.getX()/20;
        mousePy = e.getY()/20;
        System.out.println("X: " + mousePx + ", Y: " + mousePy);
        setBackground(Color.RED);
    }

    //Default methods, not in use but not a good idea to delete
    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    /**
     * Method to draw the hotplate grid
     */
    public class DrawPanel extends JPanel 
    {
        public DrawPanel() 
        {
            setPreferredSize(new Dimension(600, 600));
            setBackground(Color.BLUE); 
        }
        
        @Override
        public void paintComponent(Graphics g) 
        {
            super.paintComponent(g);
            int boxSizeX = getWidth()/20;
            int boxSizeY = getHeight()/20;
            
            for(int i=0; i<20; i++)
            {   
                for(int j=0; j<20; j++)
                {   
                    g.setColor(Color.red);
                    g.drawRect(j*boxSizeX, i*boxSizeY, boxSizeX, boxSizeY);
                    
                }
            }
        }   
    }

    public static void main(String[] args) 
    {
        HotplateGUI myPanel = new HotplateGUI();
        JFrame frame = new JFrame("Hotplate");
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().add(myPanel);
        frame.setResizable(false);
        frame.pack(); //pack frame
        frame.setSize(600, 800);
        frame.setVisible(true); //show the frame
    }
}
