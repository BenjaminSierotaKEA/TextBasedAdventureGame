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







}
