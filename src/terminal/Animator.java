package terminal;

import component.Face;
import component.Flat;

import java.lang.Math;
import java.util.concurrent.TimeUnit;

import static java.lang.Math.*;

public class Animator {
	private final static int INTERVAL = 1;
	private static Color getColor(int x, int y, Flat flat) {
		if(x < 0 || x > 16 || y < 0 || y > 16)
			return Color.EMPTY;

		int column = (int)floor(min(abs(x) / (16 / flat.getDimesion()), flat.getDimesion() - 1));
		int line = (int)floor(min(abs(y - 1) / (16 / flat.getDimesion()), flat.getDimesion() - 1));

		return flat.getFace(column, line).getColor();
	}


	public static void startHorizontallyRotation(Flat a, Flat b, boolean otherSide, Board board) {
		board.clear();

		int begin = 0;
		int end = 90;

		if(!otherSide){
			begin = -90;
			end = 0;
		}

		for (int angle=begin; angle <= end; angle++) {
			drawHorizontallyRotation(a, b, Math.toRadians(Math.abs(angle)), board);	

			try{

				TimeUnit.MILLISECONDS.sleep(INTERVAL);
			}catch(Exception e){}
				
		}
	}

	public static void startVerticallyRotation(Flat a, Flat b, boolean otherSide, Board board){
		board.clear();

		int begin = 0;
		int end = 90;

		if(!otherSide){
			begin = -90;
			end = 0;
		}

		for(int angle=begin; angle <= end; angle++){
			drawVerticallyRotation(a, b, Math.toRadians(Math.abs(angle)), board);

			try{
				TimeUnit.MILLISECONDS.sleep(INTERVAL);
			}catch(Exception e){}
		}
	}

	public static void startPlaneRotation(Flat flat, boolean counterClockWise, Board board){
		board.clear();
		int increment = 1;
		if(counterClockWise)
			increment = -1;		

		for(int angle=0; Math.abs(angle) <= 90; angle+=increment){
			drawPlaneRotation(flat, Math.toRadians(angle), board);

			try{
				TimeUnit.MILLISECONDS.sleep(INTERVAL);
			}catch(Exception e){}

		}
	}


	private static void drawHorizontallyRotation(Flat flatA, Flat flatB, double angle, Board board){ 
		board.rewind();
		for (int j = 0; j < 16; j++) {
			for (int i = 0; i < 16; i++) {
				board.pixel(getA(i, 16, angle), j, getColor(i, j, flatA));
			}
		}

		board.render();
		board.rewind();

		for (int j = 0; j < 16; j++) {
			for (int i = 0; i < 16; i++) {
				board.pixel(getB(i, 16, angle), j, getColor(16 - i, j, flatB));
			}
		}
		board.render();

	}

	private static void drawVerticallyRotation(Flat flatA, Flat flatB, double angle, Board board){
		board.rewind();
		for (int j = 0; j < 16; j++) {
			for (int i = 0; i < 16; i++) {
				board.pixel(i, getA(j, 16, angle), getColor(i, j, flatA));
			}
		}

		board.render();
		board.rewind();

		for (int j = 0; j < 16; j++) {
			for (int i = 0; i < 16; i++) {
				board.pixel(i, getB(j, 16, angle), getColor(i, 16 - j, flatB));
			}
		}
		board.render();


	}

	private static void drawPlaneRotation(Flat flat, double angle, Board board){ 
		board.rewind();
		for (int j = 0; j < 16; j++) {
			for (int i = 0; i < 16; i++) {
				int x = (int)((-16/2 + i) * cos(angle) + (-16/2 + j) * sin(angle) + 16/2);
				int y = (int)((-16/2 + i) * sin(angle) - (-16/2 + j) * cos(angle) + 16/2);

				board.pixel(i, j, getColor(x, 16 - y, flat));
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
