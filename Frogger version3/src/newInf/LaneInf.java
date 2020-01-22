package newInf;

import gameCommons.Case;
import java.util.Iterator;
import java.util.ArrayList;
import gameCommons.Game;

import gameCommons.Case;
import java.util.Iterator;
import java.util.ArrayList;
import gameCommons.Game;

public class LaneInf
{
    private Game game;
    private int ord;
    private int speed;
    private ArrayList<CarInf> cars;
    private boolean leftToRight;
    private double density;
    private int tic;

    /** contructeur 1**/
    public LaneInf(Game game,  int ord, double density) {
        this.cars = new ArrayList<CarInf>();
        this.game = game;
        this.ord = ord;
        this.speed = game.randomGen.nextInt(game.minSpeedInTimerLoops) + 1;
        this.leftToRight = game.randomGen.nextBoolean();
        this.density = density;
    }

    /** constructeur 2**/
    public LaneInf(Game game, int ord) {
        this(game, ord, game.defaultDensity);
    }


    /**
     * Procédure qui permet d'effectuer une mise à jour
     */
    public void update() {
        this.tic++;
        if (this.tic <= this.speed) {
            this.moveCars(false);
            return;
        }
        this.moveCars(true);
        this.mayAddCar();
        this.tic = 0;
    }

    /**
     * Procédure qui permet de deplacer une voiture
     * @param b
     */
    private void moveCars(boolean b) {
        for (CarInf car : this.cars) {
            car.move();
        }
        this.removeAncienneCars();
    }


    /**
     * Procédure qui permet de suprimmer les anciennes voitures
     */
    private void removeAncienneCars() {
        ArrayList<CarInf> oldCar = new ArrayList<CarInf>();
        for ( CarInf c : this.cars) {
            if (!c.inLane()) {
                oldCar.add(c);
            }
        }
        for (CarInf c : oldCar) {
            this.cars.remove(c);
        }
    }

    /**
     * Procédure qui permet d'ajouter une voiture si c'est possible
     */
    private void mayAddCar() {
        if (this.isSafe(this.getFirstCase()) && this.isSafe(this.getBeforeFirstCase()) && this.game.randomGen.nextDouble() < this.density) {
            this.cars.add(new CarInf(this.game, this.getBeforeFirstCase(), this.leftToRight));
        }
    }

    /**
     * Procédure qui  teste si la case passée en parametre est une case sûre(sans danger)
     * @param pos la position de la case qu'on doit tester
     * @return true si la case est une case sûre, false sinon
     */
    public boolean isSafe(final Case pos) {
        for (CarInf car : this.cars) {
            if (car.busyCase(pos)) {
                return false;
            }
        }
        return true;
    }


    /** fournis**/
    private Case getFirstCase() {
        if (this.leftToRight) {
            return new Case(0, this.ord);
        }
        return new Case(this.game.width - 1, this.ord);
    }
    
    private Case getBeforeFirstCase() {
        if (this.leftToRight) {
            return new Case(-1, this.ord);
        }
        return new Case(this.game.width, this.ord);
    }

}
