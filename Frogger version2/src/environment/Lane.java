package environment;

import java.util.ArrayList;
import java.util.Iterator;

import gameCommons.Case;
import gameCommons.Game;

public class Lane {
	private Game game;
	private int ord;
	private int speed;
	private ArrayList<Car> cars ;
	private boolean leftToRight;
	private double density;
    private int tic;
	
    /** Constructeur 1**/
	public Lane(Game game, int ord, double density) {
		this.game = game;
		this.ord = ord;
		this.density = density;
		this.leftToRight = game.randomGen.nextBoolean();
		this.cars = new ArrayList<>();
		this.speed = game.randomGen.nextInt(game.minSpeedInTimerLoops)+4;
		this.tic = 0; 
	}
	
	/** Constructeur 2**/
	 public Lane(Game game, int ord) {
	        this(game, ord, game.defaultDensity);
	    }

	/**
	 * Procédure qui permet de déplacer toutes les voitures
	 */
	public void moveAllCars() {
		ArrayList<Car> newCars = new ArrayList<Car>();
		Iterator car = this.cars.iterator();
		while(car.hasNext()) {
			Car voiture = (Car)car.next();
			voiture.moveCar();
			newCars.add(voiture);
		}
		this.cars = newCars;
	}


	/**
	 * Procédure qui permet d'afficher toutes les voitures
	 */
	public void displayAllCars() {
		Iterator car = this.cars.iterator();
		while(car.hasNext()) {
			Car  voiture = (Car)car.next();
			voiture.displayCar();
		}
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
        	 this.displayAllCars();
        	 
         }
     }

	/**
	 * Fonction qui teste si la case est sans danger
	 * @param c
	 * @return True si la case est sans danger, False sinon
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


	/**
	 * Procédure qui ajoute une voiture au debut de la voie avec une probabilité égale à la
	 * densité, si la première case de la voie est vide
	 */
	private void mayAddCar() {
		if (isSafe(getFirstCase()) && isSafe(getBeforeFirstCase())) {
			if (game.randomGen.nextDouble() < density) {
				cars.add(new Car(game, getBeforeFirstCase(), leftToRight));
			}
		}
	}

	/** Fournis**/
	private Case getFirstCase() {
		if (leftToRight) {
			return new Case(0, ord);
		} else
			return new Case(game.width - 1, ord);
	}

	private Case getBeforeFirstCase() {
		if (leftToRight) {
			return new Case(-1, ord);
		} else
			return new Case(game.width, ord);
	}

}
