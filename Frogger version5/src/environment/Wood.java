package environment;

import gameCommons.Case;
import gameCommons.Game;
import graphicalElements.Element;

import java.awt.Color;

public class Wood extends Car {
    private final Color colorWood = new Color(156, 100, 12); // La couleur du 'Wood'

    public Wood(Game game, Case leftPos, boolean leftToRight) {
        super(game, leftPos, leftToRight);
        this.length = 9;
    }

    /**
     * Teste si une case est prise ou pas
     * @param p la case à tester
     * @return true si la case est libre, false sinon
     */
    public boolean busyCase(Case p){
        if (p.absc<this.leftPosition.absc+ this.getLength()  && p.absc>this.leftPosition.absc - 1){
           return true;
        } return false;
    }

    /**
     * Procédure qui affiche une case representante de la classe wood
     */
    public void displayCar() {
        this.addToGraphics2();
    }

    /**
     * Procedure qui ajoute la case representante de wood dans l'environnement graphique
     */
    public void addToGraphics2() {
        for (int i = 0; i < this.getLength(); i++) {
            Color color = this.colorWood;
            game.getGraphic().add(new Element(leftPosition.absc + i, leftPosition.ord, color));
        }
    }
}
