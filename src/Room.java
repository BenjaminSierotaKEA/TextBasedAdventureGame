public class Room {
    private Room north;
    private Room south;
    private Room west;
    private Room east;

    private String name;
    private String description;

    public Room(String name, String description){
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }
    public String getDescription(){
        return description;
    }

    public Room getNorth() {
        return north;
    }
    public void setNorth(Room north){
        this.north = north;
    }

    public Room getSouth() {
        return south;
    }
    public void setSouth(Room south){
        this.south = south;
    }

    public Room getWest() {
        return west;
    }
    public void setWest(Room west){
        this.west = west;
    }

    public Room getEast() {
        return east;
    }
    public void setEast(Room east){
        this.east = east;
    }
}
