package Sitedu0Key;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class MyKeyListener {
  public static void main(String[] argv) throws Exception {
    JTextField component = new JTextField();
    component.addKeyListener(new MyKeyListsener());

    JFrame f = new JFrame();

    f.add(component);
    f.setSize(300, 300);
    f.setVisible(true);

  }
}

class MyKeyListsener extends KeyAdapter {
  public void keyPressed(KeyEvent e) {
    if (e.getKeyChar() == 'a') { 
      System.out.println("Check for key characters: " + e.getKeyCode());
    }
    if ((e.getKeyCode() == KeyEvent.VK_C) && ((e.getModifiers() & 157) != 0)) {// 157 pour cmd sous mac
        System.out.println("woot!");
    }
  }
}