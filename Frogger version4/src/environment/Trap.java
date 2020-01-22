package environment;

import gameCommons.*;
import graphicalElements.Element;

import java.awt.*;
import java.util.Objects;

public class Trap extends Car {
    private Color trapColor = new Color(70, 147, 102);
    public Trap(Game game, Case c, int trapNumber){
        super(game, c, true);
        this.length = 1;
        if(trapNumber==1){this.leftPosition = new Case(30, 23);}
        if(trapNumber==2){this.leftPosition = new Case(10, 4); // Aide à passer la première rivière !!
            this.leftToRight = false;}
        if(trapNumber==3){this.leftPosition = new Case(20, 10);}
    }

    /**
     * Procédure qui permet de deplacer le piège kniss
     */
    public void moveKnisse(){
        if (this.leftToRight) {
            this.leftPosition = new Case(this.leftPosition.absc - 1, this.leftPosition.ord);
        } else {
            this.leftPosition = new Case(this.leftPosition.absc + 1, this.leftPosition.ord);
        }
    }

    /**
     *  Teste si la case passée en parametre est une case qui propulse
     * @param c
     * @return true si c'est la cas, false sinon
     */
    public boolean knissPropulsor(Case c){
        return ((c.absc==this.leftPosition.absc)&&(c.ord==this.leftPosition.ord));
    }

    /**
     * Procédure qui affiche une voiture(dans notre classe un piège)
     */
    public void displayCar() {
        this.addToGraphics3();
    }

    /**
     * Procédure qui ajoute la case spéciale trap à l'environnement
     */
    public void addToGraphics3() {
        for (int i = 0; i < this.getLength(); i++) {
            Color color = this.trapColor;
            game.getGraphic().add(new Element(leftPosition.absc + i, leftPosition.ord, color));
            
        }
    }

}
