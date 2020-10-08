package business;

import dataAccess.CategoryManager;
import sun.awt.geom.AreaOp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import static java.util.Collection.*;
import static java.util.Collections.reverse;
import static java.util.Collections.sort;


public class Executions {

    /**
     * The shortest path between two nodes in a graph.
     */
    private static ArrayList<Node> shortestPath = new ArrayList<>();

    /**
     * Finds the shortest path between two nodes (source and destination) in a graph.
     *
     * @param izmap       The hashmap to be searched for the shortest path.
     * @param from        The source node of the graph specified by user.
     * @param to The destination node of the graph specified by user.
     * @return the shortest path stored as a list of nodes.
     * or null if a path is not found.
     * Requires: source != null, destination != null and must have a name (e.g.
     * cannot be an empty string).
     */
    public  ArrayList<Node> breadthFirstSearch(HashMap<Node, ArrayList<Node>> izmap, String from,
                                                         String to) {
        //id den source ve destinationu category de bi method yaz ondan sonra source ve destination adında iki category oluştur

        shortestPath.clear();
        Node source = findCategory(izmap,from);
        Node destination = findCategory(izmap,to);
        // A list that stores the path.
        ArrayList<Node> path = new ArrayList<>();

        // If the source is the same as destination, I'm done.
        if (source.equals(destination) && izmap.containsKey(source)) {
            path.add(source);
            return path;
        }

        // A queue to store the visited nodes.
        ArrayList<Node> queue = new ArrayList<>();

        // A queue to store the visited nodes.
        ArrayList<Node> visited = new ArrayList<>();

        queue.add(source);
        while (!queue.isEmpty()) {
            Node vertex = queue.remove(0);//remove first index
            visited.add(vertex);

            ArrayList<Node> neighboursList = izmap.get(vertex);
            for (Node neighbor : neighboursList) {
                path.add(neighbor);
                path.add(vertex);
                if (neighbor.equals(destination)) {
                    return processPath(source, destination, path);
                } else {
                    if (!visited.contains(neighbor)) {
                        queue.add(neighbor);
                    }
                }
            }


        }
        return null;
    }

