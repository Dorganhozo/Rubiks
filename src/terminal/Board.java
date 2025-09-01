package terminal;

import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.WritableByteChannel;
import java.util.Arrays;


public class Board {
	private WritableByteChannel channel;
	private ByteBuffer buffer;
	private final static String CLEAR_CODE = "\033[H\033[J";
	private final static String HIDE_CURSOR_CODE ="\033[?25l";
	private final static String UNHIDE_CURSOR_CODE ="\033[?25r";
	private final int PIXEL_CODE_LENGTH = 32;
	 
	private int width, height;


	public void pixel(int x, int y, Color color){

		if(x >= 0 && y >= 0 && x < width && y < height){
			int column = 1 + 2 * x;
			int line = 1 + y;

			String code = String.format("\033[%02d;%02dH\033[48;2;%sm  \033[m", line, column, color);
			put(code);
		}
	}


	private void put(String code){
		int min = Math.min(code.length(), buffer.remaining());
		for (int i = 0; i < min; i++) 
			buffer.put((byte)code.charAt(i));	
	}


	public void clear(){
		System.out.println(CLEAR_CODE);
	}

	public void hideCursor(){
		System.out.println(HIDE_CURSOR_CODE);
	}

	public void unhideCursor(){
		System.out.println(UNHIDE_CURSOR_CODE);
	}

	public int getWidth() {
	    return width;
	}

	public int getHeight() {
	    return height;
	}


	public void render(){
		try{
			buffer.flip();
			channel.write(buffer);
			buffer.clear();
		}catch(Exception e){
			e.printStackTrace();
		}
	}


	public Board(int width, int height){
		this.width = width;
		this.height = height;
		this.channel = Channels.newChannel(System.out);
		this.buffer = ByteBuffer.allocate(width*height*PIXEL_CODE_LENGTH*2);
	}
}
