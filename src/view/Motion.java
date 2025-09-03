package view;

import java.util.Arrays;

import component.Camera;
import component.Cube;
import component.Flat;
import terminal.Animator;
import terminal.Board;
import terminal.Color;
import terminal.KeyMapper;
import terminal.Unix;
import moviment.Magic;

public class Motion {
	private Cube cube;
	private Camera camera;
	private KeyMapper mapper;
	private Board board;
	private int dimension = 3;
	private int proportion = 16;
	private int resolution = 32;

	public void initialize() {
		cube = new Cube(dimension);
		camera = new Camera(cube);
		mapper = new KeyMapper();
		board = new Board(0, 0, resolution, resolution);

		updateBoardMetrics();

		mapper.bind('a', (e)->{
			Flat past = camera.getPerspectiveFaces();
			camera.rotateHorizontally(false);
			Flat current = camera.getPerspectiveFaces();

			Animator.startHorizontallyRotation(current, past, false, this);
		});

		mapper.bind('d', (e)->{
			Flat past = camera.getPerspectiveFaces();
			camera.rotateHorizontally(true);

			Flat current = camera.getPerspectiveFaces();

			Animator.startHorizontallyRotation(past, current, true, this);

		});

		mapper.bind('w', (e)->{
			Flat past = camera.getPerspectiveFaces();
			camera.rotateVertically(false);
			Flat current = camera.getPerspectiveFaces();

			Animator.startVerticallyRotation(current, past, false, this);
		});



		mapper.bind('s', (e)->{
			Flat past = camera.getPerspectiveFaces();
			camera.rotateVertically(true);
			Flat current = camera.getPerspectiveFaces();

			Animator.startVerticallyRotation(past, current, true, this);
		});


		mapper.bind('r', (e)->{
			Animator.startPlaneRotation(camera.getPerspectiveFaces(), false, this);
			Magic.rotate(cube, camera, false);
		});

		mapper.bind('R', (e)->{
			Animator.startPlaneRotation(camera.getPerspectiveFaces(), true, this);
			Magic.rotate(cube, camera, true);
		});

		mapper.bind('q', (e)->{
			board.unhideCursor();
			System.exit(0);
		});


		board.hideCursor();
		while(true){


			updateBoardMetrics();
			Flat currentFaces = camera.getPerspectiveFaces();
			
			board.clear();

			
			for(int y=0; y < proportion; y++)
				for(int x=0; x < proportion; x++){
					int i = Math.min(x / ( proportion/ cube.dim), cube.dim - 1);
					int j = Math.min(y / ( proportion/ cube.dim), cube.dim - 1);
					board.pixel(x + resolution/2 - proportion/2, y + resolution/2 - proportion/2, currentFaces.getFace(i, j).getColor());
				}

			System.out.printf("\033[%s;%sH%s",  board.getHeight()+board.getOffsetY() + 1, board.getOffsetX(), "ACT: wasd, (r)otate, +shift=reverse");


			board.render();
			mapper.input(this);
		}
	}

	private void updateBoardMetrics(){
		int[] size = Unix.getTerminalSize();
		resolution = (int)Math.sqrt(2 * proportion*proportion);

		board.resize(size[0]/2 - resolution/2, size[1]/2 - resolution/2, resolution, resolution);

	
	}

	private void drawChess(){
		for (int j = 0; j < board.getHeight(); j++) {
			for (int i = 0; i < board.getWidth(); i++) {
				if(j%2 == i%2)
					board.pixel(i, j, Color.ORANGE);
			}
		}
		board.render();


	}

	public Board getBoard() {
		return board;
	}

	public int getProportion() {
		return proportion;
	}
	public int getResolution() {
		return resolution;
	}
}
