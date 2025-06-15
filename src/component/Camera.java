package component;

import java.util.function.BiConsumer;
import math.Vector3;
import math.Vector3.Value;

public class Camera {
	private Cube cube;
	private Vector3[] selection;

	private BiConsumer<Vector3, Boolean> horizontalRotation = this::rotateX, verticalRotation = this::rotateY, inactiveRotation = this::rotateZ;



	private int depth;

	public Face[][] getPerspectiveFaces(){
		Face[][] faces = new Face[cube.dim][cube.dim];

		Vector3 direction = getDirection();


		if(cube.hasOnlyPiece()){
			faces[0][0] = cube.getPiece(0, 0, 0).face(direction);
			return faces;
		}


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


	public Vector3 getDirection(){
		Vector3 direction = new Vector3();

		Vector3 begin = positionToSigns(selection[0]);
		Vector3 end = positionToSigns(selection[3]);

		direction.setX((end.getX() + begin.getX()) / 2);
		direction.setY((end.getY() + begin.getY()) / 2);
		direction.setZ((end.getZ() + begin.getZ()) / 2);
	

		return direction;
	}

	private Vector3 positionToSigns(Vector3 position){
		int x, y, z;

		final int max = cube.dim - 1;
		x = position.getX()/(!cube.hasOnlyPiece() ? max : 1) * 2 - 1;
		y = position.getY()/(!cube.hasOnlyPiece() ? max : 1) * 2 - 1;
		z = position.getZ()/(!cube.hasOnlyPiece() ? max : 1) * 2 - 1;
		
		return Vector3.of(x, y, z);
	}


	
	public void rotateHorizontally(boolean counterClockWise){
		if(isRotationReversed(selection[0], selection[2], horizontalRotation))
			counterClockWise = !counterClockWise;

		for(Vector3 vector : selection)
			horizontalRotation.accept(vector, counterClockWise);

		BiConsumer<Vector3, Boolean> temp = verticalRotation;
		verticalRotation = inactiveRotation;
		inactiveRotation = temp;

	}




	public void rotateVertically(boolean counterClockWise){

		if(isRotationReversed(selection[0], selection[1], verticalRotation))
			counterClockWise = !counterClockWise;


		for(Vector3 vector : selection)
			verticalRotation.accept(vector, counterClockWise);

		BiConsumer<Vector3, Boolean> temp = horizontalRotation;
		horizontalRotation = inactiveRotation;
		inactiveRotation = temp;

	}

	private boolean isRotationReversed(Vector3 corner1, Vector3 corner2, BiConsumer<Vector3, Boolean> rotation){
		corner1 = positionToSigns(corner1);
		corner2 = positionToSigns(corner2);

		Vector3 rightDirection = Vector3.of(
			( corner1.getX() + corner2.getX() ) /2,
			( corner1.getY() + corner2.getY() ) /2,
			( corner1.getZ() + corner2.getZ() ) /2
			
		);

		rightDirection.subtract(getDirection());


		Vector3 leftTop = selection[0], rightBelow = selection[3];
		rotation.accept(leftTop, false);
		rotation.accept(rightBelow, false);

		Vector3 futureDirection = getDirection();

		rotation.accept(leftTop, true);
		rotation.accept(rightBelow, true);


		return !futureDirection.equals(rightDirection);	

	}

	
	private void rotateX(Vector3 vector, boolean counterClockWise){
		rotate(vector.x(), vector.z(), counterClockWise);	
	
	}

	private void rotateY(Vector3 vector, boolean counterClockWise){
		rotate(vector.y(), vector.z(), counterClockWise);
	}

	private void rotateZ(Vector3 vector, boolean counterClockWise){
		rotate(vector.x(), vector.y(), counterClockWise);
	}


	private void rotate(Value x, Value y, boolean counterClockWise){
		int newX = x.get(); 
		int newY = y.get();

		final int dim = !cube.hasOnlyPiece()? cube.dim : 2;

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

		final int max = !cube.hasOnlyPiece()? cube.dim - 1 : 1;
		
		this.selection = new Vector3[]{
			Vector3.of(0,		0, 	0),
			Vector3.of(max, 	0, 	0),
			Vector3.of(0, 		max,	0),
			Vector3.of(max, 	max, 	0)
		};

	}

}
