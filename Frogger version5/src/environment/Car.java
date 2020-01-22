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
	
    // deplacer la voiture
	public void moveCar() {
		if(this.leftToRight) {
			this.leftPosition = new Case (this.leftPosition.absc+1, this.leftPosition.ord);
		}else {this.leftPosition = new Case(this.leftPosition.absc-1,this.leftPosition.ord);}
	}
	
	
	//afficher la voiture
	public void displayCar() {
		this.addToGraphics();
	}
	
	// La voiture est dans la voie?
	
	public boolean inLane() {
		return (this.leftPosition.absc +length > 0 || this.leftPosition.absc < (this.game.width));
	}
	
	// La case est prise?
	
	public boolean busyCase(Case p){
		   return(p.absc<this.leftPosition.absc+this.length && p.absc>this.leftPosition.absc);
		}
	
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
