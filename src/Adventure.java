import java.util.ArrayList;

public class Adventure {
    private Map maze;
    private Player player;

    public Adventure(){
        maze = new Map();
        Room startingRoom = maze.buildMap();
        player = new Player(startingRoom);
    }

    public Room getCurrentRoom(){
        return player.getCurrentRoom();
    }

    public String goNorth(){
      String outcome =  player.goNorth();
      return outcome;
    }

    public String goSouth(){
        String outcome = player.goSouth();
        return outcome;
    }

    public String goWest(){
        String outcome = player.goWest();
        return outcome;
    }

    public String goEast(){
        String outcome = player.goEast();
        return outcome;
    }

    public void teleport(){
        player.teleport();
    }

    public int unlock(){
        return player.unlock();
    }

    public void lightUp(){
        player.lightUp();
    }

    public void  darken(){
        player.darken();
    }

    //------------------item functions---------------

    public ArrayList<Item> getInventory(){
        return player.getInventory();
    }

    public void takeItem(int index){
        player.takeItem(index);
    }

    public void dropItem(int index){
        player.dropItem(index);
    }

    public int getHealth(){
        return player.getHealth();
    }

    public String eat(String itemToEat){
        //first we check if the item is in the inventory (and eat it if it is)
        int index = 0;
        boolean inventoryNonEdible = false;
        boolean floorNonEdible = false;
        for(Item i : getInventory()){
            if(i.getName().toUpperCase().equals(itemToEat)){
                boolean outcome = player.eat(i);
                if(outcome){
                    getInventory().remove(index);
                    return "SUCCESS INVENTORY";
                }else{
                    inventoryNonEdible = true;
                }

            }
            index++;
        }

        //the we check the floor:

        index = 0;
        for(Item i : getCurrentRoom().getAllItems()){
            if(i.getName().toUpperCase().equals(itemToEat)) {
                Boolean outcome = player.eat(i);
                if (outcome) {
                    getCurrentRoom().getAllItems().remove(index);
                    return "SUCCESS ROOM";
                } else {
                    floorNonEdible = true;
                }

            }
            index++;
        }

        //if we dont find it anywhere we return Failure:
        if(floorNonEdible || inventoryNonEdible){
            return "NOT EDIBLE";
        }else{
            return "NOT FOUND";
        }

    }



}
