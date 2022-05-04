package model;

public class Orders {
    private int ID;
    private int clientID;
    private int productID;
    private int productAmount;
    private double totalPrice;

    public Orders(int ID, int clientID, int productID, int productAmount, double totalPrice) {
        this.ID = ID;
        this.clientID = clientID;
        this.productID = productID;
        this.productAmount = productAmount;
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "ID=" + ID +
                ", clientID=" + clientID +
                ", productID=" + productID +
                ", productAmount=" + productAmount +
                ", totalPrice=" + totalPrice +
                '}';
    }

    public Orders() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(int productAmount) {
        this.productAmount = productAmount;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
