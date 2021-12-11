import java.util.Arrays;
import java.util.HashMap;

public class Heap2540_SP {
  int[] heap;
  int[] index;
//   HashMap index;
  Double[] distances;
  int n = 0; // actuall heap size.
  int CAPACITY = 10001; // array size

public Heap2540_SP() {
	heap = new int[CAPACITY];
	index = new int[CAPACITY];
    // index = new HashMap<Integer,Double>();
	distances = new Double[CAPACITY];
	for (int i = 0; i < CAPACITY; i++)
		index[i] = -1;
}

public boolean isEmpty() {
	return n == 0;
}

public int removeMin() {
	int min = heap[1];
	swap(1, n);
	n--;
	sink(1);
	index[min] = -1;
	distances[min] = null;
	return min;
}

public void insert(Integer node, Double dist) {
    if(n  >= heap.length-2){
        // CAPACITY *= 2;
        int[] newHeap = Arrays.copyOf(heap, heap.length*2);
        int[] newIndex = Arrays.copyOf(index, index.length*2);
        Double[] newDistances = Arrays.copyOf(distances, distances.length*2);
        // n *= 2;
        heap = newHeap;
        index = newIndex;
        distances = newDistances;
    }
	n++;
	heap[n] = node;
	index[node] = n;
	distances[node] = dist;
	swim(n);
}


public int getHeapSize(){
    return n;
}

private void swim(int k) {
	while (k > 1 && greater(k / 2, k)) {
		swap(k, k / 2);
		k = k / 2;
	}
}

public void put(int node, double dist) {
	if (index[node] != -1) {
		distances[node] = dist;
		swim(index[node]);
	} else
		insert(node, dist);
}

private void swap(int i, int j) {
    //swapping heap 
	Integer temp = heap[i];
	heap[i] = heap[j];
	heap[j] = temp;
               // need update the index positions of nodes 

    temp = index[heap[i]]; //temp becomes the value heap[i] points to, and temp points to
    index[heap[i]] = index[heap[j]];
    index[heap[j]] = temp;

    Double tempD = distances[heap[i]];
    distances[heap[i]] = distances[heap[j]];
    distances[heap[j]] = tempD;
}
	
private void sink(int k) {
	while (2 * k <= n) {
		int j = 2 * k;
		if (j < n && greater(j, j + 1))
			j++;
		if (!greater(k, j))
			break;
		swap(k, j);
		k = j;
	}
}

private boolean greater(int i, int j) {
    //return true if the dist  to i is greater
    // System.out.printf("i:%d: %f > J:%d: %f\n",heap[i], distances[heap[i]], heap[j], distances[heap[j]]);

    return distances[heap[i]] > distances[heap[j]];
    // ret
}
}
