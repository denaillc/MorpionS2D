/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC.Controller;

import MVC.Model.Joueur;
import MVC.View.ViewConsole;
import MVC.View.ViewJeu;
import MVC.View.ViewMenu;
import enumere.Commande;
import enumere.Symbole;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author denaillc
 */
public class Controller implements Observer {
    private Joueur j1;
    private Joueur j2;
    private Joueur jCourant;
    ViewJeu viewJ;
    ViewMenu viewM;
    ViewConsole viewC;
    
    public Controller() {
        viewM = new ViewMenu();
        viewM.addObserver(this);
        
        viewJ = new ViewJeu();
        viewJ.addObserver(this);
        
        viewC = new ViewConsole();
        
    }
    
    @Override
    public void update(Observable o, Object arg) {
        
            if (arg == Commande.VALIDER.toString()) {
                this.InitialiserJoueurs();
                this.ResetJeu();
                this.getViewM().hide();
                this.getViewJ().show();
                this.viewC.write("Tour de : " + this.getjCourant().getPseudo());
                
            } else if (arg == Commande.REJOUER.toString()) {
                this.j1.resetCasesJouees();
                this.j2.resetCasesJouees();
                this.jCourant = this.j1;
                this.ResetJeu();
                this.getViewM().hide();
                this.getViewJ().show();
                
            } else if (arg == Commande.QUITTER.toString()) {
                System.exit(0);
            } 
            
            else if (arg instanceof Integer) {
                boolean b = this.getViewJ().CliquerCase(jCourant, (int) arg);
                if (b) {    // Si le tour est validé, une case possible est cliquée
                        if (!this.Scan3x3()) {  // Pas de victoire
                            if (MatchNul()) {
                                this.viewC.write("***** Match nul *****");
                                this.viewC.write("Score actuel : " + this.j1.getPseudo() + "(" + this.j1.getScore() + ") - (" + this.j2.getScore() + ")" + this.j2.getPseudo());
                                this.getViewM().show();
                            }
                            else {
                            this.jCourant = JoueurSuivant();
                            this.viewC.write("Tour de : " + this.getjCourant().getPseudo());
                            }
                        } else {
                            Victoire(jCourant);
                        }
                    }
                
                
                
                
                
                
            } else {
            this.viewC.write("Argument de Commande invalide (" + arg.toString() + ")");
            }
    }
    
    
    
    
    public void InitialiserJoueurs () {
        this.j1 = new Joueur(this.getViewM().getPseudo1().getText(), Symbole.X);
        this.j2 = new Joueur(this.getViewM().getPseudo2().getText(), Symbole.O);
        this.jCourant = this.j1;
    }
    
    
    public Joueur JoueurSuivant () {
        if (jCourant == j1) {
            return j2;
        }
        else return j1;
    }
    
    
    public void Victoire(Joueur j) {
        this.getViewJ().DesactiverPlateau();
        if (jCourant == j1) {
            j1.gagne();
        }
        else {
            j2.gagne();
        }
        this.viewC.write(j.getPseudo() + " a gagné");
        this.viewC.write("Score actuel : " + this.j1.getPseudo() + "(" + this.j1.getScore() + ") - (" + this.j2.getScore() + ")" + this.j2.getPseudo());
        this.getViewM().getScore1().setText("Score : " + j1.getScore());
        this.getViewM().getScore2().setText("Score : " + j2.getScore());
        this.getViewM().show();
    }
    
    
        public void ResetJeu() {
        for (int i = 0; i < this.getViewJ().getCases().size(); i++) {
            this.getViewJ().getCases().get(i).setText("");
            this.getViewJ().getCases().get(i).setEnabled(true);
            this.getViewJ().getCases().get(i).setBackground(null);
        }
        this.j1.getCasesJouees().clear();
        this.j2.getCasesJouees().clear();
    }
    
    
    
    /************************************************
    ***************DETECTION VICTOIRE****************
    ************************************************/
    
    public boolean Scan3x3 () {
        int i = ScanLignes();
        switch (i) {
            case 1 :
                this.getViewJ().ColorierCasesGagnantes(0, 1, 2);
                break;
            case 2 :
                this.getViewJ().ColorierCasesGagnantes(3, 4, 5);
                break;
            case 3 :
                this.getViewJ().ColorierCasesGagnantes(6, 7, 8);
                break;
            case 0 :
                int j = ScanColonnes();
                                switch (j) {
                                    case 1 :
                                        this.getViewJ().ColorierCasesGagnantes(0, 3, 6);
                                        break;
                                    case 2 :
                                        this.getViewJ().ColorierCasesGagnantes(1, 4, 7);
                                        break;
                                    case 3 :
                                        this.getViewJ().ColorierCasesGagnantes(2, 5, 8);
                                        break;
                                    case 0 :
                                        int k = ScanDiagonales();
                                                        switch (k) {
                                                            case 1 :
                                                                this.getViewJ().ColorierCasesGagnantes(0, 4, 8);
                                                                break;
                                                            case 2 :
                                                                this.getViewJ().ColorierCasesGagnantes(2, 4, 6);
                                                                break;
                                                            case 0 :
                                                                return false;
                                                        }
                                }
                
        }
        return true;
    }
    
