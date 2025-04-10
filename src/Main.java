import util.Cube;

public class Main{
	public static void main(String[] args) throws Exception{
		Cube cube = new Cube(3);
		
		cube.swipePieces(1, 1, 0, 1, 1, 2);
		System.out.println(cube.getSide(Cube.Group.FRONT));
	}
}
