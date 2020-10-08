package presentation;

import business.Executions;
import business.Node;
import dataAccess.CategoryManager;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.text.ParseException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Menu {
    private HashMap<Node,ArrayList<Node>> izmap;
    public Menu(HashMap<Node,ArrayList<Node>> izmap){
        this.izmap = izmap;
    }

    private static Scanner in = new Scanner(System.in);
    

    /**
     * to display operation menu
     */
    public static void MenuStart() {
        System.out.println();
        System.out.println("[1] Find the shortest path between given two locations. ");
        System.out.println("[2] Add locations.");
        System.out.println("[3] Remove locations .");
        System.out.println("[4] Possible reachable locations from a given location and distance.");
        System.out.println("[5] Print the neighbor locations by location name.");
        System.out.println("[6] Quit and save updated file.");
        System.out.println();
        System.out.print(">> Please select a command to execute: ");
    }

    /**
     * To execute menu
     *
     * @return true if operation continue, false if not
     * @throws ParseException
     * @throws TransformerException
     * @throws ParserConfigurationException
     */
    public boolean getAndExecuteMainMenu() throws ParseException, TransformerException, ParserConfigurationException {
        presentation.MenuExe menu = new presentation.MenuExe(this.izmap);
        int cmdId = in.nextInt();
        in.nextLine();
        switch (cmdId) {
            case 1:
                System.out.println();
                System.out.println("1 [Building, Department, Computer Engineering]\n" +
                        "2 [Landscape, Historical Ruin, Roman Bath]\n" +
                        "3 [Building, Facility, Gym]\n" +
                        "4 [Building, Facility, Pool]\n" +
                        "5 [Building, Department, Mechanical Engineering]\n" +
                        "6 [Building, Department, Food Engineering]\n" +
                        "7 [Landscape, Waterfall, The IZTECH Waterfall I]\n" +
                        "8 [Landscape, Waterfall, The IZTECH Waterfall II]\n" +
                        "9 [Building, Department, Molecular Biology and Genetics]\n" +
                        "10 [Building, Administrative, Student Affairs]\n" +
                        "11 [Building, Administrative, Rectorate Building]\n" +
                        "12 [Landscape, Beach, Gulbahce Beach]\n" +
                        "13 [Building, Cafeteria, IZTECH Cafeteria]\n" +
                        "14 [Building, Cafeteria, IZTECH Usta]\n");
                System.out.println();
                //code only accept - not any other characters!!
                System.out.print("Please enter two locations ids by the given chart. In from-to format.(EX: 1-2) ");

                boolean check = false;
                while (!check) {
                    String[] inputs = in.nextLine().split("-");
                    String from = inputs[0];
                    String to = inputs[1];
                    if (isInteger(from)&& isInteger(to)) {
                        System.out.println(menu.findShortestPath(from,to));
                        check = true;
                    } else {
                        System.out.println();
                        System.out.print("Please enter valid ids");
                        check = false;
                    }
                }
                return true;
            case 2:
                System.out.println();
                System.out.print("Please enter location info.(Format must be: id,Building/Landscape,Category Type,Location Name and New location id <--> neighbor1,New location id <--> neighbor2....");
                System.out.println("Ex: 15,Building,Cafeteria,Rien,15 <--> 13,15 <--> 7,15 <--> 4");


                String[] inputs = in.nextLine().split(",");
                menu.addLocation(inputs);
                return true;
            case 3:
                System.out.println();
                System.out.println("1 [Building, Department, Computer Engineering]\n" +
                        "2 [Landscape, Historical Ruin, Roman Bath]\n" +
                        "3 [Building, Facility, Gym]\n" +
                        "4 [Building, Facility, Pool]\n" +
                        "5 [Building, Department, Mechanical Engineering]\n" +
                        "6 [Building, Department, Food Engineering]\n" +
                        "7 [Landscape, Waterfall, The IZTECH Waterfall I]\n" +
                        "8 [Landscape, Waterfall, The IZTECH Waterfall II]\n" +
                        "9 [Building, Department, Molecular Biology and Genetics]\n" +
                        "10 [Building, Administrative, Student Affairs]\n" +
                        "11 [Building, Administrative, Rectorate Building]\n" +
                        "12 [Landscape, Beach, Gulbahce Beach]\n" +
                        "13 [Building, Cafeteria, IZTECH Cafeteria]\n" +
                        "14 [Building, Cafeteria, IZTECH Usta]\n");
                System.out.println();
                System.out.print("Please enter location id by the given chart or id that had been added before.");

                check = false;
                while (!check) {
                    String input = in.nextLine();
                    if (isInteger(input)) {
                        System.out.println(menu.removeNode(input));
                        check = true;
                    } else {
                        System.out.println();
                        System.out.print("Please enter valid id");
                        check = false;
                    }
                }
                return true;
            case 4:
                System.out.println();
                System.out.println("1 [Building, Department, Computer Engineering]\n" +
                        "2 [Landscape, Historical Ruin, Roman Bath]\n" +
                        "3 [Building, Facility, Gym]\n" +
                        "4 [Building, Facility, Pool]\n" +
                        "5 [Building, Department, Mechanical Engineering]\n" +
                        "6 [Building, Department, Food Engineering]\n" +
                        "7 [Landscape, Waterfall, The IZTECH Waterfall I]\n" +
                        "8 [Landscape, Waterfall, The IZTECH Waterfall II]\n" +
                        "9 [Building, Department, Molecular Biology and Genetics]\n" +
                        "10 [Building, Administrative, Student Affairs]\n" +
                        "11 [Building, Administrative, Rectorate Building]\n" +
                        "12 [Landscape, Beach, Gulbahce Beach]\n" +
                        "13 [Building, Cafeteria, IZTECH Cafeteria]\n" +
                        "14 [Building, Cafeteria, IZTECH Usta]\n");
                System.out.println();
                System.out.print("Please enter location id by the given chart or id that had been added before,distance. (EX: 1,15)");

                check = false;
                while (!check) {
                    String[] input = in.nextLine().split(",");

                    if ((isInteger(input[0]))&& (isInteger(input[1]))) {
                        System.out.println(menu.possibleLocs(input[0],input[1]));
                        check = true;
                    } else {
                        System.out.println();
                        System.out.print("Please enter valid input given format.");
                        check = false;
                    }
                }
                return true;

            case 5:
                System.out.println();
                System.out.println("1 [Building, Department, Computer Engineering]\n" +
                        "2 [Landscape, Historical Ruin, Roman Bath]\n" +
                        "3 [Building, Facility, Gym]\n" +
                        "4 [Building, Facility, Pool]\n" +
                        "5 [Building, Department, Mechanical Engineering]\n" +
                        "6 [Building, Department, Food Engineering]\n" +
                        "7 [Landscape, Waterfall, The IZTECH Waterfall I]\n" +
                        "8 [Landscape, Waterfall, The IZTECH Waterfall II]\n" +
                        "9 [Building, Department, Molecular Biology and Genetics]\n" +
                        "10 [Building, Administrative, Student Affairs]\n" +
                        "11 [Building, Administrative, Rectorate Building]\n" +
                        "12 [Landscape, Beach, Gulbahce Beach]\n" +
                        "13 [Building, Cafeteria, IZTECH Cafeteria]\n" +
                        "14 [Building, Cafeteria, IZTECH Usta]\n");
                System.out.println();
                System.out.print("Please enter location id that you want to learn neighbors of location by the given chart" );

                check = false;
                while (!check) {
                    String input = in.nextLine();

                    if ((isInteger(input))) {
                        System.out.println(menu.getNeighbors(input));
                        check = true;
                    } else {
                        System.out.println();
                        System.out.print("Please enter a valid id.");
                        check = false;
                    }
                }

                return true;

            case 6:
                menu.writeFile();
                return false;
            default:
                System.err.println();
                System.err.println("I do not know what to do for command id " + cmdId);
                System.err.println("Please try again!");
                System.err.println();
                return true;
        }
        }

    private  boolean isInteger(String str) {
        if (str == null) {
            return false;
        }
        if (str.isEmpty()) {
            return false;
        }
        if(str.charAt(0)=='-'){
            return false;

        }
        for (int i =0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }

        }
        return true;
    }

}