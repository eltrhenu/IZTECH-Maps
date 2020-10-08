package presentation;

import business.Node;
import dataAccess.CategoryManager;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
	
    public static void main(String[] args) throws ParserConfigurationException, TransformerException, ParseException {

        CategoryManager manager = new CategoryManager();
        HashMap<Node,ArrayList<Node>> izmap = manager.createMap();
        Menu menu = new Menu(izmap);
        while (true) {
            Menu.MenuStart();
            if (!menu.getAndExecuteMainMenu())
                break;
        }


    }
}
