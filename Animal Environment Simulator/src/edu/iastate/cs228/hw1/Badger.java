package edu.iastate.cs228.hw1;

/**
 *  
 * @author Samuel Guenette
 *
 */

/**
 * A badger eats a rabbit and competes against a fox. 
 */
public class Badger extends Animal
{
	private World world;
	private int row;
	private int col;
	private State state;
	/**
	 * Constructor 
	 * @param w: world
	 * @param r: row position 
	 * @param c: column position
	 * @param a: age 
	 */
	public Badger (World w, int r, int c, int a) 
	{
		super(w,r,c,a);
		state = State.BADGER;
		
	}
	
	/**
	 * 
	 * A badger occupies the square. 	 
	 */
	public State who()
	{
		return state;
	}
	
	/**
	 * A badger dies of old age or hunger, from isolation and attack by a group of foxes. 
	 * @param wNew     world of the next cycle
	 * @return Living  life form occupying the square in the next cycle. 
	 */
	public Living next(World wNew)
	{	
		int[] lifeForms = new int[NUM_LIFE_FORMS]; //1-D array for census 
		
		
		// LOOPS THROGH AND COUNT NUMBER OF EACH SPECIES, THEN EVALUATE
		if(age == 4)
		{
			return new Empty(wNew,row,col);                                                    
		}
		else 
		{
			census(lifeForms);
			
			if(lifeForms[BADGER] == 1 && lifeForms[FOX] > 1)
			{
				return new Empty(wNew,row,col);
				
			}
			else if(lifeForms[BADGER] + lifeForms[FOX] > lifeForms[RABBIT])
			{
				return new Empty(wNew,row,col); 
			}
			else
			{
				age++;
			}
			
		}
		
		return new Badger(wNew, row, col, age);
	}
}
