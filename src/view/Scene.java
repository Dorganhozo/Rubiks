package view;

import component.Camera;
import component.Cube;

public class Scene {
	private Screen current;
	private Cube mCube;
	private Camera mCamera;


	public void initialize(){
		mCube = new Cube();	
		mCamera = new Camera(mCube);
		
		while(true){
		
		}
	}

	

	public void setScreen(Screen screen){
		this.current = screen;
	}

	public Cube getCube(){
		return mCube;
	}

	public void setCube(Cube cube){
		this.mCube = cube;
		this.mCamera = new Camera(cube);
	}

	public interface Screen{
		public void start(Scene context);
	}

}
