package component;

import math.ValueReference;
import math.Vector2;
import math.Vector3;

//Isso vai facilitar as coisas
public class Camera {
	private Cube cube;
	private Vector3<Integer>[][] vectors;

	//TODO: Resolva esse negocio! depth sempre tem que estar perto da camera
	private int depth;
	
	public void print(String sideName){

		System.out.println(get(1, 1));
		for(int y=0; y < cube.dim; y++){
			for(int x=0; x < cube.dim; x++){
				Vector3<Integer> position = get(x, y);
				System.out.print(cube.getPiece(position.getX(), position.getY(), position.getZ()).face(sideName));
			}
			System.out.println();
		}
	}

	public void rotateX(boolean counterClockWise){
		for(int l=0; l < cube.dim; l++)
			for(int c=0; c < cube.dim; c++){
				Vector3<Integer> position = get(c, l);
				int newX = position.getX(); 
				int newY = position.getZ();

				if(counterClockWise)
					newX = (newY + 1) ^ cube.dim;
				else
					newY = (newX + 1) ^ cube.dim;

				get(c, l).setX(newY);		
				get(c, l).setZ(newX); 
			}
	}

	public void rotateY(boolean counterClockWise){
		for(int l=0; l < cube.dim; l++)
			for(int c=0; c < cube.dim; c++){
				Vector3<Integer> position = get(c, l);
				int newX = position.getY(); 
				int newY = position.getZ();

				if(!counterClockWise)
					newX = (newX + 1) ^ cube.dim;
				else
					newY = (newY + 1) ^ cube.dim;

				get(c, l).setY(newY);		
				get(c, l).setZ(newX); 
			}
	}

	public void rotateZ(boolean counterClockWise){
		for(int l=0; l < cube.dim; l++)
			for(int c=0; c < cube.dim; c++){
				Vector3<Integer> position = get(c, l);
				int newX = position.getX(); 
				int newY = position.getY();

				if(counterClockWise)
					newX = (newX + 1) ^ cube.dim;
				else
					newY = (newY + 1) ^ cube.dim;

				get(c, l).setX(newY);		
				get(c, l).setY(newX); 
			}	
	}


	private Vector3<Integer> get(int column, int line){
		return vectors[line][column];
	}

	public void setDepth(int depth){
		this.depth = depth;
	}


	public Camera(Cube cube){
		this.cube = cube;	
		this.vectors = new Vector3[cube.dim][cube.dim];

		for(int y=0; y < cube.dim; y++)
			for (int x = 0; x < vectors.length; x++) 
				this.vectors[y][x] = new Vector3<>(x, y, 0); 

	}

}
