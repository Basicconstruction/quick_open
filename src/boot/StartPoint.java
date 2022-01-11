package boot;

import frame.UiStarter;
import frame.Utils;

import javax.swing.*;

public class StartPoint {
    public static void main(String[] args){
        JFrame.setDefaultLookAndFeelDecorated(false);
        Utils.uiStarter.setVisible(true);
    }
}
