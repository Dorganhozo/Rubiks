package component;

import component.Camera.Direction;
import math.Vector3;

public class Face{
	private Vector3 diretion;
	private Piece parent;
	public static final short EMPTY=0, YELLOW=220, WHITE=15, GREEN=40, BLUE=20, RED=160, ORANGE=208;

	
	private short color;

	public short getColor() {
		return color;
	}

	public void setColor(short color) {
		this.color = color;
	}

	public void setColor(int color){
		this.color = (short)color;
	}

	public boolean isBreathing(){
		int nX, nY, nZ;

		nX = getPiece().getPositionX() + getDiretion().getX();
		nY = getPiece().getPositionY() + getDiretion().getY();
		nZ = getPiece().getPositionZ() + getDiretion().getZ();

		return getPiece().parent.isOut(nX, nY, nZ);
	}

	public Piece getPiece(){
		return parent;	
	}

	public Vector3 getDiretion(){
		return diretion;
	}

	


	@Override
	public String toString() {
		return String.format("\033[48;5;%sm  \033[m", color);
	}

	public Face(Piece piece, Direction direction, short color){
		this.diretion = Vector3.of(direction.x, direction.y, direction.z);
		this.parent = piece;
		this.color = color;
	}




}
