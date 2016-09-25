package edu.iastate.cs228.hw1;

/**
 *  
 * @author Samuel Guenette
 *
 */

/*
 * This class is to be extended by the Badger, Fox, and Rabbit classes. 
 */
public abstract class Animal extends Living implements MyAge
{
	protected int age;   // age of the animal 
	
	/**
	 * Constructor 
	 * @param a: age 
	 */
	public Animal(World w, int r, int c, int a)
	{
		super(w,r,c);
		age = a;
		
	}
	
	
	@Override
	/**
	 * 
	 * @return age of the animal 
	 */
	public int myAge()
	{
		return age; 
	}
}
