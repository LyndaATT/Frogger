package environment;

import java.util.ArrayList;
import java.util.Random;

import gameCommons.Case;
import gameCommons.Game;
import gameCommons.IEnvironment ;

public class Environment implements IEnvironment {
		
	private ArrayList<Lane> setLanes;
	private Game game;
	public final Random randomGen = new Random();
	
	public Environment(Game game){
	    this.game = game;
	    this.setLanes = new ArrayList<>();
	    // Premiere Ligne
	    this.setLanes.add(new Lane(game, 0, 0.0f));
	    // Autres lignes
	    for(int i = 1; i < game.height - 1; ++i) {
	        if(i%6==0){this.setLanes.add(new River(game, i));}
	        else {this.setLanes.add(new Lane(game, i));}
	    }
	    // Derniere ligne
	    this.setLanes.add(new Lane(game, game.height - 1, 0.0f));
	}
	
	public boolean isSafe(Case c) {
		Lane ligne = this.setLanes.get(c.ord);
		//if(ligne instanceof River){ ligne = (River) ligne;}
		ligne = ligne;
		    return (ligne.isSafe(c));
	}
	
	public boolean isWinningPosition(Case c){
	    return (c.ord == this.game.height-1);
	}
	
	public void update(){
	    ArrayList<Lane> newSetLanes = new ArrayList<>(this.setLanes.size());
	    for (int i = 0; i < this.setLanes.size(); i++) {
	        Lane lane = this.setLanes.get(i);
	        if( lane instanceof River) {
				lane = (River) lane;
	        	lane.update();
			} else {
	        	lane = (Lane)lane;
				lane.update();
			}
	        newSetLanes.add(lane);
	    }
	    this.setLanes = newSetLanes;
	}

	
	
    
}
