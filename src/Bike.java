public class Bike
{
    private int bikeID;
    private String ownerName;
    private String type;
    private String serial;
    private String brake;
    private String wheelSize;

    public Bike(int bikeID, String ownerName, String type, String serial, String brake, String wheelSize)
    {
        this.bikeID = bikeID;
        this.ownerName = ownerName;
        this.type = type;
        this.serial = serial;
        this.brake = brake;
        this.wheelSize = wheelSize;
    }

    public int getBikeID() {
        return bikeID;
    }
    public String getOwnerName() {
        return ownerName;
    }
    public String getType() {
        return type;
    }
    public String getSerial() {
        return serial;
    }
    public String getBrake() {
        return brake;
    }
    public String getWheelSize() {
        return wheelSize;
    }

    public void setBikeID(int bikeID) {
        this.bikeID = bikeID;
    }
    public void setOwnerName(String ownerName){
        this.ownerName = ownerName;
    }
    public void setType(String type){
        this.type = type;
    }
    public void setSerial(String serial){
        this.serial = serial;
    }
    public void setBrake(String brake){
        this.brake = brake;
    }
    public void setWheelSize(String wheelSize){
        this.wheelSize = wheelSize;
    }

    @Override
    public String toString() {
        return getClass().getPackage().getName() + "." + getClass().getName() +
                "[Id:" + bikeID +
                ", Owner:" + ownerName +
                ", Type:" + type +
                ", Serial:" + serial +
                ", Brake:" + brake +
                ", WheelSize:" + wheelSize +
                "]";
    }


}
