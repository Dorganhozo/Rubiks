package terminal;

import component.Flat;

import java.lang.Math;
import java.util.concurrent.TimeUnit;
import view.Motion;

import static java.lang.Math.*;

public class Animator {
	private final static int INTERVAL = 30;
	private final static int SPEED = 10;
	private static Color getColor(int x, int y, Flat flat, Motion context) {
		if(x < 0 || x >= context.getProportion() || y < 0 || y >= context.getProportion())
			return Color.EMPTY;

		int column = (int)floor(min(abs(x) / (context.getProportion() / flat.getDimesion()), flat.getDimesion() - 1));
		int line = (int)floor(min(abs(y) / (context.getProportion() / flat.getDimesion()), flat.getDimesion() - 1));

		return flat.getFace(column, line).getColor();
	}


	public static void startHorizontallyRotation(Flat a, Flat b, boolean otherSide, Motion context) {
		int begin = 0;
		int end = 90;

		if(!otherSide){
			begin = -90;
			end = 0;
		}

		for (int angle=begin; angle <= end; angle+=SPEED) {
			drawHorizontallyRotation(a, b, Math.toRadians(Math.abs(angle)), context);	

			try{

				TimeUnit.MILLISECONDS.sleep(INTERVAL);
			}catch(Exception e){}
				
		}
	}

	public static void startVerticallyRotation(Flat a, Flat b, boolean otherSide, Motion context){
		int begin = 0;
		int end = 90;

		if(!otherSide){
			begin = -90;
			end = 0;
		}

		for(int angle=begin; angle <= end; angle+=SPEED){
			drawVerticallyRotation(a, b, Math.toRadians(Math.abs(angle)), context);

			try{
				TimeUnit.MILLISECONDS.sleep(INTERVAL);
			}catch(Exception e){}
		}
	}

	public static void startPlaneRotation(Flat flat, boolean counterClockWise, Motion context){
		int begin = 0;
		int end = 90;
		int increment = 1;
		if(counterClockWise)
			increment = -1;		

		for(int angle=begin; Math.abs(angle) <= end; angle+=increment * SPEED){
			drawPlaneRotation(flat, Math.toRadians(angle), context);

			try{
				TimeUnit.MILLISECONDS.sleep(INTERVAL);
			}catch(Exception e){}

		}
	}


	private static void drawHorizontallyRotation(Flat flatA, Flat flatB, double angle, Motion context){ 
		Board board = context.getBoard();

		for (int j = 0; j < context.getProportion(); j++) {
			for (int i = 0; i < context.getProportion(); i++) {
				int xa = getA(i, context.getProportion(), angle) + context.getResolution()/2 - context.getProportion()/2;
				int ya = j + context.getResolution()/2 - context.getProportion()/2;
				Color colorA = getColor(i, j, flatA, context);

				int xb = getB(i, context.getProportion(), angle) + context.getResolution()/2 - context.getProportion()/2;
				int yb = j + context.getResolution()/2 - context.getProportion()/2;
				Color colorB = getColor(context.getProportion() - i, j, flatB, context);

				board.pixel(xa, ya, colorA);
				board.pixel(xb, yb, colorB);
			}
		}

		board.clear();
		board.render();
	}

	private static void drawVerticallyRotation(Flat flatA, Flat flatB, double angle, Motion context){
		Board board = context.getBoard();

		for (int j = 0; j < context.getProportion(); j++) {
			for (int i = 0; i < context.getProportion(); i++) {
				int xa = i + context.getResolution()/2 - context.getProportion()/2;
				int ya = getA(j, context.getProportion(), angle) + context.getResolution()/2 - context.getProportion()/2;
				Color colorA = getColor(i, j, flatA, context);

				int xb = i + context.getResolution()/2 - context.getProportion()/2;
				int yb = getB(j, context.getProportion(), angle) + context.getResolution()/2 - context.getProportion()/2;
				Color colorB = getColor(i, context.getProportion() - j, flatB, context);

				board.pixel(xa, ya, colorA);
				board.pixel(xb, yb, colorB);
			}
		}
		
		board.clear();
		board.render();

	}

	private static void drawPlaneRotation(Flat flat, double angle, Motion context){ 
		Board board = context.getBoard();

		int a = context.getResolution();
		int b = context.getResolution() - 1;

		for (int j = 0; j < board.getHeight(); j++) {
			for (int i = 0; i < board.getWidth(); i++) {
				int x = (int)( (i - a/2) * cos(angle) + (j - a/2) * sin(angle) + context.getProportion()/2);
				int y = (int)(-(i - b/2) * sin(angle) + (j - a/2) * cos(angle) + context.getProportion()/2);
				Color color = getColor(x, y, flat, context);

				board.pixel(i, j, color);

			}
		}

		board.clear();
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
