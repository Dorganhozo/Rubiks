package terminal;
public enum Color {

	RED(255, 0, 0),
	GREEN(0, 255, 0),
	BLUE(0, 0, 255),
	ORANGE(255, 225/2, 0),
	YELLOW(255, 255, 0),
	WHITE(255, 255, 255),
	EMPTY();

	public int[] rgb;

	@Override
	public String toString() {
		if(rgb == null)
			return "";
		return String.format("%s;%s;%s", rgb[0], rgb[1], rgb[2]);
	}

	private Color(int red, int green, int blue){
		this.rgb = new int[]{red, green, blue};
	}

	private Color(){}
}