    public int ScanLignes () {  //Renvoie la ligne du vainqueur, ou 0 si la partie n'est pas finie
        if (    (this.getViewJ().getCases().get(0).getText().equals(Symbole.X.toString()) || this.getViewJ().getCases().get(0).getText().equals(Symbole.O.toString()))
                && (this.getViewJ().getCases().get(0).getText().equals(this.getViewJ().getCases().get(1).getText())) 
                && (this.getViewJ().getCases().get(1).getText().equals(this.getViewJ().getCases().get(2).getText()))) {
            return 1;
        }
        else if (   (this.getViewJ().getCases().get(3).getText().equals(Symbole.X.toString()) || this.getViewJ().getCases().get(3).getText().equals(Symbole.O.toString()))
                && (this.getViewJ().getCases().get(3).getText().equals(this.getViewJ().getCases().get(4).getText())) 
                && (this.getViewJ().getCases().get(4).getText().equals(this.getViewJ().getCases().get(5).getText()))) {
            return 2;
        }
        else if (   (this.getViewJ().getCases().get(6).getText().equals(Symbole.X.toString()) || this.getViewJ().getCases().get(6).getText().equals(Symbole.O.toString()))
                && (this.getViewJ().getCases().get(6).getText().equals(this.getViewJ().getCases().get(7).getText())) 
                && (this.getViewJ().getCases().get(7).getText().equals(this.getViewJ().getCases().get(8).getText()))) {
            return 3;
        }
        else {
            return 0;
        }
    }
    
    public int ScanColonnes () {   //Renvoie la colonne du vainqueur, ou 0 si la partie n'est pas finie
        if (    (this.getViewJ().getCases().get(0).getText().equals(Symbole.X.toString()) || this.getViewJ().getCases().get(0).getText().equals(Symbole.O.toString()))
                && (this.getViewJ().getCases().get(0).getText().equals(this.getViewJ().getCases().get(3).getText())) 
                && (this.getViewJ().getCases().get(3).getText().equals(this.getViewJ().getCases().get(6).getText()))) {
            return 1;
        }
        else if (   (this.getViewJ().getCases().get(1).getText().equals(Symbole.X.toString()) || this.getViewJ().getCases().get(1).getText().equals(Symbole.O.toString()))
                && (this.getViewJ().getCases().get(1).getText().equals(this.getViewJ().getCases().get(4).getText())) 
                && (this.getViewJ().getCases().get(4).getText().equals(this.getViewJ().getCases().get(7).getText()))) {
            return 2;
        }
        else if (   (this.getViewJ().getCases().get(2).getText().equals(Symbole.X.toString()) || this.getViewJ().getCases().get(2).getText().equals(Symbole.O.toString()))
                && (this.getViewJ().getCases().get(2).getText().equals(this.getViewJ().getCases().get(5).getText())) 
                && (this.getViewJ().getCases().get(5).getText().equals(this.getViewJ().getCases().get(8).getText()))) {
            return 3;
        }
        else {
            return 0;
        }
    }
    
    public int ScanDiagonales() {   //Renvoie 1 si la diagonale gauche termine la partie, 2 si la diagonale droite termine la partie, ou 0 si la partie n'est pas finie
        if (    (this.getViewJ().getCases().get(0).getText().equals(Symbole.X.toString()) || this.getViewJ().getCases().get(0).getText().equals(Symbole.O.toString()))
                && (this.getViewJ().getCases().get(0).getText().equals(this.getViewJ().getCases().get(4).getText())) 
                && (this.getViewJ().getCases().get(4).getText().equals(this.getViewJ().getCases().get(8).getText()))) {
            return 1;
        }
        else if (   (this.getViewJ().getCases().get(2).getText().equals(Symbole.X.toString()) || this.getViewJ().getCases().get(2).getText().equals(Symbole.O.toString()))
                && (this.getViewJ().getCases().get(2).getText().equals(this.getViewJ().getCases().get(4).getText())) 
                && (this.getViewJ().getCases().get(4).getText().equals(this.getViewJ().getCases().get(6).getText()))) {
            return 2;
        }
        else {
            return 0;
        }
    }
    
    
    public boolean MatchNul() {
        boolean b = true;
        for (int i = 0; i < this.getViewJ().getCases().size(); i++) {
            if (this.getViewJ().getCases().get(i).isEnabled()) {
                b = false;
            }
        }
        return b;
    }
    
    
    /************************************************
    **************GETTERS ET SETTERS*****************
    ************************************************/  
    
    

    public Joueur getJ1() {
        return j1;
    }

    public void setJ1(Joueur j1) {
        this.j1 = j1;
    }

    public Joueur getJ2() {
        return j2;
    }

    public void setJ2(Joueur j2) {
        this.j2 = j2;
    }

    public ViewJeu getViewJ() {
        return viewJ;
    }

    public void setViewJ(ViewJeu viewJ) {
        this.viewJ = viewJ;
    }

    public ViewMenu getViewM() {
        return viewM;
    }

    public void setViewM(ViewMenu viewM) {
        this.viewM = viewM;
    }

    public Joueur getjCourant() {
        return jCourant;
    }

    public void setjCourant(Joueur jCourant) {
        this.jCourant = jCourant;
    }
    

    

}


