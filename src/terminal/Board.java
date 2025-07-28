package terminal;

import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.WritableByteChannel;


public class Board {
	private WritableByteChannel channel;
	private ByteBuffer buffer;
	private String CLEAR_CODE = "\033[H\033[J";

	public void pixel(int x, int y, Color color){
		if(x >= 0 && y >= 0 && x < 16 && y < 16)
			put(String.format("\033[%s;%sH\033[48;2;%sm  \033[m", 1 + y, 1 + 2 * x , color));	
	}

	private void put(String code){
		int min = Math.min(code.length(), buffer.remaining());
		if(buffer.hasRemaining())
			for (int i = 0; i < min; i++) 
				buffer.put((byte)code.charAt(i));	
	}
	
	public void clear(){
		put(CLEAR_CODE);
		render();
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
		this.buffer = ByteBuffer.allocate(8192);
	}
}
