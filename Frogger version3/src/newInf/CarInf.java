package newInf;

import graphicalElements.Element;
import java.awt.Color;
import gameCommons.Case;
import gameCommons.Game;

public class CarInf {
	private Game game;
	private Case leftPosition;
	private boolean leftToRight;
	private int length;
	private final Color colorLtR = Color.pink;
	private final Color colorRtL = Color.lightGray;
	
    /** Constructeur **/
    public CarInf(Game game, Case leftPos, boolean leftToRight) {
    	   this.game = game;
    	   this.leftToRight = leftToRight;
    	   this.leftPosition = leftPos;
    	   this.length = game.randomGen.nextInt(2)+2;
    }

	/**
	 * Procédure qui permet de deplacer une voiture
	 */
	public void move() {
	    	if(this.leftToRight) {
				this.leftPosition = new Case (this.leftPosition.absc+1, this.leftPosition.ord);
				this.addToGraphics();
			}else {this.leftPosition = new Case(this.leftPosition.absc-1,this.leftPosition.ord);}
	        this.addToGraphics();
	    }

	/**
	 * Procédure qui permet d'ajouter une voiture à l'environement
	 */
	private void addToGraphics() {
	        for (int i = 0; i < this.length; i++) {
	            this.game.getGraphic().add(new Element(this.leftPosition.absc + i, this.leftPosition.ord - this.game.score, this.leftToRight ? this.colorLtR : this.colorRtL));
	        }
	    }

	/**
	 * Teste si la voiture est dans une voie
	 * @return vrai si la voiture appartient à la voie, false sinon
	 */
	public boolean inLane() {
	        return this.leftPosition.absc + this.length > 0 || this.leftPosition.absc < this.game.width;
	    }

	/**
	 * Teste si la case est prise ou pas
	 * @param p
	 * @return true si la case est prise, false sinon
	 */
	public boolean busyCase(Case p) {
	        return p.ord == this.leftPosition.ord && (p.absc >= this.leftPosition.absc && p.absc < this.leftPosition.absc + this.length);
	    }
}
