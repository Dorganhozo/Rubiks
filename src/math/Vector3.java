package math;

import java.util.Objects;

public class Vector3 {

	private int x, y, z;

	public Vector3(int x, int y, int z){
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Vector3(Vector3 vector3){
		this(vector3.x, vector3.y, vector3.z);
	}


	public Vector3(){
		this.x = 0;
		this.y = 0;
		this.z = 0;
		

	}

	public static Vector3 of(int x, int y, int z){
		return new Vector3(x, y, z);
	}


	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getZ() {
		return z;
	}

	public void setX(int value) {
		this.x = value;
	}

	public void setY(int value) {
		this.y = value;
	}

	public void setZ(int value) {
		this.z = value;
	}


	public void set(int x, int y, int z){
		setX(x);
		setY(y);
		setZ(z);
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y, z);
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		
		if(!(obj instanceof Vector3))
			return false;

		Vector3 vector = (Vector3)obj;

		return 	vector.getX() == getX() &&
		        vector.getY() == getY() &&
			vector.getZ() == getZ();
	}
	

	@Override
	public String toString() {
		return String.format("(%s, %s, %s)", x, y, z);
	}

}
