package component;



import component.Cube.Group;

//Matriz 3D para a Matriz 2D
public class Flat {

	private final int x1, y1, z1, x2, y2, z2;
	private final Cube cube;
	private int firstAxis, secondAxis;
	private final boolean reverseX, reverseY;

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
		position[firstAxis] = reverseX? cube.dim -1 -x : x;
		position[secondAxis] = reverseY? cube.dim -1 -y : y;
		return position;
	}
	public int getDimension() {
		return cube.dim;
	}

	@Override
	public String toString() {
		return String.format("(%s, %s, %s), (%s, %s, %s)", x1, y1, z1, x2, y2, z2);
	}

	public Flat(Group side, Cube cube){
		int[] positionA = {
			(int)(side.x1 * (cube.dim - 1)), (int)(side.y1 * (cube.dim - 1)), (int)(side.z1 * (cube.dim - 1))	
		};
		int[] positionB = {
			(int)(side.x2 * (cube.dim - 1)), (int)(side.y2 * (cube.dim - 1)), (int)(side.z2 * (cube.dim - 1))	
		};

		this.x1 = Math.min(positionA[0], positionB[0]);
		this.y1 = Math.min(positionA[1], positionB[1]);
		this.z1 = Math.min(positionA[2], positionB[2]);


		this.x2 = Math.max(positionA[0], positionB[0]);
		this.y2 = Math.max(positionA[1], positionB[1]);
		this.z2 = Math.max(positionA[2], positionB[2]);

		this.cube = cube;

		setAxes(x1, y1, z1, x2, y2, z2);

		reverseX = positionA[firstAxis] < positionB[firstAxis];
		reverseY = positionA[secondAxis] < positionB[secondAxis];
		
	}

	public Flat(int x1, int y1, int z1, int x2, int y2, int z2, boolean reverseX, boolean reverseY, Cube cube){
		this.x1 = x1;
		this.y1 = y1;
		this.z1 = z1;

		this.x2 = x2;
		this.y2 = y2;
		this.z2 = z2;
		this.cube = cube;
		

		this.reverseX = reverseX;
		this.reverseY =  reverseY;

		
		setAxes(x1, y1, z1, x2, y2, z2);
		

	}

	private void setAxes(int x1, int y1, int z1, int x2, int y2, int z2) {
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

}
