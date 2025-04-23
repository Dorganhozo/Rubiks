package view;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

import cli.Command;
import component.Cube;
import component.Flat;
import component.Piece;
import moviment.Magic;
public class App {

	private Cube cube;

	public void initialize(){
		cube = new Cube();

		System.out.println("Welcome to Rubiks!");
		System.out.println("Type help to see the commands.");

		Scanner scan = new Scanner(System.in);
		while (true) {
			System.out.print(": ");
			String name = scan.next();
			String[] args = scan.nextLine().trim().split("\\s");

			try{
				Command command = Command.valueOf(name.toUpperCase());

				try{
					command.execute(this, args);
				}catch(Exception e){
					System.out.println(e.getMessage());
				}
			}catch(IllegalArgumentException e){
				System.err.printf("%s is invalid\n", name);
			}
		}

	}

	public void print(Cube.Group group){
		int count = 0;
		System.out.println("\033[H\033[J");

		for(Piece piece : cube.getSide(group)){
			System.out.print(piece.face(group.name()));

			if(++count % cube.dim == 0)
				System.out.println();
		}
	}
	

	public void rotate(String side, boolean inverse){
		Cube.Group group = Cube.Group.valueOf(side.toUpperCase());
		Flat flat = new Flat(group, cube);

		Magic.rotate(flat, inverse);

		print(group);
	}

	



}
