package util;
import java.util.Map;
import java.util.HashMap;

class Piece{
	Cube parent;
	Map<String, Face> faces = new HashMap<>();
	int positionX, positionY, positionZ;

	Piece toPosition(int x, int y, int z){

		if(parent.isOut(x, y, z))
			return null;

		Piece old = parent.pieces[x][y][z];

		if(old.getType() != getType())
			throw new IllegalArgumentException("As peças não se equivalem em tipo.");

		parent.pieces[x][y][z] = this;
		positionX = x;
		positionY = y;
		positionZ = z;

		verifyFaces();

		return old;
	}

	void verifyFaces(){
		Face blocked = null;


		for(Face face : faces.values())
			if(face.color != Face.EMPTY && !face.isBreathing()){
				blocked = face;
				break;
			}

		if(blocked == null)
			return;


		for(Face face : faces.values())
			if(face.color == Face.EMPTY && face.isBreathing()){
				face.color = blocked.color;
				blocked.color = Face.EMPTY;
				break;
			}
	}

	int getType(){
		int type = 0;

		for(Face face : faces.values())
			if(face.color != Face.EMPTY)
				type++;

		return type;
	}

	public Piece(Cube parent, int x, int y, int z){
		this.parent = parent;
		positionX = x;
		positionY = y;
		positionZ = z;

		faces.put("up", new Face(this, Face.YELLOW, 0, -1, 0));
		faces.put("down", new Face(this, Face.WHITE, 0, 1, 0));
		faces.put("left", new Face(this, Face.GREEN, -1, 0, 0));
		faces.put("right", new Face(this, Face.BLUE, 1, 0, 0));
		faces.put("front", new Face(this, Face.RED, 0, 0, -1));
		faces.put("back", new Face(this, Face.ORANGE, 0, 0, 1));

	}
}
