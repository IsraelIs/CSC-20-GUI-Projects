/*
Isaac Israel
Section 1
CSC20
Lab08
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class actionTest implements ActionListener {
static JButton[] array = new JButton[20];

	public static void main(String[] args) {
		JFrame frm = new JFrame("HelloWorldSwing");
      Container pane2 = frm.getContentPane();
      JPanel toptext = new JPanel();
      toptext.setLayout(new GridLayout(2,1));
      
      JLabel label = new JLabel("CSC20 Calc",JLabel.CENTER);
      toptext.add(label);
      
      JTextField text = new JTextField();
      toptext.add(text);
     
      
      pane2.add(toptext,BorderLayout.NORTH);
      ActionListener AL = new actionTest() ;
      for (int i=0; i<=19;++i) {
			array[i] = new JButton(""+i);
			pane2.add(array[i]);
			array[i].addActionListener(AL);
		}

      
      JPanel panelc = new JPanel();
      panelc.setLayout(new GridLayout(4,5,2,2)); 
      panelc.add(new JButton("Bksp"));
      panelc.add(new JButton("CE"));
      panelc.add(new JButton("C"));
      panelc.add(new JButton("/"));
      panelc.add(new JButton("Sqt"));
      panelc.add(array[7]);
      panelc.add(array[8]);
      panelc.add(array[9]);
      panelc.add(new JButton("*"));
      panelc.add(new JButton("%"));
      panelc.add(array[4]);
      panelc.add(array[5]);
      panelc.add(array[6]);
      panelc.add(new JButton("-"));
      panelc.add(new JButton("1/x"));
      panelc.add(array[1]);
      panelc.add(array[2]);
      panelc.add(array[3]);
      panelc.add(new JButton("+"));
      panelc.add(new JButton("+/-"));
      frm.add(panelc,BorderLayout.CENTER);
      
      
      JPanel bottom = new JPanel();
      bottom.setLayout(new GridLayout(1,3,2,2));
      bottom.add(new JButton("0"));
      bottom.add(new JButton("."));
      bottom.add(new JButton("="));
      frm.add(bottom,BorderLayout.SOUTH); 
      
      
      frm.pack();
		frm.setSize(375,220);
		frm.setResizable(false);
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      frm.setVisible(true);
	}
}
