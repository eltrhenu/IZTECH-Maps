package business;


public class Landscape extends Node {
	/**
	 * Constructor of Landscape
	 * @param id unique id
	 * @param type type of Landscape
	 * @param location name of location
	 */

    public Landscape(String id, CategoryType type, String location) {
        super(id,"Landscape",type,location);
    }
}
