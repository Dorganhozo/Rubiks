package util;

import java.util.Arrays;

//Matriz 3D para a Matriz 2D
public class Flat {

	private final int x1, y1, z1, x2, y2, z2;
	private final Cube cube;
	private int firstAxis, secondAxis;

	public Piece getPiece(int x, int y) {
		int[] position = getPosition(x, y);
		return cube.getPiece(position[0], position[1], position[2]);
	}

	public Piece setPiece(int x, int y, Piece piece) {
		int[] position = getPosition(x, y);
	        return piece.toPosition(position[0], position[1], position[2]);
	}

	private int[] getPosition(int x, int y){
		int[] position = {x1, y1, z1};
		position[firstAxis] = x;
		position[secondAxis] = y;
		return position;
	}
	public int getDimension() {
		return cube.dim;
	}

	@Override
	public String toString() {
	    	StringBuilder strb = new StringBuilder();
		for(int y=0; y < cube.dim; y++){
			for(int x=0; x < cube.dim; x++)
				strb.append(getPiece(x, y));
			strb.append("\n");
		}
		return strb.toString();
	}

	public Flat(int x1, int y1, int z1, int x2, int y2, int z2, Cube cube){
		this.x1 = x1;
		this.y1 = y1;
		this.z1 = z1;

		this.x2 = x2;
		this.y2 = y2;
		this.z2 = z2;
		this.cube = cube;

		

		boolean[] equivalences = {x2 == x1, y2 == y1, z2 == z1};

		for(int i=0; i < equivalences.length; i++){
			if(!equivalences[i]){
				firstAxis = i;
				break;
			}
		}

		for(int i=firstAxis+1; i < equivalences.length; i++){
			if(!equivalences[i]){
				secondAxis = i;
				break;
			}
		}

	}

	public Flat(Cube.Group group, Cube cube){
		this(
			group.x1*(cube.dim - 1), 
			group.y1*(cube.dim - 1),
			group.z1*(cube.dim - 1), 
			group.x2*(cube.dim - 1),
			group.y2*(cube.dim - 1),
			group.z2*(cube.dim - 1),
			cube
		);
	}

}
