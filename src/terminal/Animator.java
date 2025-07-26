package terminal;

import component.Face;
import java.lang.Math;
import static java.lang.Math.*;

public class Animator {
	private static Color getColor(int x, int y, Face[][] flat) {
		if(x < 0 || x > 16 || y < 0 || y > 16)
			return Color.EMPTY;

		return flat[(int)floor(min(abs(y) / (16 / flat.length), flat.length - 1))][(int)floor(min(abs(x) / (16 / flat.length), flat.length - 1))].getColor();
	}

	public static void drawHorizontallyRotation(Face[][] flatA, Face[][] flatB, boolean otherSide, double angle, Board board){ 
		board.clear();
		for (int j = 0; j < 16; j++) {
			for (int i = 0; i < 16; i++) {
				board.pixel(getA(i, 16, angle), j, getColor(i, j, flatA));
			}
		}

		board.render();
		board.clear();

		for (int j = 0; j < 16; j++) {
			for (int i = 0; i < 16; i++) {
				board.pixel(getB(i, 16, angle), j, getColor(i, j, flatB));
			}
		}
		board.render();

	}

	public static void drawVerticallyRotation(Face[][] flatA, Face[][] flatB, boolean otherSide, double angle, Board board){
		board.clear();
		for (int j = 0; j < 16; j++) {
			for (int i = 0; i < 16; i++) {
				board.pixel(i, getA(j, 16, angle), getColor(i, j, flatA));
			}
		}

		board.render();
		board.clear();

		for (int j = 0; j < 16; j++) {
			for (int i = 0; i < 16; i++) {
				board.pixel(i, getB(j, 16, angle), getColor(i, j, flatB));
			}
		}
		board.render();


	}

	public static void drawPlaneRotation(Face[][] flat, boolean counterClockwise, double angle, Board board){ 
		board.clear();
		for (int j = 0; j < 16; j++) {
			for (int i = 0; i < 16; i++) {
				int x = (int)((-16/2 + i) * cos(angle) + (-16/2 + j) * sin(angle) + 16/2);
				int y = (int)((-16/2 + i) * sin(angle) - (-16/2 + j) * cos(angle) + 16/2);

				board.pixel(i, j, getColor(x, y, flat));
			}
		}
		board.render();
	}

	
	private static int getA(int x, int size, double angle){
		double pivot = size / 2 - (Math.sin(angle) * size) / 2;
		double width = (-Math.cos(angle) * size) / 2 + Math.cos(angle) * x;

		return (int)Math.ceil(pivot + width);	
	}
	private static int getB(int x, int size, double angle){ 
		double pivot = size / 2 - (Math.sin(angle) * size) / 2 + (size / 2) * Math.cos(angle);
		double width = -Math.sin(angle) * x + Math.sin(angle) * size;

		return (int)Math.floor(pivot + width);
	}
}
