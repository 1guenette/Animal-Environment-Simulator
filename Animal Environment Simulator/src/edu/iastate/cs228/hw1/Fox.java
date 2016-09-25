package edu.iastate.cs228.hw1;

/**
 *  
 * @author Samuel Guenette
 *
 */

/**
 * A fox eats rabbits and competes against a badger. 
 */
public class Fox extends Animal 
{
	private World world;
	private int row;
	private int col;
	private int age;
	private State state;
	
	/**
	 * Constructor 
	 * @param w: world
	 * @param r: row position 
	 * @param c: column position
	 * @param a: age 
	 */
	public Fox (World w, int r, int c, int a) 
	{
		super(w,r,c,a);
		state = State.FOX;
	}
		
	/**
	 * A fox occupies the square. 	 
	 */
	public State who()
	{
		return state; 
	}
	
	/**
	 * A fox dies of old age or hunger, or from attack by numerically superior badgers. 
	 * @param wNew     world of the next cycle
	 * @return Living  life form occupying the square in the next cycle. 
	 */
	public Living next(World wNew)
	{
	
		// LOOPS THROGH AND COUNT NUMBER OF EACH SPECIES, THEN EVALUATE
		
		int[] lifeForms = new int[NUM_LIFE_FORMS];
		
		if(age == 6)
		{
			return new Empty(wNew,row,col);                                                    
		}
		
		else //if(row<world.grid.length-1 && row>0 && col<world.grid.length-1 && col>0)
		{
			census(lifeForms);
			if(lifeForms[BADGER] >lifeForms[FOX])
			{
				return new Badger(wNew,row,col,0);
				
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
		
		
		return new Fox(wNew, row, col, age); 
	}
}
