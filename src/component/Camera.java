package component;

import java.util.function.BiConsumer;
import math.Vector3;
import math.Vector3.Value;

public class Camera {
	private Cube cube;
	private Vector3[] selection;

	@Deprecated
	private Vector3 direction;

	private BiConsumer<Vector3, Boolean> horizontalRotation = this::rotateX, verticalRotation = this::rotateY, inactiveRotation = this::rotateZ;



	private int depth;

	public Face[][] getPerspectiveFaces(){
		Face[][] faces = new Face[cube.dim][cube.dim];


		Vector3 toRight = selection[0].getDirection(selection[1]);
		Vector3 toDown = selection[0].getDirection(selection[2]);
		Vector3 position = new Vector3(selection[0]);

		boolean[] axisEquivalences = {
			selection[0].getX() == selection[3].getX(),
			selection[0].getY() == selection[3].getY(), 
			selection[0].getZ() == selection[3].getZ()
		};

		Value[] axes = {position.x(), position.y(), position.z()};	

		for(int i=0; i < axes.length; i++)
			if(axisEquivalences[i])
				axes[i].set(Math.abs(axes[i].get() - depth));


		for(int j=0; j < cube.dim; j++){
			for(int i=0; i < cube.dim; i++){
				if(position.getX() < 0 || position.getX() >= cube.dim)
					position.setX(selection[0].getX());

				if(position.getY() < 0 || position.getY() >= cube.dim)
					position.setY(selection[0].getY());

				if(position.getZ() < 0 || position.getZ() >= cube.dim)
					position.setZ(selection[0].getZ());

				faces[j][i] = cube.getPiece(position.getX(), position.getY(), position.getZ()).face(direction);
				position.add(toRight);	

			}
			position.add(toDown);
		}
		

		return faces;
	}



	
	public void rotateHorizontally(boolean counterClockWise){
		horizontalRotation.accept(direction, counterClockWise);

		for(Vector3 vector : selection)
			horizontalRotation.accept(vector, counterClockWise);

		BiConsumer<Vector3, Boolean> temp = verticalRotation;
		verticalRotation = inactiveRotation;
		inactiveRotation = temp;

	}

	public void rotateVertically(boolean counterClockWise){
		verticalRotation.accept(direction, counterClockWise);

		for(Vector3 vector : selection)
			verticalRotation.accept(vector, counterClockWise);

		BiConsumer<Vector3, Boolean> temp = horizontalRotation;
		horizontalRotation = inactiveRotation;
		inactiveRotation = temp;

	}


	public void rotateX(Vector3 vector, boolean counterClockWise){
		int dim = vector == direction? 1 : cube.dim;
	
		rotate(vector.x(), vector.z(), dim, counterClockWise);	
	
	}

	public void rotateY(Vector3 vector, boolean counterClockWise){
		int dim = vector == direction? 1 : cube.dim;

		rotate(vector.y(), vector.z(), dim, counterClockWise);

	}

	public void rotateZ(Vector3 vector, boolean counterClockWise){
		int dim = vector == direction? 1 : cube.dim;

		rotate(vector.x(), vector.y(), dim, counterClockWise);

	}


	private void rotate(Value x, Value y, int dim, boolean counterClockWise){
		int newX = x.get(); 
		int newY = y.get();

		if(counterClockWise)
			newY = dim - (newY + 1);
		else
			newX = dim - (newX + 1);

		x.set(newY);		
		y.set(newX); 

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
		this.direction = Direction.FRONT.vect();
		
		this.selection = new Vector3[]{
			Vector3.of(0,		0, 		0),
			Vector3.of(cube.dim-1, 	0, 		0),
			Vector3.of(0, 		cube.dim-1,	0),
			Vector3.of(cube.dim-1, 	cube.dim-1, 	0)
		};

	}

}
