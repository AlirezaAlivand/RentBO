import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private List<Bike> RentedList = new ArrayList<>();

    public User() {
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public List<Bike> getRentedList() {
        return RentedList;
    }
    public boolean AddBike(Bike bike) {
        if(RentedList.size()>=2) {
            System.out.println("Tedad charkh haye" +
                    "keraye shode barabar 3 mibashad");
            return false;
        }
        else {
            RentedList.add(bike);
            return true;
        }
    }
    public Bike RemoveBike(int index){
        Bike bike = RentedList.get(index-1);
        RentedList.remove(index-1);
        return bike;
    }
    public void setRentedList(List<Bike> rentedList) {
        RentedList = rentedList;
    }
    @Override
    public String toString() {
        String data = "";
        data += "username : "+username+" -- ";
        data += " Rented Bikes : ";
        for(Bike bike : RentedList){
            data+=bike.getSerialNumber()+",";
        }
        return data;
    }
}
