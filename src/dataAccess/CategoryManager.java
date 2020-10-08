package dataAccess;

import business.Building;
import business.Node;
import business.CategoryType;
import business.Landscape;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class CategoryManager {
	/**
	 * To read file and create hash map.
	 * @return hash map created.
	 */
    public HashMap<Node,ArrayList<Node>> createMap(){
        FileOperations fileoperation = new FileOperations();
        ArrayList<Node> created = new ArrayList<>();
        ArrayList<String> category = fileoperation.readCategory("iztech.izmap");
        ArrayList<String> direction = fileoperation.readDirections("iztech.izmap");


        for( String line : category){
            ArrayList<String> temp = new ArrayList<String>();
            StringTokenizer token = new StringTokenizer(line,"[,]");
            while(token.hasMoreTokens()){
                temp.add(token.nextToken());
            }
            if(temp.get(1).equalsIgnoreCase("building")){
                CategoryType type = determineType(temp.get(2));
                String id = temp.get(0);
                if(id.length()>2){
                    id = id.substring(0,2);
                }
                else{
                    id = id.substring(0,1);
                }
                Node building = new Building(id,type,temp.get(3));
                created.add(building);
            }
            if(temp.get(1).equalsIgnoreCase("landscape")){
                String id = temp.get(0);
                if(id.length()>2){
                    id = id.substring(0,2);
                }
                else{
                    id = id.substring(0,1);
                }
                CategoryType type = determineType(temp.get(2));
                Node landscape = new Landscape(id,type,temp.get(3));
                created.add(landscape);
            }

        }
        return createMap(created,direction);
    }
    private HashMap<Node,ArrayList<Node>> createMap(ArrayList<Node> categories, ArrayList<String> direction){
        HashMap<Node,ArrayList<Node>> izMap = new HashMap<>();
        for(Node category : categories){
            ArrayList<Node> neighbor = findNeighbor(category,direction,categories);
            izMap.put(category,neighbor);

        }
        return izMap;
    }
    /**
     * To find neighbors of a node.
     * @param node the node that wanted to be find neighbors.
     * @param direction list of neighbors as string.
     * @param categories list of nodes.
     * @return list of neighbors.
     */
    public ArrayList<Node> findNeighbor(Node node,ArrayList<String> direction,ArrayList<Node> categories){
        ArrayList<Node> neighbors = new ArrayList<Node>();
        String id = node.getId();
        //System.out.println(direction.get(15));
        for(String line : direction){
            //System.out.println(line);

            String[] splitLine = line.split(" ");
            //System.out.println("l");
            if(splitLine[0].equals(id)){
                for(Node category1:categories){
                    if(category1.getId().equals(splitLine[2]) && !(neighbors.contains(category1))){
                        neighbors.add(category1);

                    }
                }
            }
            if(splitLine[2].equals(id)){
                for(Node cat:categories){
                    if(cat.getId().equals(splitLine[0]) && !(neighbors.contains(cat))){
                        neighbors.add(cat);

                    }
                }
            }
        }
        return neighbors;
    }
    /**
     * To determine type.
     * @param type type of Building or Landscape.
     * @return type as CategoryType
     */
    public CategoryType determineType(String type){
        if(type.equals(" Department")){
            return CategoryType.Department;
        }
        else if(type.equals(" Historical Ruin")){
            return CategoryType.HistoricalRuin;
        }
        else if(type.equals(" Cafeteria")){
            return CategoryType.Cafeteria;
        }
        else if(type.equals(" Administrative")){
            return CategoryType.Administrative;
        }
        else if(type.equals(" Facility")){
            return CategoryType.Facility;
        }
        else if(type.equals(" Waterfall")){
            return CategoryType.WaterFall;
        }
        else if(type.equals(" Beach")){
            return CategoryType.Beach;
        }
        else{
            throw new IllegalArgumentException("Invalid Category Type.");
        }
    }


}
