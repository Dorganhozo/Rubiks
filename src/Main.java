import util.Cube;
import util.Piece;

public class Main{
	public static void main(String[] args) throws Exception{
		Cube cube = new Cube(3);
		
		cube.swipePieces(1, 1, 0, 1, 1, 2);
		
		for(Piece piece : cube.getSide(Cube.Group.FRONT)){
			System.out.print(piece.face(Cube.Group.FRONT.name().toLowerCase()));;

			if(piece.getPositionX()==2)
				System.out.println();
		}
	}
}
