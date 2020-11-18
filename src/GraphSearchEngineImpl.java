import java.util.*;

public class GraphSearchEngineImpl implements GraphSearchEngine {

	@Override
	public List<Node> findShortestPath(Node s, Node t) {
		Map<Node, Integer> visitedDistances = new HashMap<>();
		Queue<Node> nodesToVisit = new ArrayDeque<>();
		visitedDistances.put(s, 0);
		nodesToVisit.add(s);
		while(!nodesToVisit.isEmpty()) {
			Node current = nodesToVisit.remove();
			if (current.equals(t)) {
				break;
			}
			for (Node neighbor : current.getNeighbors()) {
				if (!visitedDistances.containsKey(neighbor)) {
					visitedDistances.put(neighbor, visitedDistances.get(current) + 1);
					nodesToVisit.add(neighbor);
				}
			}
		}

		List<Node> result = new ArrayList<>();
		result.add(t);
		Node current = t;
		int shortestDistance = visitedDistances.get(t);
		while(current != s){
			shortestDistance--;
			for(Node neighbor: current.getNeighbors()){
				if(visitedDistances.get(neighbor) == shortestDistance){
					result.add(neighbor);
					current = neighbor;
				}
			}
		}
		Collections.reverse(result);
		return result;
	}

}
