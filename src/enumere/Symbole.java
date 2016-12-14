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
public enum Symbole {
    X ("X"),
    O ("0");
    
    private String symbole;

    private Symbole(String symbole) {
        this.symbole = symbole;
    }
    
    @Override
    public String toString() {
        return this.symbole;
    }
    
}
