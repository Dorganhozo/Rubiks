package cli;

import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Stream;

import component.Camera.Direction;
import view.Prompt;

public enum Command{
		HELP{
			@Override
			public void execute(Prompt app, String... args) {
				System.out.println("Commands:");
				System.err.println(String.join("\n", Stream.of(values()).map(e -> e.name().toLowerCase()).toList()));
			}		

		},
		SHOW{
			@Override
			public void execute(Prompt app, String... args) {
				app.print(); 
			}
		},
		GO{
			@Override
			public void execute(Prompt app, String... args) {
				if(args.length == 1 && args[0].isBlank())
					throw new RuntimeException("Empty arguments!");


				Optional<Direction> direction = Stream.of(Direction.values())
				.filter(v -> v.name().matches(args[0].toUpperCase()+".*"))
				.findFirst();
				
				if(direction.isEmpty())
					throw new RuntimeException("This direction is not valid");

				

			   	app.go(direction.get()); 
			}
		},
		ROTATE{

			@Override
			public void execute(Prompt app, String... args) {
				//if(args.length == 1 && args[0].isBlank())
				//	throw new RuntimeException("Empty arguments!");

				app.rotate(args.length > 0 && args[0].equals("1"));	    
				
			}
		},
		RESET{

			@Override
			public void execute(Prompt app, String... args) {
			   	app.reset(); 
			}
		},
		RESIZE{
			@Override
			public void execute(Prompt app, String... args) {
				if(!args[0].matches("[0-9]+"))
						throw new RuntimeException("size is not a number");

			    	app.resize(Integer.parseInt(args[0]));
			}
		},
		CLEAR{
			@Override
			public void execute(Prompt app, String... args) {
				System.out.println("\033[H\033[J");
			}
		},
	
		EXIT{
			@Override
			public void execute(Prompt app, String... args) {
				app.close();
			}
		};

		public abstract void execute(Prompt app, String... args);

		
	}


