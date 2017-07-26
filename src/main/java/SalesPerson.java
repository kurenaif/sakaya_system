import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SalesPerson {
    private StockOutput stockoutput;
    private ShipmentPerformance shipmentPerform;

    public SalesPerson(Warehouse warehouse,VoucherList vouncherlist){
        stockoutput = new StockOutput(warehouse);
        shipmentPerform = new ShipmentPerformance(vouncherlist);
    }

    public void selection(){
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        int select;

        System.out.println("どちらの操作を行いますか？");
        System.out.println("1. 在庫を表示する");
        System.out.println("2. 出荷実績を表示する");
        System.out.println("数字を入力してください：");
        while(true) {
            try {
                select = Integer.parseInt(br.readLine());
                if (select == 1) {
                    stockoutput.printStock();
                    break;
                } else if (select == 2) {
                    shipmentPerform.shipment();
                    break;
                } else {
                    System.out.println("正しい入力が行われませんでした．");
                    System.out.println("もう一度入力をお願いします．");
                    System.out.println("-------------------------------------------------");
                }
            } catch (IOException e) {
                System.out.println("正しい入力が行われませんでした．");
                System.out.println("もう一度入力をお願いします．");
                System.out.println("-------------------------------------------------");
            }
        }
    }
}
