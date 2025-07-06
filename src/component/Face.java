package component;

import component.Camera.Direction;
import math.Vector3;
import terminal.Color;

public class Face{
	private Vector3 diretion;
	private Piece parent;
	private Color color;
	

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
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

	public Vector3 getDiretion(){
		return diretion;
	}

	


	@Override
	public String toString() {
		return String.format("\033[48;2;%sm  \033[m", color);
	}

	public Face(Piece piece, Direction direction, Color color){
		this.diretion = Vector3.of(direction.x, direction.y, direction.z);
		this.parent = piece;
		this.color = color;
	}




}
