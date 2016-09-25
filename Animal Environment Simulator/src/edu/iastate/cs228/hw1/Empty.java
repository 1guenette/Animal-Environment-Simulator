package edu.iastate.cs228.hw1;

/**
 *  
 * @author Samuel Guenette
 *
 */

/** 
 * Empty squares are competed by various forms of life.
 */
public class Empty extends Living 
{
	private World world;
	private int row;
	private int col;
	private int age;
	private State state;
	
	public Empty (World w, int r, int c) 
	{
		super(w, r, c);
		state = State.EMPTY;
	}
	
	public State who()
	{
		return state.EMPTY; 
	}
	
	/**
	 * An empty square will be occupied by a neighboring Badger, Fox, Rabbit, or Grass, or 
	 * remain empty. 
	 * @param wNew     world of the next life cycle.
	 * @return Living  life form in the next cycle.   
	 */
	public Living next(World wNew)
	{
		// LOOPS THROGH AND COUNT NUMBER OF EACH SPECIES, THEN EVALUATE
		int[] lifeForms = new int[NUM_LIFE_FORMS];
		
		//if(row<mapping.length-1 && row>0 && col<mapping.length-1 && col>0)
		{
						
			if(lifeForms[RABBIT] > 1)
			{
				return new Rabbit(wNew,row,col, 0);
				
			}
			else if(lifeForms[FOX] > 1)
			{
				return new Fox(wNew,row,col,0); 
			}
			else if(lifeForms[BADGER] > 1)
			{
				return new Badger(wNew,row,col,0);
			}
			else if(lifeForms[GRASS] > 1)
			{
				return new Grass(wNew,row,col);
			}
		}
		
		
		return new Empty(wNew, row, col);
	}
}
