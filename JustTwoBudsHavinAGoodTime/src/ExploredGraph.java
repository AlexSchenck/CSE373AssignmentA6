import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Stack;
import java.util.function.Function;

/**
 * 
 */

/**
 * @author your name(s) here.
 * Extra Credit Options Implemented, if any:  (mention them here.)
 * 
 * Solution to Assignment 6 in CSE 373, Winter 2016
 * University of Washington.
 * 
 * Starter code v1.1b. By Steve Tanimoto, with modifications
 * by Kuikui Liu and S.J. Liu.
 *
 * The main differences between this version (1.1a) and version 1.1 are:
 *  1. The use of ArrayList rather than array for the pegs variable in class Vertex.
 *  2. Type information provided for both Function prototypes in class Operator.
 *
 * This code requires Java version 8 or higher.
 *
 */

// Here is the main application class:
public class ExploredGraph {
	Set<Vertex> Ve; // collection of explored vertices
	Set<Edge> Ee;   // collection of explored edges
	
	public ExploredGraph() {
		Ve = new LinkedHashSet<Vertex>();
		Ee = new LinkedHashSet<Edge>();
	}

	public void initialize(Vertex v) {
		Ve.add(v);
	}
	
	public int nvertices() {
		return Ve.size();
	}
	
	public int nedges() {
		return Ee.size();
	}
	
	public void dfs(Vertex vi, Vertex vj) {
		
	} // Implement this.
	
	public void bfs(Vertex vi, Vertex vj) {
		if (vi.toString().equals(vj.toString())) {
			
		}
	} // Implement this.
	
	public ArrayList<Vertex> retrievePath(Vertex vi) {
		return null;
	} // Implement this.
	
	public ArrayList<Vertex> shortestPath(Vertex vi, Vertex vj) {
		return null;
	} // Implement this.
	
	public Set<Vertex> getVertices() {return Ve;} 
	public Set<Edge> getEdges() {return Ee;} 
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ExploredGraph eg = new ExploredGraph();
		// Test the vertex constructor: 
		Vertex v0 = eg.new Vertex("[[4,3,2,1],[],[]]");
		System.out.println(v0);
		// Add your own tests here.
		// The autograder code will be used to test your basic functionality later.

	}
	
	class Vertex {
		ArrayList<Stack<Integer>> pegs; // Each vertex will hold a Towers-of-Hanoi state.
		// There will be 3 pegs in the standard version, but more if you do extra credit option A5E1.
		
		// Constructor that takes a string such as "[[4,3,2,1],[],[]]":
		public Vertex(String vString) {
			String[] parts = vString.split("\\],\\[");
			pegs = new ArrayList<Stack<Integer>>(3);
			for (int i=0; i<3;i++) {
				pegs.add(new Stack<Integer>());
				try {
					parts[i]=parts[i].replaceAll("\\[","");
					parts[i]=parts[i].replaceAll("\\]","");
					List<String> al = new ArrayList<String>(Arrays.asList(parts[i].split(",")));
					System.out.println("ArrayList al is: "+al);
					Iterator<String> it = al.iterator();
					while (it.hasNext()) {
						String item = it.next();
                                                if (!item.equals("")) {
                                                        System.out.println("item is: "+item);
                                                        pegs.get(i).push(Integer.parseInt(item));
                                                }
					}
				}
				catch(NumberFormatException nfe) { nfe.printStackTrace(); }
			}		
		}
		public String toString() {
			String ans = "[";
			for (int i=0; i<3; i++) {
			    ans += pegs.get(i).toString().replace(" ", "");
				if (i<2) { ans += ","; }
			}
			ans += "]";
			return ans;
		}
	}
	
	class Edge {
		private Vertex i, j;
		
		//The Edge constructor takes two vertices and sets the private
		//vertices equal to them.
		public Edge(Vertex vi, Vertex vj) {
			this.i = vi;
			this.j = vj;
		}
		
		//Returns the edge in string format.
		public String toString() {
			return "Edge from " + i.toString() + " to " + j.toString();
		}
		
		//Returns the first vertex in the edge
		public Vertex getEndpoint1() {
			return i;
		}
		
		//Returns the second vertex in the edge
		public Vertex getEndpoint2() {
			return j;
		}
	}
	
	class Operator {
		private int i, j;

		public Operator(int i, int j) {
			this.i = i;
			this.j = j;
		}

		Function<Vertex, Boolean> getPrecondition() {
			// TODO: return a function that can be applied to a vertex (provided
			// that the precondition is true) to get a "successor" vertex -- the
			// result of making the move.
			return new Function<Vertex, Boolean>() {
				@Override
				public Boolean apply(Vertex vertex) {
					// if the "from" peg's disk is smaller than the "to" peg's disk
					return vertex.pegs.get(i).peek() < vertex.pegs.get(j).peek();
				}
			};
		}

		Function<Vertex, Vertex> getTransition() {
			// TODO: return a function that can be applied to a vertex (provided
			// that the precondition is true) to get a "successor" vertex -- the 
			// result of making the move.
			return new Function<Vertex, Vertex>() {
				@Override
				public Vertex apply(Vertex vertex) {
					Function<Vertex, Boolean> asdf = getPrecondition();
					
					return null;
				}
			};
		}

		public String toString() {
			// TODO: return a string good enough
			// to distinguish different operators
			return "Operator " + i + ", " + j;
		}
	}

}