package business;

import java.util.ArrayList;
/**Building class tha inherits Node
 */
public class Building extends Node{

	/**
	 * Constructor of Building
	 * @param id unique id
	 * @param type type of building
	 * @param location name of location
	 */
    public Building(String id, CategoryType type, String location) {
        super(id,"Building",type,location);
    }

}
