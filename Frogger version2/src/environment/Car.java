package environment;

import java.awt.Color;

import gameCommons.Case;
import gameCommons.Game;
import graphicalElements.Element;

public class Car {
	private Game game;
	private Case leftPosition;
	private boolean leftToRight;
	private int length;
	private final Color colorLtR = Color.pink;
	private final Color colorRtL = Color.lightGray;
	

    public Car(Game game, Case leftPos, boolean leftToRight) {
    	   this.game = game;
    	   this.leftToRight = leftToRight;
    	   this.leftPosition = leftPos;
    	   this.length = game.randomGen.nextInt(2)+1;
    }
	

	/**
	 * Procédure qui permet de deplacer les voitures
	 */
	public void moveCar() {
		if(this.leftToRight) {
			this.leftPosition = new Case (this.leftPosition.absc+1, this.leftPosition.ord);
		}else {this.leftPosition = new Case(this.leftPosition.absc-1,this.leftPosition.ord);}
	}
	

	/**
	 * Procédure qui affiche une voiture
	 */
	public void displayCar() {
		this.addToGraphics();
	}
	

	/**
	 * Fonction qui teste si une voiture appartient à une voie
	 * @return True si la voiture appartient à la voie , False sinon
	 */
	public boolean inLane() {
		return (this.leftPosition.absc +length > 0 || this.leftPosition.absc < (this.game.width));
	}

	/**
	 * Fonction qui teste si une case est prise ou non
	 * @param p une case
	 * @return True si la case est prise, False sinon
	 */
	public boolean busyCase(Case p){
		   return(p.absc<this.leftPosition.absc+this.length && p.absc>=this.leftPosition.absc);
		}
	

	/**
	 * Procedure qui permet d'ajouter un élement graphique correspondant à la voiture ( fourni)
	 */
	private void addToGraphics() {
		for (int i = 0; i < length; i++) {
			Color color = colorRtL;
			if (this.leftToRight){
				color = colorLtR;
			}
			game.getGraphic().add(new Element(leftPosition.absc + i, leftPosition.ord, color));
		}
	}

}
