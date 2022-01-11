package frame;

import javax.swing.*;
import java.awt.*;

public class TestItem extends JFrame{
    public TestItem(){
        super("testJPanel");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(500,100,500,200);
        Item i = new Item("v","C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe","vv","http://atpgroup.work/search_war/win_4.jpg");
        getContentPane().add(i);
        setLayout(null);
        i.setLocation(new Point(0,0));
        setVisible(true);
    }
    public static void main(String[] args) {
        TestItem ij = new TestItem();
    }
}
