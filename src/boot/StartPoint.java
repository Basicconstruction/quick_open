package boot;

import frame.UiStarter;

import javax.swing.*;

public class StartPoint {
    public static void main(String[] args){
        JFrame.setDefaultLookAndFeelDecorated(false);
        UiStarter uiStarter = new UiStarter();
        uiStarter.setVisible(true);
    }
}
