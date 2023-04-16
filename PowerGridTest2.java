import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class PowerGridTest2 {

	@Test
	public void testPowerGrid() {

		SimpleGraph simpleGraph = new SimpleGraph();
		Vertex p, q, r, s, t;
		Edge x, y, z;

		p = simpleGraph.insertVertex(null, "M");
		r = p;
		q = simpleGraph.insertVertex(null, "S");
		s = q;
		x = simpleGraph.insertEdge(p, q, null, "A");
		y = x;
		p = simpleGraph.insertVertex(null, "K");
		t = p;
		x = simpleGraph.insertEdge(q, p, null, "C");
		z = x;

		PowerGrid pg = new PowerGrid();
		pg.kruskal(simpleGraph);

		// asserting data on vertices of power grid
		assertEquals(((Vertex) simpleGraph.vertices().next()).getName(), "M");

	}
}
