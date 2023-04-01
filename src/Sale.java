public class Sale {
    public String title;
    public int count;
    public int price;
    public String city;
    public boolean isReceived;

    public Sale(String title, int count, int price, String city, boolean isReceived) {
        this.title = title;
        this.count = count;
        this.price = price;
        this.city = city;
        this.isReceived = isReceived;
    }
}
