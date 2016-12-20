/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC.Model;

import enumere.Symbole;
import java.util.ArrayList;

/**
 *
 * @author denaillc
 */
public class Joueur {
    private String pseudo;
    private Symbole symbole;
    private ArrayList<Integer> casesJouees = new ArrayList<>();

    public Joueur(String pseudo, Symbole symbole) {
        this.pseudo = pseudo;
        this.symbole = symbole;
    }


    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public ArrayList<Integer> getCasesJouees() {
        return casesJouees;
    }

    public void setCasesJouees(ArrayList<Integer> casesJouees) {
        this.casesJouees = casesJouees;
    }
    
    public void addCaseJouee (int caseJouee) {
        this.casesJouees.add(caseJouee);
    }

    public Symbole getSymbole() {
        return symbole;
    }

    public void setSymbole(Symbole symbole) {
        this.symbole = symbole;
    }

    
    
    
}
