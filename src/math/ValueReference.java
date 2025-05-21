package math;


public final class ValueReference<T extends Number> extends Number{
	
	private Set<T> setter;
	private Get<?> getter;

	public interface Set<T extends Number> {
		public void set(T value);	
	}

	public interface Get<T extends Number> {
		public Number get();
	}

	private static class NewValue<T extends Number> implements Set<T>, Get<T>{
		private Number value;
		@Override
		public void set(Number value) {
		    this.value = value;
		    
		}

		@Override
		public Number get() {
		    return value;
		}
		private NewValue(Number value){
			this.value = value;
		}
	}

	public static <T extends Number> ValueReference<T> parse(T value){
		NewValue<T> nvalue = new NewValue<>(value);
		return new ValueReference<T>(nvalue, nvalue);
	}



	@Override
	public double doubleValue() {
		return getter.get().doubleValue();
	}	

	@Override
	public int intValue() {
		return getter.get().intValue();
	}

	@Override
	public long longValue() {
		return getter.get().longValue();
	}

	@Override
	public float floatValue() {
		return getter.get().floatValue();
	}	

	@Override
	public String toString() {
		return getter.get().toString();
	}


	public void set(T value){
		setter.set(value);
	}

	public ValueReference(Get<T> getter, Set<T> setter){
		this.setter = setter;
		this.getter = getter;
	}


	//private ValueReference(){}


}
