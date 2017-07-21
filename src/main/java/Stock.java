/**
 * entity 在庫を管理するクラス 在庫は0以上であることが保証されている．
 * Created by kurenaif on 2017/07/21.
 */
public class Stock {
    private String brand;
    private int number;

    /**
     * 在庫を生成する
     * @param brand 酒の銘柄
     * @param number 酒の本数
     * @throws InputException 酒の本数が0以下の場合に例外を吐く
     */
    public Stock(String brand, int number) throws InputException {
        if(number <= 0) throw new InputException("Stock number exception");
        this.brand = brand;
        this.number = number;
    }

    /**
     * ブランド名の取得
     * @return ブランド名
     */
    public String getBrand() {
        return brand;
    }

    /**
     * 在庫数の取得
     * @return 在庫数
     */
    public int getNumber() {
        return number;
    }

    /**
     * 在庫の追加を行う． 負の数が入力されれば減算するので例外を吐くことにする
     * @param num 追加する在庫の数
     * @throws InputException 0か負の数が入力されれば例外を吐く
     */
    public void addNumber(int num) throws InputException{
        if(num <= 0) throw new InputException("arg value is minus or zero");
        number += num;
    }

    /**
     * 在庫の減算を行う． 負の数が入力されれば加算されることになるので例外を吐くことにする
     * @param num 減らす在庫の数
     * @throws InputException 0か負の数が入力されれば例外を吐く, 在庫の数が結果的に負の数になるならば，在庫を減らさずに例外を吐く
     */
    public void substNumber(int num) throws InputException{
        if(num <= 0) throw new InputException("arg value is minus or zero");
        if(number < num) throw new InputException("arg value is greater than stock number ");
        number -= num;
    }
}