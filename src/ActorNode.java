import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Node that stores an actor
 */
public class ActorNode implements Node {
	String name;
	List<MovieNode> movies;

	/**
	 * Constructor
	 * @param name
	 */
	public ActorNode(String name){
		movies = new ArrayList<>();
		this.name = name;
	}

	/**
	 * Add movie to neighbors list
	 * @param movieNode
	 */
	public void addMovie(MovieNode movieNode){
		movies.add(movieNode);
	}

	/**
	 * Get the name
	 * @return name
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * Get the neighbors
	 * @return movies
	 */
	@Override
	public Collection<? extends Node> getNeighbors() {
		return movies;
	}
}
