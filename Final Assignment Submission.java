import java.util.ArrayList;
import java.util.Random;
import java.util.*;

class FinalAssignmentSubmission {


    public static void main(String[] args){
        Scanner inputScanner = new Scanner(System.in);
        int requestedStartingNode;
        int soughtAfterValue;
        Random rand  = new Random();
        ArrayList<node> nodes = new ArrayList<node>();
        Graph graph = new Graph(1500);
        for (int i = 0; i<1500; i++) {
            node node = new node();
            node.setNumberNode(i);
            node.setValue(rand.nextInt(100000)+1);
            node.setEdges(rand.nextInt(5)+1);
            node.setTrackEdges(0);
            node.setAttemptTracker(0);
            nodes.add(node);
        }
        for (node currentNode : nodes){

            do {

                int temp = rand.nextInt(1500);
                while (temp == currentNode.getnumberNode()){
                temp = rand.nextInt(1500);
                }

                    if(nodes.get(temp).getTrackEdges()<nodes.get(temp).getEdges()-1) {


                    graph.addEdge(currentNode.getnumberNode(), temp, currentNode, nodes.get(temp));
                    currentNode.setTrackEdges(currentNode.getTrackEdges()+1);
                    nodes.get(temp).setTrackEdges(nodes.get(temp).getTrackEdges() + 1);
                    
                   
                }
                
                currentNode.setAttemptTracker(currentNode.getAttemptTracker() + 1);

            } while((currentNode.getTrackEdges() < currentNode.getEdges()-1) && currentNode.getAttemptTracker() < 150);

        }
        
        //nodes.get(150).setValue(12345);

        System.out.println("Please enter the node you wish to start at as an integer between 1 and 1000");
        requestedStartingNode = inputScanner.nextInt();
        System.out.println("Please enter the value in the graph that you are looking for as an integer between 1 and 100,000");
        soughtAfterValue = inputScanner.nextInt();
        System.out.println(graph.BFS(nodes.get(requestedStartingNode), soughtAfterValue));
        System.out.println(graph.DFS(nodes.get(requestedStartingNode), soughtAfterValue));

        //The following were various test cases.
        //graph.printGraph();
        //graph.addEdge(0, 150, nodes.get(0), nodes.get(150));
        //nodes.get(150).setValue(4);
        //System.out.println(graph.BFS(nodes.get(1), 4));
        //System.out.print(graph.DFS(nodes.get(1), 4));
}
}

class node {
    private int numberNode;
    private int value;
    private int maxEdges;
    private int trackEdges;
    private int attemptTracker;
    private node previous;

    node(){}

    node(int numberNode,int value, int maxEdges) {
        this.numberNode = numberNode;
        this.value = value;
        this.maxEdges =maxEdges;
    }

    public int setNumberNode(int number) {
        this.numberNode = number;
        return this.numberNode;
    }
    public int getnumberNode() {
        return numberNode;
    }
    public void setEdges(int edges) {
        this.maxEdges = edges;
    }
    public int getEdges() {
    return maxEdges;
    }
    public int getTrackEdges(){
        return trackEdges;
    }
    public void setTrackEdges(int value) {
        this.trackEdges = value;
    }
    public int getValue() {
    return value;
    }
    public void setValue(int value) {
        this.value = value;
    }
    public int getAttemptTracker() {
    return attemptTracker;
    }
    public void setAttemptTracker(int value) {
        this.attemptTracker = value;
    }
    public void setPrevious(node node) {
        this.previous  = node;
    }
    public node getPrevious() {
    return previous;
    }
}

class Graph {
    ArrayList<ArrayList<node>> graph;
    int vertices;

    Graph(int nodes) {
        vertices = nodes;
        graph = new ArrayList<ArrayList<node>>();
        for(int i = 0; i<vertices;i++) 
            graph.add(new ArrayList<node>());
    }


    void addEdge(int startingNumberNode, int endingNumberNode, node startingNode, node endingNode) {

        HashSet<node> existingConnection1 = new HashSet<>();
        HashSet<node> existingConnection2 = new HashSet<>();

        for(node x: graph.get(startingNumberNode))
        existingConnection1.add(x);
        for(node y: graph.get(endingNumberNode))
        existingConnection2.add(y);

        if(!existingConnection1.contains(endingNode) && !existingConnection2.contains(startingNode)){
        graph.get(startingNumberNode).add(endingNode);
        graph.get(endingNumberNode).add(startingNode);
        }

    }
    
    void printGraph() {
        for(int i = 0; i<vertices;i++){
            System.out.println("Node " + i + " is connected to nodes ");
            for (node x:graph.get(i))
            System.out.print(x.getnumberNode() + ", ");
            System.out.println();
        }
    }



public String BFS(node startingNode, int end)
{
    HashSet<node> visited = new HashSet<>();
    Queue<node> queue = new LinkedList<>();
    int arrayAssignment = 0;
    int counter = 0;
    int temp = 1;
    int shortestPath = 0;
    node [] previous = new node[vertices];

    queue.add(startingNode);
    //System.out.print("Tested Path: ");

    while (!queue.isEmpty()) {
        node currentNode = queue.poll();
        if(!visited.contains(currentNode)){ 
        previous[arrayAssignment] = currentNode;
        arrayAssignment++;
        //System.out.print(" "  + currentNode.getnumberNode());
        counter++;
        if (currentNode.getValue() == end){
        
        
        node[] path = new node[vertices];
        path[0] = currentNode;
        node tempNode = currentNode;
            while(tempNode != null){
                tempNode = tempNode.getPrevious();
                path[temp] = tempNode;
                temp++;
                shortestPath++;
            }
        
        return "Successful search! There were " + counter + " nodes searched during the breadth-first search and the shortest path includes " + shortestPath + " nodes.";
        }
    
    for(int i = 0; i<graph.get(currentNode.getnumberNode()).size();i++){
        node adjacentNode = graph.get(currentNode.getnumberNode()).get(i);
        if (!visited.contains(adjacentNode)){
            queue.add(graph.get(currentNode.getnumberNode()).get(i));
            graph.get(currentNode.getnumberNode()).get(i).setPrevious(currentNode); 
            }
    }
    visited.add(currentNode);
    }
}
    return "Failed search, the value was not found. There were " + counter + " nodes searched during the breadth-first search.";
}



public String DFS(node startingNode, int end)
{
    HashSet<node> visited = new HashSet<>();
    Stack<node> stack = new Stack<node>();
    int counter = 0;

    stack.push(startingNode);
    //System.out.print("Tested Path: ");

    while (!stack.isEmpty()) {
        node currentNode = stack.pop();
        if(!visited.contains(currentNode)) {
        //System.out.print(" "  + currentNode.getnumberNode());
        counter++;
        if (currentNode.getValue() == end)
        return "Successful search! There were " + counter + " nodes searched during the depth-first search.";


        if(!visited.contains(currentNode)){
            visited.add(currentNode);
            for(node adjacentNode : graph.get(currentNode.getnumberNode())) {
                if (!visited.contains(adjacentNode))
                stack.push(adjacentNode);
            }
        }
    }
    }
    return "Failed search, the value was not found. There were " + counter + " nodes searched during the depth-first search.";
}
    
    }



