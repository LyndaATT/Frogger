package environment;

import java.awt.Color;

import gameCommons.Case;
import gameCommons.Game;
import graphicalElements.Element;

public class Car {
	protected Game game;
	protected Case leftPosition;
	protected boolean leftToRight;
	protected int length;
	protected final Color colorLtR = Color.pink;
	protected final Color colorRtL = Color.lightGray;
	

    public Car(Game game, Case leftPos, boolean leftToRight) {
    	   this.game = game;
    	   this.leftToRight = leftToRight;
    	   this.leftPosition = leftPos;
    	   this.length = game.randomGen.nextInt(5)+2;
			//this.length = 16;
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
	 * Procédure qui permet d'afficher une voiture
	 */
	public void displayCar() {
		this.addToGraphics();
			}

	/**
	 * Teste si une voiture est dans une voie ou pas
	 * @return true si la voiture est dans une voie, false sinon
	 */
	public boolean inLane() {
		return (this.leftPosition.absc +length > 0 || this.leftPosition.absc < (this.game.width));
	}


	/**
	 * Teste si une case est prise ou libre
	 * @param p la case à tester
	 * @return true si la case est prise, false sinon
	 */
	public boolean busyCase(Case p){
		   return(p.absc<this.leftPosition.absc+this.length && p.absc>this.leftPosition.absc);
		}

	/**
	 * Permet d'avoir la taille d'une voiture
 	 * @return un entier representant la taille d'une voiture
	 */
	public int getLength(){
    	return this.length;
	}
	/* Fourni : addToGraphics() permettant d'ajouter un element graphique correspondant a la voiture*/
	private void addToGraphics() {
		for (int i = 0; i < getLength(); i++) {
			Color color = colorRtL;
			if (this.leftToRight){
				color = colorLtR;
			}
			game.getGraphic().add(new Element(leftPosition.absc + i, leftPosition.ord, color));
		}
	}

}
