import java.util.*;

/**
 * Implementation of GraphSearchEngine
 */
public class GraphSearchEngineImpl implements GraphSearchEngine {

	/**
	 * Return the list of the shortest path from s to t
	 * @param s the start node.
	 * @param t the target node.
	 * @return the list of the shortest path from s to t
	 */
	@Override
	public List<Node> findShortestPath(Node s, Node t) {

		// Store visited Nodes and their distances
		Map<Node, Integer> visitedDistances = new HashMap<>();
		Queue<Node> nodesToVisit = new ArrayDeque<>();

		visitedDistances.put(s, 0);
		nodesToVisit.add(s);

		// Run BFS to find shortest distance
		while(!nodesToVisit.isEmpty()) {
			Node current = nodesToVisit.remove();
			if (current.equals(t)) {
				break;
			}

			// Move to neighbors and update their distances
			for (Node neighbor : current.getNeighbors()) {
				if (!visitedDistances.containsKey(neighbor)) {
					visitedDistances.put(neighbor, visitedDistances.get(current) + 1);
					nodesToVisit.add(neighbor);
				}

			}
		}

		// If never found t from s, end
		if(!visitedDistances.containsKey(t)){
			return null;
		}

		// Run find shortest path, knowing the shortest distance
		return findPathFromDistance(s, t, visitedDistances);
	}

	/**
	 * With the shortest distance, find a shortest path from s to t
	 * @param s the start node
	 * @param t the target
	 * @param visitedDistances
	 * @return the list of the shortest path from s to t
	 */
	public List<Node> findPathFromDistance(Node s, Node t, Map<Node, Integer> visitedDistances) {
		List<Node> result = new ArrayList<>();
		result.add(t);
		Node current = t;
		boolean modified = true;

		int shortestDistance = visitedDistances.get(t)-1;
		System.out.println(shortestDistance);
		while(!current.equals(s) && modified){
			modified = false;
			for(Node neighbor: current.getNeighbors()){

				// If the node wasn't visited on the path to finding the shortest distance, then ignore
				if(!visitedDistances.containsKey(neighbor)){
					continue;
				}

				// If this node is the correct distance from s, then move along it
				if(visitedDistances.get(neighbor) == shortestDistance) {
					result.add(neighbor);
					current = neighbor;
					shortestDistance--;
					modified = true;
					break;
				}
			}
		}

		// Reverse the array
		Collections.reverse(result);
		return result;
	}

}
