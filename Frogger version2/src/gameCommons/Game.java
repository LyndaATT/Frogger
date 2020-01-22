package gameCommons;

import java.awt.Color;
import java.util.Random;

import graphicalElements.Element;
import graphicalElements.IFroggerGraphics;

public class Game {

	public  int time;
	public final Random randomGen = new Random();

	// Caracteristique de la partie
	public final int width;
	public final int height;
	public final int minSpeedInTimerLoops;
	public final double defaultDensity;
    public  int scoreMax ;
    public  int score;
	// Lien aux objets utilis�s
	private IEnvironment environment;
	private IFrog frog;
	private IFroggerGraphics graphic;

	/**
	 * 
	 * @param graphic
	 *            l'interface graphique
	 * @param width
	 *            largeur en cases
	 * @param height
	 *            hauteur en cases
	 * @param minSpeedInTimerLoop
	 *            Vitesse minimale, en nombre de tour de timer avant d�placement
	 * @param defaultDensity
	 *            densite de voiture utilisee par defaut pour les routes
	 */
	public Game(IFroggerGraphics graphic, int width, int height, int minSpeedInTimerLoop, double defaultDensity) {
		super();
		this.graphic = graphic;
		this.width = width;
		this.height = height;
		this.minSpeedInTimerLoops = minSpeedInTimerLoop;
		this.defaultDensity = defaultDensity;
		this.time=0;
		this.score = 0;
		this.scoreMax=0;
	}

	/**
	 * Lie l'objet frog � la partie
	 * 
	 * @param frog
	 */
	public void setFrog(IFrog frog) {
		this.frog = frog;
	}

	/**
	 * Lie l'objet environment a la partie
	 * 
	 * @param environment
	 */
	public void setEnvironment(IEnvironment environment) {
		this.environment = environment;
	}

	/**
	 * 
	 * @return l'interface graphique
	 */
	public IFroggerGraphics getGraphic() {
		return graphic;
	}

	/**
	 * Fonction qui teste si la partie est perdue et lance un écran de fin approprié si tel
	 * est le cas
	 * 
	 * @return true si le partie est perdue
	 */
	public boolean testLose() {
		if(this.environment.isSafe(this.frog.getPosition())){
			return false;
		} else {
			this.graphic.endGameScreen("You lose Froggy! Your score : "+this.scoreMax+" pts" );
			return true;
		}
	}

	/**
	 * Fonction qui teste si la partie est gagnee et lance un écran de fin approprié si tel
	 * est le cas
	 * 
	 * @return true si la partie est gagnée,
	 */
	public boolean testWin() {
		if(this.environment.isWinningPosition(this.frog.getPosition())){
			this.graphic.endGameScreen("Congrats Froggy, You won!  You spent :" +this.time/10+ "s and  your score :"+ this.scoreMax+" pts" );
			return true;
		} else {return false;}
	}

	/**
	 * Procédure qui actualise l'environnement, affiche la grenouille et verifie la fin de la
	 * partie.
	 */
	public void update() {
		graphic.clear();
		environment.update();
		this.graphic.add(new Element(frog.getPosition(), Color.ORANGE));
		testLose();
		testWin();
	}

	/** Procédure qui incrémente le temps**/
	public void time() {
		this.time++;
	}

}
