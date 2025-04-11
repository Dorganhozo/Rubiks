package util;
import java.util.Spliterator;
import java.util.Iterator;


//Um simples cubo com pecinhas
public class Cube{
	
	final int dim;
	Piece[][][] pieces;

	public Piece getPiece(int x, int y, int z) {
		return pieces[x][y][z];
	}

	public boolean isOut(int x, int y, int z){
		return x < 0 || x >= dim || y < 0 || y >= dim || z < 0 || z >= dim;
	}

	public void swipePieces(int x1, int y1, int z1, int x2, int y2, int z2){
		getPiece(x1, y1, z1).toPosition(x2, y2, z2).toPosition(x1, y1, z1);
	}

	//Lutar pela minha sobrevivencia!!!
	public Iterable<Piece> getSide(Group side) {
		final Iterator<Piece> itr = new Iterator<Piece>() {
		
			int count;

			int begin(int x, int start, int end){
				return end - start < 0?(dim - 1)-x:x;
			}

			int x(){ return begin(count%dim, side.x1, side.x2); }
			int y(){ return begin((z()/dim)%dim, side.y1, side.y2); }
			int z(){ return begin((count/dim)%dim, side.z1, side.z2); }

			@Override
			public Piece next() {
				Piece piece = getPiece(x(), y(), z());

				count++;
				
				return piece;
			}
			@Override
			public boolean hasNext() {
				 return count < dim*dim;
			}
		};


		return new Iterable<Piece>() {
			@Override
			public Iterator<Piece> iterator() {
			    return itr;
			}	

			@Override
			public Spliterator<Piece> spliterator() {
			    return null;
			}
		};
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


					for(Face face : piece.faces())
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
