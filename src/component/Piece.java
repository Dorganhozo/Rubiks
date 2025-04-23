package component;
import java.util.Map;
import java.util.Collection;
import java.util.HashMap;

public class Piece{
	public final Cube parent;
	private Map<String, Face> faces = new HashMap<>();
	private int positionX, positionY, positionZ;

	public Piece toPosition(int x, int y, int z){

		if(parent.isOut(x, y, z))
			return null;

		Piece old = parent.getPiece(x, y, z);

		if(old.getType() != getType())
			throw new IllegalArgumentException("As peças não se equivalem em tipo.");

		parent.setPiece(x, y, z, this);
		positionX = x;
		positionY = y;
		positionZ = z;

		verifyFaces();

		return old;
	}

	public void verifyFaces(){
		Face blocked = null;


		for(Face face : faces.values())
			if(face.getColor() != Face.EMPTY && !face.isBreathing()){
				blocked = face;
				break;
			}

		if(blocked == null)
			return;


		for(Face face : faces.values())
			if(face.getColor() == Face.EMPTY && face.isBreathing()){
				face.setColor(blocked.getColor());
				blocked.setColor(Face.EMPTY);
				break;
			}
	}

	public int getType(){
		int type = 0;

		for(Face face : faces())
			if(face.getColor() != Face.EMPTY)
				type++;

		return type;
	}

	public Collection<Face> faces(){ return faces.values(); }
		
	public Face face(String name){ return faces.get(name.toLowerCase()); }

	public int getPositionX() { return positionX; }

	public int getPositionY() { return positionY; }

	public int getPositionZ() { return positionZ; }

	@Override
	public String toString() {
		return String.format("(%s, %s, %s)", positionX, positionY, positionZ);
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
