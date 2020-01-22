package gameCommons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import environment.*;
import javax.swing.Timer;

import frog.Frog;
import graphicalElements.FroggerGraphic;
import graphicalElements.IFroggerGraphics;

public class Main {
 	public static void main(String[] args) {
  
		//Caractristiques du jeu
		int width = 40;
		int height = 40;
		int tempo = 100;
		int minSpeedInTimerLoops = 1;
		double defaultDensity = 0.1;
		
		//Création de l'interface graphique
		IFroggerGraphics graphic = new FroggerGraphic(width, height);
		//Création de la partie
		Game game = new Game(graphic, width, height, minSpeedInTimerLoops, defaultDensity);
		//Création et liason de la grenouille
		IFrog frog = new Frog(game);
		game.setFrog(frog);
		graphic.setFrog(frog);
		//Cration et liaison de l'environnement
        IEnvironment env = new Environment(game);
		game.setEnvironment(env);
				
		//Boucle principale : l'environnement s'acturalise tous les tempo milisecondes
		Timer timer = new Timer(tempo, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.update();
				game.time();
				graphic.repaint();
			}
		});
		timer.setInitialDelay(0);
		timer.start();
	}
}
