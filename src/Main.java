public class Main {
    public static void main(String[] args) {
    SaleManager saleManager=new SaleManager("resources/sales.csv");

        System.out.println("Топовый товар "+saleManager.getTopProduct());
    DeliveryManager deliveryManager=new DeliveryManager();
    deliveryManager.loadFile("msc","resources/msc.delivery.csv");
    deliveryManager.loadFile("spb", "resources/spb.delivery.csv");
        System.out.println("Топовый город " +deliveryManager.topCity(3));
    Checker checker=new Checker(saleManager,deliveryManager);
    boolean answer=checker.check();
        System.out.println("Результат проверки:"+ answer);
    }
}