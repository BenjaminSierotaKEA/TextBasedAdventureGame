public class Adventure {
    private Room currentRoom;
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
    }

    public Room getCurrentRoom(){
        return currentRoom;
    }

    public boolean goNorth(){
        if(currentRoom.getNorth() == null){
            return false;
        }else{
            currentRoom = currentRoom.getNorth();
            return true;
        }
    }

    public boolean goSouth(){
        if (currentRoom.getSouth() == null){
            return false;
        }else{
            currentRoom = currentRoom.getSouth();
            return true;
        }
    }

    public boolean goWest(){
        if(currentRoom.getWest() == null){
            return false;
        }else{
            currentRoom = currentRoom.getWest();
            return true;
        }
    }

    public boolean goEast(){
        if(currentRoom.getEast() == null){
            return false;
        }else{
            currentRoom = currentRoom.getEast();
            return true;
        }
    }


    private void buildMaze(){
        room1 = new Room("The Entrance", "A dry and ancient antechamber to what appears to be \n" +
                "an ancient tomb from a long lost civilisation");
        room2 = new Room("Dank Corridor", "An ancient stone corridor with blank walls. water is dripping" +
                "\n down the sides from above");
        room3 = new Room("Corner Chamber", "A hauntingly silent room filled with strange stone statues." +
                "\n they are all staring at the same spot on the wall ");
        room4 = new Room("Carved Corridor", "A long corridor with immaculate carvings adorning the sides"
        + "\n They seem to depict some great danger up ahead");
        room5 = new Room("The Nexus", "Past the seal, A great crystal glowing with a strange green light lies" +
                "\n in the center of this room, surrounded by weird statues. Will you grow rich from this treasure, or will it" +
                "\n be your undoing?");
        room6 = new Room("The Deep Corridor", "this dark corridor is the deepest part of the tomb. there is a light" +
                "\n at the southern end of the tunnel");
        room7 = new Room("The Statue Chamber", "This room houses a massive statue of a monster. its eyes seems" +
                "\n to follow you around the room. You feel a sense of dread at what you might find further in.");
        room8 = new Room("The Seal Chamber", "Two corridors meet in this room, joining together in front of a giant" +
                "\n seal, which has cracked open. A light shines through the gap. the seal is carved with skulls and" +
                "\n figures writhing in pain. You wander if it is wise to proceed");
        room9 = new Room("The Mirror Chamber", "The walls in this room are covered in mirrors. the light you saw from" +
                "\n the corridor was reflected from the next room. The light is a strange, sickly green colour.");

        //setting the rooms relations to each other:
        room1.setEast(room2);
        room1.setSouth(room4);
        room2.setWest(room1);
        room2.setEast(room3);
        room3.setWest(room2);
        room3.setSouth(room6);
        room4.setNorth(room1);
        room4.setSouth(room7);
        room5.setSouth(room8);
        room6.setNorth(room3);
        room6.setSouth(room9);
        room7.setNorth(room4);
        room7.setEast(room8);
        room8.setWest(room7);
        room8.setNorth(room5);
        room8.setEast(room9);
        room9.setWest(room8);
        room9.setNorth(room6);
    }
}
