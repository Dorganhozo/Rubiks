package component;
import java.util.Map;

import terminal.Color;
import math.Vector3;
import component.Camera.Direction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class Piece{
	public final Cube parent;
	private Map<Vector3, Face> faces;
	private final Vector3 position;

	public Piece toPosition(int x, int y, int z){
		if(parent.isOut(x, y, z))
			return null;

		Piece old = parent.getPiece(x, y, z);

		if(old.getType() != getType())
			throw new IllegalArgumentException("As peças não se equivalem em tipo.");
	
		parent.setPiece(x, y, z, this);
		position.set(x, y, z);


		return old;
	}

	public void verifyFaces(){
		for(Face face : faces()){
			Vector3 key = face.getDiretion();
			this.faces.values().remove(face);
			this.faces.put(key, face);
		}
	}

	public Piece toPosition(Vector3 position){
		return toPosition(position.getX(), position.getY(), position.getZ());
	}

	public int getType(){
		int type = 0;

		for(Face face : faces())
			if(face.getColor() != Color.EMPTY)
				type++;

		return type;
	}

	public Collection<Face> faces(){ return new ArrayList<>(faces.values()); }
		
	public Face face(Vector3 direction){ return faces.get(direction); }
	public Face face(Direction direction){ return face(direction.vect()); }

	public int getPositionX() { return position.getX(); }

	public int getPositionY() { return position.getY(); }

	public int getPositionZ() { return position.getZ(); }

	public Vector3 getPosition(){
		return new Vector3(position);
	}


	@Override
	public String toString() {
		return position.toString();
	}

	public Piece(Cube parent, int x, int y, int z){
		this.parent = parent;

		faces = new HashMap<>();

		position = new Vector3(x, y, z);


		Face[] faces = {
			new Face(this, Direction.UP, Color.YELLOW),
			new Face(this, Direction.DOWN, Color.WHITE),
			new Face(this, Direction.LEFT, Color.BLUE),
			new Face(this, Direction.RIGHT, Color.GREEN),
			new Face(this, Direction.FRONT, Color.RED),
			new Face(this, Direction.BACK, Color.ORANGE)
		};

		for(Face face : faces){
			Vector3 key = face.getDiretion();
			this.faces.put(key, face);

		}

	}
}
