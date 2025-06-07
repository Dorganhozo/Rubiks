package cli;

import java.util.Scanner;
import java.util.stream.Stream;

import component.Camera.Direction;
import view.App;

public enum Command{
		HELP{
			@Override
			public void execute(App app, String... args) {
				System.out.println("Commands:");
				System.err.println(String.join(" ", Stream.of(values()).map(e -> e.name().toLowerCase()).toList()));
			}		

		},
		SHOW{
			@Override
			public void execute(App app, String... args) {
				if(args.length == 1 && args[0].isBlank())
					throw new RuntimeException("Empty arguments!");

				if(!Stream.of(Direction.values()).anyMatch(e->e.name().equalsIgnoreCase(args[0])))	
					throw new IllegalArgumentException("This side does not exist");	

			   	app.print(); 
			}
		},
		GO{
			@Override
			public void execute(App app, String... args) {
				if(args.length == 1 && args[0].isBlank())
					throw new RuntimeException("Empty arguments!");

				

			   	app.go(args[1]); 
			}
		},
		ROTATE{

			@Override
			public void execute(App app, String... args) {
				if(args.length == 1 && args[0].isBlank())
					throw new RuntimeException("Empty arguments!");

				app.rotate(args.length >= 2 && args[1].equals("1"));	    
				
			}
		},
		RESET{

			@Override
			public void execute(App app, String... args) {
			   	app.reset(); 
			}
		},
		CLEAR{
			@Override
			public void execute(App app, String... args) {
				System.out.println("\033[H\033[J");
			}
		},
	
		EXIT{
			@Override
			public void execute(App app, String... args) {
				app.close();
			}
		};

		public abstract void execute(App app, String... args);

		
	}


