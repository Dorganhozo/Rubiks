package component;
import java.util.Map;

import math.Vector3;
import component.Camera.Direction;

import java.util.Collection;
import java.util.HashMap;

public class Piece{
	public final Cube parent;
	private Map<Vector3, Face> faces;
	private Vector3 position;

	public Piece toPosition(int x, int y, int z){

		if(parent.isOut(x, y, z))
			return null;

		Piece old = parent.getPiece(x, y, z);

		if(old.getType() != getType())
			throw new IllegalArgumentException("As peças não se equivalem em tipo.");

		parent.setPiece(x, y, z, this);
		position.set(x, y, z);

		verifyFaces();

		return old;
	}

	private void verifyFaces(){
		Face blocked = null;


		for(Face face : faces())
			if(face.getColor() != Face.EMPTY && !face.isBreathing()){
				blocked = face;
				break;
			}

		if(blocked == null)
			return;

		Face free = null;

		for(Face face : faces())
			if(face.getColor() == Face.EMPTY && face.isBreathing()){
				free = face;
				break;
			}

		
		if(getType() == 2){
			free.setColor(blocked.getColor());
			blocked.setColor(Face.EMPTY);
			return;
		}

		Face used = null;
		for(Face face : faces())
			if(face.getColor() != Face.EMPTY && face.isBreathing() && !face.isLinked()){
				used = face;
				break;
			}
		
		free.setColor(used.getColor());
		used.setColor(blocked.getColor());
		blocked.setColor(Face.EMPTY);
	}

	public int getType(){
		int type = 0;

		for(Face face : faces())
			if(face.getColor() != Face.EMPTY)
				type++;

		return type;
	}

	public Collection<Face> faces(){ return faces.values(); }
		
	public Face face(Vector3 direction){ return faces.get(direction); }
	public Face face(Direction direction){ return face(Vector3.of(direction.x, direction.y, direction.z)); }

	public int getPositionX() { return position.getX(); }

	public int getPositionY() { return position.getY(); }

	public int getPositionZ() { return position.getZ(); }

	@Override
	public String toString() {
		return position.toString();
	}

	public Piece(Cube parent, int x, int y, int z){
		this.parent = parent;

		faces = new HashMap<>();

		position = new Vector3(x, y, z);

		Face[] faces = {
			new Face(this, Direction.UP, Face.YELLOW),
			new Face(this, Direction.DOWN, Face.WHITE),
			new Face(this, Direction.LEFT, Face.BLUE),
			new Face(this, Direction.RIGHT, Face.GREEN),
			new Face(this, Direction.FRONT, Face.RED),
			new Face(this, Direction.BACK, Face.ORANGE)
		};

		for(Face face : faces){
			Vector3 key = face.getDiretion();
			this.faces.put(key, face);

		}

	}
}
