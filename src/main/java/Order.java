/**
 * entity 注文に関するクラス
 * Created by kurenaif on 2017/07/21.
 */
public class Order {
    private Warehouse warehouse;
    private VoucherList voucherList;

    public Order(Warehouse warehouse, VoucherList voucherList) {
        this.warehouse = warehouse;
        this.voucherList = voucherList;
    }

    public void order(String customerName, String brand, int number ) throws InputException, DataBaseException{
        //voucherListの方が条件が厳しいため必ずこちらを先に実行する
        voucherList.add(customerName, brand, number);
        warehouse.add(new Stock(brand, number));
    }
}