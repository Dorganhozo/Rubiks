//import component.Camera;
//import component.Cube;
//import component.Piece;
//import component.Face;
//import component.Camera.Direction;
//import static java.lang.Math.*;
import view.Prompt;



public class Main{
	public static void main(String[] args) throws Exception{
		//final int dim = 3;
		//Cube cube = new Cube(dim);
	
		//cube.getPiece(0, 0, 0).face(Direction.FRONT).setColor(Face.EMPTY);
		//cube.getPiece(2, 0, 0).face(Direction.FRONT).setColor(Face.EMPTY);
		//cube.getPiece(1, 1, 0).face(Direction.FRONT).setColor(Face.EMPTY);
		//cube.getPiece(3, 1, 0).face(Direction.FRONT).setColor(Face.ORANGE);
		//cube.getPiece(0, 2, 0).face(Direction.FRONT).setColor(Face.EMPTY);
		//cube.getPiece(2, 2, 0).face(Direction.FRONT).setColor(Face.EMPTY);
		//cube.getPiece(1, 3, 0).face(Direction.FRONT).setColor(Face.ORANGE);
		//cube.getPiece(3, 3, 0).face(Direction.FRONT).setColor(Face.ORANGE);	
		//int fin = cube.dim-1;
		//for (int i = 0; i < cube.dim; i++) {
		//	cube.getPiece(i, fin, 0).face(Direction.FRONT).setColor(Face.EMPTY);
		//	cube.getPiece(i, fin, fin).face(Direction.BACK).setColor(Face.EMPTY);
		//	cube.getPiece(0, fin, i).face(Direction.LEFT).setColor(Face.EMPTY);
		//	cube.getPiece(fin, fin, i).face(Direction.RIGHT).setColor(Face.EMPTY);
		//	cube.getPiece(i, 0, 0).face(Direction.UP).setColor(Face.EMPTY);
		//	cube.getPiece(i, fin, fin).face(Direction.DOWN).setColor(Face.EMPTY);
		//}


		//Camera c = new Camera(cube);
		//c.setDepth(0);

		//c.rotateVertically(false);
		//c.rotateHorizontally(false);

		//Face[][] faces = c.getPerspectiveFaces();
	

		//for(int y=0; y < 16; y++){
		//	for (int x = 0; x < 16; x++) {
		//		System.out.print(faces[min(y/(16/dim), dim-1)][min(x/(16/dim), dim-1)]);	
		//	}
		//	System.out.println();
		//}
		//for(Face[] lines : faces){
		//	for(Face face : lines)
		//		System.out.print(face);
		//	System.out.println();

		//}

		//Camera camera = new Camera(cube);
		//camera.print();




		new Prompt().initialize();	



	}
}
