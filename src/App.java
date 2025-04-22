import moviment.Magic;
import util.Cube;
import util.Face;
import util.Flat;
import util.Piece;

public class App {
	public void initialize(){
		Cube cube = new Cube(3);

		for(int y=0; y < cube.dim; y++){
			cube.getPiece(0, y, 0).face("front").setColor(Face.BLUE);
			cube.getPiece(cube.dim-1, y, 0).face("front").setColor(Face.GREEN);
		}
			


		for(Piece piece : cube.getSide(Cube.Group.FRONT)){
			if(piece != null)
				System.out.print(piece.face(Cube.Group.FRONT.name()));
			else
				System.out.print("  ");

			if(piece.getPositionX()==cube.dim-1)
				System.out.println();
		}



		Magic.rotate(new Flat(Cube.Group.FRONT, cube), true);
		
		for(Piece piece : cube.getSide(Cube.Group.FRONT)){
			if(piece != null)
				System.out.print(piece.face(Cube.Group.FRONT.name()));
			else
				System.out.print("  ");

			if(piece.getPositionX()==cube.dim-1)
				System.out.println();
		}
	}

}
