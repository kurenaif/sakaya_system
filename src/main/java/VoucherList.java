import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;


/**
 * entity 複数の伝票を管理するクラス
 * Created by kurenaif on 2017/07/21.
 */
public class VoucherList implements Closeable{
    private ArrayList<Voucher> voucherList;
    private static VoucherList instanceVoucherList = new VoucherList();

    private VoucherList() {
        voucherList = new ArrayList<>();
    }

    public static VoucherList getInstance(){
        return instanceVoucherList;
    }

    public ArrayList<Voucher> getVoucherList() {
        return voucherList;
    }

    /**
     * 出荷伝票の追加を行う
     * changed by matsuzaki on 2017/07/22.
     * @return 注文番号
     */
    public int add(String customerName, String brand, int number) throws InputException, DataBaseException{
        //登録されている注文番号の中の最大の数([1-999999])
        int maxNum = 0;
        for (Voucher aVoucherList : voucherList) {
            maxNum = Math.max(maxNum, aVoucherList.getOrderNumber());
        }
        if(maxNum >= 999999) throw new DataBaseException("ordered number rather than 999999");
        voucherList.add(new Voucher(maxNum+1, customerName, brand, number));
        return maxNum+1;
    }

    /**
     * 在庫の状態を保存する
     * @throws IOException ファイル入出力時の何かしらの例外
     */
    public void saveFile() throws IOException {
        FileWriter fw = new FileWriter("./voucher_list.csv");
        PrintWriter pw = new PrintWriter(new BufferedWriter(fw));
        for (Voucher v : voucherList){
            pw.println(v.getOrderNumber()+","+v.getCustomerName()+","+v.getBrand()+","+v.getNumber());
        }
        pw.close();
    }

    /**
     * ファイルのロードを行う
     * @throws IOException ファイル入出力の例外(ファイルが見つからないなど)
     * @throws InputException ファイル中身が不正(numberが負の数等)
     * @throws DataBaseException voucherListがcleanな状態出ない場合
     */
    public void loadFile() throws IOException, InputException, DataBaseException {
        if(!voucherList.isEmpty()) throw new DataBaseException("Stock List is not empty");

        FileReader fr;
        try{
        fr = new FileReader("./voucher_list.csv");
        } catch (FileNotFoundException e) {
        	return;
        }
        BufferedReader br = new BufferedReader(new BufferedReader(fr));

        String line;
        StringTokenizer token;
        while((line = br.readLine()) != null){
            token = new StringTokenizer(line, ",");

            Voucher v = new Voucher(
                    Integer.parseInt(token.nextToken()),
                    token.nextToken(),
                    token.nextToken(),
                    Integer.parseInt(token.nextToken()));

            voucherList.add(v);
        }
        br.close();
    }

    public void clear(){
        voucherList.clear();
    }

    /**
     * デストラクタでsaveする
     * @throws IOException ファイルを保存する際に発生する例外
     */
    public void close() throws IOException {
        saveFile();
    }
}