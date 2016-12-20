/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC.View;

import MVC.Model.Joueur;
import enumere.Symbole;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author denaillc
 */
public class ViewJeu extends Observable {
    
    private final JFrame window;
    private ArrayList<JButton> cases = new ArrayList<>();
    private JPanel mainPanel;
    private JPanel panelCentre;
    
    public ViewJeu() {
        window = new JFrame();
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        window.setSize(600, 600);
        window.setTitle("J'ai des morpions");
        
        mainPanel = new JPanel(new BorderLayout());
        window.add(mainPanel);
        panelCentre = new JPanel(new GridLayout(3,3));
        for (int i = 1; i <= 9; i++) {
            JButton c = new JButton("");
            c.setBorderPainted(true);
            cases.add(c) ;
            panelCentre.add(c);
            c.setName(String.valueOf(i));
            c.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton btn = (JButton) e.getSource();
                    String btnName = btn.getName();
                    Integer numCase = Integer.valueOf(btnName);
                    setChanged();
                    notifyObservers(numCase);
                    clearChanged();
                }
            });
        }
        
        mainPanel.add(panelCentre, BorderLayout.CENTER);
        
        window.setVisible(true);
    }

    
    
    public boolean CliquerCase (Joueur j, int i) {
        if (CaseDejaJouee(i)) {
            System.err.println("Case " + i + " déjà jouée");
            return false;
        }
        else {
            j.addCaseJouee(i-1);
            this.getCases().get(i-1).setText(j.getSymbole().toString());
            this.getCases().get(i-1).setEnabled(false);
            this.show();
            return true;
        }
        
        
       
    }
    
    public boolean CaseDejaJouee (int i) {
        return this.getCases().get(i-1).getText() != "";
    }
    
    
    public void show() {
        this.getWindow().setVisible(true);
    }
    
    public void hide() {
        this.getWindow().setVisible(false);
    }
    
    public void desactiverPlateau() {
        for (int i = 0; i < this.getCases().size(); i++) {
            this.getCases().get(i).setEnabled(false);
        }
    }
    
    public void colorierCasesGagnantes(int i1, int i2, int i3){
        this.getCases().get(i1).setBackground(Color.GREEN);
        this.getCases().get(i2).setBackground(Color.GREEN);
        this.getCases().get(i3).setBackground(Color.GREEN);
    }
    
    /************************************************
    **************GETTERS ET SETTERS*****************
    ************************************************/  

    public ArrayList<JButton> getCases() {
        return cases;
    }

    public void setCases(ArrayList<JButton> cases) {
        this.cases = cases;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public JPanel getPanelCentre() {
        return panelCentre;
    }

    public void setPanelCentre(JPanel panelCentre) {
        this.panelCentre = panelCentre;
    } 

    public JFrame getWindow() {
        return window;
    }
    
    
    
    
}
