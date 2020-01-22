package environment;

import gameCommons.Case;
import gameCommons.Game;
import graphicalElements.Element;

import java.awt.Color;

public class Wood extends Car {
    private final Color colorWood = new Color(156, 100, 12); // La couleur du 'Wood'

    /** COnstructeur**/
    public Wood(Game game, Case leftPos, boolean leftToRight) {
        super(game, leftPos, leftToRight);
        this.length = 9;
    }

    /**
     * Test si la case est prise
     * @param p la case à tester
     * @return true si la case n'est pas prise, false sinon
     */
    public boolean busyCase(Case p){
        if (p.absc<this.leftPosition.absc+ this.getLength()  && p.absc>this.leftPosition.absc - 1){
           return true;
        } return false;
    }

    /**
     * Procédure  qui affiche une case , wood
     */
    public void displayCar() {
        this.addToGraphics2();
    }

    /**
     * Procédure qui permet d'afficher la case representant le wood dans l'environnement graphique
     */
    public void addToGraphics2() {
        for (int i = 0; i < this.getLength(); i++) {
            Color color = this.colorWood;
            game.getGraphic().add(new Element(leftPosition.absc + i, leftPosition.ord, color));
        }
    }
}
