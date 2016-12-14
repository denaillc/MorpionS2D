/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC.Controller;

import MVC.Model.Joueur;
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
    
    public Controller() {
        viewM = new ViewMenu();
        viewM.addObserver(this);
        
        
        
    }
    
    @Override
    public void update(Observable o, Object arg) {
        
            if (arg == Commande.VALIDER.toString()) {
                viewJ = new ViewJeu();
                viewJ.addObserver(this);
                this.InitialiserJoueurs();
                this.getViewM().hide();
                
            } else if (arg == Commande.REJOUER.toString()) {
                //à implémenter
                
            } else if (arg == Commande.QUITTER.toString()) {
                System.exit(0);
                
            } 
            
            else if (arg instanceof Integer) {
                boolean b = this.getViewJ().CliquerCase(jCourant, (int) arg);
                if (b) {    // Si le tour est validé, une case possible est cliquée
                if (!this.Scan3x3()) {  // Pas de victoire
                    System.out.println("Au tour de : " + this.jCourant.getPseudo());
                    this.jCourant = JoueurSuivant();
                } else {
                    
                    System.out.println(jCourant.getPseudo() + " a gagné");
                }
                    
                    
                }
                
                
                
                
                
                
            } else {
            System.err.println("Argument de Commande invalide (" + arg.toString() + ")");
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
    
    
    
    
    
    /************************************************
    ***************DETECTION VICTOIRE****************
    ************************************************/
    
    public boolean Scan3x3 () {
        
        int i = ScanLignes();
        switch (i) {
            case 1 :
                jCourant.addCaseGagnante(0);
                jCourant.addCaseGagnante(1);
                jCourant.addCaseGagnante(2);
                System.out.println("ligne 1");
                break;
            case 2 :
                jCourant.addCaseGagnante(3);
                jCourant.addCaseGagnante(4);
                jCourant.addCaseGagnante(5);
                System.out.println("ligne 2");
                break;
            case 3 :
                jCourant.addCaseGagnante(6);
                jCourant.addCaseGagnante(7);
                jCourant.addCaseGagnante(8);
                System.out.println("ligne 3");
                break;
            case 0 :
                int j = ScanColonnes();
                                switch (j) {
                                    case 1 :
                                        jCourant.addCaseGagnante(0);
                                        jCourant.addCaseGagnante(1);
                                        jCourant.addCaseGagnante(2);
                                        System.out.println("colonne 1");
                                        break;
                                    case 2 :
                                        jCourant.addCaseGagnante(3);
                                        jCourant.addCaseGagnante(4);
                                        jCourant.addCaseGagnante(5);
                                        System.out.println("colonne 2");
                                        break;
                                    case 3 :
                                        jCourant.addCaseGagnante(6);
                                        jCourant.addCaseGagnante(7);
                                        jCourant.addCaseGagnante(8);
                                        System.out.println("colonne 3");
                                        break;
                                    case 0 :
                                        int k = ScanDiagonales();
                                                        switch (k) {
                                                            case 1 :
                                                                jCourant.addCaseGagnante(0);
                                                                jCourant.addCaseGagnante(4);
                                                                jCourant.addCaseGagnante(8);
                                                                System.out.println("diago 1");
                                                                break;
                                                            case 2 :
                                                                jCourant.addCaseGagnante(2);
                                                                jCourant.addCaseGagnante(4);
                                                                jCourant.addCaseGagnante(6);
                                                                System.out.println("diago 2");
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








//            switch (commandeRecue) {
//                case (Commande.) : {
//                                            
//                                        break; }
//				case Commande.R.toString(): {
//                                            
//                                        break;}
//				case Commande.Q.toString(): {
//                                            
//                                        break;}
//                                case "0":
//                                        return;
//				default:
//                                       System.out.println("Choix non valide");
//                                       break;
//				} // switch
//                        }