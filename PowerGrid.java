import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class PowerGrid {

	private int totalNumberOfPlacesThatNeedPower;
	Edge[] allEdgesThatConnectVertices;

	private void powerGridUnion(RetrieveSubsetOfUnion subGroup[], int x, int y) {

		int xParent = retieveSet(subGroup, x);
		int yParent = retieveSet(subGroup, y);

		if (subGroup[xParent].position < subGroup[yParent].position)
			subGroup[xParent].root = yParent;
		else if (subGroup[xParent].position > subGroup[yParent].position)
			subGroup[yParent].root = xParent;

		else {
			subGroup[yParent].root = xParent;
			subGroup[xParent].position++;
		}
	}

	public Set<Edge> kruskal(SimpleGraph graph) {

		Set<Edge> edgeList = new LinkedHashSet<Edge>();

		// index to be used for obtaining results
		int j = 0;

		edgeList.addAll(graph.edgeList);

		RetrieveSubsetOfUnion subGroup[] = new RetrieveSubsetOfUnion[totalNumberOfPlacesThatNeedPower];
		for (int i = 0; i < totalNumberOfPlacesThatNeedPower; ++i)
			subGroup[i] = new RetrieveSubsetOfUnion();

		for (int places = 0; places < totalNumberOfPlacesThatNeedPower; ++places) {
			subGroup[places].root = places;
			subGroup[places].position = 0;
		}

		while (j < totalNumberOfPlacesThatNeedPower - 1) {

			Edge next_edge = allEdgesThatConnectVertices[j];
			int x = retieveSet(subGroup, next_edge.getData());
			int y = retieveSet(subGroup, next_edge.getData());
			if (x != y) {
				allEdgesThatConnectVertices[j++] = next_edge;
				powerGridUnion(subGroup, x, y);
			}
		}

		return edgeList;
	}

	// Driver's Code
	public static void main(String[] args) {

		SimpleGraph G = new SimpleGraph();
		Vertex i, j, k;
		Edge a, e, f;

		i = G.insertVertex(null, "k");
		k = i;
		j = G.insertVertex(null, "e");
		a = G.insertEdge(i, j, null, "e");
		e = a;
		i = G.insertVertex(null, "k");
		a = G.insertEdge(j, i, null, "f");
		f = a;

		PowerGrid pg = new PowerGrid();
		Set<Edge> edges = pg.kruskal(G);
		System.out.println("Following are the edges in " + "the constructed MST");
		int minCost = 0;

		Iterator<Edge> iterator = edges.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next().getFirstEndpoint() + " -- " + iterator.next().getSecondEndpoint());

			try {
				minCost = +(Integer.parseInt((String) iterator.next().getData()));
			} catch (Exception e2) {

			}

		}

		System.out.println("The minimum cost of spanning tree is " + minCost);
	}

	/**
	 * finds sets of an element
	 */
	private int retieveSet(RetrieveSubsetOfUnion subGroup[], Object i) {

		Integer value = (Integer) i;

		if (subGroup[value.intValue()].root != value.intValue())
			subGroup[value.intValue()].root = retieveSet(subGroup, subGroup[value.intValue()].root);

		return subGroup[value.intValue()].root;
	}

	/**
	 * Helper class for finding subsets
	 */
	private class RetrieveSubsetOfUnion {
		int root;
		int position;
	}
}
