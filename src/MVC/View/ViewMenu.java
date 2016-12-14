/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC.View;

import enumere.Commande;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author denaillc
 */
public class ViewMenu extends Observable {
    
    private final JFrame window;
    
    private JPanel mainPanel = new JPanel(new BorderLayout());
    private JPanel panelCentre = new JPanel(new GridLayout(3,3));
    private JButton btnRejouer = new JButton("Rejouer");
    private JButton btnValider = new JButton("Valider");
    private JLabel joueur1 = new JLabel("Joueur 1 :");
    private JLabel joueur2 = new JLabel("Joueur 2 :");
    private JTextField pseudo1 = new JTextField("");
    private JTextField pseudo2 = new JTextField("");
    private JButton btnQuitter = new JButton("Quitter");

    public ViewMenu() {
        window = new JFrame();
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        window.setSize(300, 150);
        window.setLocation(650, 0);
        window.setTitle("Menu Principal");
        
        window.add(mainPanel);
        
        btnValider.setName(Commande.VALIDER.toString());
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                if (pseudo1.getText() != pseudo2.getText()) {
                    setChanged();
                    notifyObservers(btnValider.getName());
                    clearChanged();
                }
                else if (pseudo1.getText() == "" || pseudo2.getText() == "") {
                    System.err.println("Erreur : Pseudos incomplets");
                } 
                else {
                    System.err.println("Erreur : Pseudos identiques");
                }
                
                
                
            }
        });
        
        btnRejouer.setName(Commande.REJOUER.toString());
        btnRejouer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setChanged();
                notifyObservers(btnRejouer.getName());
                clearChanged();
            }
        });
        
        btnQuitter.setName(Commande.QUITTER.toString());
        btnQuitter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setChanged();
                notifyObservers(btnQuitter.getName());
                clearChanged();
            }
        });
        
        panelCentre.add(joueur1); //Case 1
        panelCentre.add(pseudo1); //Case 2
        panelCentre.add(new JLabel("")); //Case 3 - vide
        panelCentre.add(joueur2); //Case 4
        panelCentre.add(pseudo2); //Case 5        
        panelCentre.add(new JLabel("")); //Case 6 - vide
        panelCentre.add(btnValider);    //Case 7
        panelCentre.add(btnRejouer);    //Case 8
        panelCentre.add(btnQuitter);    //Case 9
        mainPanel.add(panelCentre, BorderLayout.CENTER);
        
        
        this.show();
    }
    
    
    
    public void show() {
        this.getWindow().setVisible(true);
    }
    
    public void hide() {
        this.getWindow().setVisible(false);
    }
    
    /************************************************
    **************GETTERS ET SETTERS*****************
    ************************************************/
    
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

    public JButton getBtnRejouer() {
        return btnRejouer;
    }

    public void setBtnRejouer(JButton btnRejouer) {
        this.btnRejouer = btnRejouer;
    }

    public JButton getBtnValider() {
        return btnValider;
    }

    public void setBtnValider(JButton btnValider) {
        this.btnValider = btnValider;
    }

    public JLabel getJoueur1() {
        return joueur1;
    }

    public void setJoueur1(JLabel joueur1) {
        this.joueur1 = joueur1;
    }

    public JLabel getJoueur2() {
        return joueur2;
    }

    public void setJoueur2(JLabel joueur2) {
        this.joueur2 = joueur2;
    }

    public JTextField getPseudo1() {
        return pseudo1;
    }

    public void setPseudo1(JTextField pseudo1) {
        this.pseudo1 = pseudo1;
    }

    public JTextField getPseudo2() {
        return pseudo2;
    }

    public void setPseudo2(JTextField pseudo2) {
        this.pseudo2 = pseudo2;
    }

    public JButton getBtnQuitter() {
        return btnQuitter;
    }

    public void setBtnQuitter(JButton btnQuitter) {
        this.btnQuitter = btnQuitter;
    }

    public JFrame getWindow() {
        return window;
    }
    
    
    
    
}
