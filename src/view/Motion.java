package view;

import component.Camera;
import component.Cube;
import component.Face;
import terminal.Board;
import terminal.KeyMapper;

public class Motion {
	private Cube cube;
	private Camera camera;
	private KeyMapper mapper;
	private Board board;
	private int dimension = 3;


	public void initialize() {
		cube = new Cube(dimension);
		camera = new Camera(cube);
		mapper = new KeyMapper();
		board = new Board();

		mapper.bind('a', (e)->{
			camera.rotateHorizontally(false);
		});

		mapper.bind('d', (e)->{
			camera.rotateHorizontally(true);

		});

		mapper.bind('w', (e)->{
			camera.rotateVertically(false);
		});


		mapper.bind('s', (e)->{
			camera.rotateVertically(true);
		});

		mapper.bind('q', (e)->{
			System.exit(0);
		});


		while(true){
			board.clear();
			Face[][] faces = camera.getPerspectiveFaces();
			for(int y=0; y < 16; y++)
				for(int x=0; x < 16; x++){
					int i = Math.min(x / ( 16/ cube.dim), cube.dim-1);
					int j = Math.min(y / ( 16/ cube.dim), cube.dim - 1);
					board.pixel(x, y, faces[i][j].getColor());
				}
			board.render();
			mapper.input(this);
		}
	}
}
