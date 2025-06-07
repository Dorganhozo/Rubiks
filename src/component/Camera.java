package component;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;

import math.Vector3;

//Isso vai facilitar as coisas
public class Camera {
	private Cube cube;
	private Vector3[][] vectors;

	private Vector3 direction;

	private Consumer<Boolean> horizontalRotation = this::rotateX, verticalRotation = this::rotateY, inactiveRotation = this::rotateZ;



	private int depth;

	public Face[][] getPerspectiveFaces(){
		Face[][] colors = new Face[vectors.length][vectors.length];

		int axisIndex = 0;

		Vector3 first = vectors[0][0], last = vectors[cube.dim-1][cube.dim-1];

		boolean[] axisEquivalences = {
			first.getX() == last.getX(),
			first.getY() == last.getY(), 
			first.getZ() == last.getZ()
		};

		do if(axisEquivalences[axisIndex])break;
		while (++axisIndex < axisEquivalences.length);


		for(int y=0; y < cube.dim; y++)
			for(int x=0; x < cube.dim; x++){
				int[] position = {vectors[x][y].getX(), vectors[x][y].getY(), vectors[x][y].getZ()};

				position[axisIndex] = Math.abs(position[axisIndex] - depth);	
				
				colors[y][x] = cube.getPiece(position[0], position[1], position[2]).face(direction);
			}

		

		return colors;
	}

	public void print(Vector3 direction){
		

		int axisIndex = 0;

		Vector3 first = vectors[0][0], last = vectors[cube.dim-1][cube.dim-1];

		boolean[] axisEquivalences = {
			first.getX() == last.getX(),
			first.getY() == last.getY(), 
			first.getZ() == last.getZ()
		};

		do if(axisEquivalences[axisIndex])break;
		while (++axisIndex < axisEquivalences.length);


		for(int y=0; y < cube.dim; y++){
			for(int x=0; x < cube.dim; x++){
				int[] position = {vectors[x][y].getX(), vectors[x][y].getY(), vectors[x][y].getZ()};

				position[axisIndex] = Math.abs(position[axisIndex] - depth);	
				
				System.out.print(cube.getPiece(position[0], position[1], position[2]).face(direction));
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
		int dirX = direction.getX();
		int dirY = direction.getZ();

		if(counterClockWise)
			dirY = -dirY;
		else 
			dirX = -dirX;

		direction.setX(dirY);
		direction.setZ(dirX);
	

		for(int y=0; y < cube.dim; y++)
			for(int x=0; x < cube.dim; x++){
				Vector3 position = vectors[x][y];
				int newX = position.getX(); 
				int newY = position.getZ();

				if(counterClockWise)
					newY = cube.dim - (newY + 1);
				else
					newX = cube.dim - (newX + 1);
				
				position.setX(newY);		
				position.setZ(newX); 

			}
	}

	public void rotateY(boolean counterClockWise){

		int dirX = direction.getY();
		int dirY = direction.getZ();

		if(counterClockWise)
			dirY = -dirY;
		else 
			dirX = -dirX;

		direction.setY(dirY);
		direction.setZ(dirX);


		for(int y=0; y < cube.dim; y++)
			for(int x=0; x < cube.dim; x++){
				Vector3 position = vectors[x][y];
				int newX = position.getY(); 
				int newY = position.getZ();

				if(counterClockWise)
					newY = cube.dim - (newY + 1);
				else
					newX = cube.dim - (newX + 1);
				

				position.setY(newY);		
				position.setZ(newX); 

			}


	}

	public void rotateZ(boolean counterClockWise){

		int dirX = direction.getX();
		int dirY = direction.getY();

		if(counterClockWise)
			dirY = -dirY;
		else 
			dirX = -dirX;

		direction.setX(dirY);
		direction.setY(dirX);

		for(int y=0; y < cube.dim; y++)
			for(int x=0; x < cube.dim; x++){
				Vector3 position = vectors[x][y];
				int newX = position.getX(); 
				int newY = position.getY();

				if(counterClockWise)
					newY = cube.dim - (newY + 1);
				else
					newX = cube.dim - (newX + 1);


				position.setX(newY);		
				position.setY(newX); 

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

		public Vector3 vect(){
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
			for (int x = 0; x < cube.dim; x++) 
				this.vectors[x][y] = Vector3.of(x, y, 0); 

	}

}
