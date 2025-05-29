package component;

import java.util.Map;
import java.util.Map.Entry;

import component.Camera.Direction;
import math.Vector3;

public class Face{
	private Vector3<Integer> diretion;
	private Piece parent;
	public final static short EMPTY = -1, YELLOW=3, WHITE=7, GREEN=2, BLUE=4, RED=1, ORANGE=5;
	private short color;
	private boolean linked;

	public short getColor() {
	    return color;
	}

	public void setColor(short color) {
	    this.color = color;
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

	public Vector3<Integer> getDiretion(){
		return diretion;
	}

	
	
	public boolean isLinked() {
	    return linked;
	}

	public void setLinked(boolean linked) {
	    this.linked = linked;
	}

	@Override
	public String toString() {
		if(color == EMPTY)
			return "  ";
		return String.format("\033[4%s;1m  \033[m", color);
	}

	public Face(Piece piece, Direction direction, short color){
		this.diretion = Vector3.of(direction.x, direction.y, direction.z);
		this.parent = piece;
		this.color = color;
	}


}
