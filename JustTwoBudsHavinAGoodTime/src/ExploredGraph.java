import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.Stack;
import java.util.function.Function;

/**
 * 
 */

/**
 * @author 
 * Alex Schenck & Harrison McDonough 
 * Extra Credit Options Implemented, if any:  None
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
	Map<Vertex, Vertex> path; // path to vj from last called search
	ArrayList<Operator> operations;
	
	public ExploredGraph() {
		Ve = new LinkedHashSet<Vertex>();
		Ee = new LinkedHashSet<Edge>();
		path = new HashMap<Vertex, Vertex>(); //this should be a set, use a map to store the transition
		//i.e. value a, we got here from b
		operations = new ArrayList<Operator>();
		
		operations.add(new Operator(0, 1));
		operations.add(new Operator(0, 2));
		operations.add(new Operator(1, 0));
		operations.add(new Operator(1, 2));
		operations.add(new Operator(2, 0));
		operations.add(new Operator(2, 1));
	}

	public void initialize(Vertex v) {
		//clear the path (explored vertices)
		//clear the edges (explored edges)
		//add v
		Ve.clear();
		Ee.clear();
		Ve.add(v);
		
	}
	
	public int nvertices() {
		return Ve.size();
	}
	
	public int nedges() {
		return Ee.size();
	}
		
	public void dfs(Vertex vi, Vertex vj) {
		//while stack not empty
		//current = pop();
		//if (!visited) 
		//   mark visited
		//   get children
		//   add children to stack
		//   
		Stack<Vertex> fringe = new Stack<Vertex>();
		fringe.push(vi);
		while(!fringe.isEmpty()) {
			Vertex current = fringe.pop();
			if(!Ve.contains(current)) {
				Ve.add(current);
				for(Operator o : operations) {
					if ((Boolean) o.getPrecondition().apply(current)) {
						Vertex child = o.getTransition().apply(current);
						fringe.push(child);
						path.put(child, current);
					}
				}
			}
		}
	}
	
	public void bfs(Vertex vi, Vertex vj) {
		Queue<Vertex> fringe = new LinkedList<Vertex>();
		fringe.add(vi);
		while(!fringe.isEmpty()) {
			Vertex current = fringe.remove();
			if(!Ve.contains(current)) {
				Ve.add(current);
				for(Operator o : operations) {
					if ((Boolean) o.getPrecondition().apply(current)) {
						Vertex child = o.getTransition().apply(current);
						fringe.add(child);
						path.put(child, current);
					}
				}
			}
		}
	}

	public ArrayList<Vertex> retrievePath(Vertex vi) {
		//OH: uses the last search used to get to that specific node (vi)
		//
		return null;
	}
	
	public ArrayList<Vertex> shortestPath(Vertex vi, Vertex vj) {
		//OH: uses bfs. change this to call a bfs
		return null;
	}
	
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
		
		eg.initialize(v0);
		
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

		// since it's implicit graph, use these to get adjacent vertices.
		//with implicit, we only know v0, use preCondition and getTransition to find vertices
		//once we get an edge and vertex store them in Ve and Ee
		//"every time we search we build that on the fly"
		//
		//gameplan - run through each precondition, if applicable then apply. store the returned vertex in Ve
		//and store a new edge of vertex to new vertex in Ee
		
		// returns a function that can be applied to a vertex to show whether or not it's applicable
		Function<Vertex, Boolean> getPrecondition() {
			return new Function<Vertex, Boolean>() {
				@Override
				public Boolean apply(Vertex vertex) {
					// if the "from" peg's disk is smaller than the "to" peg's disk
					return vertex.pegs.get(i).peek() < vertex.pegs.get(j).peek();
				}
			};
		}

		//
		// returns a function that can be applied to a vertex, making a successor vertex
		// assumes precondition is met
		Function<Vertex, Vertex> getTransition() {
			return new Function<Vertex, Vertex>() {
				@Override
				public Vertex apply(Vertex vertex) {
					// create new board state, commit operation
					Vertex newVertex = new Vertex(vertex.toString());
					newVertex.pegs.get(j).push(newVertex.pegs.get(i).pop());
					
					// return successor board state
					return newVertex;
				}
			};
		}

		// returns a string describing this operator
		public String toString() {
			return "Operator from peg " + i + " to peg " + j;
		}
	}

}