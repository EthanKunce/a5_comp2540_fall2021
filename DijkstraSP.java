import java.io.File;
import java.util.*;

public class DijkstraSP {
  public double[] distTo;
  public Edge[] edgeTo;
  public Heap2540_SP pq;

// Find the shortest paths in G emanating from node s.
public DijkstraSP(Graph G, int s) {
	distTo = new double[G.V]; 
        //record the distances from source s to all other nodes
	edgeTo = new Edge[G.V]; // the best edge to the node
	for (int v = 0; v < G.V; v++)
		distTo[v] = Double.POSITIVE_INFINITY;
	distTo[s] = 0.0;

	// relax vertices in order of distance from s
	pq = new Heap2540_SP();
	pq.insert(s, distTo[s]);
	while (!pq.isEmpty()) {
		int v = pq.removeMin();
		for (Edge e : G.edgesOf[v])
			relax(e);
	}
}

// relax edge e and update pq if changed
private void relax(Edge e) {
	int v = e.from(), w = e.to();
	if (distTo[w] > distTo[v] + e.weight()) {
		distTo[w] = distTo[v] + e.weight();
		edgeTo[w] = e;
		pq.put(w, distTo[w]);
	}
}
}