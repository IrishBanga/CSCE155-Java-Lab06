package unl.cse.florist;

import java.util.Scanner;

public class Florist {

	private enum Color { RED, WHITE }
	private enum Flower { }
	private enum Arrangement { }

	/**
	 * A function to return the cost of the given flower arrangement
	 * @param flower
	 * @param color
	 * @param arr
	 * @return
	 */
	public static double getCost(Flower flower, Color  color, Arrangement arr) {
		//TODO: Implement this method

		return 0.0;
	}
	
	public static void main(String args[]) {
		
		Flower flower = null;
		Color color = null;
		Arrangement arr = null;
		double cost = 0.0;
		int flower_choice, color_choice, arrangement_choice;
		
		Scanner s = new Scanner(System.in);
		
		System.out.println("\nTypes of flowers");
		System.out.println("1. Roses");
		System.out.println("2. Lilies");
		System.out.println("3. Daises");
		System.out.print("Please enter the item number for your choice: ");
		flower_choice = s.nextInt();
			
		System.out.println("\nColor choices");
		System.out.println("1. Red");
		System.out.println("2. White");
		System.out.print("Please enter the item number for your choice: ");
		color_choice = s.nextInt();
			
		System.out.println("\nArrangements");
		System.out.println("1. Bouquet");
		System.out.println("2. Vase");
		System.out.print("Please enter the item number for your choice: ");
		arrangement_choice = s.nextInt();

		//TODO: process the choices to get the appropriate enum types
		//TODO: get the cost of the arrangement and echo it(print) to the user
		
		
		
	}
	
}
