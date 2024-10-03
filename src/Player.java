import java.util.ArrayList;

public class Player {
    private Room currentRoom;
    private Room previousRoom;
    private Room teleportRoom;

    private int health = 100;

    private ArrayList<Item> inventory;

    public Player(Room startingRoom){
        currentRoom = startingRoom;
        previousRoom = startingRoom;
        teleportRoom = startingRoom;
        inventory = new ArrayList<Item>();
        //test items:
        inventory.add(new Item("Explorers Clothes", "A very durable set of clothes perfect for exploring"));
        inventory.add(new Item("Wallet", "Contains some loose change and a few plastic cards"));
        inventory.add(new Item("Length of rope", "60 ft of hempen rope, good for climbing "));
        inventory.add(new Food("Biscuit", "A sweet, brown buiscuit", 30));
    }


    public Room getCurrentRoom(){
        return currentRoom;
    }

    //----------------MOVEMENT DUNCTIONS:--------------------

    public String goNorth(){
        currentRoom.tryNorth();

        //You can only go into the previous room you were in if the room is dark
        if (!currentRoom.getLitUp()){
            if(currentRoom.getNorth() != previousRoom){
                return "DARK";
            }
        }

        if(currentRoom.getNorth() == null){
            return "WALL";
        }else if(currentRoom.getNorth().getLockedSouth()) {
            return  "LOCKED";
        }else{
            previousRoom = currentRoom;
            currentRoom = currentRoom.getNorth();
            return "SUCCESS";
        }
    }

    public String goSouth(){

        if (!currentRoom.getLitUp()){
            if(currentRoom.getSouth() != previousRoom){
                return "DARK";
            }
        }

        currentRoom.trySouth();
        if(currentRoom.getSouth() == null){
            return "WALL";
        }else if(currentRoom.getSouth().getLockedNorth()) {
            return  "LOCKED";
        }else{
            previousRoom = currentRoom;
            currentRoom = currentRoom.getSouth();
            return "SUCCESS";
        }
    }

    public String goWest(){

        if (!currentRoom.getLitUp()){
            if(currentRoom.getWest() != previousRoom){
                return "DARK";
            }
        }

        currentRoom.tryWest();
        if(currentRoom.getWest() == null){
            return "WALL";
        }else if(currentRoom.getWest().getLockedEast()) {
            return  "LOCKED";
        }else{
            previousRoom = currentRoom;
            currentRoom = currentRoom.getWest();
            return "SUCCESS";
        }
    }

    public String goEast(){

        if (!currentRoom.getLitUp()){
            if(currentRoom.getEast() != previousRoom){
                return "DARK";
            }
        }


        currentRoom.tryEast();
        if(currentRoom.getEast() == null){
            return "WALL";
        }else if(currentRoom.getEast().getLockedWest()) {
            return  "LOCKED";
        }else{
            previousRoom = currentRoom;
            currentRoom = currentRoom.getEast();
            return "SUCCESS";
        }
    }

    public void teleport(){
        Room holder = currentRoom;
        currentRoom = teleportRoom;
        teleportRoom = holder;
    }

    //---------MISC ACTIONS---------------

    public int unlock(){
        int doorsUnlocked = 0;
        if(currentRoom.getEast() != null){
            if (currentRoom.getEast().getLockedWest()){
                currentRoom.getEast().unlockWest();
                doorsUnlocked++;
            }
        }

        if(currentRoom.getWest() != null){
            if(currentRoom.getWest().getLockedEast()){
                currentRoom.getWest().unlockEast();
                doorsUnlocked++;
            }
        }

        if (currentRoom.getSouth() != null){
            if(currentRoom.getSouth().getLockedNorth()){
                currentRoom.getSouth().unlockNorth();
                doorsUnlocked++;
            }
        }

        if (currentRoom.getNorth() != null){
            if(currentRoom.getNorth().getLockedSouth()){
                currentRoom.getNorth().unlockSouth();
                doorsUnlocked++;
            }
        }


        return doorsUnlocked;
    }

    //---LIGHT FUNCTIONS---------

    public void lightUp(){
        currentRoom.lighten();
    }

    public void darken(){
        currentRoom.darken();
    }
    //------------------------item functions-------------------------
    //we need to sanitize input for these functions in the user interface. out of bounds throws an exception
    public void takeItem(int index){
        inventory.add(currentRoom.takeItem(index));

    }

    public void dropItem(int index){
        currentRoom.addItem(inventory.get(index));
        inventory.remove(index);
    }


    public ArrayList<Item> getInventory(){
        return inventory;
    }

    //Finish this after health
    public Boolean eat(Item foodToEat){
        if(foodToEat instanceof Food){
            Food food = (Food) foodToEat;
            health +=  food.getHpRecovery();
            return true;
        }
     return false;
    }

    public int getHealth() {
        return health;
    }
}
