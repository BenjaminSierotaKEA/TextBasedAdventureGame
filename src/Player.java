public class Player {
    private Room currentRoom;
    private Room previousRoom;
    private Room teleportRoom;

    public Player(Room startingRoom){
        currentRoom = startingRoom;
        previousRoom = startingRoom;
        teleportRoom = startingRoom;
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
}
