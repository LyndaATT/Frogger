package environment;

import gameCommons.Case;
import gameCommons.Game;
import graphicalElements.Element;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class River extends Lane {
    public River(Game game, int ord){
        super(game,ord, 0.1f);
    }

    /**
     * Procédure qui permet de faire la mise à jour
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
     * Procédure qui permet d'ajouter une voiture si c'est possible
     */
    private void mayAddCar() {
        if (!(isSafe(getFirstCase()) && isSafe(getBeforeFirstCase()))) {
            if (game.randomGen.nextDouble() < density) {
                cars.add(new Wood(game, getBeforeFirstCase(), leftToRight));
            }
        }
    }

    /**
     * Teste si une case est sûre
     * @param c la case à tester
     * @return true si la case est sure , false sinon
     */
    public boolean isSafe(Case c) {
        Iterator tonobil = this.cars.iterator();
        while(tonobil.hasNext()) {
            Car totot = (Wood)tonobil.next();
            if(totot.busyCase(c)) {
                return true;
            }
        } return false;
    }

    /**
     * Procédure qui ajoute la riviere à l'environnement graphisuqe
     */
    protected void addRiverToGraphics(){
        Color riverColor = new Color(27, 79, 114);
        for (int i = 0; i < game.width; i++) {
            game.getGraphic().add(new Element(i, this.ord, riverColor));
        }
    }

}
