package gameCommons;

import gameCommons.Case;
import gameCommons.Direction;

import java.awt.*;

public interface IFrog {
	
	/**
	 * Donne la position actuelle de la grenouille
	 * @return
	 */
	public Case getPosition();
	
	/**
	 * Donne la direction de la grenouille, c'est � dire de son dernier mouvement 
	 * @return
	 */
	public Direction getDirection();
	
	/**
	 * D�place la grenouille dans la direction donn�e et teste la fin de partie
	 * @param key
	 */
	public void move(Direction key);

	/**
	 * Retourne la couleur de notre grenouille
	 * @return color
	 */
	public Color getColor();
}
