import java.io.IOException;

/**
 * entity 入荷に関するクラス
 * Created by kurenaif on 2017/07/21.
 */
public class Arrival {
    private Warehouse warehouse;

    /**
     * コンストラクタ 在庫を与える
      * @param warehouse 在庫
     */
    public Arrival(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    /**
     * 入荷を行う
     * @param brand 入荷する酒
     * @param number 入荷する酒の本数
     * @throws InputException 入荷する酒の本数が0以下の場合exception
     */
    void arrive(String brand, int number) throws InputException{
        warehouse.add(new Stock(brand,  number));
    	try {
			warehouse.saveFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}