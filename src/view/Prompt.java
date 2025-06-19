package view;
import java.util.Scanner;

import cli.Command;
import component.Camera;
import component.Cube;
import component.Face;
import component.Camera.Direction;
import moviment.Magic;

public class Prompt {

	private Cube cube;
	private Camera camera;
	private Scanner scan;
	private int dimension = 3;

	public void initialize(){
		cube = new Cube(dimension);
		camera = new Camera(cube);
		scan = new Scanner(System.in);


		System.out.println("Welcome to Rubiks!");
		System.out.println("Type help to see the commands.");
		

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
					e.printStackTrace();
				}
			}catch(IllegalArgumentException e){
				System.err.printf("%s is invalid\n", name);
			}

		}

	}

	public void print(){
		Face[][] faces = camera.getPerspectiveFaces();

		for(Face[] line : faces){
			for (Face face : line) {
				System.out.print(face);	
			}
			System.out.println();
		}
	}

	public void go(Direction direction){

		switch (direction) {
			case UP:
				camera.rotateVertically(false);
				break;
			case DOWN:
				camera.rotateVertically(true);
				break;
			case LEFT:
				camera.rotateHorizontally(false);
				break;
			case RIGHT:
				camera.rotateHorizontally(true);
				break;

			default: break;
		}	
	}

	public void rotate(boolean counterClockWise){
		Magic.rotate(cube, camera, counterClockWise);
	}

	public void resize(int dimension) {
		this.dimension = dimension;
		reset();
	}


	public void reset(){
		this.cube = new Cube(dimension);
		this.camera = new Camera(cube);
	}

	public void close(){
		scan.close();
		System.exit(0);
	}



}
