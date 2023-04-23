import javax.swing.*;
import java.awt.*;

public class Main extends JWindows {
    
    public void Main(String[] args){
        setSize(700,700);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width-getSize().width)/2;
        int y = (screenSize.height-getSize().height)/2;
        setLocation(x,y);
        setVisible(true);
    }
}
  
