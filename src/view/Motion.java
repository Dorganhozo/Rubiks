package view;

import java.util.Arrays;

import component.Camera;
import component.Cube;
import component.Flat;
import terminal.Animator;
import terminal.Board;
import terminal.KeyMapper;
import moviment.Magic;

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
		mapper = new KeyMapper();
		board = new Board(resolution, resolution);



		mapper.bind('a', (e)->{
			Flat past = camera.getPerspectiveFaces();
			camera.rotateHorizontally(false);
			Flat current = camera.getPerspectiveFaces();

			Animator.startHorizontallyRotation(current, past, false, board);
		});

		mapper.bind('d', (e)->{
			Flat past = camera.getPerspectiveFaces();
			camera.rotateHorizontally(true);

			Flat current = camera.getPerspectiveFaces();

			Animator.startHorizontallyRotation(past, current, true, board);

		});

		mapper.bind('w', (e)->{
			Flat past = camera.getPerspectiveFaces();
			camera.rotateVertically(false);
			Flat current = camera.getPerspectiveFaces();

			Animator.startVerticallyRotation(current, past, false, board);
		});



		mapper.bind('s', (e)->{
			Flat past = camera.getPerspectiveFaces();
			camera.rotateVertically(true);
			Flat current = camera.getPerspectiveFaces();

			Animator.startVerticallyRotation(past, current, true, board);
		});


		mapper.bind('r', (e)->{
			Animator.startPlaneRotation(camera.getPerspectiveFaces(), false, board);
			Magic.rotate(cube, camera, false);
		});

		mapper.bind('R', (e)->{
			Animator.startPlaneRotation(camera.getPerspectiveFaces(), true, board);
			Magic.rotate(cube, camera, true);
		});

		mapper.bind('q', (e)->{
			board.unhideCursor();
			System.exit(0);
		});


		board.hideCursor();
		while(true){
			Flat currentFaces = camera.getPerspectiveFaces();
			
			board.clear();

			for(int y=0; y < resolution; y++)
				for(int x=0; x < resolution; x++){
					int i = Math.min(x / ( resolution/ cube.dim), cube.dim-1);
					int j = Math.min(y / ( resolution/ cube.dim), cube.dim - 1);
					board.pixel(x, y, currentFaces.getFace(i, j).getColor());
				}
			board.render();
			mapper.input(this);
		}
	}
}
