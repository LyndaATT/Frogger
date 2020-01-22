package environment;

import java.util.ArrayList;
import java.util.Iterator;

import gameCommons.Case;
import gameCommons.Game;

public class Lane {
	protected Game game;
	protected int ord;
	protected int speed;
	protected ArrayList<Car> cars;
	protected boolean leftToRight;
	protected double density;
	protected int tic;
	
	
	// constructeurs
	public Lane(Game game, int ord, double density) {
		this.game = game;
		this.ord = ord;
		this.density = density;
		this.leftToRight = game.randomGen.nextBoolean();
		this.cars = new ArrayList<>();
		this.speed = game.randomGen.nextInt(game.minSpeedInTimerLoops)+4;
		this.tic = 0; 
	}
	
	
	 public Lane(Game game, int ord) {
	        this(game, ord, game.defaultDensity);
	    }

	/**
	 * Procédure qui deplace les voitures
	 */
	public void moveAllCars() {
		ArrayList<Car> newCars = new ArrayList<Car>();
		Iterator tonobil = this.cars.iterator();
		while(tonobil.hasNext()) {
			Car totot = (Car)tonobil.next();
			totot.moveCar();
			newCars.add(totot);
		}
		this.cars = newCars;
	}

	/**
	 * Procedure qui affiche toutes les voitrues
	 */
	public void displayAllCars() {
		for (Car totot: this.cars) {
			if(totot instanceof Wood){ totot = (Wood) totot; totot.displayCar();}
			else{ totot = (Car)totot; totot.displayCar();}
		}
	}

	/**
	 * Procédure qui fait la mise à jour
	 */
	public void update() {
         tic++;
         if (this.tic < this.speed) {
        	this.displayAllCars();
         }else {
        	 this.moveAllCars();
        	 this.mayAddCar();
        	 this.displayAllCars();
         }
     }


	/**
	 * Teste si la case est sure
	 * @param c la case à tester
	 * @return true si la case est sûre , false sinon
	 */
	public boolean isSafe(Case c) {
    	Iterator tonobil = this.cars.iterator();
		while(tonobil.hasNext()) {
			Car totot = (Car)tonobil.next();
			if(totot.busyCase(c)) {
				return false;
			}
		} return true;
    }

	/*
	 * Fourni : mayAddCar(), getFirstCase() et getBeforeFirstCase() 
	 */

	/**
	 * Ajoute une voiture au d�but de la voie avec probabilit� �gale � la
	 * densit�, si la premi�re case de la voie est vide
	 */
	private void mayAddCar() {
		if (isSafe(getFirstCase()) && isSafe(getBeforeFirstCase())) {
			if (game.randomGen.nextDouble() < density) {
				cars.add(new Car(game, getBeforeFirstCase(), leftToRight));
			}
		}
	}

	/** Fournis **/
	protected Case getFirstCase() {
		if (leftToRight) {
			return new Case(0, ord);
		} else
			return new Case(game.width - 1, ord);
	}

	protected Case getBeforeFirstCase() {
		if (leftToRight) {
			return new Case(-1, ord);
		} else
			return new Case(game.width, ord);
	}

}
