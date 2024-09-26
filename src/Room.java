public class Room {
    private Room north;
    private Room south;
    private Room west;
    private Room east;

    private boolean triedNorth = false;
    private boolean triedSouth = false;
    private boolean triedWest = false;
    private boolean triedEast = false;

    private boolean lockedNorth = false;
    private boolean lockedSouth = false;
    private boolean lockedWest = false;
    private boolean lockedEast = false;


    private String name;
    private String description;
    private String shortDescription;
    private String darkDescription;

    private boolean visited = false;
    private boolean litUp = true;


    public Room(String name, String description, String shortDescription, String darkDescription){
        this.name = name;
        this.description = description;
        this.shortDescription = shortDescription;
        this.darkDescription = darkDescription;
    }

    public void tryNorth(){
        triedNorth = true;
    }
    public void trySouth(){
        triedSouth = true;
    }
    public void tryWest(){
        triedWest = true;
    }
    public void tryEast(){
        triedEast = true;
    }

    public boolean allDirectionsTried(){
        return triedNorth && triedSouth && triedWest && triedEast;
    }

    public String getName() {
        return name;
    }
    public String getDescription(){
        return description;
    }

    public String getShortDescription(){
        return shortDescription;
    }

    public String getDarkDescription(){
        return darkDescription;
    }

    public Room getNorth() {
        return north;
    }
    public void setNorth(Room north,  boolean first){
        this.north = north;
        if(first){
            north.setSouth(this, false);
        }

    }

    public Room getSouth() {
        return south;
    }
    public void setSouth(Room south, boolean first){
        this.south = south;
        if(first){
            south.setNorth(this, false);
        }

    }

    public Room getWest() {
        return west;
    }
    public void setWest(Room west, boolean first){
        this.west = west;
        if(first){
            west.setEast(this, false);
        }

    }

    public Room getEast() {
        return east;
    }
    public void setEast(Room east, boolean first){
        this.east = east;
        if (first){
            east.setWest(this, false);
        }
    }

    public boolean isVisited(){
        return visited;
    }
    public void visit(){
        visited = true;
    }


    //---------------lock functions------------------
    public void lockNorth(){
        lockedNorth = true;
    }
    public void unlockNorth(){
        lockedNorth = false;
    }
    public boolean getLockedNorth(){
        return lockedNorth;
    }

    public void lockSouth(){
        lockedSouth = true;
    }
    public void unlockSouth(){
        lockedSouth = false;
    }
    public boolean getLockedSouth(){
        return lockedSouth;
    }

    public void lockWest(){
        lockedWest = true;
    }
    public void unlockWest(){
        lockedWest = false;
    }

    public boolean getLockedWest(){
        return lockedWest;
    }

    public void lockEast(){
        lockedEast = true;
    }
    public void unlockEast(){
        lockedEast = false;
    }

    public boolean getLockedEast(){
        return  lockedEast;
    }

    //-------light functions----------

    public boolean getLitUp(){
        return litUp;
    }

    public void lighten(){
        litUp = true;
    }
    public void darken(){
        litUp = false;
    }
}
