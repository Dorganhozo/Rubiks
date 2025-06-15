package math;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;

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

	public void addX(int value){
		setX(getX() + value);
	}

	public void addY(int value){
		setY(getY() + value);
	}

	public void addZ(int value) {
		setZ(getZ() + value);
	}

	public Value x(){
		return new Value(this::getX, this::setX);
	}

	public Value y(){
		return new Value(this::getY, this::setY);
	}

	public Value z(){
		return new Value(this::getZ, this::setZ);
	}

	

	public void set(int x, int y, int z){
		setX(x);
		setY(y);
		setZ(z);
	}
	public void set(Vector3 vector){
		set(vector.getX(), vector.getY(), vector.getZ());
	}

	public void set(int value){
		setX(value);
		setY(value);
		setZ(value);
	}

	public void add(Vector3 vector){
		addX(vector.getX());
		addY(vector.getY());
		addZ(vector.getZ());
	}

	public void subtract(Vector3 vector){
		addX(-vector.getX());
	        addY(-vector.getY());
                addZ(-vector.getZ());
	}

	public void add(int value){
		addX(value);
		addY(value);
		addZ(value);
	}

	public Vector3 getDirection(Vector3 dest){
		int dirX, dirY, dirZ;
		dirX = (int)Math.signum(dest.x - x);
		dirY = (int)Math.signum(dest.y - y);
		dirZ = (int)Math.signum(dest.z - z);

		return Vector3.of(dirX, dirY, dirZ);
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


	public class Value {
		private Supplier<Integer> getter;
		private Consumer<Integer> setter;
		public int get(){
			return getter.get();

		}

		public void set(int value){
			setter.accept(value);
		}
		
		private Value(Supplier<Integer> getter, Consumer<Integer> setter){
			this.getter = getter;
			this.setter = setter;
		}
		
	}

}
