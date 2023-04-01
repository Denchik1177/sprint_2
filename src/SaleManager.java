import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

public class SaleManager {
    public ArrayList<Sale> sales= new ArrayList<>();//сохранение в память менэджера
    public  SaleManager(String path){
        String content= readFileContentsOrNull(path);
        String[] lines= content.split("\r?\n");//["1st stroka", "2nd stroka",...]
        for (int i = 1; i < lines.length; i++) {
            String line =lines[i];//очки,1,9800,msc,false
            String[] parts=line.split(",");// нарезание на части ["очки", "1"...]
            String title=parts[0];// первая часть
            int count =Integer.parseInt(parts[1]);
            int price =Integer.parseInt(parts[2]);
            String city =parts[3];
            boolean isReceived= Boolean.parseBoolean(parts[4]);//"false" ->false

            Sale sale =new Sale(title,count,price,city,isReceived);//объект продажи
            sales.add(sale);// добавление в список

        }
    }
    public String getTopProduct(){
        HashMap<String,Integer> freqs= new HashMap<>();
        for (Sale sale : sales) {
            freqs.put(sale.title, freqs.getOrDefault(sale.title,0)+sale.count);// в мапе по ключу находится сколько раз купили этот товар
        } //title-товар
        String maxTitle=null;
        for (String title : freqs.keySet()) {
            if(maxTitle==null){
                maxTitle=title;
                continue; // переход на след итерацию
            }
            if(freqs.get(maxTitle)< freqs.get(title)){
                maxTitle=title;
            }
        }
        return maxTitle;
    }
    public String readFileContentsOrNull(String path) {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно файл не находится в нужной директории.");
            return null;
        }
    }
}
