import java.util.ArrayList;
import java.util.Random;

public class Player extends Character{
    private Room currentRoom;
    private Room previousRoom;
    private Room teleportRoom;

    private int health = 100;
    private int armourClass = 12;

    private ArrayList<Item> inventory;
    private Weapon equippedWeapon;

    public Player(Room startingRoom){
        currentRoom = startingRoom;
        previousRoom = startingRoom;
        teleportRoom = startingRoom;
        inventory = new ArrayList<Item>();

        super.name = "Player";
        //test items:
        inventory.add(new Item("Explorers Clothes", "A very durable set of clothes perfect for exploring"));
        inventory.add(new Item("Wallet", "Contains some loose change and a few plastic cards"));
        inventory.add(new Item("Length of rope", "60 ft of hempen rope, good for climbing "));
        inventory.add(new Food("Biscuit", "A sweet, brown buiscuit", 30, true));
        inventory.add(new MeleeWeapon("Knife", "A large, sharp knife"));
        inventory.add(new RangedWeapon("Gun", "An ordinary looking handgun", 3));
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
        if(inventory.get(index).equals(equippedWeapon)){
            equippedWeapon = null;
        }
        inventory.remove(index);
    }


    public ArrayList<Item> getInventory(){
        return inventory;
    }

    //Finish this after health
    public EatOutcomes eat(String itemToEat, boolean confirmed){
        //first we check if the item is in the inventory (and eat it if it is)
        int index = 0;
        boolean inventoryNonEdible = false;
        boolean floorNonEdible = false;
        for(Item i : inventory){
            if(i.getName().toUpperCase().equals(itemToEat)){
                //checking if the item is food and downcasting it:
                if(i instanceof Food){
                    Food food = (Food) i;
                    if (!food.getLooksEdible() && !confirmed){
                        return EatOutcomes.LOOKS_BAD;
                    }else{
                        health += food.getHpRecovery();
                        inventory.remove(index);
                        return EatOutcomes.EAT_INVENTORY;

                    }

                }else{
                    inventoryNonEdible = true;
                }

            }
            index++;
        }

        //then we check the floor:

        index = 0;
        for(Item i : currentRoom.getAllItems()){

            if(i.getName().toUpperCase().equals(itemToEat)) {
                if(i instanceof Food){
                    Food food = (Food) i;
                    if(!food.getLooksEdible() && !confirmed){
                        return  EatOutcomes.LOOKS_BAD;
                    }else {
                        health += food.getHpRecovery();
                        currentRoom.getAllItems().remove(index);
                        return EatOutcomes.EAT_FLOOR;
                    }

                }else{
                    floorNonEdible = true;
                }

            }
            index++;
        }

        //if we dont find it anywhere we return Failure:
        if(floorNonEdible || inventoryNonEdible){
            return EatOutcomes.NON_EDIBLE;
        }else{
            return EatOutcomes.NOT_FOUND;
        }

    }

    public int getHealth() {
        return health;
    }

    public Enum equip(String weaponToEquip){
        for(Item i : inventory){
            if (i.getName().toUpperCase().equals(weaponToEquip)){
                if(i instanceof Weapon){
                    Weapon weapon = (Weapon) i;
                    equippedWeapon = weapon;
                    return EquipOutcomes.SUCCESS;
                }else{
                    return EquipOutcomes.NOT_A_WEAPON;
                }
            }
        }

        return EquipOutcomes.NOT_FOUND;
    }

    public Item getEquippedWeapon(){
        return equippedWeapon;
    }

    //souts in the attack functions are place holders until we get some enemies
    public int[] attack(Character target, UserInterface ui){
        int [] attackData;
        Random random = new Random();

        if(equippedWeapon != null){
            attackData = equippedWeapon.attack();
        }else{
            attackData = new int[2];
            attackData[0] = random.nextInt(1, 20 + 1);
            attackData[1] = random.nextInt(1, 6+1);
        }


       target.recieveAttack(attackData[0], attackData[1], ui, equippedWeapon, this);

       //then we have all the enemies attack the player
       currentRoom.enemyTurn(this, ui);

       return attackData;
    }

    public void recieveAttack(int hitRoll, int damageRoll, UserInterface ui, Weapon attackingWeapon, Character attacker){
        if(hitRoll > armourClass){
            health -= damageRoll;
            ui.enemyAttacksPlayer(attacker.getName(),hitRoll,damageRoll,true, attackingWeapon);
        }else{
            ui.enemyAttacksPlayer(attacker.getName(),hitRoll,damageRoll,false, attackingWeapon);
        }
    }

    public Enemy findEnemy(String enemyToFind){
        for(Enemy e: currentRoom.getEnemiesInRoom()){
            if(e.getName().toUpperCase().equals(enemyToFind)){
                return e;
            }
        }
        return null;
    }
}
