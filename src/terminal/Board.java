package terminal;

import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.WritableByteChannel;
import java.util.Arrays;


public class Board {
	private WritableByteChannel channel;
	private ByteBuffer buffer;
	private String CLEAR_CODE = "\033[H\033[J";
	private final int RESOLUTION = 16;
	private final int PIXEL_CODE_LENGTH = 32;

	public void pixel(int x, int y, Color color){
		//n = width * y + x 
		if(x >= 0 && y >= 0 && x < RESOLUTION && y < RESOLUTION){
			int column = 1 + 2 * x;
			int line = 1 + y;

			String code = String.format("\033[%02d;%02dH\033[48;2;%sm  \033[m", line, column, color);
			put(code);	
		}
	}

	private void put(String code){
		int min = Math.min(code.length(), buffer.remaining());
		if(buffer.hasRemaining())
			for (int i = 0; i < min; i++) 
				buffer.put((byte)code.charAt(i));	
	}

	private void insert(String code, int position){
	}
	
	public void clear(){
		System.out.println(CLEAR_CODE);
		buffer.clear();
	}

	public void rewind(){
		buffer.clear();
	}

	public void render(){
		try{
			buffer.flip();
			channel.write(buffer);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}


	public Board(){
		this.channel = Channels.newChannel(System.out);
		this.buffer = ByteBuffer.allocate(RESOLUTION*RESOLUTION*PIXEL_CODE_LENGTH);
	}
}
