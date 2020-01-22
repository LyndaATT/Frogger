package newInf;



import graphicalElements.Element;
import java.awt.Color;
import gameCommons.*;

public class FrogInf implements IFrog
{
	    private Case position;
	    private Direction direction;
	    private Game game;
	    
	    public FrogInf(final Game game) {
	        this.position = new Case(game.width / 2, 1);
	        this.direction = Direction.up;
	        this.game = game;
	    }


	public Case getPosition() {
	        return this.position;
	    }


	public Direction getDirection() {
	        return this.direction;
	    }

	public void move(final Direction key) {
	        this.direction = key;
	        
	    switch(key) {
	      case up   : { this.position = new Case(this.position.absc, this.position.ord + 1);
	               this.game.addLane();
		            this.game.score++;
		            if (this.game.score > this.game.scoreMax) {
		                this.game.scoreMax = this.game.score;
		            }
			      } break;
			      
			case down :
				     if(this.position.ord!=0) {
				        this.position = new Case(this.position.absc, this.position.ord - 1);
			            this.game.score--;} break;
			      
			case left :
				  if(this.position.absc >= 0) {
	                   this.position = new Case(this.position.absc -2, this.position.ord);
	             }
			case right :
				 if(this.position.absc < this.game.width - 1) {
			            this.position = new Case(this.position.absc + 1, this.position.ord);
			}   
			this.game.testLose();
			this.game.testWin();

	    }
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    }}
	    
	    
	    
	    
	    
