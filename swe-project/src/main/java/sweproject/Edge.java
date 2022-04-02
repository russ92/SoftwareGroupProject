package sweproject;

public class Edge{
    String source,destination;
    int weight;

    // Constructor
    Edge(String sourceNode, String destinationNode, int edgeWeight){
        this.source = sourceNode;
        this.destination = destinationNode;
        this.weight = edgeWeight;
    }
    // Methods
    public void updateSource(String sourceUpdate){
        this.source = sourceUpdate;
    }

    public void updateDestination(String destinationUpdate){
        this.destination = destinationUpdate;
    }

    public void updateWeight(int weightUpdate){
        this.weight = weightUpdate;
    }
}
