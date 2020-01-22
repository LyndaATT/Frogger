package newInf;
//
//Decompiled by Procyon v0.5.36
//

import java.util.Iterator;
import gameCommons.Case;
import gameCommons.Game;
import java.util.ArrayList;
import gameCommons.IEnvironment;

public class EnvironementInf implements IEnvironment
{
	 private ArrayList<LaneInf> setLanes;
	    private Game game;
	    
	    public EnvironementInf( Game game) {
	        this.game = game;
		    this.setLanes = new ArrayList<>();
		    this.setLanes.add(new LaneInf(game, 0, 0f));

		    // Premiere Ligne
		    this.setLanes.add(new LaneInf(game, 1, 0f));
		    // Autres lignes
		    for(int i = 2; i < game.height; i++) {
		    	this.addLane(i);
		    }
	    }

	/**
	 * Procedure qui permet d'ajouter une voie à l'ordonnée donnée
	 * @param ord
	 */
	public void addLane( int ord) {
	        this.setLanes.add(new LaneInf(this.game, ord));
	    }

	/**
	 * Test si la case donnée en parametre est sans danger (case sûre)
	 * @param c la case à tester
	 * @return true si la case est sûre, false sinon
	 */
	public boolean isSafe( Case c) {
	    	LaneInf ligne = this.setLanes.get(this.game.score+1);
			    return (ligne.isSafe(c));
	    }


	/**
	 * Teste si une case est la case de fin (qui permet de gagner la partie)
	 * @param c la case à tester
	 * @return true si c'est une case gagnante, false sinon
	 */
	public boolean isWinningPosition( Case c) {
	        return false;
	    }

	/**
	 * Procédure qui permet de faire la mise à jour
	 */
	public void update() {
	    	 ArrayList<LaneInf> newSetLanes = new ArrayList<>(this.setLanes.size());
	 	    for (int i = 0; i < this.setLanes.size(); i++) {
	 	        LaneInf lane = this.setLanes.get(i);
	 	        lane.update();
	 	        newSetLanes.add(lane);
	 	    }
	 	    this.setLanes = newSetLanes;
	    }

	/**
	 * Procédure qui permet d'ajouter une voie
	 */
	public void addLane() {
	    	int s = this.setLanes.size();
	        this.addLane(s);
	    }
}
