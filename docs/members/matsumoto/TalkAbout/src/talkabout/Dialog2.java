package talkabout;

import java.awt.*;
import java.awt.event.*;

/**
 * タイトル:
 * 説明:
 * 著作権:   Copyright (c) 2002
 * 会社名:
 * @author
 * @version 1.0
 */

public class Dialog2 extends Dialog {
  Panel panel1 = new Panel();
  BorderLayout borderLayout1 = new BorderLayout();
  Label label1 = new Label();

  public Dialog2(Frame frame, String title, boolean modal) {
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

  public Dialog2(Frame frame) {
    this(frame, "", false);
  }

  public Dialog2(Frame frame, boolean modal) {
    this(frame, "", modal);
  }

  public Dialog2(Frame frame, String title) {
    this(frame, title, false);
  }
  void jbInit() throws Exception {
    panel1.setLayout(borderLayout1);
    panel1.add(label1, BorderLayout.CENTER);
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
}