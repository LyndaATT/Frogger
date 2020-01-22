package frog;

import gameCommons.Direction;
import gameCommons.Case;
import gameCommons.Game;
import gameCommons.IFrog;

import java.awt.*;

public class Frog implements IFrog {
	private Direction direction;
	private Case position;
	private Game game;
	private Color color;

	public Frog(Game game){
		this.position = new Case(game.width/2, 0);
		this.direction = Direction.up;
		this.game = game;
	}

	public Frog(Game game, int numeroJoueur){
		this.direction = Direction.up;
		this.game = game;
		if(numeroJoueur==1){this.color = new Color(108, 201, 25); this.position = new Case(game.width/4, 0);}
		else{ this.color = new Color(227, 225, 64);this.position = new Case(3*game.width/4, 0);}
	}
	public Case getPosition(){
		return this.position;
	}

	public Direction getDirection(){
		return this.direction;
	}

	public Color getColor(){
		return this.color;
	}
	public void move(Direction key){
		this.direction = key;
		switch(key){
			case up   : if(this.position.ord!=game.height){ this.position = new Case(this.position.absc,this.position.ord+1);
						    this.game.score++;
				            if (this.game.score > this.game.scoreMax) {
				                this.game.scoreMax = this.game.score;
				            }
		      } break;
			case down : if(this.position.ord!=0){ this.position = new Case(this.position.absc,this.position.ord-1);
			                 this.game.score--;} break;
			case left : if(this.position.absc!=0){ this.position = new Case(this.position.absc-1,this.position.ord);} break;
			case right : if(this.position.absc!=game.width){ this.position = new Case(this.position.absc+1,this.position.ord);} break;
		}
		this.game.testWin();
		this.game.testLose();
		this.game.update();
	}
}
