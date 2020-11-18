import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MovieNode implements Node {
	String name;
	List<ActorNode> actors;

	public MovieNode(String name){
		actors = new ArrayList<>();
		this.name = name;
	}

	public void addActor(ActorNode actor){
		actors.add(actor);
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Collection<? extends Node> getNeighbors() {
		return actors;
	}
}
