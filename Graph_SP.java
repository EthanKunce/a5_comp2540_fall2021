import java.io.*;
import java.util.*;


public class Graph_SP {
  public double[] distTo;
  public Edge[] edgeTo;
  public Map distTable = new HashMap<Integer, Double>();  

  public Graph_SP(Graph G, int s) {
	distTo = new double[G.V]; 
	edgeTo = new Edge[G.V];
	 
	for (int v = 0; v < G.V; v++) 
		distTo[v] = Double.POSITIVE_INFINITY;
	distTo[s] = 0.0;
	// relax vertices in order of distance from s
	distTable.put(s, distTo[s]);
	while (!distTable.isEmpty()) {
		int v = removeMin(distTable);
		for (Edge e : G.edgesOf[v])
			relax(e);
	}
  }

  private void relax(Edge e) {
	int v = e.from(), w = e.to();
	if (distTo[w] > distTo[v] + e.weight()) {
		distTo[w] = distTo[v] + e.weight();
		edgeTo[w] = e;
		distTable.put(w, distTo[w]);
	  //add the relaxation part here. 
	  //three lines of code.
	}
  }

	public static int removeMin(Map<Integer, Double> distTable) {
		Iterator it = distTable.entrySet().iterator();
		double minValue = Integer.MAX_VALUE;
		int minKey = -1;
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			if (minValue > (Double) pair.getValue()) {
				minKey = (Integer) pair.getKey();
				minValue = (Double) pair.getValue();
			}
		}
		distTable.remove(minKey);
		return minKey;
	}
}
