import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.*;

public class learning {  

    JFrame frame;
    learning(){
        frame = new JFrame("My Window");
        frame.setVisible(true);
        frame.setBounds(300,300,500,300); //(x,y,width of window,height of window)  x and y are the co-ordinates from where window 
                                            //start. change it to 0,0 window opens in upper left corner.
        
    }

    public static void main(String[] args) {
        new learning();
    }  
}  