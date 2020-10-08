package presentation;

import business.Executions;
import business.Node;
import dataAccess.CategoryManager;
import dataAccess.FileOperations;

import java.util.ArrayList;
import java.util.HashMap;

public class MenuExe {
    private HashMap<Node,ArrayList<Node>> izmap;
    private Executions executions = new Executions() ;
    
    public MenuExe(HashMap<Node, ArrayList<Node>> izmap) {
        this.izmap = izmap;
    }
    /**
     * Finds the shortest path between two nodes (source and destination) in a graph.
     * @param from The source node of the graph specified by user.
     * @param to The source node of the graph specified by user.
     * @return The nodes of shortest path as string.
     */
    public String findShortestPath(String from, String to){
        return executions.breadthFirstSearch(this.izmap,from,to).toString();
    }
    /**
     * To add a new node.
     * @param inputs The new nodes infos.
     * @return Returns message of succession.
     */
    public String addLocation(String[] inputs){
        ArrayList<String> nodeInfo = new ArrayList<String>();
        ArrayList<String> nodeNeighbors = new ArrayList<String>();
        for(int i=0;i<inputs.length;i++){
            if(i<4){
                nodeInfo.add(inputs[i]);
            }

            else{
                nodeNeighbors.add((inputs[i]));
            }
        }
         executions.add(this.izmap,nodeInfo,nodeNeighbors);

        return ("Addition succeed!");
    }
    /**
     * To remove a node.
     * @param id id that will be removed.
     * @return Returns message of succession.
     */
    public String removeNode(String id){
        executions.remove(this.izmap,id);
        return ("Location had been removed.");
    }

    /**
     * To find possible locations.
     * @param id the source location given by user for starting point
     * @param distance the check point to look for equal or less than calculated distance
     * @return Locations as string.
     */
    public String possibleLocs(String id,String distance){
        double dist = Double.parseDouble(distance);
        return executions.findPossibleLocations(this.izmap,id,dist).toString();
    }
    
    /**
     * To find neighbor nodes.
     * @param id Node that wanted to find neighbors.
     * @return Neighbors of node as string.
     */
    public String getNeighbors(String id){
        return executions.getNeighbors(this.izmap,id).toString();
    }

    /**
     * To write file.
     */
    public void writeFile(){
        FileOperations fileOps = new FileOperations();
        fileOps.writeFile(this.izmap);
    }
}
