package frame;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class Item extends JPanel {
    Clipboard cb = this.getToolkit().getSystemClipboard();
    public String runPath;
    public String tag;
    public String hashCode;
    public JLabel label;
    public JLabel iconLabel;
    public ImageIcon icon;
    public JButton open;
    public JButton copyPath;
    public Item(){
        super();
    }
    public Item(String runPath,String iconRes,String tag,String hashCode){
        super();
        setLocation(0,0);
        setBackground(new Color(0xabcdef));
        this.runPath = runPath;
        this.tag = tag.equals("")?runPath.split("/")[runPath.split("/").length-1]:tag;
        this.hashCode = hashCode;
        if(iconRes.startsWith("http")){
            /*
             * 从网络中获取地址
             * */
            try{
                icon = new ImageIcon(new URL(iconRes));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }else{
            //use absolute path
            icon = new ImageIcon(iconRes);
        }
        iconLabel = (icon!=null&&icon.getIconWidth()!=-1)?new JLabel(icon,JLabel.CENTER):new JLabel(iconRes,JLabel.CENTER);
        int ts = 400;
        int iw = 70;
        int bw = 100;
        int gap = (ts-iw-bw*2)/3;
        setSize(400,70);
        setLayout(null);
        label = new JLabel(runPath,JLabel.CENTER);
        open = new JButton("open");
        copyPath = new JButton("copy path");
        add(iconLabel);
        add(label);
        add(open);
        add(copyPath);
        iconLabel.setBounds(0,0,iw,iw);
        label.setBounds(iw,0,ts-iw,30);
        open.setBounds(iw+gap,30,bw,40);
        copyPath.setBounds(iw+bw+gap*2,30,bw,40);
        AddListener();
    }
    public void AddListener(){
        this.copyPath.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                cb.setContents(new StringSelection(runPath), (ClipboardOwner)null);
            }
        });
        this.open.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String finalSign = runPath.trim();
                if(finalSign.charAt(1)==':'){
                    /*使用默认程序 打开文本文件和图片等。
                     * */
                    Desktop dek = Desktop.getDesktop();
                    if(dek.isSupported(Desktop.Action.OPEN)){
                        try {
                            dek.open(new File(finalSign));
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }else if(startWith(finalSign,"http")||finalSign.split("\\.").length>=1){
                    Desktop dek = Desktop.getDesktop();
                    if(dek.isSupported(Desktop.Action.BROWSE)){
                        try {
//                            dek.browse(new URI(startWith(finalSign,"http")?finalSign:"https://"+finalSign));
                            dek.browse(new URI(finalSign));
                        } catch (IOException | URISyntaxException ex) {
                            ex.printStackTrace();
                        }
                    }
                }else{
                    //运行程序或脚本, 或许要扩展
                    Runtime rt = Runtime.getRuntime();
                    try {
                        Process process = rt.exec(finalSign.split(" "));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
//                    System.exit(0);
                }
            }
        });
    }
    public boolean startWith(String str, String with_str){
        String[] split_str = str.split("_",2);
        return split_str[0].equals(with_str);
    }
}
