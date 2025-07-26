package moviment;

import java.util.Collections;
import java.util.stream.Stream;

import component.Camera;
import component.Cube;
import component.Face;
import component.Piece;
import component.Camera.Rotation;
import math.Vector3;

//Reponsavel pelos movimentos do cubo magico
public class Magic{
	public static void rotate(Cube cube, Camera camera, boolean counterClockWise){
		final Face[][] faces = camera.getPerspectiveFaces();
		final int numberSides = 4;
		final int middle = cube.dim/2;
		final int max = cube.dim - 1;
		
		if(camera.isPlaneRotationReversed(counterClockWise))
				counterClockWise = !counterClockWise;

		if(cube.hasOnlyPiece()){
			rotateFaces(faces[0][0].getPiece(), camera.getPlaneRotation(), counterClockWise);
			return;
		}




		for(int j=0; j < middle; j++)
			for(int i=j; i < max - j; i++){
				final Vector3 begin = faces[j][i].getPiece().getPosition();
				final Vector3 newPosition = new Vector3(begin);
				
				

				for(int count=0; count < numberSides; count++){
					camera.getPlaneRotation().apply(newPosition, cube.dim, counterClockWise);
					rotateFaces(cube.getPiece(begin), camera.getPlaneRotation(), counterClockWise);
					cube.swipePieces(cube.getPiece(newPosition), cube.getPiece(begin));
				}
		

			}

	}

	private static void rotateFaces(Piece piece, Rotation rotation, boolean counterClockWise){
		for(Face face : piece.faces()){
			rotation.apply(face.getDiretion(), 1, counterClockWise);
		}

		piece.verifyFaces();

	}


}
