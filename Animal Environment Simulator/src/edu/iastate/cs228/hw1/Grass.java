package edu.iastate.cs228.hw1;

/**
 *  
 * @author Samuel Guenette
 *
 */

/**
 * Grass remains if more than rabbits in the neighborhood;
 * otherwise, it is eaten. 
 *
 */
public class Grass extends Living 
{
	private World world;
	private int row;
	private int col;
	private State state;
	
	public Grass (World w, int r, int c) 
	{
		super(w, r, c);
		state = State.GRASS;
	}
	
	public State who()
	{
		return state; 
	}
	
	/**
	 * Grass can be eaten out by too many rabbits in the neighborhood. Rabbits may also 
	 * multiply fast enough to take over Grass. 
	 */
	public Living next(World wNew)
	{
		// LOOPS THROGH AND COUNT NUMBER OF EACH SPECIES, THEN EVALUATE
		int[] lifeForms = new int[NUM_LIFE_FORMS];
		
		//if(row<world.grid.length-1 && row>0 && col<world.grid.length-1 && col>0)
		{
			if(lifeForms[RABBIT] > lifeForms[GRASS]*3)
			{
				return new Empty(wNew,row,col);
			}
			else if(lifeForms[RABBIT] >= 3)
			{
				return new Rabbit(wNew,row,col,0); 
			}			
		}
		
		
		return new Grass(wNew,row, col);
	}
}
