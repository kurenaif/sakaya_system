import java.io.IOException;

/**
 * entity 注文に関するクラス
 * Created by kurenaif on 2017/07/21.
 * Changed by matsuzaki in 2017/07/22.
 */
public class Order {
    private Warehouse warehouse;
    private VoucherList voucherList;

	public Order(Warehouse warehouse, VoucherList voucherList) {
        this.warehouse = warehouse;
        this.voucherList = voucherList;
    }

    /**
     * changed by matsuzaki on 2017/07/22.
     * @param customerName
     * @param brand
     * @param number
     * @return 注文番号
     * @throws InputException
     * @throws DataBaseException
     */
    public int order(String customerName, String brand, int number ) throws InputException, DataBaseException{
    	warehouse.subst(new Stock(brand, number));
    	try {
			warehouse.saveFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return voucherList.add(customerName, brand, number);
    }
}