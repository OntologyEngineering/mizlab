package talkabout;

import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.io.*;

public class talkabout extends Applet {
  BorderLayout borderLayout1 = new BorderLayout();

  DrawPanel drawpanel;
  ControlPanel controlpanel;
  BorderLayout borderLayout2 = new BorderLayout();
  FlowLayout flowLayout1 = new FlowLayout();
  List list1 = new List();

  /**アプレットの構築*/
  public talkabout() {
  }
  /**アプレットの初期化*/
  public void init() {
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  /**コンポーネントの初期化*/
  private void jbInit() throws Exception {
    this.setLayout(borderLayout1);
    drawpanel = new DrawPanel();
    controlpanel = new ControlPanel();
    drawpanel.setBackground(Color.lightGray);
    drawpanel.setForeground(Color.black);


    controlpanel.setLayout(flowLayout1);
    controlpanel.setBackground(Color.gray);
    controlpanel.txtWord.setBackground(Color.white);
    list1.addItemListener(new java.awt.event.ItemListener() {
      public void itemStateChanged(ItemEvent e) {
        list1_itemStateChanged(e);
      }
    });
    this.add(drawpanel, BorderLayout.CENTER);
    this.add(controlpanel, BorderLayout.SOUTH);
    this.add(list1,BorderLayout.EAST);

    controlpanel.base = this;
    controlpanel.base.list1 = this.list1;
    controlpanel.panel = this.drawpanel;
    drawpanel.control = this.controlpanel;

  }
  /**アプレットの開始*/
  public void start() {

  }
  /**アプレットの停止*/
  public void stop() {
  }
  /**アプレットの破棄*/
  public void destroy() {
    String filename = "log";
    String dir = this.getCodeBase().toString();
    dir = dir.substring(6,dir.length()-1);
    //System.out.println(dir + filename);
    StringBuffer w = new StringBuffer();
    try{
      File file = new File(dir,filename);
      BufferedWriter bw = new BufferedWriter(new FileWriter(file));
      for(int i=0;i<controlpanel.log.size();i++){
        bw.write((String)controlpanel.log.elementAt(i));
        bw.newLine();
      }
      bw.close();
    }
    catch(IOException e){
      System.out.println("IOException:"+e.getMessage());
    }

    remove(drawpanel);
    remove(controlpanel);
  }
  /**アプレットの情報取得*/
  public String getAppletInfo() {
    return "Applet Information";
  }

  public static void main(String[] args){
    Frame f = new Frame();
    talkabout base = new talkabout();
    base.init();
    base.start();
    f.add("Center",base);
    f.addWindowListener(new WindowAdapter() {
        public void windowClosing(WindowEvent e)  {
        System.exit(0);
        }
    });
    f.setSize(600,500);
    f.setVisible(true);
  }

  void list1_itemStateChanged(ItemEvent e) {
    //System.out.println("*******itemStateChanged ");
    //タイトルの選択
    controlpanel.txtWord.setText(list1.getSelectedItem());
  }
}