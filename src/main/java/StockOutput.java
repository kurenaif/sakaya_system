import java.io.IOException;
import java.util.ArrayList;

/**
 * StockOutput 在庫表示に関するクラス
 * Created by nakayama on 2017/07/24
 */
public class StockOutput {
    private Warehouse warehouse;

    /**
     * コンストラクタ
     */
    public StockOutput(Warehouse warehouse){
        this.warehouse = warehouse;
    }

    public void printStock(){
        ArrayList<Stock> stock = warehouse.GetStockList();

        System.out.println("------------------------------------------------------------");
        System.out.println("在庫を表示します");
        System.out.println("-----------------------------");
        System.out.println("酒の銘柄    本数");
        System.out.println("-----------------------------");
        for (int i=0;i < stock.size();i++){
            System.out.println(stock.get(i).getBrand()+"\t"+stock.get(i).getNumber());
        }
        System.out.println("-----------------------------");
        System.out.println("------------------------------------------------------------");
    }
}
