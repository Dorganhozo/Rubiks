import moviment.Magic;
import util.Cube;
import util.Face;
import util.Piece;

public class Main{
	public static void main(String[] args) throws Exception{
		Cube cube = new Cube(3);

		for(int y=0; y < cube.dim; y++){
			cube.getPiece(0, y, 0).face("front").setColor(Face.BLUE);
			cube.getPiece(cube.dim-1, y, 0).face("front").setColor(Face.GREEN);
		}
			


		for(Piece piece : cube.getSide(Cube.Group.FRONT)){
			if(piece != null)
				System.out.print(piece.face(Cube.Group.FRONT.name().toLowerCase()));
			else
				System.out.print("  ");

			if(piece.getPositionX()==cube.dim-1)
				System.out.println();
		}



		Magic.rotate(cube, Cube.Group.FRONT, false);
		
		for(Piece piece : cube.getSide(Cube.Group.FRONT)){
			System.out.print(piece.face(Cube.Group.FRONT.name().toLowerCase()));;

			if(piece.getPositionX()==cube.dim-1)
				System.out.println();
		}
	}
}
