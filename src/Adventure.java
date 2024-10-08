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

    public Enum equip(String weaponName){
        return player.equip(weaponName);
    }

    public int getHealth(){
        return player.getHealth();
    }

    //if confirmed is true, it wont stop eating if the food looks bad
    public EatOutcomes eat(String itemToEat, boolean confirmed){
        return  player.eat(itemToEat, confirmed);
    }

    public void attack(){
        player.attack();
    }





}
