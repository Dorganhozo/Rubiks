package view;

import component.Camera;
import component.Cube;
import component.Face;
import component.Flat;
import terminal.Animator;
import terminal.Board;
import terminal.Color;
import terminal.KeyMapper;

public class Motion {
	private Cube cube;
	private Camera camera;
	private KeyMapper mapper;
	private Board board;
	private int dimension = 3;
	private int resolution = 16;


	public void initialize() {
		cube = new Cube(dimension);
		camera = new Camera(cube);
		//mapper = new KeyMapper();
		board = new Board();
		
		

		Flat facesA = camera.getPerspectiveFacesNew();
	
	

		for (int j = 0; j < cube.dim; j++) {
			for (int i = 0; i < cube.dim; i++) {
				if(i%2 == j%2)
					facesA.getFace(i, j).setColor(Color.GREEN);
				else
					facesA.getFace(i, j).setColor(Color.RED);
			}
		}

	

		Animator.drawPlaneRotation(camera.getPerspectiveFaces(), false, Math.toRadians(65), board);


		//mapper.bind('a', (e)->{
		//	camera.rotateHorizontally(false);
		//});

		//mapper.bind('d', (e)->{
		//	camera.rotateHorizontally(true);

		//});

		//mapper.bind('w', (e)->{
		//	camera.rotateVertically(false);
		//});


		//mapper.bind('s', (e)->{
		//	camera.rotateVertically(true);
		//});

		//mapper.bind('q', (e)->{
		//	System.exit(0);
		//});


		//while(true){
		//	board.clear();
		//	Face[][] faces = camera.getPerspectiveFaces();
		//	
		//	for(int y=0; y < 16; y++)
		//		for(int x=0; x < resolution; x++){
		//			int i = Math.min(x / ( resolution/ cube.dim), cube.dim-1);
		//			int j = Math.min(y / ( resolution/ cube.dim), cube.dim - 1);
		//			board.pixel(x, y, faces[i][j].getColor());
		//		}
		//	board.render();
		//	mapper.input(this);
		//}
	}
}
