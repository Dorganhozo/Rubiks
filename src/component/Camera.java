package component;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;

import math.Vector3;

//Isso vai facilitar as coisas
public class Camera {
	private Cube cube;
	private Vector3<Integer>[][] vectors;
	private Vector3<Integer> direction;

	private Consumer<Boolean> horizontalRotation = this::rotateX, verticalRotation = this::rotateY, inactiveRotation = this::rotateZ;


	//TODO: Resolva esse negocio! depth sempre tem que estar perto da camera
	/*
	 * 0 a finn
	 * 2 -> 0
	 * 2 - 0
	 * 0 + 0
	 */
	private int depth;

	public void print(Vector3<Integer> direction){
		for(int y=0; y < cube.dim; y++){
			for(int x=0; x < cube.dim; x++){
				Vector3<Integer> position = vectors[x][y];
				System.out.print(cube.getPiece(position).face(direction));
			}

			System.out.println();
		}
	}


	public void print(){
		print(direction);
	}

	public void rotateHorizontally(boolean counterClockWise){
		horizontalRotation.accept(counterClockWise);

		Consumer<Boolean> temp = verticalRotation;
		verticalRotation = inactiveRotation;
		inactiveRotation = temp;

	}

	public void rotateVertically(boolean counterClockWise){
		verticalRotation.accept(counterClockWise);

		Consumer<Boolean> temp = horizontalRotation;
		horizontalRotation = inactiveRotation;
		inactiveRotation = temp;

	}


	public void rotateX(boolean counterClockWise){

		for(int y=0; y < cube.dim; y++)
			for(int x=0; x < cube.dim; x++){
				Vector3<Integer> position = vectors[x][y];
				int newX = position.getX(); 
				int newY = position.getZ();

				int dirX = direction.getX();
				int dirY = direction.getZ();


				if(counterClockWise){
					newY = (newY + 1) ^ cube.dim;
					dirY = -dirY;
				}else{
					newX = (newX + 1) ^ cube.dim;
					dirX = -dirX;
				}
				position.setX(newY);		
				position.setZ(newX); 

				direction.setX(dirY);
				direction.setZ(dirX);
			}
	}

	public void rotateY(boolean counterClockWise){

		for(int y=0; y < cube.dim; y++)
			for(int x=0; x < cube.dim; x++){
				Vector3<Integer> position = vectors[x][y];
				int newX = position.getY(); 
				int newY = position.getZ();

				int dirX = direction.getY();
				int dirY = direction.getZ();

				if(counterClockWise){
					newY = (newY + 1) ^ cube.dim;
					dirY = -dirY;
				}else{
					newX = (newX + 1) ^ cube.dim;
					dirX = -dirX;
				}

				position.setY(newY);		
				position.setZ(newX); 

				direction.setY(dirY);
				direction.setZ(dirX);
			}


	}

	public void rotateZ(boolean counterClockWise){

		for(int y=0; y < cube.dim; y++)
			for(int x=0; x < cube.dim; x++){
				Vector3<Integer> position = vectors[x][y];
				int newX = position.getX(); 
				int newY = position.getY();

				int dirX = direction.getX();
				int dirY = direction.getY();

				if(counterClockWise){
					newY = (newY + 1) ^ cube.dim;
					dirY = -dirY;
				}else{
					newX = (newX + 1) ^ cube.dim;
					dirX = -dirX;
				}
				position.setX(newY);		
				position.setY(newX); 

				direction.setX(dirY);
				direction.setY(dirX);
			}	

	}


	public void setDepth(int depth){
		this.depth = depth;
	}



	public enum Direction{
		UP	(0, -1, 0), 
		DOWN	(0, 1, 0),
		LEFT	(-1, 0, 0), 
		RIGHT	(1, 0, 0), 
		FRONT	(0, 0, -1), 
		BACK	(0, 0, 1);

		public final int x, y, z;

		public Vector3<Integer> vect(){
			return Vector3.of(x, y, z);
		}

		private Direction(int x, int y, int z){
			this.x = x;
			this.y = y;
			this.z = z;
		}

	}


	public Camera(Cube cube){
		this.cube = cube;	
		this.vectors = new Vector3[cube.dim][cube.dim];
		this.direction = Direction.FRONT.vect();

		for(int y=0; y < cube.dim; y++)
			for (int x = 0; x < vectors.length; x++) 
				this.vectors[x][y] = Vector3.of(x, y, 0); 

	}

}
