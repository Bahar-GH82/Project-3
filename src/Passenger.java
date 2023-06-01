public class Passenger {
    private String userName;
    private String password;
    private float charge=0;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public float getCharge() {
        return charge;
    }

    public void setCharge(float charge) {
        this.charge = charge;
    }

    public void addCharge(float price){
        charge+=price;
        System.out.println("Your charge:");
        System.out.println(getCharge());
    }

    public void changePassword(String password){
        this.password=password;
    }
}
