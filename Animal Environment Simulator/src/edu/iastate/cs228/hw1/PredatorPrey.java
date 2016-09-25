package edu.iastate.cs228.hw1;

import java.io.FileNotFoundException;
import java.util.Scanner; 

/**
 *  
 * @author Samuel Guenette
 *
 */

/**
 * 
 * The PredatorPrey class performs the predator-prey simulation over a grid world 
 * with squares occupied by badgers, foxes, rabbits, grass, or none. 
 *
 */
public class PredatorPrey 
{
	/**
	 * Update the new world from the old world in one cycle. 
	 * @param wOld  old world
	 * @param wNew  new world 
	 * @return void
	 */
	public static void updateWorld(World wOld, World wNew)
	{
		// For every life form (i.e., a Living object) in the grid wOld, generate  
		// a Living object in the grid wNew at the corresponding location such that 
		// the former life form changes into the latter life form. 
		// 
		// Employ the method next() of the Living class. 
		for(int i =0; i< wOld.grid.length; i++)
		{
			for(int j =0; j< wOld.grid[0].length; j++)
			{
				Living updatedWorld = wOld.grid[i][j].next(wNew);
				wNew.grid[i][j] = updatedWorld; 
			}
		}
	}
	
	/**
	 * Update the new world from the old world in one cycle. 
	 * @param wOld  old world
	 * @param wNew  new world 
	 * @throws FileNotFoundException 
	 * @return void
	 */
	public static void getInput() throws FileNotFoundException
	{
		World even;   // the world after an even number of cycles 
		World odd;                   // the world after an odd number of cycles
		
		System.out.println("Press 1 to create a random environment \n" + " Press 3 to terminate platform \n");
			
		Scanner scan = new Scanner(System.in);
		int input = scan.nextInt();
	
		
		if(input == 1)
		{
			System.out.println("Enter the height: ");
			System.out.println("");
			int height = scan.nextInt();
			
			System.out.println("Enter a number of cycles: ");
			System.out.println("");
			int cycleNum = scan.nextInt();
			
			even = new World(height);
			even.randomInit();
			odd = new World(height);
			
			//odd.toString();
			System.out.println(even.toString());
			for(int i = 0; i<cycleNum; i++)
			{
				if(i%2 == 0)
				{
					updateWorld(even, odd);
					
					if(i == cycleNum-1)//FOR PRINTING ONLY THE FINAL STATEMENT
					{
					System.out.println(odd.toString());
					}
				}
				else
				{
					updateWorld(odd, even);	
					if(i == cycleNum-1)   //FOR PRINTING ONLY THE FINAL STATEMENT
					{
						System.out.println(even.toString());
					}
				}
			}
			getInput();	
		}
		else if(input == 2)
		{
			System.out.println("Enter your file name: ");
			String file = scan.next();
			even = new World(file);
			odd = new World(file);
			System.out.println("Enter a number of cycles: ");
			int cycleNum = scan.nextInt();
			
			
			
			for(int i = 0; i<cycleNum; i++)
			{
				if(i%2 == 0)
				{
					updateWorld(even, odd);	
						odd.toString();			
				}
				else
				{
						even.toString();
				}
			}	
			getInput();
		}
		else if(input == 3)
		{
			System.out.println("SIMULATION PLATFORM TERMINATED");
		}
		else
		{
			System.out.println("Invalid Command");
			getInput();
		}
	}

	
	/**
	 * Repeatedly generates worlds either randomly or from reading files. 
	 * Over each world, carries out an input number of cycles of evolution. 
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException
	{			
		getInput();
	}
}
