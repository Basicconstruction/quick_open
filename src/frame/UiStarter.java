package frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UiStarter extends JFrame {
    public boolean setSmuggleAccordingToSpecificLocation = false;
    public int x_smuggle = 0;
    public int y_smuggle = 0;
    public State state = State.UI_ON;
    public JPanel cont = new JPanel();
    public int ui_width = 200;
    public int ui_height = 200;

    public UiStarter(){
        super();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setDefaultLookAndFeelDecorated(false);
        setSize(ui_width+Utils.x,ui_height+Utils.y);
        setLocation(new Point(800,100));//need to update
        System.out.println(isDefaultLookAndFeelDecorated());
        setLayout(null);
        getContentPane().add(cont);
        cont.setBounds(0,0,ui_width,ui_height);
        cont.setLayout(null);
        cont.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                emerge();
            }

            @Override
            public void mouseExited(MouseEvent e) {
//                smuggle();
            }
        });
//        cont.setBackground(Color.RED);
        JButton smuggleButton = new JButton("smuggle");
        cont.add(smuggleButton);
        smuggleButton.setBounds(50,70,100,40);
        smuggleButton.addActionListener(e->{
            smuggle();
        });

        JButton big = new JButton("big");
        cont.add(big);
        big.setBounds(50,20,100,40);
        big.addActionListener(e->{
            UiStarter.this.setVisible(false);
            Utils.temporaryCar.setLocation(Math.min(UiStarter.this.getX(), 1400),UiStarter.this.getY());
            Utils.temporaryCar.setVisible(true);
        });


    }
    public void emerge(){
        if(state==State.UI_SMUGGLE){
            setLocation(new Point(getX(),0));
            state = State.UI_ON;
        }
    }
    public void smuggle(){
        if(setSmuggleAccordingToSpecificLocation){
            smuggle(x_smuggle,y_smuggle);
        }else{
            int x = this.getX();
            smuggle(x, 0.85F);
        }
    }
    public void smuggle(int x,float factor){
        smuggle(x,-(int)(this.getHeight()*factor));
    }
    public void smuggle(int x,int y){
        smuggle(new Point(x,y));
    }
    public void smuggle(Point p){
        setLocation(p);
        state = State.UI_SMUGGLE;
    }
}
