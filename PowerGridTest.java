import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class PowerGridTest {

	@Test
	public void testPowerGrid() {

		SimpleGraph simpleGraph = new SimpleGraph();
		Vertex m, s, g, h, k;
		Edge a, b, c;

		// the edges store numbers
		m = simpleGraph.insertVertex(null, 0);
		g = m;
		s = simpleGraph.insertVertex(null, 10);
		h = s;
		a = simpleGraph.insertEdge(m, s, null, 20);
		b = a;
		m = simpleGraph.insertVertex(null, 40);
		k = m;
		a = simpleGraph.insertEdge(s, m, null, 30);
		c = a;

		PowerGrid pg = new PowerGrid();
		pg.kruskal(simpleGraph);
		simpleGraph.opposite(g, b).setData(30);
		simpleGraph.opposite(h, b).setData(40);
		simpleGraph.opposite(h, c).setData(60);

		assertEquals(simpleGraph.opposite(h, c).getData(), 60);
		assertEquals(simpleGraph.opposite(h, b).getData(), 40);
		assertEquals(simpleGraph.opposite(g, b).getData(), 30);

	}
}
