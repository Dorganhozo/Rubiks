package util;
import java.util.Map;
import util.Piece;

//Um simples cubo com pecinhas
public class Cube{
	
	final int dim;
	Piece[][][] pieces;

	public Piece getPiece(int x, int y, int z) {
		//TODO: Use sempre esse metodo quando acessar o objeto Cube!
    		return pieces[x][y][z];
	}

	public boolean isOut(int x, int y, int z){
		return x < 0 || x >= dim || y < 0 || y >= dim || z < 0 || z >= dim;
	}

	public void swipePieces(int x1, int y1, int z1, int x2, int y2, int z2){
		getPiece(x1, y1, z1).toPosition(x2, y2, z2).toPosition(x1, y1, z1);
	}

	public String getSide(Group side) {

		StringBuilder strb = new StringBuilder();
		int count=0;

		int dirX, dirY, dirZ;

		dirX = side.x2 - side.x1 < 0? -1 : 1;
		dirY = side.y2 - side.y1 < 0? -1 : 1;
		dirZ = side.z2 - side.z1 < 0? -1 : 1;
		
		for(int y=side.y1 * (dim -1); y * dirY <= side.y2 * (dim - 1); y+=dirY)
			for(int z=side.z1 * (dim - 1); z * dirZ <= side.z2 * (dim - 1); z+=dirZ)
				for(int x=side.x1 * (dim - 1); x * dirX <= side.x2 * (dim - 1); x+=dirX){
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

	public enum Group{
		UP   (0, 0, 1, 1, 0, 0),
		DOWN (0, 1, 0, 1, 1, 1),
		LEFT (0, 0, 1, 0, 1, 0),
		RIGHT(1, 0, 0, 1, 1, 1),
		FRONT(0, 0, 0, 1, 1, 0), 
		BACK (1, 0, 1, 0, 1, 1);
		//MIDDLE 
		//EQUATOR 
		//STANDING
		
		
		final int x1, y1, z1, x2, y2, z2;

		private Group(int x1, int y1, int z1, int x2, int y2, int z2){
			this.x1 = x1;
			this.y1 = y1;
			this.z1 = z1;
			this.x2 = x2;
			this.y2 = y2;
			this.z2 = z2;
		}

	}

}
