package terminal;

import java.util.Optional;
import java.util.stream.Stream;

import component.Camera.Direction;
import view.Prompt;

public enum Command{
		HELP("help?"){
			@Override
			public void execute(Prompt app, String... args) {
				if(args.length == 1 && args[0].isBlank()){
					System.out.println("Commands:");
					System.err.println(String.join("\n", Stream.of(values()).map(e -> e.name().toLowerCase()).toList()));
					return;
				}	


				try {
					Command command = valueOf(args[0].toUpperCase());
					System.out.println(command.HINT);
				} catch (Exception e) {
					throw new IllegalArgumentException("What command is this?");
				}

			}		

		},
		SHOW("shows the faces of the pieces in perspective."){
			@Override
			public void execute(Prompt app, String... args) {
				app.print(); 
			}
		},
		GO("go <up|down|left|right>"){
			@Override
			public void execute(Prompt app, String... args) {
				if(args.length == 1 && args[0].isBlank())
					throw new IllegalArgumentException("Empty arguments!");


				Optional<Direction> direction = Stream.of(Direction.values())
				.filter(v -> v.name().matches(args[0].toUpperCase()+".*"))
				.findFirst();
				
				if(direction.isEmpty())
					throw new IllegalArgumentException("This direction is not valid");

				

			   	app.go(direction.get()); 
			}
		},
		ROTATE("rotates the entire face of the cube in the camera view."){

			@Override
			public void execute(Prompt app, String... args) {
				//if(args.length == 1 && args[0].isBlank())
				//	throw new IllegalArgumentException("Empty arguments!");

				app.rotate(args.length > 0 && args[0].equals("1"));	    

			}
		},
		RESET("new cube with the same size as the previous one."){

			@Override
			public void execute(Prompt app, String... args) {
				app.reset(); 
			}
		},
		RESIZE("resize <dimension>"){
			@Override
			public void execute(Prompt app, String... args) {
				if(!args[0].matches("[0-9]+"))
					throw new IllegalArgumentException("size is not a number");

				app.resize(Integer.parseInt(args[0]));
			}
		},
		CLEAR("clear the prompt."){
			@Override
			public void execute(Prompt app, String... args) {
				System.out.println("\033[H\033[J");
			}
		},

		EXIT("finish the rubiks."){
			@Override
			public void execute(Prompt app, String... args) {
				app.close();
			}
		};

		private final String HINT;

		public abstract void execute(Prompt app, String... args);
		private Command(String hint){
			this.HINT = hint;
		}

}


