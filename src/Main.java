import component.Camera.Direction;
import component.Camera;
import component.Cube;
import component.Face;
import view.App;

public class Main{
	public static void main(String[] args) throws Exception{
		Cube cube = new Cube();




		for (int i = 0; i < cube.dim; i++) {
			int fin = cube.dim-1;
			cube.getPiece(i, fin, 0).face(Direction.FRONT).setColor(Face.EMPTY);
			cube.getPiece(i, fin, fin).face(Direction.BACK).setColor(Face.EMPTY);
			cube.getPiece(0, fin, i).face(Direction.LEFT).setColor(Face.EMPTY);
			cube.getPiece(fin, fin, i).face(Direction.RIGHT).setColor(Face.EMPTY);
			cube.getPiece(i, 0, 0).face(Direction.UP).setColor(Face.EMPTY);
			cube.getPiece(i, fin, fin).face(Direction.DOWN).setColor(Face.EMPTY);
		}


		Camera camera = new Camera(cube);
		camera.print();
		camera	.rotateVertically(false);
		camera.print();
		camera.rotateHorizontally(true);
		camera.print();
		camera	.rotateVertically(false);
		camera.print();
		camera	.rotateVertically(false);
		camera.print();
		//new App().initialize();	



	}
}
