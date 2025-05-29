package view;
import java.util.Scanner; 
import cli.Command;
import component.Cube;
import component.Face;
import math.ValueReference;
import math.Vector3;
import moviment.Magic;
	public class App {

		private Cube cube;
		private Scanner scan;

		public void initialize(){
			cube = new Cube();


			System.out.println("Welcome to Rubiks!");
			System.out.println("Type help to see the commands.");

			scan = new Scanner(System.in);
			while (true) {
				System.out.print(": ");
				String name = scan.next();
				String[] args = scan.nextLine().trim().split("\\s");

				try{
					Command command = Command.valueOf(name.toUpperCase());

					try{
						command.execute(this, args);
					}catch(Exception e){
						//System.out.println(e.getMessage());
						e.printStackTrace();
					}
				}catch(IllegalArgumentException e){
					System.err.printf("%s is invalid\n", name);
				}
				
			}
		

		}

		public void print(){ }
		

		public void rotate(String side, boolean inverse){ }


		public void reset(){
			this.cube = new Cube();
		}
		

		public void clear(){
			//System.out.println("\033[H\033[J");
		}
	
		public void exit() {
			scan.close();
			System.exit(0);
		}



}
