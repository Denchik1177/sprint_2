import java.util.HashMap;

public class Checker {
    public SaleManager saleManager;
    public DeliveryManager deliveryManager;

    public Checker(SaleManager saleManager, DeliveryManager deliveryManager) {
        this.saleManager = saleManager;
        this.deliveryManager = deliveryManager;
    }

    public boolean check(){
        boolean check=true;
        HashMap<String, HashMap<String, Integer>> deliveryBySales =new HashMap<>();//ключ-город, зн-е-(ключ-товар, зн-е кол-во)
        for (Sale sale : saleManager.sales) {
            if(!sale.isReceived){
                continue;
            }
            if(!deliveryBySales.containsKey(sale.city)){
                deliveryBySales.put(sale.city, new HashMap<>());
            }
            HashMap<String, Integer> titleToCount=deliveryBySales.get(sale.city);
            titleToCount.put(sale.title, titleToCount.getOrDefault(sale.title,0)+sale.count);
        }
        HashMap<String, HashMap<String, Integer>> deliveryByDelivery =new HashMap<>();
        for (Delivery delivery : deliveryManager.deliveries) {
            if(!deliveryByDelivery.containsKey(delivery.city)){
                deliveryByDelivery.put(delivery.city,new HashMap<>());
            }
            HashMap<String,Integer> titleToCount=deliveryByDelivery.get(delivery.city);
            titleToCount.put(delivery.title, titleToCount.getOrDefault(delivery.title, 0)+ delivery.count);
        }
        for (String city : deliveryBySales.keySet()) {
            HashMap<String, Integer> titleToCountBySales= deliveryBySales.get(city);
            HashMap<String, Integer> titleToCountByDelivery= deliveryByDelivery.get(city);
            if(titleToCountByDelivery==null){
                System.out.println("Город "+city+" есть в отчете о продажах, но нет в отчетах о доставке");
            check= false;
            continue;
            }

            for (String title : titleToCountByDelivery.keySet()) {
                int countBySales= titleToCountBySales.get(title);
                int countByDelivery =titleToCountByDelivery.getOrDefault(title,0);
                if(countBySales!= countByDelivery){
                    System.out.println("В городе "+city+" товар "+title+" был продан по отчету о продажах"+"в количестве "
                            +countBySales+"шт, а по отчету о доставках в количестве "+countByDelivery+ " шт.");
                    check=false;
                }
            }
        }
        return check;
    }
}
