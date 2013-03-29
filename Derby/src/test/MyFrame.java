package test;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
public class MyFrame extends JFrame {
    private int count = 0;
 
    class PushingListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            Object button = e.getSource();
 
            if(button instanceof JButton){
                count++;
                ((JButton)button).setText("Pressed " + count + " times");
             }
        }
    }
 
    public MyFrame(String title){
        super(title);
        createGUI();
    }
 
    private void createGUI(){
        JButton button = new JButton("Push me");
        button.addActionListener(new PushingListener());
        getContentPane().add(button);
    }
}