package frame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;

public class TemporaryCar extends JFrame {
    public boolean test = false;
    public ArrayList<Item> items = new ArrayList<Item>();
    private JButton add = new JButton("add");
    private JButton small = new JButton("small");
//    private JPanel listItems = new JPanel(new GridLayout(3,1));
//    private JPanel listItems = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private JPanel listItems;
    private JScrollPane jScrollPane;
    private JDialog addItemDialog;
    private final JFrame me;
    public TemporaryCar(int x){
        super("临时巴士");
        setLocation(x,20);
        setSize(430+Utils.x,360+Utils.y);
        setLayout(null);
        getContentPane().add(add);
        getContentPane().add(small);
        add.setBounds(45,0,120,40);
        small.setBounds(255,0,120,40);
        listItems = new JPanel(null);
        listItems.setBounds(0,0,400,300);
        jScrollPane = new JScrollPane(listItems,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        getContentPane().add(jScrollPane);
        jScrollPane.setBounds(5,50,420,300);
        me = this;
        AddListeners();
    }
    public void AddListeners(){
        small.addActionListener(e->{
            me.setVisible(false);
            Utils.uiStarter.setLocation(me.getLocation());
            Utils.uiStarter.setVisible(true);
        });
        add.addActionListener(e->{
            if(addItemDialog==null){
                addItemDialog = new JDialog(me);
                Dialog d = addItemDialog;
                d.setLayout(null);
                d.setTitle("添加项目");
                d.setLocation(new Point(me.getLocation().x,me.getLocation().y+50));
                d.setSize(400+Utils.x,310+Utils.y);
                JTextField path = new JTextField();
                JLabel pathLabel = new JLabel("specific path");
                JTextField icon = new JTextField();
                JLabel iconLabel = new JLabel("icon res/text");
                JTextField tag = new JTextField();
                JLabel tagLabel = new JLabel("tag");
                JButton ok = new SButton("ok");
                JButton cancel = new SButton("cancel");
                JButton apply = new SButton("apply");
                pathLabel.setBounds(40,40,85,40);
                path.setBounds(140,40,230,40);
                iconLabel.setBounds(40,110,85,40);
                icon.setBounds(140,110,230,40);
                tagLabel.setBounds(40,180,85,40);
                tag.setBounds(140,180,230,40);

                ok.setBounds(200,274,60,36);
                cancel.setBounds(260,274,60,36);
                apply.setBounds(320,274,60,36);
                d.add(pathLabel);d.add(path);
                d.add(iconLabel);d.add(icon);
                d.add(tagLabel);d.add(tag);
                d.add(ok);d.add(cancel);d.add(apply);
                ok.addActionListener(ec->{
                    if(!path.getText().equals("")){
                        items.add(new Item(path.getText(),icon.getText(),tag.getText(),new Date().toString()));
                    }
                    Utils.temporaryCar.addChangedNotify();
                    if(!test){
                        path.setText("");
                        icon.setText("");
                        tag.setText("");
                    }
                    d.setVisible(false);
                });
                cancel.addActionListener(ec->{
                    d.setVisible(false);
                });
                apply.addActionListener(ec->{
                    if(!path.getText().equals("")){
                        items.add(new Item(path.getText(),icon.getText(),tag.getText(),new Date().toString()));
                    }
                });
                Utils.temporaryCar.addChangedNotify();
                d.setVisible(true);
            }else{
                addItemDialog.setVisible(true);
            }
        });
    }
    public void addChangedNotify(){
        if(items.size()>4){
            listItems.setSize(400,70*items.size());
            listItems.setPreferredSize(new Dimension(400,70* items.size()));
        }else{
            listItems.setSize(400,300);
            listItems.setPreferredSize(new Dimension(400,300));
        }
        listItems.removeAll();
        for(int i = 0;i<items.size();i++){
            Item item = items.get(i);
            listItems.add(item);
            item.setBounds(0,70*i,400,70);
        }
//        getContentPane().remove(jScrollPane);
//        jScrollPane = new JScrollPane(listItems,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
//                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
//        getContentPane().add(jScrollPane);
//        jScrollPane.setBounds(5,50,420,300);
        listItems.repaint();
        jScrollPane.repaint();
        jScrollPane.getVerticalScrollBar().setUnitIncrement(getNewScrollSpeed ( listItems.getHeight() ));
        setVisible(true);

    }
    int getNewScrollSpeed( int scrollPaneHeight ){
        return (int) ( scrollPaneHeight * 0.1 );
    }

}
