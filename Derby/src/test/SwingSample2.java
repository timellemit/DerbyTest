package test;

import java.util.ArrayList;

import javax.swing.*;

public class SwingSample2 {
    public static void main(String args[]){
        int width = 200, height = 100;
 
        if(args.length == 2){
            try{
                width = Integer.valueOf(args[0]);
            }catch(NumberFormatException e){
                System.out.println("Using default width 200");
            }
 
            try{
                height = Integer.valueOf(args[1]);
            }catch(NumberFormatException e){
                System.out.println("Using default height 100");
            }
        }else{
            System.out.println("Using default size 200x100");
        }
        ArrayList<String> res = new ArrayList();
        res = new ReturnSQL().go();
        System.out.println(res);
        MyFrame mainFrame = new MyFrame("Hello SWING!!!");
        mainFrame.setSize(width, height);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
    }
}