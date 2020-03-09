package talkabout;

import java.awt.event.*;
import java.awt.*;
import java.awt.List;
import java.applet.*;
import java.io.*;
import java.lang.*;
import java.util.*;
//import javax.swing.*;

public class ControlPanel extends Panel{
  talkabout base;
  DrawPanel panel;

  Vector log = new Vector();
  int node_no=0;


  FlowLayout flowLayout1 = new FlowLayout();
  TextField txtWord = new TextField(20);
  Button btnArrow = new Button();
  Button btnOK = new Button();
  List list1;
  Button btnClear = new Button();

  public ControlPanel() {
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  private void jbInit() throws Exception {
    base = new talkabout();
    base.controlpanel = this;


    this.setLayout(flowLayout1);
    btnOK.setFont(new java.awt.Font("SansSerif", 0, 12));
    btnOK.setLabel("����");
    btnOK.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        btnOK_actionPerformed(e);
      }
    });
    txtWord.setFont(new java.awt.Font("SansSerif", 0, 14));

    btnArrow.setFont(new java.awt.Font("Dialog", 1, 12));
    btnArrow.setLabel("�E");
    btnArrow.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        btnArrow_actionPerformed(e);
      }
    });
    btnClear.setFont(new java.awt.Font("SansSerif", 0, 12));
    btnClear.setLabel("�N���A");
    btnClear.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        btnClear_actionPerformed(e);
      }
    });
    this.add(txtWord, null);
    this.add(btnArrow, null);
    this.add(btnOK, null);
    this.add(btnClear, null);


  }

  void btnArrow_actionPerformed(ActionEvent e) {
    String lbl = btnArrow.getLabel();

    if(panel.nodes.size()==0){
    }
    else{
      if(lbl.equals("�E")){
        btnArrow.setLabel("��");
      }
      else if(lbl.equals("��")){
        btnArrow.setLabel("��");
      }
      else{
        btnArrow.setLabel("�E");
      }
    }

  }

  void btnOK_actionPerformed(ActionEvent e) {
      int i;
      int count;
      String rtn;
      String word;
      String lbl = btnArrow.getLabel();
      StringTokenizer tokenizer_rtn;
      boolean same = false;
      boolean same2 = false;

      word = txtWord.getText();

      if (word.equals("")){
          if(selectedFind()>=0){
            Node select = (Node)panel.nodes.elementAt(selectedFind());
            //���X�g�ɉ��ʊT�O����\��
            String candidate;
            String filename = select.lbl;

            String fileDir = base.getCodeBase().toString();
            fileDir = fileDir.substring(6,fileDir.length()-1);
            log.addElement("ls:"+word);

            if(filename.equals("")){
            }
            else{
              File file = new File(fileDir,filename);
              StringBuffer w = new StringBuffer();
              try{
                BufferedReader br = new BufferedReader(new FileReader(file));
                while(br.ready()){
                candidate = br.readLine();
                if(candidate==null){
                  break;
                }
                //���������폜
                for(int p=0;p<base.list1.getItemCount();p++){

                  String s = base.list1.getItem(p);
                  if(candidate.equals(s)){
                    same2 = true;
                    break;
                  }
                }
                if(!same2){
                  base.list1.add(candidate);
                }
                same2 = false;
              }
              br.close();
            }
            catch(IOException e1){
            }

          }
        }
      }
      else {//word not null
        for(i=0;i<panel.nodes.size();i++){
          Node node = (Node)panel.nodes.elementAt(i);
          if(word.equals(node.lbl)){
            same = true;
            break;
          }
        }
        if(same){
          Node nsame = (Node)panel.nodes.elementAt(i);
          if(btnArrow.getLabel().equals("��")){
            if(nsame.parent_no<0){//�e���Ȃ��Ȃ�
              if(selectedFind()>=0){
                Node select = (Node)panel.nodes.elementAt(selectedFind());
                nsame.parent_no = select.node_no;
                String child_no = Integer.toString(nsame.node_no);
                select.child.addElement(child_no);
                Edge ed = new Edge();
                ed.from = select.node_no;
                ed.to = nsame.node_no;
                panel.edges.addElement((Edge)ed);
                log.addElement(nsame.lbl+" Lower to "+select.lbl);
                base.list1.removeAll();
              }

            }
          }
          else if(btnArrow.getLabel().equals("��")){
            Node select = (Node)panel.nodes.elementAt(selectedFind());
            if(select.parent_no<0){
              select.parent_no = nsame.node_no;
              String child_no = Integer.toString(select.node_no);
              nsame.child.addElement(child_no);
              Edge ed = new Edge();
              ed.from = nsame.node_no;
              ed.to = select.node_no;
              panel.edges.addElement((Edge)ed);
              log.addElement(select.lbl+" Lower to "+nsame.lbl);
              base.list1.removeAll();
            }
          }

        }
        else{//not same
          //�m�[�h�݂̂̒ǉ�
          if(lbl.equals("�E")){

              //�m�[�h�̒ǉ�
                Node n = new Node();
                n.lbl = word;
                Dimension panelSize = panel.getSize();
                n.x = panelSize.width/2;
                n.y = panelSize.height/2;
                n.node_no = node_no++;
                panel.nodes.addElement((Node)n);
                log.addElement("Create node:"+word);
                base.list1.removeAll();
          }

          //���ʊT�O�̕\��
          else if(lbl.equals("��")){
            if(selectedFind()>=0){
              //�m�[�h��ǉ�
              Node n = new Node();
              Edge ed = new Edge();
              Node select = (Node)panel.nodes.elementAt(selectedFind());
              n.lbl = word;
              n.x = select.x;
              n.y = select.y + 100;
              n.parent_no = select.node_no;
              n.node_no = node_no++;
              panel.nodes.addElement((Node)n);
              String child_no = Integer.toString(n.node_no);
              select.child.addElement((String)child_no);
              //select.child_no = panel.nodes.size()-1;
              panel.nodes.setElementAt(select,selectedFind());
              ed.from = select.node_no;
              ed.to = n.node_no;
              panel.edges.addElement((Edge)ed);
              log.addElement("Create node:"+word);
              log.addElement("Lower to:"+select.lbl);

              base.list1.removeAll();
            }
            else{
              //�`�̏�ʊT�O��I�����Ă��������B
            }
          }

          //��ʊT�O�̕\��
          else{
            if(selectedFind()>=0){
              //�m�[�h�̒ǉ�
              Node n = new Node();
              Edge ed = new Edge();
              Node select = (Node)panel.nodes.elementAt(selectedFind());
            if(select.parent_no<0){
              n.lbl = word;
              n.x = select.x;
              n.y = select.y - 100;
              n.node_no = node_no++;
              String child_no = Integer.toString(select.node_no);
              n.child.addElement((String)child_no);
              panel.nodes.addElement((Node)n);

              select.parent_no = n.node_no;
              panel.nodes.setElementAt(select,selectedFind());
              ed.from = n.node_no;
              ed.to = select.node_no;
              panel.edges.addElement((Edge)ed);
              log.addElement("Create node:"+word);
              log.addElement("Upper to:"+select.lbl);

              base.list1.removeAll();
            }
            }
            else{
              //�`�̉��ʊT�O��I�����Ă�������
            }
          }


        }
       }
       panel.paint(panel.getGraphics());
       txtWord.setText("");

  }

