import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Node that stores Movie
 */
public class MovieNode implements Node {
	String name;
	List<ActorNode> actors;

	/**
	 * Constructor
	 * @param name
	 */
	public MovieNode(String name){
		actors = new ArrayList<>();
		this.name = name;
	}

	/**
	 * Add actor to the neighbors list
	 * @param actor
	 */
	public void addActor(ActorNode actor){
		actors.add(actor);
	}

	/**
	 * Get name
	 * @return
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * Get actor list
	 * @return
	 */
	@Override
	public Collection<? extends Node> getNeighbors() {
		return actors;
	}
}
