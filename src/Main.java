import component.Camera;
import component.Cube;
import view.App;

public class Main{
	public static void main(String[] args) throws Exception{



		Cube cube = new Cube(16);
		Camera c = new Camera(cube);
		c.rotateVertically(true);
		c.print();


		//Cube cube = new Cube(3);
		//
		//int fin = cube.dim-1;
		//for (int i = 0; i < cube.dim; i++) {
		//	cube.getPiece(i, fin, 0).face(Direction.FRONT).setColor(Face.EMPTY);
		//	cube.getPiece(i, fin, fin).face(Direction.BACK).setColor(Face.EMPTY);
		//	cube.getPiece(0, fin, i).face(Direction.LEFT).setColor(Face.EMPTY);
		//	cube.getPiece(fin, fin, i).face(Direction.RIGHT).setColor(Face.EMPTY);
		//	cube.getPiece(i, 0, 0).face(Direction.UP).setColor(Face.EMPTY);
		//	cube.getPiece(i, fin, fin).face(Direction.DOWN).setColor(Face.EMPTY);
		//}


		//Camera camera = new Camera(cube);
		//camera.print();
		//new App().initialize();	



	}
}
