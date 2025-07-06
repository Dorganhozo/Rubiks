package terminal;

import java.io.IOError;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import view.Motion;

public class KeyMapper {
	private Map<Character, Consumer<Motion>> map = new HashMap<>();
	
	public void bind(char character, Consumer<Motion> action){
		map.put(character, action);
	}

	public void input(Motion context){
		try{
			Runtime.getRuntime().exec(new String[]{
				"/bin/sh", "-c", "stty raw -echo < /dev/tty"
			}).waitFor();

			Consumer<Motion> action = map.get((char)System.in.read());

			Runtime.getRuntime().exec(new String[]{
				"/bin/sh", "-c", "stty sane < /dev/tty"
			}).waitFor();


			if(action == null)
				return;

			action.accept(context);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
}
