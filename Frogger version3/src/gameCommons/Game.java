package gameCommons;

import java.awt.Color;
import java.util.Random;

import graphicalElements.Element;
import graphicalElements.IFroggerGraphics;
import graphicalElements.IFroggerGraphics;
import graphicalElements.Element;
import java.awt.Color;
import graphicalElements.IFroggerGraphics;
import java.util.Random;

public class Game {
	 public final Random randomGen;
	    public final int width;
	    public final int height;
	    public final int minSpeedInTimerLoops;
	    public final double defaultDensity;
	    public int score;
	    public int scoreMax;
	    private IEnvironment environment;
	    private IFrog frog;
	    private IFroggerGraphics graphic;
	    
	    public Game( IFroggerGraphics graphic,  int width, int height,  int minSpeedInTimerLoop, double defaultDensity) {
	        this.randomGen = new Random();
	        this.score = 0;
	        this.scoreMax = 0;
	        this.graphic = graphic;
	        this.width = width;
	        this.height = height;
	        this.minSpeedInTimerLoops = minSpeedInTimerLoop;
	        this.defaultDensity = defaultDensity;
	    }
	    
	    public void setFrog(final IFrog frog) {
	        this.frog = frog;
	    }
	    
	    public void setEnvironment(final IEnvironment environment) {
	        this.environment = environment;
	    }
	    
	    public IFroggerGraphics getGraphic() {
	        return this.graphic;
	    }

	/**
	 * Teste si la partie est perdue
	 * @return true si la partie est perdue, false sinon
	 */
	public boolean testLose() {
	        if (!this.environment.isSafe(this.frog.getPosition())) {
	            this.graphic.endGameScreen("You Lose Froggy! Your score is " + this.scoreMax+" pts");
	            return true;
	        }
	        return false;
	    }

	/**
	 * Test si la partie est gagnée
	 * @return true si la partie est gagnée, false sinon
	 */
	public boolean testWin() {
	        if (this.environment.isWinningPosition(this.frog.getPosition())) {
	            return true;
	        }
	        return false;
	    }

	/**
	 * Procédure qui permet de faire la mise à jour
	 */
	public void update() {
	        this.graphic.clear();
	        this.environment.update();
	        this.graphic.add(new Element(this.frog.getPosition().absc, 1, Color.GREEN));
	        this.testLose();
	        this.testWin();
	    }

	/**
	 * Procédure qui ajoute des voies à l'environnement
	 */
	public void addLane() {
	        this.environment.addLane();
	    }
}
