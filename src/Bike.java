
public class Bike {
    private boolean isRented;
    private String SerialNumber;

    public Bike() {
        if(this instanceof ElectricBike) generateSerialNumber("E-B");
        else generateSerialNumber("R-B");
    }

    private void generateSerialNumber(String Code) {
        SerialNumber = Code;
    }
    public boolean isRented() {
        return isRented;
    }
    public void setRented(boolean rented) {
        isRented = rented;
    }
    public String getSerialNumber() {
        return SerialNumber;
    }
    public void setSerialNumber(String serialNumber) {
        SerialNumber = serialNumber;
    }
    public void ReturnBike(){
        isRented = false;
    }
    public void RentBike(){
        isRented = true;
    }

    @Override
    public String toString() {
        return "is Rented : "+isRented+" -- Serial Number : "+SerialNumber;
    }
}
