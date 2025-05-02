package component;


//Um simples cubo com pecinhas
public class Cube{
	
	public final int dim;
	private Piece[][][] pieces;

	public Piece getPiece(int x, int y, int z) {
		return pieces[x][y][z];
	}

	public void setPiece(int x, int y, int z, Piece piece) {
		pieces[x][y][z] = piece;
	}

	public boolean isOut(int x, int y, int z){
		return x < 0 || x >= dim || y < 0 || y >= dim || z < 0 || z >= dim;
	}

	public void swipePieces(int x1, int y1, int z1, int x2, int y2, int z2){
		getPiece(x1, y1, z1).toPosition(x2, y2, z2).toPosition(x1, y1, z1);
	}

	public Flat getSide(Group side) {
		return new Flat(side, this);

	}

	public Cube(){
		dim = 3;
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
							face.setColor(Face.EMPTY);
				}
	}

	public enum Group{
		//UP      (0, 0, 0, 1, 0, 1),
		//DOWN    (0, 1, 0, 1, 1, 1),
		//LEFT    (0, 0, 0, 0, 1, 1),
		//RIGHT   (1, 0, 0, 1, 1, 1),
		//FRONT   (0, 0, 0, 1, 1, 0), 
		//BACK    (0, 0, 1, 1, 1, 1);
		//MIDDLE  (.5f, 0, 0, .5f, 1, 1),
		//EQUATOR (0, .5f, 0, 1, .5f, 1),
		//STANDING(0, 0, .5f, 1, 1, .5f);

		UP      (0, 0, 1, 1, 0, 0),
		DOWN    (0, 1, 0, 1, 1, 1),
		LEFT    (0, 0, 1, 0, 1, 0),
		RIGHT   (1, 0, 0, 1, 1, 1),
		FRONT   (0, 0, 0, 1, 1, 0), 
		BACK    (1, 0, 1, 0, 1, 1);
		//MIDDLE  (.5f, 0, 1, .5f, 1, 0),
		//EQUATOR (0, .5f, 0, 1, .5f, 1),
		//STANDING(1, 0, .5f, 0, 1, .5f);


		public final float x1, y1, z1, x2, y2, z2;

		private Group(float x1, float y1, float z1, float x2, float y2, float z2){
			this.x1 = x1;
			this.y1 = y1;
			this.z1 = z1;
			this.x2 = x2;
			this.y2 = y2;
			this.z2 = z2;
		}

	}

}
