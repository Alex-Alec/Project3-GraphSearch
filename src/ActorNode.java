import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ActorNode implements Node {
	String name;
	List<MovieNode> movies;

	public ActorNode(String name){
		movies = new ArrayList<>();
		String[] parts = name.split(" ");
		if(parts.length == 2){
			parts[0].substring(0, parts[0].length()-1);
			this.name = parts[1] + parts[0];
		}else{
			this.name = name;
		}
	}

	public void addMovie(MovieNode movieNode){
		movies.add(movieNode);
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Collection<? extends Node> getNeighbors() {
		return movies;
	}
}
