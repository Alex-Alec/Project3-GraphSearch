import java.io.*;
import java.util.*;
import java.util.stream.*;
import java.util.function.*;

/**
 * Implementation of IMDBGraph
 */
public class IMDBGraphImpl implements IMDBGraph {
	private static List<ActorNode> actorList;
	private static List<MovieNode> movieList;
	private static HashMap<String, MovieNode> movieMap;

	/**
	 * creates an IMDBGraphImpl instance that loads and parses the actors and actresses files
	 * @param actorsFilename the name of the file containing the information about actors
	 * @param actressesFilename the name of the file containing the information about actresses
	 * @throws IOException if the filenames are inaccessible
	 */
	public IMDBGraphImpl (String actorsFilename, String actressesFilename) throws IOException {
		actorList = new ArrayList<>();
		movieList = new ArrayList<>();
		movieMap = new HashMap<>();
		processActors(actorsFilename);
		processActors(actressesFilename);
	}

	/**
	 * returns the arraylist of ActorNode stored in actorList
	 * @return actorList
	 */
	public Collection<? extends Node> getActors () {
		System.out.println("actorlist size " + actorList.size());
		return actorList;
	}

	/**
	 * returns the arraylist of MovieNode stored in movieList
	 * @return movieList
	 */
	public Collection<? extends Node> getMovies () {
		System.out.println(movieMap.keySet().size());
		System.out.println("movelist size " + movieList.size());
		return movieList;
	}

	/**
	 * returns a MovieNode whose title is the param name
	 * @param name the title of a movie to find
	 * @return movie a MovieNode with the same title as param name
	 */
	public Node getMovie (String name) {
		for(MovieNode movie: movieList){
			if(name.equals(movie.getName())){
				return movie;
			}
		}
		return null;
	}

	/**
	 * returns an ActorNode whose name is the param name
	 * @param name the name of an actor to find
	 * @return actor an ActorNode with same name as param name
	 */
	public Node getActor (String name) {
		for(ActorNode actor: actorList){
			if(name.equals(actor.getName())){
				return actor;
			}
		}
		return null;
	}

	/**
	 * Parses the movie title from a line containing a movie
	 * @param str line containing a movie
	 * @return the movie title
	 */
	protected static String parseMovieName (String str) {
		int idx1 = str.indexOf("(");
		int idx2 = str.indexOf(")", idx1 + 1);
		return str.substring(0, idx2 + 1);
	}

	/**
	 * Scans an IMDB file for its actors/actresses and movies
	 * @param filename the movie file to parse
	 */
	protected static void processActors (String filename) throws IOException {
		final Scanner s = new Scanner(new File(filename), "ISO-8859-1");

		// Skip until:  Name...Titles
		while (s.hasNextLine()) {
			String line = s.nextLine();
			if (line.startsWith("Name") && line.indexOf("Titles") >= 0) {
				break;
			}
		}
		s.nextLine();  // read one more

		ActorNode mostRecent = null;
		int counter = -1;
		while (s.hasNextLine()) {
			final String line = s.nextLine();
			//System.out.println(line);
			if (line.indexOf("\t") >= 0) {  // new movie, either for an existing or a new actor
				int idxOfTab = line.indexOf("\t");
				if (idxOfTab > 0) {  // not at beginning of line => new actor
					if(counter == 0){
						actorList.remove(actorList.size()-1); // removes the actor from the list if they are not in any movies
					}
					counter = 0;
					String actorName = line.substring(0, idxOfTab);
					ActorNode newActor = new ActorNode(actorName);
					actorList.add(newActor);
					mostRecent = newActor;
				}
				if (line.indexOf("(TV)") < 0 && line.indexOf("\"") < 0) {  // Only include bona-fide movies
					counter++;
					int lastIdxOfTab = line.lastIndexOf("\t");
					final String movieName = parseMovieName(line.substring(lastIdxOfTab + 1));
					if (movieMap.containsKey(movieName)) {
						MovieNode currentMovie = movieMap.get(movieName);
						currentMovie.addActor(mostRecent);
						movieMap.put(movieName, currentMovie);
						actorList.get(actorList.size()-1).addMovie(movieMap.get(movieName));
					} else {
						MovieNode newMovie = new MovieNode(movieName);
						newMovie.addActor(mostRecent);
						movieList.add(newMovie);
						actorList.get(actorList.size()-1).addMovie(newMovie);
						movieMap.put(movieName, newMovie);
					}
				}
			}
		}
		s.close();
	}
}