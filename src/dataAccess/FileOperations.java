package dataAccess;

import business.Node;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class FileOperations {
	/**
	 * To read categories from the file.
	 * @param file File name that wanted to read.
	 * @return Category lines of file.
	 */
    public ArrayList<String> readCategory(String file){
        ArrayList<String> tempArray = new ArrayList<String>();
        String temp;
        try {
            BufferedReader fileCategory = new BufferedReader(new FileReader(file));
            while ( !(temp = fileCategory.readLine()).equals("") ){

                    tempArray.add(temp);


            }
            tempArray.remove(0);
            fileCategory.close();
            return tempArray;

        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
    /**
     * To read directions from file.
     * @param file Filename to be read.
     * @return List of lines that contains directions.
     */
    public ArrayList<String> readDirections(String file){
        ArrayList<String> tempArray = new ArrayList<String>();
        String temp;
        try {
            int i =0;
            BufferedReader directionFile = new BufferedReader(new FileReader(file));
            while ( (temp = directionFile.readLine()) != null){
                i++;
                if(i>18 && i<35){
                    tempArray.add(temp);
                }

            }
            directionFile.close();

            return tempArray;

        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
    /**
     * At the end ,to write file by reading hash map.
     * @param izmap The hashmap to be searched for the shortest path.
     */
    public void writeFile(HashMap<Node,ArrayList<Node>> izmap){
        Node[] tempKey = new Node[izmap.keySet().size()];
        tempKey = izmap.keySet().toArray(tempKey);
        //Collection<ArrayList<Node>> values = izmap.values();
        String startNode = "# Node (Location) definitions";
        String startDirection = "# Edges From/To <--> To/From\r\n" +
                "# Remember that every arrow (<-->) is bi-directional.";
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter("iztech.izmap"));
            //writin nodes
            writer.write(startNode);
            for(int i = 0; i<tempKey.length;i++) {
                Node tempNode = tempKey[i];
                String node = tempNode.getId() + " [" + tempNode.getCategory() + ", " + tempNode.getType() + ", " + tempNode.getLocation() + "]\r\n";
                writer.write(node);
            }
            writer.write("\r\n");
            writer.write("\r\n");
            writer.write(startDirection);
            ArrayList<String> writedDirection = new ArrayList<>();
            for(int j = 0; j<tempKey.length;j++){
                for(Node neighbor : izmap.get(tempKey[j])){
                    String direcion = tempKey[j].getId() +" <--> " +neighbor.getId()+"\r\n";
                    String checkDirecion = neighbor.getId() +" <--> " +tempKey[j].getId()+"\r\n";
                    if(!writedDirection.contains(direcion)){
                        writer.write(direcion);
                        writedDirection.add(direcion);
                        writedDirection.add(checkDirecion);
                    }


                }
            }
            writer.close();


        } catch (IOException e) {
            e.printStackTrace();
        }


    }





}
