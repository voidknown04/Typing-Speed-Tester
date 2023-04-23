import java.awt.*;
import javax.swing.*;

public class Main extends JWindow {
   Image splashScreen;
   ImageIcon imageIcon;
   public Main() {  
      splashScreen = Toolkit.getDefaultToolkit().getImage("splash2.png");
      imageIcon = new ImageIcon(splashScreen);
      setSize(700,700);
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      int x = (screenSize.width-getSize().width)/2;
      int y = (screenSize.height-getSize().height)/2;
      setLocation(x,y);
      setVisible(true);
   }
   public void paint(Graphics g) {
      super.paint(g);
      g.drawImage(splashScreen, 0, 0, this);
   }
   public static void main(String[]args) {
      
      Main splash = new Main();
      try {
         Thread.sleep(5000);
         splash.dispose();
      } catch(Exception e) {
         e.printStackTrace();
      }
	  new Frame();
       }
}
