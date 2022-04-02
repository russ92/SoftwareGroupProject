package sweproject;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        assertEquals(graph().getEdgesAsString(), "@Poop : 2\n" +
                                                        "@Potato : 10\n" +
                                                        "@Tahoe : 69" + "\n");
    }

    @Test
    public void testDoesArcExist(){
        assertEquals(graph().doesArcExist("@source", "@Potato"), true);

        assertEquals(graph().doesArcExist("@source", "@PotatoMan"), false);
    }

    @Test
    public void testGetAllUserRetweets(){
        assertEquals(graph().getAllUsersRetweeted("@source").toString(), "[@Poop, @Potato, @Tahoe]");

        assertEquals(graph().getAllUsersRetweeted("@Potato"), null);
    }

//    @Test
//    public void testInvert(){
//        assertEquals(graph().invert(), "[@Poop, @Potato, @Tahoe]");
//    }
}
