public class Adventure {
    private Room currentRoom;
    private Room previousRoom;
    private Room teleportRoom;
    private Room room1;
    private Room room2;
    private Room room3;
    private Room room4;
    private Room room5;
    private Room room6;
    private Room room7;
    private Room room8;
    private Room room9;

    public Adventure(){
        buildMaze();
        currentRoom = room1;
        previousRoom = room1;
        teleportRoom = room1;
    }

    public Room getCurrentRoom(){
        return currentRoom;
    }

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

    public void lightUp(){
        currentRoom.lighten();
    }

    public void darken(){
        currentRoom.darken();
    }

    public void teleport(){
        Room holder = currentRoom;
        currentRoom = teleportRoom;
        teleportRoom = holder;
    }



    private void buildMaze(){
        room1 = new Room("The Entrance",
                "A dry and ancient antechamber to what appears to be \n" +
                "an ancient tomb from a long lost civilisation",
                "the antechamber",
                "A dark room. you can only see the light of the night sky from the hole in the cieling you sropped in from");
        room2 = new Room("Dank Corridor",
                "An ancient stone corridor with blank walls. water is dripping" +
                "\n down the sides from above",
                "the damp corridor",
                "A dark corridor. You can hear water dripping down the sides but see nothing");
        room3 = new Room("Corner Chamber",
                "A hauntingly silent room filled with strange stone statues." +
                "\n they are all staring at the same spot on the wall ",
                "the room filled with statues.",
                "A dark room. you can make out several figures in the gloom");
        room4 = new Room("Carved Corridor",
                "A long corridor with immaculate carvings adorning the sides"
        + "\n They seem to depict some great danger up ahead",
                "the corridor with the carvings.",
                "A dark corridor. You can feel strange shapes on the walls");
        room5 = new Room("The Nexus",
                "Past the seal, A great crystal glowing with a strange green light lies" +
                "\n in the center of this room, surrounded by weird statues. Will you grow rich from this treasure, or will it" +
                "\n be your undoing?",
                "the central room with the glowing crystal.",
                "The room is lit up in green by the crystal in the center, making it hard to see anything. \n" +
                        "you get the feeling that this is what you were looking for.");
        room6 = new Room("The Deep Corridor",
                "this dark corridor is the deepest part of the tomb. there is a light" +
                "\n at the southern end of the tunnel",
                "the deep, dark corridor.",
                "The corridor is pitch black, with only a pale green glimmer of light at the end. you feel \n" +
                        "as though the darkness here could be dangerous if you remain in it too long");
        room7 = new Room("The Statue Chamber",
                "This room houses a massive statue of a monster. its eyes seems" +
                "\n to follow you around the room. You feel a sense of dread at what you might find further in.",
                "the room with the massive monster statue.",
                "A dark room. you can make out a large figure in the gloom");
        room8 = new Room("The Seal Chamber",
                "Two corridors meet in this room, joining together in front of a giant" +
                "\n seal, which has cracked open. A light shines through the gap. the seal is carved with skulls and" +
                "\n figures writhing in pain. You wander if it is wise to proceed",
                "the room with the great, cracked seal.",
                "A dark room. you can see a green glimmer of light coming from a hole in the far wall.");
        room9 = new Room("The Mirror Chamber",
                "The walls in this room are covered in mirrors. the light you saw from" +
                "\n the corridor was reflected from the next room. The light is a strange, sickly green colour.",
                "the room covered in mirrors",
                "Despite this room being dark, you can make out a few of its features thanks to its reflective walls.");

        //setting the rooms relations to each other: (they automatically make a connection the other way as well)
        room1.setEast(room2, true);
        room1.setSouth(room4, true);
        room2.setEast(room3, true);
        room3.setSouth(room6, true);
        room4.setSouth(room7, true);
        room5.setSouth(room8, true);
        room6.setSouth(room9, true);
        room7.setEast(room8, true);
        room8.setEast(room9, true);

        room5.lockSouth();
        room2.lockWest();
    }
}
