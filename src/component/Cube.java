package component;

import math.Vector3;

//Um simples cubo com pecinhas
public class Cube{
	
	public final int dim;
	private Piece[][][] pieces;

	public Piece getPiece(int x, int y, int z) {
		return pieces[z][y][x];
	}
	public Piece getPiece(Vector3<Integer> position) {
		return getPiece(position.getX(), position.getY(), position.getZ());
	}
	
	public void setPiece(int x, int y, int z, Piece piece) {
		pieces[z][y][x] = piece;
	}


	public void setPiece(Vector3<Integer> position, Piece piece) {
		setPiece(position.getX(), position.getY(), position.getZ(), piece);
	}

	public boolean isOut(int x, int y, int z){
		return x < 0 || x >= dim || y < 0 || y >= dim || z < 0 || z >= dim;
	}

	public void swipePieces(int x1, int y1, int z1, int x2, int y2, int z2){
		getPiece(x1, y1, z1).toPosition(x2, y2, z2).toPosition(x1, y1, z1);
	}

	
	public Cube(){
		dim = 3;
		pieces = new Piece[dim][dim][dim];
		for(int z=0; z<dim; z++)
			for(int y=0; y<dim; y++)
				for(int x=0; x<dim; x++)
					pieces[z][y][x] = new Piece(this, x, y, z);
	 

		for(int z=0; z<dim; z++)
			for(int y=0; y<dim; y++)
				for(int x=0; x<dim; x++){
					Piece piece = pieces[z][y][x];


					for(Face face : piece.faces())
						if(!face.isBreathing())
							face.setColor(Face.EMPTY);
				}
	}

	
}
