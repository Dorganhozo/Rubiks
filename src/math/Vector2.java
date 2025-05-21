package math;

import java.util.Objects;

public class Vector2<T extends Number>{
	private Number x, y;

	public Vector2(T x, T y){
		this.x = acceptValue(x);
		this.y = acceptValue(y);
	}

	public Vector2(){
		this.x = 0;
		this.y = 0;
	}

	private Number acceptValue(Number value){

		if(value instanceof ValueReference)
			return ((ValueReference<T>)value);


		return (T)value;

	}


	public T getX() {
		return (T)x;
	}

	public T getY() {
		return (T)y;
	}

	public void setX(T value) {
		if (x instanceof ValueReference) {
			((ValueReference<T>)x).set(value);
			return;
		}
		this.x = x;
	}

	public void setY(T value) {
		if(y instanceof ValueReference){
			((ValueReference<T>)y).set(value);
			return;
		}
		this.y = y;
	}

	public ValueReference<T> getReferenceX(){
		return new ValueReference<T>(this::getX, this::setX);
	}

	public ValueReference<T> getReferenceY(){
		return new ValueReference<>(this::getY, this::setY);
	}

	public void setX(ValueReference<T> value){
		this.x = value;
	}

	public void setY(ValueReference<T> value){
		this.y = value;
	}

	public void set(T x, T y) {
		setX(x);
		setY(y);
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}
	
	@Override
	public String toString() {
	    return String.format("(%s %s)", x, y);
	}
}