/************************************************************************/
  public int selectedFind(){
    //�I������Ă���m�[�h��������
    int selected_i = -1;
    for(int i=0;i<panel.nodes.size();i++){
      Node select = (Node)panel.nodes.elementAt(i);
      if(select.selected){

        selected_i = i;
        break;
      }
    }

    if(selected_i>=0){

      return selected_i;
    }
    else{
      return -1;
    }
  }

/***************************************************************************/
  public int getNodeIndex(int node_no){
    //node_no����nodes��index��Ԃ�
    for(int i=0;i<panel.nodes.size();i++){
      Node n = (Node)panel.nodes.elementAt(i);
      if(n.node_no==node_no){
        return i;
      }
    }
    return -1;

  }

/****************************************************************************/
  void btnClear_actionPerformed(ActionEvent e) {
    int selected_i = -1;
    Frame f = new Frame();
    ClearDialog dlg = new ClearDialog(f,"�폜�ΏۑI��",true);
    dlg.panel = this.panel;
    dlg.control = this;
    Dimension dlgSize = dlg.getPreferredSize();
    Dimension frmSize = getSize();
    Point loc = getLocation();
    dlg.setLocation(loc.x + frmSize.width, loc.y + frmSize.height);
    dlg.show();


    //���ׂč폜
    if(dlg.flag1){
      panel.nodes.removeAllElements();
      panel.edges.removeAllElements();
      panel.paint(panel.getGraphics());
      txtWord.setText("");
      base.list1.removeAll();
      btnArrow.setLabel("�E");
      log.addElement("ClearAll");
    }
    //�I���������x�����폜
    else if(dlg.flag2){
      selected_i = selectedFind();
      if(selected_i>=0){
        Node ns = (Node)panel.nodes.elementAt(selected_i);
        //�q�̐ݒ�
        for(int j=0;j<ns.child.size();j++){
          int child_no = Integer.parseInt((String)ns.child.elementAt(j));
          Node nc = (Node)panel.nodes.elementAt(getNodeIndex(child_no));
          nc.parent_no = -1;
          panel.nodes.setElementAt(nc,getNodeIndex(child_no));
        }

        if(ns.parent_no>=0){//�e������Ȃ�
          Node np = (Node)panel.nodes.elementAt(getNodeIndex(ns.parent_no));
          for(int j=0;j<np.child.size();j++){
            int child_no = Integer.parseInt((String)np.child.elementAt(j));
            if(getNodeIndex(child_no) == selected_i){
              np.child.removeElementAt(j);
            }
          }
          panel.nodes.setElementAt(np,getNodeIndex(ns.parent_no));
          ns.parent_no = -1;
        }
        log.addElement("Clear:"+ns.lbl);

        //�G�b�W���폜
        for(int k=panel.edges.size()-1;k>=0;k--){
          Edge ed = (Edge)panel.edges.elementAt(k);
          if(getNodeIndex(ed.from) == selected_i||getNodeIndex(ed.to) == selected_i){
            panel.edges.removeElementAt(k);

          }

        }
        panel.nodes.removeElementAt(selected_i);

        panel.paint(panel.getGraphics());
      }
      if(panel.nodes.size()==0){
        btnArrow.setLabel("�E");
      }
    }

    //�I���������x�����Ȃ��Ȃ���
   /* else if(dlg.flag3){
      selected_i = selectedFind();
      Node ns = (Node)panel.nodes.elementAt(selected_i);
      for(int i=panel.edges.size()-1;i>=0;i--){
        Edge ed = (Edge)panel.edges.elementAt(i);
        if(getNodeIndex(ed.from)==selected_i){
          for(int j=0;j<ns.child.size();j++){
            int child_no = Integer.parseInt((String)ns.child.elementAt(j));
            Node nc = (Node)panel.nodes.elementAt(getNodeIndex(child_no));
            nc.parent_no = -1;
            panel.nodes.setElementAt(nc,child_no);
          }
          ns.child.removeAllElements();

        }
        else if(getNodeIndex(ed.to)==selected_i){
          Node np = (Node)panel.nodes.elementAt(getNodeIndex(ns.parent_no));
          np.child.removeElementAt(getNodeIndex(ns.node_no));
          panel.nodes.setElementAt(np,getNodeIndex(ns.parent_no));
          ns.parent_no = -1;
        }
        panel.nodes.setElementAt(ns,selected_i);
        panel.edges.removeElementAt(i);
      }
      panel.paint(panel.getGraphics());

    }*/

  }







}