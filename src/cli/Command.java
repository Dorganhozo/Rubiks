package cli;

import java.util.stream.Stream;

import component.Cube;
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

				if(!Stream.of(Cube.Group.values()).anyMatch(e->e.name().equalsIgnoreCase(args[0])))	
					throw new IllegalArgumentException("This side does not exist");	

			   	app.print(Cube.Group.valueOf(args[0].toUpperCase())); 
			}
		},
		ROTATE{

			@Override
			public void execute(App app, String... args) {
				if(args.length == 1 && args[0].isBlank())
					throw new RuntimeException("Empty arguments!");


				if(!Stream.of(Cube.Group.values()).anyMatch(e->e.name().equalsIgnoreCase(args[0])))	
					throw new IllegalArgumentException("This side does not exist");	

				app.rotate(args[0], args.length > 2 && args[1].equals("1"));	    
				
			}
		},
		EXIT{
			@Override
			public void execute(App app, String... args) {
				System.exit(0);
			}
		};
		public abstract void execute(App app, String... args);

		
	}


