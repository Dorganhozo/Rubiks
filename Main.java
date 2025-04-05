import java.util.HashMap;
import java.util.Map;

public class Main{
	public static void main(String[] args) throws Exception{
		Cube cube = new Cube(3);
		System.out.println(cube.getSide(Cube.Side.FRONT));
	}
}

//Reponsavel pelas regras do cubo magico
class Magic{}

//Solucionador e Sorteiador de Cubo magico
class Auto{}

//Um simples cubo com pecinhas
class Cube{
	final int dim;
	Piece[][][] pieces;

	public Piece getPiece(int x, int y, int z) {
		//TODO: Use sempre esse metodo quando acessar o objeto Cube!
    		return pieces[x][y][z];
	}


	public void swipePieces(int x1, int y1, int z1, int x2, int y2, int z2){
		getPiece(x1, y1, z1).toPosition(x2, y2, z2).toPosition(x1, y1, z1);
	}

	public String getSide(Side side) {
		StringBuilder strb = new StringBuilder();
		
		int count=0;

		int dirX, dirY, dirZ;

		dirX = side.x2 - side.x1 < 0? -1 : 1;
		dirY = side.y2 - side.y1 < 0? -1 : 1;
		dirZ = side.z2 - side.z1 < 0? -1 : 1;
		
		for(int y=side.y1 * dim; y * dirY <= side.y2 * (dim - 1); y+=dirY)
			for(int z=side.z1 * dim; z * dirZ <= side.z2 * (dim - 1); z+=dirZ)
				for(int x=side.x1 * dim; x * dirX <= side.x2 * (dim - 1); x+=dirX){
					strb.append(pieces[x][y][z].faces.get(side.name().toLowerCase()));
					if(++count % dim == 0 && count != dim*dim)
						strb.append("\n");

				}

		return strb.toString();
	}

	public Cube(int dimension){
		dim = dimension;
		pieces = new Piece[dim][dim][dim];
		for(int z=0; z<dim; z++)
			for(int y=0; y<dim; y++)
				for(int x=0; x<dim; x++)
					pieces[x][y][z] = new Piece(this, x, y, z);

		for(int z=0; z<dim; z++)
			for(int y=0; y<dim; y++)
				for(int x=0; x<dim; x++){
					Piece piece = pieces[x][y][z];


					for(Face face : piece.faces.values())
						if(!face.isBreathing())
							face.color = Face.EMPTY;
				}
	}

	enum Side{
		UP   (0, 0, 1, 1, 0, 0),
		DOWN (0, 1, 0, 1, 1, 1),
		LEFT (0, 0, 1, 0, 1, 0),
		RIGHT(1, 0, 0, 1, 1, 1),
		FRONT(0, 0, 0, 1, 1, 0), 
		BACK (1, 0, 1, 0, 1, 1);




		final int x1, y1, z1, x2, y2, z2;
		private Side(int x1, int y1, int z1, int x2, int y2, int z2){
			this.x1 = x1;
			this.y1 = y1;
			this.z1 = z1;
			this.x2 = x2;
			this.y2 = y2;
			this.z2 = z2;
		}

	}

}

class Piece{
	Cube parent;
	Map<String, Face> faces = new HashMap<>();
	int positionX, positionY, positionZ;

	Piece toPosition(int x, int y, int z){

		Piece old  = null;

		= parent.pieces[x][y][z];

		if(old.getType() != getType())
			throw new IllegalArgumentException("As peças não se equivalem em tipo.");

		parent.pieces[x][y][z] = this;
		positionX = x;
		positionY = y;
		positionZ = z;

		verifyFaces();

		return old;
	}

	void verifyFaces(){
		Face blocked = null;


		for(Face face : faces.values())
			if(face.color != Face.EMPTY && !face.isBreathing()){
				blocked = face;
				break;
			}

		if(blocked == null)
			return;


		for(Face face : faces.values())
			if(face.color == Face.EMPTY && face.isBreathing()){
				face.color = blocked.color;
				blocked.color = Face.EMPTY;
				break;
			}
	}

	int getType(){
		int type = 0;

		for(Face face : faces.values())
			if(face.color != Face.EMPTY)
				type++;

		return type;
	}

	public Piece(Cube parent, int x, int y, int z){
		this.parent = parent;
		positionX = x;
		positionY = y;
		positionZ = z;

		faces.put("up", new Face(this, Face.YELLOW, 0, -1, 0));
		faces.put("down", new Face(this, Face.WHITE, 0, 1, 0));
		faces.put("left", new Face(this, Face.GREEN, -1, 0, 0));
		faces.put("right", new Face(this, Face.BLUE, 1, 0, 0));
		faces.put("front", new Face(this, Face.RED, 0, 0, -1));
		faces.put("back", new Face(this, Face.ORANGE, 0, 0, 1));

	}
}

class Face{

	Piece parent;
	final static short 
		EMPTY = -1,
		      YELLOW=0, WHITE=1, GREEN=2, BLUE=3, RED=4, ORANGE=5;
	final int directionX, directionY, directionZ;
	short color;


	boolean isBreathing(){
		int nX, nY, nZ;

		nX = parent.positionX + directionX;
		nY = parent.positionY + directionY;
		nZ = parent.positionZ + directionZ;

		try{
			Piece piece = parent.parent.pieces[nX][nY][nZ];
			return false;
		}catch(Exception e){
			return true;
		}
	}

	@Override
	public String toString() {
		return String.valueOf(color);
	}

	public Face(Piece parent, short color, int directionX, int directionY, int directionZ){
		this.parent = parent;
		this.color = color;
		this.directionX = directionX;
		this.directionY = directionY;
		this.directionZ = directionZ;
	}
}
