package math;

import java.util.Objects;

public class Vector3<T extends Number> {

	private Number x, y, z;

	public Vector3(Number x, Number y, Number z){
		this.x = acceptValue(x);
		this.y = acceptValue(y);
		this.z = acceptValue(z);
	}

	private Number acceptValue(Number value){

		if(value instanceof ValueReference)
			return ((ValueReference<T>)value);


		return (T)value;

	}

	

	public Vector3(){
		this.x = 0;
		this.y = 0;
		this.z = 0;

	}

	public T getX() {
		return (T)x;
	}

	public T getY() {
		return (T)y;
	}

	public T getZ() {
		return (T)z;
	}

	public void setX(T value) {

		if(x instanceof ValueReference){
			((ValueReference<T>)x).set(value);
			return;
		} 

		this.x = value;
	}

	public void setY(T value) {
		
		if(y instanceof ValueReference){
			((ValueReference<T>)y).set(value);
			return;
		}

		this.y = value;
	}

	public void setZ(T value) {

		if(z instanceof ValueReference){
			((ValueReference<T>)z).set(value);
			return;
		}

		this.z = value;
	}



	public ValueReference<T> getReferenceX(){
		return new ValueReference<T>(this::getX, this::setX);
	}

	public ValueReference<T> getReferenceY(){
		return new ValueReference<T>(this::getY, this::setY);
	}

	public ValueReference<T> getReferenceZ(){
		return new ValueReference<T>(this::getZ, this::setZ);
	}

	public void setX(ValueReference<T> value){
		this.x = value;
	}

	public void setY(ValueReference<T> value) {
		this.y = value;
	}

	public void setZ(ValueReference<T> value) {
		this.z = value;
	}

	public void set(T x, T y, T z){
		setX(x);
		setY(y);
		setZ(z);
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y, z);
	}

	@Override
	public String toString() {
		return String.format("(%s, %s, %s)", x, y, z);
	}

}
