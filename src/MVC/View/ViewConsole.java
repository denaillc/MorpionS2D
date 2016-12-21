/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC.View;

import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.JTextArea;

/**
 *
 * @author denaillc
 */
public class ViewConsole {
    
    private final JFrame window;
    private JTextArea text;

    public ViewConsole() {
        window = new JFrame();
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        window.setSize(300, 300);
        window.setLocation(700, 250);
        window.setTitle("Console");
        
        text = new JTextArea();
        window.add(text);
        
        this.show();
    }
    
    
    public void show() {
        this.getWindow().setVisible(true);
    }
    
    public void hide() {
        this.getWindow().setVisible(false);
    }
    
    public void write(String msg) {
        this.text.append(msg + "\n");
    }

    public JTextArea getText() {
        return text;
    }

    public void setText(JTextArea text) {
        this.text = text;
    }

    public JFrame getWindow() {
        return window;
    }

    
    
    
    
    
    
    
   
    
    
    


}
