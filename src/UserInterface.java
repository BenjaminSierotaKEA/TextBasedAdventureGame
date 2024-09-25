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
                case "GO NORTH":
                    goNorth(adventure);
                    break;
                case "GO SOUTH":
                    goSouth(adventure);
                    break;
                case "GO WEST":
                    goWest(adventure);
                    break;
                case "GO EAST":
                    goEast(adventure);
                    break;
                case "LOOK":
                    look(adventure);
                    break;
                case "HELP":
                    help();
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
        boolean success = adventure.goNorth();
        if(success){
            System.out.println("You find your way into a new room!");
            look(adventure);
        }else{
            System.out.println("there isnt a passageway in that direction");
        }
    }

    private void goSouth(Adventure adventure){
        boolean success = adventure.goSouth();
        if(success){
            System.out.println("You find your way into a new room!");
            look(adventure);
        }else{
            System.out.println("there isnt a passageway in that direction");
        }
    }

    private void goWest(Adventure adventure){
        boolean success = adventure.goWest();
        if(success){
            System.out.println("You find your way into a new room!");
            look(adventure);
        }else{
            System.out.println("there isnt a passageway in that direction");
        }
    }

    private void goEast(Adventure adventure){
        boolean success = adventure.goEast();
        if(success){
            System.out.println("You find your way into a new room!");
            look(adventure);
        }else{
            System.out.println("there isnt a passageway in that direction");
        }
    }

    private void look(Adventure adventure){
        System.out.println("Lets take a look around");
        String roomName = adventure.getCurrentRoom().getName();
        String roomDescription = adventure.getCurrentRoom().getDescription();
        System.out.println("ROOM NAME: " + roomName);
        System.out.println(roomDescription);
    }

    private void help(){
        System.out.println("------LIST OF COMMANDS:------");
        System.out.println("GO NORTH: attempt to move North");
        System.out.println("GO SOUTH: attempt to move South");
        System.out.println("GO WEST: attempt to move west");
        System.out.println("GO EAST: attempt to move east");
        System.out.println("LOOK: look around at the room you are in");
        System.out.println("HELP: print a list of commands");
        System.out.println("EXIT: quit the program");
    }
}
