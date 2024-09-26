import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {

    public void gameplayLoop(Adventure adventure){
        boolean exit = false;
        Scanner input = new Scanner(System.in);

        while(!exit){
            System.out.println("Please enter a command (help for a list of commands)");

            String choice = input.next() + input.nextLine();
            choice = choice.toUpperCase();

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
                    System.out.println("Please enter a valid command.");

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
    }

    private void entryLook(Adventure adventure, String outcome){

        //TODO: make the door opening outcomes into enum thingies once youve reminded yourself how to make those, this sucks
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
}
