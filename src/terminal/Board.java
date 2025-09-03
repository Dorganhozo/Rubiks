package terminal;

import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.WritableByteChannel;
import java.util.Arrays;


public class Board {
	private final WritableByteChannel channel;
	private final ByteBuffer buffer;
	private final ByteBuffer[] bucket;


	private final static String CLEAR_CODE = "\033[H\033[J";
	private final static String HIDE_CURSOR_CODE ="\033[?25l";
	private final static String UNHIDE_CURSOR_CODE ="\033[?25h";
	private final int PIXEL_CODE_LENGTH = 32;
	 
	private int offsetX, offsetY, width, height;


	public void pixel(int x, int y, Color color){

		if(color == Color.EMPTY)
			return;

		if(x >= 0 && y >= 0 && x <= width && y < height){
			int column = 1 + 2 * (x + this.offsetX);
			int line = 1 + (y + this.offsetY);

			int position = width * y + x;

			String code = String.format("\033[%02d;%02dH\033[48;2;%sm  \033[m", line, column, color);
			
			//System.err.printf("%s(%s, %s)\n", color.name(), x, y);

			if(position >= bucket.length)return;
			if(bucket[position] == null){
				bucket[position] = buffer.slice(buffer.position(), PIXEL_CODE_LENGTH);
				put(code, buffer);
			}else{
				put(code, bucket[position]);

			}

		}
	}


	private void put(String code, ByteBuffer buffer){
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

	public int getOffsetX() {
	    return offsetX;
	}

	public int getOffsetY() {
	    return offsetY;
	}

	public void resize(int offsetX, int offsetY, int width, int height){
		this.offsetX = offsetX;
		this.offsetY = offsetY;
		this.width = width;
		this.height = height;
	}


	public void render(){
		try{
			buffer.flip();
			channel.write(buffer);
			buffer.clear();
			Arrays.fill(bucket, null);

		}catch(Exception e){
			e.printStackTrace();
		}
	}


	public Board(int offsetX, int offsetY, int width, int height){
		this.offsetX =offsetX;
		this.offsetY = offsetY;
		this.width = width;
		this.height = height;
		this.channel = Channels.newChannel(System.out);
		this.buffer = ByteBuffer.allocate(width*height*PIXEL_CODE_LENGTH);
		this.bucket = new ByteBuffer[width*height];
	}
}
