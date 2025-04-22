package moviment;

import util.Cube;
import util.Flat;
import util.Piece;

//Reponsavel pelos movimentos do cubo magico
public class Magic{
	private static final 
		double RADIANS_90 = Math.toRadians(90),
		       RADIANS_180 = Math.toRadians(180),
		       RADIANS_270 = Math.toRadians(270);

	public static void rotate(Flat flat, boolean inverse){
		Piece[][] temp = new Piece[flat.getDimension()][flat.getDimension()];

		for(int y=0; y < flat.getDimension(); y++)
			for(int x=0; x < flat.getDimension(); x++){
				
				int dx = flat.getDimension()/2 - x;
				int dy = flat.getDimension()/2 - y;

				if(dx == 0 && dy == 0){
					temp[x][y] = flat.getPiece(x, y);
					continue;
				}
			
				double angle = getRadians(dx, dy) + (inverse? RADIANS_90 : -RADIANS_90);
				double ray = Math.sqrt(dx*dx + dy*dy);

				temp[getX(angle, ray)][getY(angle, ray)] = flat.getPiece(x, y);
			}

		for(int y=0; y < flat.getDimension(); y++)
			for(int x=0; x < flat.getDimension(); x++)
				if(temp[x][y] != null)
					flat.setPiece(x, y, temp[x][y]);
	


	}
	private static double getRadians(int dx, int dy){
		double hypotenuse = Math.sqrt(dx*dx + dy*dy);
		int adjacentSide = Math.abs(dx);
		int oppositeSide = Math.abs(dy);

		double cos = adjacentSide/hypotenuse;
		double sin = oppositeSide/hypotenuse;

		double sumOfRightAngles = 0;

		if(dx < 0 && dy <= 0) sumOfRightAngles = RADIANS_90;
		if(dx >= 0 && dy <= 0) sumOfRightAngles = RADIANS_180;
		if(dx > 0 && dy >= 0) sumOfRightAngles = RADIANS_270;

		//Pelo amor de deus não mexa nisso!
		double remainder = (Math.signum(dx) == Math.signum(dy) ? Math.acos(cos) : Math.asin(sin)) % Math.toRadians(90);
		

		return sumOfRightAngles + remainder; 
	}

	private static int getX(double angle, double ray){ return (int)(ray * (1 + (float)Math.sin(angle))); }

	private static int getY(double angle, double ray){ return (int)(ray * (1 - (float)Math.cos(angle))); }
}
