package util;
public class Face{

	public final Piece parent;
	final static short 
		EMPTY = -1,
		      YELLOW=3, WHITE=7, GREEN=2, BLUE=4, RED=1, ORANGE=5;
	final int directionX, directionY, directionZ;
	short color;


	boolean isBreathing(){
		int nX, nY, nZ;

		nX = parent.getPositionX() + directionX;
		nY = parent.getPositionY() + directionY;
		nZ = parent.getPositionZ() + directionZ;

		return parent.parent.isOut(nX, nY, nZ);
	}

	@Override
	public String toString() {
		return String.format("\033[4%s;1m  \033[m", color);
	}

	public Face(Piece parent, short color, int directionX, int directionY, int directionZ){
		this.parent = parent;
		this.color = color;
		this.directionX = directionX;
		this.directionY = directionY;
		this.directionZ = directionZ;
	}
}
