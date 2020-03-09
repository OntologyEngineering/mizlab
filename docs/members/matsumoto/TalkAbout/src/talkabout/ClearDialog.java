package talkabout;

import java.awt.*;
import java.awt.event.*;
//import com.borland.jbcl.layout.*;

/**
 * �^�C�g��:
 * ����:
 * ���쌠:   Copyright (c) 2002
 * ��Ж�:
 * @author
 * @version 1.0
 */

public class ClearDialog extends Dialog {

  Panel panel1 = new Panel();
  BorderLayout borderLayout1 = new BorderLayout();
  Panel panel2 = new Panel();
  Button button1 = new Button();
  Button button2 = new Button();

  DrawPanel panel;
  ControlPanel control;
  CheckboxGroup chkgroup = new CheckboxGroup();
  boolean flag1;
  boolean flag2;
  boolean flag3;
  Panel panel3 = new Panel();
  BorderLayout borderLayout2 = new BorderLayout();
  Panel panel4 = new Panel();
  BorderLayout borderLayout3 = new BorderLayout();
  Checkbox chk1 = new Checkbox();
  Checkbox chk2 = new Checkbox();


  public ClearDialog(Frame frame, String title, boolean modal) {
    super(frame, title, modal);
    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    try {
      jbInit();
      add(panel1);
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }


  public ClearDialog(Frame frame, String title) {
    this(frame, title, true);
  }
  void jbInit() throws Exception {
    flag1 = false;
    flag2 = false;
    panel1.setLayout(borderLayout1);
    button1.setLabel("O.K.");
    button1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        button1_actionPerformed(e);
      }
    });
    button2.setLabel("Cancel");
    button2.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        button2_actionPerformed(e);
      }
    });
    panel3.setLayout(borderLayout2);
    panel4.setLayout(borderLayout3);
    chk1.setLabel("���ׂč폜����");
    chk1.addItemListener(new java.awt.event.ItemListener() {
      public void itemStateChanged(ItemEvent e) {
        chk1_itemStateChanged(e);
      }
    });
    chk1.setCheckboxGroup(chkgroup);
    chk2.setLabel("�I�����Ă��郉�x�����폜����");
    chk2.setCheckboxGroup(chkgroup);
    panel1.add(panel2, BorderLayout.SOUTH);
    panel2.add(button1, null);
    panel2.add(button2, null);
    panel1.add(panel3, BorderLayout.CENTER);
    panel3.add(panel4, BorderLayout.NORTH);
    panel4.add(chk1, BorderLayout.NORTH);
    panel3.add(chk2, BorderLayout.SOUTH);
  }
  protected void processWindowEvent(WindowEvent e) {
    if (e.getID() == WindowEvent.WINDOW_CLOSING) {
      cancel();
    }
    super.processWindowEvent(e);
  }
  void cancel() {
    dispose();
  }

  void button1_actionPerformed(ActionEvent e) {
    //System.out.println("OK Pressed");
    int selected_i = -1;
    if(chkgroup.getSelectedCheckbox()==null){
    }
    else{
    if(chkgroup.getSelectedCheckbox().getLabel().equals("���ׂč폜����")){
      flag1 = true;
      //System.out.println("flag1******");

      this.cancel();
    }

    else if(chkgroup.getSelectedCheckbox().getLabel().equals("�I�����Ă��郉�x�����폜����")){
      flag2 = true;
      //System.out.println("flag2******");

      this.cancel();
    }
    else{
      flag3 = true;
      //System.out.println("flag3******");

      this.cancel();
    }

    }
  }

  void button2_actionPerformed(ActionEvent e) {
    flag1 = false;
    flag2 = false;
    flag3 = false;

    this.cancel();
  }
  void chk1_itemStateChanged(ItemEvent e) {

  }



}