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
	private IFrog frog2;
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
	 * Lie les objets frog1 et frog2 à la partie
	 * 
	 * @param frog
	 */
	public void setFrog(IFrog frog, IFrog frog2) {
		this.frog = frog;
		this.frog2 = frog2;
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
	 * Teste si la partie est perdue et lance un �cran de fin appropri� si tel
	 * est le cas
	 * 
	 * @return true si le partie est perdue
	 */
	public boolean testLose() {
		if(!(this.environment.isSafe(this.frog.getPosition()))){
			this.graphic.endGameScreen("Congrats Yellow Froggy You won! ");
			return true;
		} else if(!(this.environment.isSafe(this.frog2.getPosition()))){
			this.graphic.endGameScreen("Congrats Green Froggy You won!" );
			return true;
		} else {return false;}
	}

	/**
	 * Teste si la partie est gagnee et lance un �cran de fin appropri� si tel
	 * est le cas
	 * 
	 * @return true si la partie est gagn�e
	 */
	public boolean testWin() {
		if(this.environment.isWinningPosition(this.frog.getPosition())){
			this.graphic.endGameScreen("Player 1 win! You spent :" +this.time/10+ "s   your score :"+ this.scoreMax );
			return true;
		} else if(this.environment.isWinningPosition(this.frog2.getPosition())){
			this.graphic.endGameScreen("Player 1 win! You spent :" +this.time/10+ "s   your score :"+ this.scoreMax );
			return true;
		} else { return false;}
	}

	/**
	 * Actualise l'environnement, affiche la grenouille et verifie la fin de
	 * partie.
	 */
	public void update() {
		graphic.clear();
		environment.update();
		this.graphic.add(new Element(frog.getPosition(), frog.getColor()));
		this.graphic.add(new Element(frog2.getPosition(), frog2.getColor()));
		testLose();
		testWin();
	}
	public void time() {
		this.time++;
	}

}
