import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

public class ScrollPaneFrame {
  public static void main(String[] args) {
    JFrame frame = new JFrame();
    JLabel image = new JLabel(new ImageIcon("A.jpg"));
    frame.getContentPane().add(new JScrollPane(image));

    frame.setSize(300, 300);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }
}