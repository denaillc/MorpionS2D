/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enumere;

/**
 *
 * @author denaillc
 */
public enum Commande {
    VALIDER ("Valider"),
    REJOUER ("Rejouer"),
    QUITTER ("Quitter");
    
    private String commande;

    private Commande(String Commande) {
        this.commande = Commande;
    }
    
    @Override
    public String toString() {
        return this.commande;
    }
    
}
