public class Map {
    Room room1;
    Room room2;
    Room room3;
    Room room4;
    Room room5;
    Room room6;
    Room room7;
    Room room8;
    Room room9;

    public Map(){

    }

    public Room buildMap(){
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

        return room1;
    }
}
