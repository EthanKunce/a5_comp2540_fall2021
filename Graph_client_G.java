import java.io.*;
import java.util.*;

public class Graph_client_G {
  public static void main(String[] args) throws Exception {

    Graph G = new Graph(new Scanner(new File("graph10000.txt")));
    int s = 0;
    long startTime = System.currentTimeMillis();
    Graph_SP sp = new Graph_SP(G, s);

    System.out.println(System.currentTimeMillis() - startTime);

    for (int t = 0; t < 5; t++) { // print the first a few shortest paths
      if (sp.distTo[t] < Double.POSITIVE_INFINITY) {
        System.out.printf("%d => %d (%.2f)  ", s, t, sp.distTo[t]);
        String path = t + "";

        for (Edge e = sp.edgeTo[t]; e != null; e = sp.edgeTo[e.from()]) {
            path = e.from() + "->(" + e.weight() + ")->" + path;
        }

        System.out.println(path);

      } else
        System.out.printf("no path\n");

    }

  }

}
