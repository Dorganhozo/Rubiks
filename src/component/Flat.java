package component;

import math.Vector3;

public class Flat {
	private Vector3 toRight, toDown, position, cameraDirection;
	private Cube cube;

	public Flat(Cube cube, Vector3 origin, Vector3 toRight, Vector3 toDown, Vector3 cameraDirection) {
		this.toRight = toRight;
		this.toDown = toDown;
		this.position = origin;
		this.cameraDirection = cameraDirection;
		this.cube = cube;
	}

	public int getDimesion(){
		return cube.dim;
	}

	public Face getFace(int x, int y) {
		Vector3 toRight = new Vector3(this.toRight);
		Vector3 toDown = new Vector3(this.toDown);
		
		toRight.multiply(x);
		toDown.multiply(y);
		
		position.add(toRight);
		position.add(toDown);
		
		Face face = cube.getPiece(position).face(cameraDirection);


		position.subtract(toRight);
	
		position.subtract(toDown);


		return face;
	}
}
