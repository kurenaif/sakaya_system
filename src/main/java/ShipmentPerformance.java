import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * 出荷実績に関するクラス
 * Created by nakayama 2017/07/24
 */
public class ShipmentPerformance {
    private VoucherList voucherlist;

    public ShipmentPerformance(VoucherList voucherlist){
        this.voucherlist = voucherlist;
    }

    public void shipment(){
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        ArrayList<Voucher> vlist = voucherlist.getVoucherList();
        String customerName;

        System.out.println("出荷実績を表示します");
        System.out.println("出荷実績を表示する顧客名を入力してください．");
        System.out.print("顧客名：");
        while(true) {
            try {
                customerName = br.readLine();
                break;
            } catch (IOException e) {
            	System.out.println("-------------------------------------------------");
                System.out.println("正しい入力が行われませんでした．");
                System.out.println("もう一度入力をお願いします．");
                System.out.println("-------------------------------------------------");
            }
        }
        System.out.println("-------------------------------------------------");

        for (int i=0;i < vlist.size(); i++){
            if(customerName.equals(vlist.get(i).getCustomerName())){
                System.out.println(vlist.get(i).getCustomerName()+"\t"+vlist.get(i).getNumber());
            }
        }
        System.out.println("-------------------------------------------------");

    }
}