    /**
     * Adds the nodes involved in the shortest path.
     *
     * @param src         The source node.
     * @param destination The destination node.
     * @param path        The path that has nodes and their neighbours.
     * @return The shortest path.
     */
    private ArrayList<Node> processPath(Node src, Node destination,
                                                   ArrayList<Node> path) {

        // Finds out where the destination node directly comes from.
        int index = path.indexOf(destination);
        Node source = path.get(index + 1);

        // Adds the destination node to the shortestPath.
        shortestPath.add(0, destination);

        if (source.equals(src)) {
            // The original source node is found.
            shortestPath.add(0, src);
            return  shortestPath;
        } else {
            // We find where the source node of the destination node
            // comes from.
            // We then set the source node to be the destination node.
            return processPath(src, source, path);
        }

    }
    //Finding node by id
    private Node findCategory(HashMap<Node,ArrayList<Node>> izmap,String id){
        Node[] tempKey = new Node[izmap.keySet().size()];
        tempKey = izmap.keySet().toArray(tempKey);
        for(int i = 0; i<tempKey.length;i++){
            Node tempCat = tempKey[i];
            if(tempCat.getId().equals(id)){
                return tempCat;
            }
        }
        return null;

    }
    /**
     * Possible reachable locations from a given location and distance. If a location is reachable
     * location�s distance is less or equal to the given distance you print all the possible reachable
     * locations
     * @param izmap HashMap that contain nodes as a key and node list as neighbor
     * @param id the source location given by user for starting point
     * @param sourceDistance the check point to look for equal or less than calculated distance
     * @return Nodes that provide conditions
     */
    public  ArrayList<Node> findPossibleLocations(HashMap<Node,ArrayList<Node>> izmap,String id ,double sourceDistance) {

        //Category sourceLocation = findCategory(izmap,id);
        Node[] tempKey = new Node[izmap.keySet().size()];
        tempKey = izmap.keySet().toArray(tempKey);
        ArrayList<Node> allLocations = new ArrayList<>();
        double distance = 0.0;
        for(int i = 0; i<tempKey.length;i++){
            ArrayList<Node> tempPath = breadthFirstSearch(izmap,id,tempKey[i].getId());
            distance = findDistance(tempPath);

            if(distance <= sourceDistance){
                System.out.println(distance);
                allLocations = addLocations(allLocations,tempPath);
            }

        }
        return allLocations;

    }
    //Add Locations if that not exist in given list
    private ArrayList<Node> addLocations(ArrayList<Node> locations,ArrayList<Node> path){
        Iterator<Node> itr = path.iterator();
        while(itr.hasNext()){
            Node next = itr.next();
            if(!(locations.contains(next))){
                locations.add(next);
            }
        }
        return locations;
    }
    //Find distance from one location to other location
    private double findDistance(ArrayList<Node> path){
        Iterator<Node> itr = path.iterator();
        double distance = 0.0;
        CalculateDistance calDistance = new CalculateDistance();
        if(path.size()<2){
            return distance;
        }
        Node from = null;
        Node to = null;
        if(itr.hasNext()){
            from =  itr.next();}
        if(itr.hasNext()){
             to = itr.next();
        }
        distance += calDistance.calculateDistance(from,to);
        while(itr.hasNext()){
               from = to;
               to = itr.next();
               distance += calDistance.calculateDistance(from,to);
        }
        return distance;
    }
    /**
     * Remove node 
     * @param izmap HashMap that contain nodes as a key and node list as neighbor
     * @param id the node id to remove
     */
    public void remove(HashMap<Node,ArrayList<Node>> izmap,String id){
        Node source = findCategory(izmap,id);
        removeNodeFromNeighbors(izmap,source);
        if(izmap.containsKey(source)){
            izmap.remove(source);
        }
        
    }
    //To remove required node all the neighbors
    private void removeNodeFromNeighbors(HashMap<Node,ArrayList<Node>> izmap, Node removeNode){
        ArrayList<Node> neighbors = izmap.get(removeNode);
        for(Node tempNode: neighbors){
            if(izmap.containsKey(tempNode)) {
                ArrayList<Node> tempNeighbor = izmap.get(tempNode);
                // izmap.remove(tempNode);
                if((tempNeighbor.contains(removeNode))){
                    tempNeighbor.remove(removeNode);

                }
                //izmap.put(tempNode,tempNeighbor);
            }
        }

    }
    /**
     * Add node
     * @param izmap HashMap that contain nodes as a key and node list as neighbor
     * @param nodeInfo nodes info to create an node abject
     * @param nodeNeighbors directions that new node goes 
     */
    public void add(HashMap<Node,ArrayList<Node>> izmap,ArrayList<String> nodeInfo,ArrayList<String> nodeNeighbors){
        CategoryManager manager = new CategoryManager();
        ArrayList<Node> nodes = getNodes(izmap);
        Node newNode = null;
        //Check category to create that object
        if(nodeInfo.get(1).equalsIgnoreCase("building")){
            CategoryType type = manager.determineType(" "+ nodeInfo.get(2));
            String id = nodeInfo.get(0);

            newNode = new Building(id,type,nodeInfo.get(3));}
        //Check category to create that object
        if(nodeInfo.get(1).equalsIgnoreCase("landscape")){
            String id = nodeInfo.get(0);

            CategoryType type = manager.determineType(nodeInfo.get(2));
            newNode = new Landscape(id,type,nodeInfo.get(3));

        }
        ArrayList<Node> neighbors = manager.findNeighbor(newNode,nodeNeighbors,nodes);//find neighbor by used given nodes
        addNeighborsNewNode(izmap,newNode,neighbors);
        izmap.put(newNode,neighbors);// add that node and neighbor to hashmap
       
    }
    //Get nodes by using HashMap
    private ArrayList<Node> getNodes(HashMap<Node,ArrayList<Node>> izmap){
        Node[] tempKey = new Node[izmap.keySet().size()];
        ArrayList<Node> nodes = new ArrayList<>();
        tempKey = izmap.keySet().toArray(tempKey);
        for(int i = 0; i<tempKey.length;i++){
            nodes.add(tempKey[i]);
        }
        return nodes;

    }
    //Add new node to its neighbors
    private HashMap<Node,ArrayList<Node>>  addNeighborsNewNode(HashMap<Node,ArrayList<Node>> izmap,Node node,ArrayList<Node> neighbors){
        for(Node tempNode: neighbors){
            if(izmap.containsKey(tempNode)) {
                ArrayList<Node> tempNeighbor = izmap.get(tempNode);
               
                if(!(tempNeighbor.contains(node))){
                    tempNeighbor.add(node);

                }
                
            }
        }
        return izmap;
    }
    /**
     * To get neighbors of a node
     * @param izmap HashMap that contain nodes as a key and node list as neighbor
     * @param id the node id to get neighbors
     * @return nodes that are neighbors
     */
    public  ArrayList<Node> getNeighbors(HashMap<Node,ArrayList<Node>> izmap,String id) {
        Node source = findCategory(izmap,id);
        ArrayList<Node> neighbors = izmap.get(source);

        sort(neighbors);
        reverse(neighbors);

        return neighbors;
    }
}