package edu.iastate.cs228.hw1;

/**
 *  
 * @author Samuel Guenette
 *
 */

import java.io.File; 
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.xml.bind.ValidationEventLocator;

import java.util.Random; 

/**
 * 
 * The world is represented as a square grid of size width X width. 
 *
 */
public class World 
{
	private int width; // grid size: width X width 
	
	public Living[][] grid; 
	private File file;
	private PrintWriter mapMaker;
	//private Scanner reader;
	
	
	/**
	 *  Default constructor reads from a file 
	 */
	public World(String inputFileName) throws FileNotFoundException
	{		
		// 
		// Assumption: The input file is in correct format. 
		// 
		// You may create the grid world in the following steps: 
		// 
		// 1) Reads the first line to determine the width of the grid.
		// 
		// 2) Creates a grid object. 
		// 
		// 3) Fills in the grid according to the input file. 
		// 
		// Be sure to close the input file when you are done. 
		
		file = new File(inputFileName);
		
		int length = 0;
		Scanner reader = new Scanner(file);
		
		double doubleWidth = 0;
		while(reader.hasNext())
		{
			doubleWidth++;
			reader.next();
		}
		reader.close();
		doubleWidth = Math.sqrt(doubleWidth);
		width = (int)doubleWidth;
		
		grid = new Living[width][width];
		
		file = new File(inputFileName);
		Scanner scan2 = new Scanner(file); 
		
		
		
		
		int x=0;
		int y=0;
		
		while(scan2.hasNextLine())
		{
			String line = scan2.nextLine();
			Scanner lineReader = new Scanner(line);
			while(lineReader.hasNext())
			{
				String combo = lineReader.next();
			if(combo.substring(0,1) == "F" || combo.substring(0,1) == "B"  || combo.substring(0,1) == "R")
			{
				int age = Integer.parseInt(combo.substring(1));
				if(combo.substring(0,1) == "F")
				{
					grid[x][y] = new Fox(this,x,y,age);
				}
				else if(combo.substring(0,1) == "B")
				{
					grid[x][y] = new Badger(this,x,y,age);
				}
				else if(combo.substring(0,1) == "R")
				{
					grid[x][y] = new Rabbit(this,x,y,age);
				}
				y++;
			}
			else if(combo.substring(0,1) == "E"  || combo.substring(0,1) == "G")
			{
				if(combo.substring(0,1) == "E")
				{
					grid[x][y] = new Empty(this,x,y);
				}
				else if(combo.substring(0,1) == "G")
				{
					grid[x][y] = new Grass(this,x,y);
				}
				y++;
			}
			
			}
			x++;
		}
		
		
	}
	
	/**
	 * Constructor that builds a w X w grid without initializing it. 
	 * @param width  the grid 
	 */
	public World(int w)
	{
		width = w;
		grid = new Living[width][width];
	}
	
	
	public int getWidth()
	{
		return width;  
	}
	
	/**
	 * Initialize the world by randomly assigning to every square of the grid  
	 * one of BADGER, FOX, RABBIT, GRASS, or EMPTY.  
	 * 
	 * Every animal starts at age 0.
	 */
	public void randomInit()
	{
		Random generator = new Random();
		for(int i = 0; i<width; i++)
		{
			for(int j = 0; j<width; j++)
			{

				int val = generator.nextInt(5);
				if(val == 0)
				{
					grid[i][j] = new Badger(this, i, j, 0);
				}
				else if(val == 1)
				{
					grid[i][j] = new Fox(this, i, j, 0);
				}
				else if(val == 2)
				{
					grid[i][j] = new Rabbit(this, i, j, 0);
				}
				else if(val == 3)
				{
					grid[i][j] = new Grass(this, i, j);
				}
				else
				{
					grid[i][j] = new Empty(this, i, j);
				}
			}
		}
		
	}
	
	
	/**
	 * Output the world grid. For each square, output the first letter of the living form
	 * occupying the square. If the living form is an animal, then output the age of the animal 
	 * followed by a blank space; otherwise, output two blanks.  
	 */
	public String toString()
	{
		System.out.println("");
		String map = "";
		for(int i = 0; i<width; i++)
		{
			System.out.println("");
			
			for(int j = 0; j<width; j++)
			{
				State val = grid[i][j].who();
				Animal type = null;
				int age;
						
				if(val != State.EMPTY && val != State.GRASS)
				{
					type = (Animal)grid[i][j];
					age = type.myAge();
				}
				
				if(val == State.BADGER)
				{
					map += "B" +  type.myAge()+ " ";
					//System.out.print("B" +  type.myAge()+ " ");
				}
				else if(val == State.FOX)
				{
					map += "F" + type.myAge()+ " ";
					//System.out.print("F" + type.myAge()+ " ");
				}
				else if(val == State.RABBIT)
				{
					map += "R" + type.myAge()+ " ";
					//System.out.print("R" + type.myAge()+ " ");
				}
				else if(val == State.GRASS)
				{
					map += "G  ";
					//System.out.print("G  ");
				}
				else
				{
					map += "E  ";
					//System.out.print("E  ");
				}
				
				map+="\t";
			}
			map+="\n";
		}
		return map;
	}
	

	/**
	 * Write the world grid to an output file.  Also useful for saving a randomly 
	 * generated world for debugging purpose. 
	 * @throws FileNotFoundException
	 */
	public void write(String outputFileName) throws FileNotFoundException
	{
		File map = new File(outputFileName);
		mapMaker = new PrintWriter(map);
		String ref = this.toString();
		Scanner scan = new Scanner(ref);
		while(scan.hasNext())
		{
			mapMaker.println(scan.nextLine());
		}
		
		scan.close();
		mapMaker.close();
		
		
		// 1. Open the file. 
		// 
		// 2. Write to the file. The five life forms are represented by characters 
		//    B, E, F, G, R. Leave one blank space in between. Examples are given in
		//    the project description. 
		// 
		// 3. Close the file. 
	}			

}
