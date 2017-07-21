/**
 * entity 伝票に関するクラス
 * Created by kurenaif on 2017/07/21.
 */
public class Voucher{
    private int orderNumber;
    private String customerName;
    private String brand;
    private int number;

    /**
     * コンストラクタ 伝票を生成する
     * @param orderNumber 注文番号
     * @param customerName 顧客名
     * @param brand 銘柄
     * @param number 本数
     */
    public Voucher(int orderNumber, String customerName, String brand, int number) throws InputException {
        if(number <= 0) throw new InputException("ordered number is minus or zero");
        this.orderNumber = orderNumber;
        this.customerName = customerName;
        this.brand = brand;
        this.number = number;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getBrand() {
        return brand;
    }

    public int getNumber() {
        return number;
    }
}