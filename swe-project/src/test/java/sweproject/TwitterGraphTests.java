package sweproject;

import org.junit.jupiter.api.Test;
import sweproject.graph.sprint3.TwitterGraph;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TwitterGraphTests {

    public TwitterGraph graph() {
        TwitterGraph g = new TwitterGraph();
        g.addArc("@source", "@Potato", 10);
        g.addArc("@source", "@Tahoe", 69);
        g.addArc("@source", "@Poop", 2);
        return g;
    }

    @Test
    public void testGetNumOfRetweets(){
        assertEquals(graph().getNumOfRetweets("@source", "@Potato"), 10);
    }

    @Test
    public void testGetEdges(){
        assertEquals(graph().getEdges().toString(), "{@source={@Tahoe=69, @Potato=10, @Poop=2}}");
    }

    @Test
    public void testDoesArcExist(){
        assertEquals(graph().doesArcExist("@source", "@Potato"), true);

        assertEquals(graph().doesArcExist("@source", "@PotatoMan"), false);
    }

    @Test
    public void testGetAllUserRetweets(){
        assertEquals(graph().getVertex("@source").toString(), "[@Poop, @Potato, @Tahoe]");

        assertNull(graph().getVertex("@Potato").toString());
    }

    @Test
    public void testInvert(){
        assertEquals(graph().invert().toString(), "{@Tahoe={@source=69}, @Potato={@source=10}, @Poop={@source=2}}");
    }
}
