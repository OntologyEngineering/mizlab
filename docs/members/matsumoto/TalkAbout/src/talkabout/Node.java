package talkabout;

import java.util.Vector;
/**
 * �^�C�g��:
 * ����:
 * ���쌠:   Copyright (c) 2002
 * ��Ж�:
 * @author
 * @version 1.0
 */

public class Node {
    int node_no;
    double x;
    double y;

    double x1;
    double y1;
    double x2;
    double y2;

    Vector child = new Vector();
    int parent_no = -1;
    boolean selected = false;

    String lbl;

    //���W���m�[�h��ł��邩�ǂ���
    boolean on_node(double xx,double yy){

      if(xx>=x1 && xx<=x2 && yy>=y1 && yy<=y2){
        return true;
      }
      else{

        return false;
      }
    }

}