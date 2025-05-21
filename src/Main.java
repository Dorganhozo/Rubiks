import component.Camera;
import component.Cube;
import component.Face;
import view.App;

public class Main{
	public static void main(String[] args) throws Exception{
		Cube cube = new Cube();

		cube.getPiece(0, 2, 0).face("front").setColor(Face.EMPTY);
		cube.getPiece(1, 2, 0).face("front").setColor(Face.EMPTY);
		cube.getPiece(2, 2, 0).face("front").setColor(Face.EMPTY);

		cube.getPiece(0, 2, 2).face("back").setColor(Face.EMPTY);
		cube.getPiece(1, 2, 2).face("back").setColor(Face.EMPTY);
		cube.getPiece(2, 2, 2).face("back").setColor(Face.EMPTY);
		
		cube.getPiece(0, 2, 0).face("left").setColor(Face.EMPTY);
		cube.getPiece(0, 2, 1).face("left").setColor(Face.EMPTY);
		cube.getPiece(0, 2, 2).face("left").setColor(Face.EMPTY);

		cube.getPiece(2, 2, 0).face("right").setColor(Face.EMPTY);
		cube.getPiece(2, 2, 1).face("right").setColor(Face.EMPTY);
		cube.getPiece(2, 2, 2).face("right").setColor(Face.EMPTY);


		cube.getPiece(0, 0, 0).face("up").setColor(Face.EMPTY);
		cube.getPiece(1, 0, 0).face("up").setColor(Face.EMPTY);
		cube.getPiece(2, 0, 0).face("up").setColor(Face.EMPTY);


		cube.getPiece(0, 2, 2).face("down").setColor(Face.EMPTY);
		cube.getPiece(1, 2, 2).face("down").setColor(Face.EMPTY);
		cube.getPiece(2, 2, 2).face("down").setColor(Face.EMPTY);

		Camera camera = new Camera(cube);
		camera.rotateX(false);

		camera.print("down");


		//new App().initialize();	



	}
}
