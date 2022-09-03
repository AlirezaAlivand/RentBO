public class ElectricBike extends Bike{
    private boolean Charge=true;

    @Override
    public void ReturnBike(){
        setRented(false);
        Charge = false;
        ChargeTheBike();

    }
    private void ChargeTheBike() {
        Charge = true;
        System.out.println("Charkh ba movafaqiat sharzh shod.");
    }

    public boolean isCharge() {
        return Charge;
    }
}
