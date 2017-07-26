import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * entity 在庫を表すクラス (Singleton)
 * Created by kurenaif on 2017/07/21.
 */

public class Warehouse implements Closeable {
    private ArrayList<Stock> stockList;
    private static Warehouse warehouse = new Warehouse();

    private Warehouse() {
        stockList = new ArrayList<>();
    }

    public static Warehouse getInstance(){
        return warehouse;
    }

    /**
     * ブランド名のindexを検索する
     * @param brand ブランド名
     * @return 該当するブランド名の在庫のindex 見つからなかった場合は-1を返す
     */
    private int searchIndex(String brand){
        for(int i = 0; i< stockList.size(); i++){
            if( stockList.get(i).getBrand().equals(brand)) return i;
        }
        return -1;
    }

    public ArrayList<Stock> GetStockList(){
        return stockList;
    }

    /**
     * 在庫を追加する． すでに在庫がある場合は在庫を追加し，ない場合は新たに酒の銘柄を生成する.
     * @param stock 追加する在庫
     * @throws InputException 追加する在庫が0以下であれば例外を吐く
     */
    public void add(Stock stock) throws InputException {
        int index = searchIndex(stock.getBrand());
        if (index == -1) {
            stockList.add(stock);
            return;
        }
        stockList.get(index).addNumber(stock.getNumber());
    }

    /**
     * 在庫の減算を行う． すでにある在庫を検索し，在庫の減算を行う
     * @param stock 減らす在庫
     * @throws InputException 減らした結果，在庫が負の数になる場合は例外 減らす在庫が0以下であれば例外を吐く
     */
    public void subst(Stock stock) throws InputException{
        int index = searchIndex(stock.getBrand());
        if(index == -1) throw new InputException("brand(" + stock.getBrand() + ") not found");
        stockList.get(index).substNumber(stock.getNumber());
    }

    /**
     * 渡された銘柄の在庫数を返す
     * @param brand 在庫数が知りたい銘柄
     * @return 渡された銘柄の在庫数
     * @throws InputException 銘柄が在庫に登録されていない場合，例外を返す
     */
    public int getStockNumber(String brand) throws InputException{
        int index = searchIndex(brand);
        if(index == -1) throw new InputException("brand(" + brand + ") not found");
        return stockList.get(index).getNumber();
    }

    /**
     * 現在の在庫をwarehouse.csvに保存する
     * @throws IOException ファイル入出力の際の例外
     */
    public void saveFile() throws IOException {
        FileWriter fw = new FileWriter("./warehouse.csv");
        PrintWriter pw = new PrintWriter(new BufferedWriter(fw));
        for (Stock s : stockList){
        	if(s.getNumber() != 0) pw.println(s.getBrand()+","+s.getNumber());
        }
        pw.close();
    }

    /**
     * csvファイルから在庫のloadを行う．
     * @throws IOException 在庫のファイルがない場合 例外を吐く
     * @throws InputException すでにオブジェクトに在庫がある場合，例外を吐く
     */
    public void loadFile() throws IOException, InputException, DataBaseException {
        if(!stockList.isEmpty()) throw new DataBaseException("Stock List is not empty");
        
        FileReader fr;
        try{
        	fr = new FileReader("./warehouse.csv");
        } catch (FileNotFoundException e){
        	return;
        }
        BufferedReader br = new BufferedReader(new BufferedReader(fr));

        String line;
        StringTokenizer token;
        while((line = br.readLine()) != null){
            token = new StringTokenizer(line, ",");
            try{
            	Stock s = new Stock(token.nextToken(), Integer.parseInt(token.nextToken()));
            	stockList.add(s);
            } catch (InputException e){
            }
        }
        br.close();
    }

    /**
     * 在庫の消去を行う
     */
    public void clear(){
        stockList.clear();
    }
    
    /**
     * デストラクタでsaveする
     * @throws IOException ファイルを保存する際に発生する例外
     */
    public void close() throws IOException {
        saveFile();
    }

}