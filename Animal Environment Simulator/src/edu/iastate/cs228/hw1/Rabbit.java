package edu.iastate.cs228.hw1;

/**
 *  
 * @author Samuel Guenette
 *
 */

/*
 * A rabbit eats grass and lives no more than three years.
 */
public class Rabbit extends Animal 
{	
	private World world;
	private int row;
	private int col;
	private State state;
	/**
	 * Creates a Rabbit object.
	 * @param w: world  
	 * @param r: row position 
	 * @param c: column position
	 * @param a: age 
	 */
	public Rabbit (World w, int r, int c, int a) 
	{
		super(w, r, c, a);
		state = State.RABBIT;
	}
		
	// Rabbit occupies the square.
	public State who()
	{
		return state; 
	}
	
	/**
	 * A rabbit dies of old age or hunger, or it is eaten if there are as many 
	 * foxes and badgers in the neighborhood.  
	 * @param wNew     world of the next cycle 
	 * @return Living  new life form occupying the same square
	 */
	public Living next(World wNew)
	{
		// LOOPS THROGH AND COUNT NUMBER OF EACH SPECIES, THEN EVALUATE
		int[] lifeForms = new int[NUM_LIFE_FORMS];
		
		if(age == 3)
		{
			return new Empty(wNew,row,col);                                                    
		}
		else //if(row<world.grid.length-1 && row>0 && col<world.grid.length-1 && col>0)
		{
			census(lifeForms);
			
			if(lifeForms[GRASS] == 0)
			{
				return new Empty(wNew,row,col);
				
			}
			else if((lifeForms[FOX] + lifeForms[BADGER] >= lifeForms[RABBIT]) && (lifeForms[FOX] > lifeForms[BADGER] ))
			{
				return new Fox(wNew,row,col,0); 
			}
			else if(lifeForms[BADGER] > lifeForms[RABBIT])
			{
				return new Badger(wNew,row,col,0); 
			}
			else
			{
				age++;
			}
		}
		
		 
		return new Rabbit(wNew, row, col, age); 
	}
}
