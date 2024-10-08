import java.lang.reflect.Array;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface{

    public void gameplayLoop(Adventure adventure){
        boolean exit = false;
        Scanner input = new Scanner(System.in);

        while(!exit){
            System.out.println("Please enter a command (help for a list of commands)");

            //the take and drop commands have their own switch statement that only looks at the first word. this
            //becomes true if one of them is entered:
            boolean firstWordChoiceMade = false;

            String choice = input.next() + input.nextLine();
            choice = choice.toUpperCase();
            String[] splitChoice = choice.split(" ");

            switch (splitChoice[0]){
                case "TAKE":
                    firstWordChoiceMade = true;
                    take(adventure, splitChoice);
                    break;
                case "DROP":
                    firstWordChoiceMade = true;
                    drop(adventure, splitChoice);
                    break;
                case "EAT":
                    firstWordChoiceMade = true;
                    eat(adventure, splitChoice, input);
                    break;
                case "EQUIP":
                    firstWordChoiceMade = true;
                    equip(adventure, splitChoice);
                    break;
                case "ATTACK":
                    firstWordChoiceMade = true;
                    attack(adventure, splitChoice);
                    break;

            }

            switch(choice){
                case "GO NORTH", "NORTH", "GO N", "N":
                    goNorth(adventure);
                    break;
                case "GO SOUTH", "SOUTH", "GO S", "S":
                    goSouth(adventure);
                    break;
                case "GO WEST", "WEST", "GO W", "W":
                    goWest(adventure);
                    break;
                case "GO EAST", "EAST", "GO E", "E":
                    goEast(adventure);
                    break;
                case "LOOK":
                    look(adventure);
                    break;
                case "HELP":
                    help();
                    break;
                case "HEALTH":
                    health(adventure);
                    break;
                case "INVENTORY", "INVENT", "I", "BAG":
                    displayInventory(adventure);
                    break;
                case "UNLOCK":
                    unlock(adventure);
                    break;
                case "TURN ON LIGHT":
                    turnOnLight(adventure);
                    break;
                case "TURN OFF LIGHT":
                    turnOffLight(adventure);
                    break;
                case "XYZZY":
                    teleport(adventure);
                    break;
                case "EXIT":
                    exit = true;
                    break;
                default:
                    if(!firstWordChoiceMade){
                        System.out.println("Please enter a valid command.");
                    }


            }
        }
    }

    private void goNorth(Adventure adventure){
        String outcome = adventure.goNorth();
        entryLook(adventure,outcome);
    }

    private void goSouth(Adventure adventure){
        String outcome = adventure.goSouth();
        entryLook(adventure,outcome);
    }

    private void goWest(Adventure adventure){
        String outcome = adventure.goWest();
        entryLook(adventure,outcome);
    }

    private void goEast(Adventure adventure){
        String outcome = adventure.goEast();
        entryLook(adventure,outcome);
    }

    private void look(Adventure adventure){
        System.out.println("Lets take a look around");
        String roomName = adventure.getCurrentRoom().getName();
        String roomDescription;
        if(adventure.getCurrentRoom().getLitUp()){
            roomDescription = adventure.getCurrentRoom().getDescription();
        }else{
            roomDescription = adventure.getCurrentRoom().getDarkDescription();
        }

        System.out.println("ROOM NAME: " + roomName);
        System.out.println(roomDescription);
        if(adventure.getCurrentRoom().allDirectionsTried()){
            printDoorways(adventure);
        }

        if(adventure.getCurrentRoom().getLitUp()){
            System.out.println("On the floor of this room there are the following items:");
            if(adventure.getCurrentRoom().getAllItems().size() == 0){
                System.out.println("No items.");
            }else{
                for(Item item : adventure.getCurrentRoom().getAllItems()){
                    System.out.println(item.getName());
                }
            }
        }
    }

    private void entryLook(Adventure adventure, String outcome){

        //TODO: Maybe make the magic strings here into enum thingies if thats better suited for this
        if(outcome.equals("SUCCESS")){
            if(adventure.getCurrentRoom().isVisited()){
                System.out.println("You've been here before.");
                String shortLook = adventure.getCurrentRoom().getShortDescription();
                System.out.println("This is " + shortLook);
            }else {
                System.out.println("You find your way into a new room!");
                look(adventure);
            }
            adventure.getCurrentRoom().visit();
        }else if(outcome.equals("LOCKED")){

            System.out.println("The door in this direction is locked. try unlocking it");

        } else if (outcome.equals("WALL")) {

            System.out.println("there isnt a passageway in that direction");

        }else if(outcome.equals("DARK")){
            System.out.println("This room is dark. you can only find your way back to the door you came in!");
        }

    }

    //most of this method should probably be in the adventure class
    private void printDoorways(Adventure adventure){
        String result = "There are doorways to the ";
        ArrayList<String> directions = new ArrayList<String>();
        if(adventure.getCurrentRoom().getNorth() != null){
            directions.add("North");
        }
        if(adventure.getCurrentRoom().getSouth() != null){
            directions.add("South");
        }
        if(adventure.getCurrentRoom().getWest() != null){
            directions.add("West");
        }
        if(adventure.getCurrentRoom().getEast() != null){
            directions.add("East");
        }
        if(directions.size() == 0){
            result = "There are no doorways in this room!";
        }else{
            for(int i = 0; i < directions.size(); i++){
                if(i == directions.size()-1){
                    result = result + "and " + directions.get(i);
                }else {
                    result = result + directions.get(i) + ", ";
                }
            }
        }

        System.out.println(result);

    }

    private void help(){
        System.out.println("------LIST OF COMMANDS:------");
        System.out.println("GO NORTH: attempt to move North");
        System.out.println("GO SOUTH: attempt to move South");
        System.out.println("GO WEST: attempt to move west");
        System.out.println("GO EAST: attempt to move east");
        System.out.println("LOOK: look around at the room you are in");
        System.out.println("HELP: print a list of commands");
        System.out.println("UNLOCK: unlock all doors facing this room");
        System.out.println("TAKE: write this and the name of the item you wish to pick up to do so");
        System.out.println("DROP: write this and the name of the item you wish to drop from your bag to do so");
        System.out.println("TURN ON LIGHT: light up the room with a torch");
        System.out.println("TURN OFF LIGHT: extinguish the torches in this room and darken it");
        System.out.println("XYZZY: A magic word that lets you teleport to the place you last teleported from");
        System.out.println("EXIT: quit the program");
    }

    private void unlock(Adventure adventure){
        int doorsUnlocked = adventure.unlock();
        if(doorsUnlocked > 0){
            System.out.println("You just unlocked " + doorsUnlocked + " doors.");
        }else{
            System.out.println("There are no locked doors in this room");
        }

    }

    //--------------light up functions-----------------
    private void turnOnLight(Adventure adventure){
        adventure.lightUp();
        System.out.println("You light a torch and place it in a socket in the wall. You can now see the room clearly.");
    }

    private void turnOffLight(Adventure adventure){
        adventure.darken();
        System.out.println("You extinguish the torch in this room and it is plunged into darkness. You cannot see much now.");
    }

    private void teleport(Adventure adventure){
        adventure.teleport();
        System.out.println("With a flash of light you teleport away to the last place you teleported from:");
        String shortLook = adventure.getCurrentRoom().getShortDescription();
        System.out.println(shortLook);

    }

    //--------------------item functions-------------------------------
    private void displayInventory(Adventure adventure){
        ArrayList<Item> invent = adventure.getInventory();
        System.out.println("INVENTORY CONTENTS:");
        for (Item item : invent){
            System.out.println(item.getName());
        }
    }

    private void take(Adventure adventure, String[] splitChoice){

        //reconstructing the item name
        String itemName = reconstructSelection(splitChoice);


        //checking if such an item is on the floor:
        boolean itemIsThere = false;
        int index = 0;
        int counter = 0;
        for(Item i: adventure.getCurrentRoom().getAllItems()){
            if(i.getName().toUpperCase().equals(itemName)){
                itemIsThere = true;
                index = counter;
            }
            counter++;
        }

        //actuallu taking the item:
        if(itemName.isEmpty()){
            System.out.println("Please also enter the name of the item you wish to pickup");
        }else if(!itemIsThere){
            System.out.println("Could not find that item in this room");
        }else {
            adventure.takeItem(index);
            System.out.println("You pick up the " + itemName + " and place it in your bag.");
        }
    }

    private void drop(Adventure adventure, String[] splitChoice){

        //making a String that contains only the item name and not the command:
        String itemName = reconstructSelection(splitChoice);

        boolean itemIsThere = false;
        int index = -1;
        //checking if the item is in the inventory:
        for(int i = 0; i < adventure.getInventory().size(); i++){
            if(adventure.getInventory().get(i).getName().toUpperCase().equals(itemName)){
                itemIsThere = true;
                index = i;
            }
        }

        //dropping the item:
        if(itemName.isEmpty()){
            System.out.println("Please also enter the name of the item you wish to drop");
        } else if (!itemIsThere) {
            System.out.println("That item is not in your inventory");

        }else {
            System.out.println("You drop the " + itemName + " on the floor");
            adventure.dropItem(index);
        }

    }

    //This is probably a hack and could be replaced by some substring usage
    private String reconstructSelection(String[] splitChoice){
        String itemName = "";
        for(int i=1; i<splitChoice.length; i++){
            if(i == splitChoice.length -1){
                itemName = itemName + splitChoice[i];
            }else {
                itemName = itemName + splitChoice[i] + " ";
            }

        }

        return itemName;
    }

    //--------------Health and food:-------------------

    private void health(Adventure adventure){
        int health = adventure.getHealth();
        System.out.println("You currently have " + health + " health points.");
    }

    //lets eat the food on the floor first
    private void eat(Adventure adventure, String[] splitChoice, Scanner input){
        String itemName = reconstructSelection(splitChoice);
        EatOutcomes outcome = adventure.eat(itemName, false);

        switch (outcome){
            case EAT_INVENTORY:
                System.out.println("You eat the " + itemName + " in your inventory");
                break;
            case EAT_FLOOR:
                System.out.println("You eat the " + itemName + " you found in this room");
                break;
            case NON_EDIBLE:
                System.out.println("The " + itemName + " doesnt look very edible");
                break;
            case LOOKS_BAD:
                System.out.println("This food doesnt exactly look like a healthy meal. Are you sure you want to eat it? (y/n)");
                String choice = "";
                while (!choice.equals("y") && !choice.equals("n")) {
                    choice = input.next() + input.nextLine();
                    if (choice.equals("y")) {
                        adventure.eat(itemName, true);
                        System.out.println("You eat the off-putting food anyway");
                    }else if(choice.equals("n")){
                        System.out.println("You decide better and dont eat the food");
                    }else {
                        System.out.println("Please enter a valid input.");
                    }

                }
                break;
            case NOT_FOUND:
                System.out.println("That item could not be found");
                break;
            default:
                System.out.println("An error has occured");
        }
    }

    private void equip(Adventure adventure, String[] splitChoice){
        String selection = reconstructSelection(splitChoice);
        Enum result = adventure.equip(selection);

        switch (result){
            case EquipOutcomes.SUCCESS -> {
                System.out.println("You equip the " + selection + ".");
            }
            case EquipOutcomes.NOT_A_WEAPON ->{
                System.out.println("That isnt a weapon and you can't equip it.");
            }
            case EquipOutcomes.NOT_FOUND -> {
                System.out.println("That item isn't in your inventory");
            }
            default -> System.out.println("an error has occured in the equip function");
        }
    }

    private void attack(Adventure adventure, String[] splitChoice){
        adventure.attack();
    }
}
