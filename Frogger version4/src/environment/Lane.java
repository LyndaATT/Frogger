package environment;

import java.util.ArrayList;
import java.util.Iterator;

import gameCommons.Case;
import gameCommons.Game;

public class Lane {
	private int inc;
	protected Game game;
	protected int ord;
	protected int speed;
	protected ArrayList<Car> cars;
	protected boolean leftToRight;
	protected double density;
	protected int tic;
	
	
	/**Constructeur 1**/
	public Lane(Game game, int ord, double density) {
		this.game = game;
		this.ord = ord;
		this.density = density;
		this.leftToRight = game.randomGen.nextBoolean();
		this.cars = new ArrayList<>();
		this.speed = game.randomGen.nextInt(game.minSpeedInTimerLoops)+4;
		this.tic = 0;
		this.inc = 0;
	}
	
	/**Constructeur 2**/
	 public Lane(Game game, int ord) {
	        this(game, ord, game.defaultDensity);
	    }

	/**
	 * Procédure qui nous permet de deplacer toutes les voitures
	 */
	public void moveAllCars() {
		ArrayList<Car> newCars = new ArrayList<Car>();
		Iterator tonobil = this.cars.iterator();
		while(tonobil.hasNext()) {
			Car totot = (Car)tonobil.next();
			if(totot instanceof Trap) {
				((Trap) totot).moveKnisse();
			}
			else if(totot instanceof Car){
				((Car) totot).moveCar();
			}
			newCars.add(totot);
		}
		this.cars = newCars;
	}

	/**
	 * Procédure qui nous permet d'afficher toutes les voitures
	 */
	public void displayAllCars() {
		for (Car totot: this.cars) {
			if(totot instanceof Wood){ totot = (Wood) totot; totot.displayCar();}
			else if(totot instanceof Trap ){ totot = (Trap)totot; ((Trap) totot).displayCar();}
			else {
				((Car) totot).displayCar();
			}

		}
	}

	/**
	 * Procédure qui nous permet de faire une mise à jour
	 */
	public void update() {
         tic++;
         inc++;
         if (this.tic < this.speed) {
        	this.displayAllCars();
         }else {
        	 this.moveAllCars();
        	 this.mayAddCar();
        	 if(inc%25==0){ addTrap();}
        	 this.displayAllCars();
         }
     }


	/**
	 * Teste si une case est sans danger pour la grenouille
	 * @param c la case à tester
	 * @return true si la case est sure, false sinon
	 */
	public boolean isSafe(Case c) {
    	Iterator car = this.cars.iterator();
		while(car.hasNext()) {
			Car voiture = (Car)car.next();
			if(voiture.busyCase(c)) {
				return false;
			}
		} return true;
    }

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

	/**
	 * Fonction qui permet d'ajouter un trap à l'environement
	 */
	public void addTrap(){
		cars.add(new Trap(game, getBeforeFirstCase(), 1));
		cars.add(new Trap(game, getBeforeFirstCase(), 2));
		cars.add(new Trap(game, getBeforeFirstCase(), 3));
	}


	/**
	 * Teste si la case est une case propulsor!
	 * @param c
	 * @return true si c'est une case propulsor, false sinon
	 */
	public boolean isPropulsor(Case c){
		for (Car i: this.cars) {
			if(i instanceof Trap){
				if( ((Trap) i).knissPropulsor(c)){
					return true;}
			}
		}
		return false;
	}

}
