package terminal;

import java.io.IOException;
import java.io.InputStream;

public final class Unix {
	public static int[] getTerminalSize(){
		try {
			Process process = Runtime.getRuntime().exec(new String[]{
				"/bin/sh", "-c", "stty size < /dev/tty"
			});

			process.waitFor();
			InputStream out = process.getInputStream();

			String[] result = new String(out.readAllBytes()).trim().split(" ");

			int[] size = {
				Integer.parseInt(result[0]),
				Integer.parseInt(result[1])
			};

			return size;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void rawMode() throws InterruptedException, IOException{
		Runtime.getRuntime().exec(new String[]{
			"/bin/sh", "-c", "stty raw -echo < /dev/tty"
		}).waitFor();
	}

	public static void normalMode() throws InterruptedException, IOException{
		Runtime.getRuntime().exec(new String[]{
			"/bin/sh", "-c", "stty sane < /dev/tty"
		}).waitFor();


	}

}
