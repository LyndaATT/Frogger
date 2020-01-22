package environment;

import gameCommons.Case;
import gameCommons.Game;
import graphicalElements.Element;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class River extends Lane {
    /** Constructeur**/
    public River(Game game, int ord){
        super(game,ord, 0.1f);
    }

    /**
     * Procédure qui permet de faire une mise à jour
     */
    public void update() {
        tic++;
        if (this.tic < this.speed) {
            this.displayAllCars();
        }else {
            this.moveAllCars();
            this.mayAddCar();
            this.addRiverToGraphics();
            this.displayAllCars();
        }
    }

    /**
     * Procédure qui ajoute une case à notre riviere
     */
    private void mayAddCar() {
        if (!(isSafe(getFirstCase()) && isSafe(getBeforeFirstCase()))) {
            if (game.randomGen.nextDouble() < density) {
                cars.add(new Wood(game, getBeforeFirstCase(), leftToRight));
            }
        }
    }

    /**
     * Teste si une case st sûre
     * @param c la case à tester
     * @return
     */
    public boolean isSafe(Case c) {
        Iterator car = this.cars.iterator();
        while(car.hasNext()) {
            Car voiture = (Wood)car.next();
            if(voiture.busyCase(c)) {
                return true;
            }
        } return false;
    }

    /**
     * Procedure qui permet d'ajouter une riviere à l'environemment
     */
    protected void addRiverToGraphics(){
        Color riverColor = new Color(27, 79, 114);
        for (int i = 0; i < game.width; i++) {
            game.getGraphic().add(new Element(i, this.ord, riverColor));
        }
    }

}
