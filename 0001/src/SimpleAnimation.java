import javax.swing.*;import java.awt.*;public class SimpleAnimation {    int x1 = 0;    int y1 = 0;    int x2 = 200;    int y2 = 200;    public static void main (String[] args) {       SimpleAnimation gui = new SimpleAnimation ();       gui.go();   }   public void go() {       JFrame frame = new JFrame();       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       MyDrawPanel drawPanel = new MyDrawPanel();              frame.getContentPane().add(drawPanel);       frame.setSize(260,280);       frame.setVisible(true);       for (int i = 0; i < 200; i++) {          x1++;          y1++;          x2--;          y2--;          drawPanel.repaint();            try {            Thread.sleep(100);          } catch(Exception ex) { }       }       }// close go() method    class MyDrawPanel extends JPanel {           /**		 * 		 */		private static final long serialVersionUID = 1L;	public void paintComponent(Graphics g) {
          g.setColor(Color.white);          g.fillRect(0,0,this.getWidth(), this.getHeight());
          g.setColor(Color.red);          g.fillOval(x1,y1,40,40);          g.fillOval(x2,y2,40,40);       }    } // close inner class} // close outer class

       
      

       