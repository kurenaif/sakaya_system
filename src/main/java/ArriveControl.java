/**
 * ArriveControl 入荷コントローラに関するクラス
 * Created by nakayama on 2017/07/23
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ArriveControl {
    private Warehouse warehouse;
    private Arrival arrival;

    public ArriveControl(Warehouse warehouse){
        this.warehouse = warehouse;
        arrival = new Arrival(warehouse);
    }

    /**
     * 完了メッセージ
     * @param brand 酒の銘柄
     * @param number 酒の本数
     */
    private void finishArrive(String brand,int number){
        int wareNumber = 0;

        System.out.println("------------------------------------------------------------");
        System.out.println(brand+"が"+number+"本入荷されました．");

        try{
            wareNumber = warehouse.getStockNumber(brand);
        } catch(InputException e){
            e.printStackTrace();
        }

        System.out.println("倉庫内の"+brand+"の在庫本数"+wareNumber);
        System.out.println("------------------------------------------------------------");

        /*
        try {
            warehouse.saveFile();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    /**
     * 確認メッセージ
     * @param brand 酒の銘柄
     * @param number 酒の本数
     */
    private void checkArrive(String brand,int number){
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        int select;

        System.out.println("------------------------------------------------------------");
        System.out.println(brand+"を"+number+"本，入荷依頼を行いますか？");
        System.out.println("1. はい");
        System.out.println("2. いいえ");
        System.out.print("数字を入力してください：");
        while(true){
            try {
                select = Integer.parseInt(br.readLine());
                if(select==1){//はい
                    try {
                        arrival.arrive(brand, number);
                    } catch (InputException e) {
                        e.printStackTrace();
                    }

                    System.out.println("------------------------------------------------------------");
                    System.out.println(number+"本の"+brand+"の入荷依頼が完了いたしました．");
                    System.out.println("------------------------------------------------------------");
                    finishArrive(brand,number);
                    break;
                }
                else if(select==2){//いいえ
                    doArrive();//もう一度入力を促す。
                    break;
                }
                else{//1か2以外
                    System.out.println("------------------------------------------------------------");
                    System.out.println("正しい入力が行われませんでした．");
                    System.out.println("もう一度入力をお願いします．");
                    System.out.println("------------------------------------------------------------");
                }
            }
            catch (IOException | NumberFormatException e){
                System.out.println("------------------------------------------------------------");
                System.out.println("正しい入力が行われませんでした．");
                System.out.println("もう一度入力をお願いします．");
                System.out.println("------------------------------------------------------------");
            }
        }
    }

    /**
     * 入荷を実際に行う
     */
    public void doArrive(){
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String brand;
        int number;

        System.out.println("------------------------------------------------------------");
        System.out.println("酒の入荷依頼を行います．");
        System.out.println("入荷依頼を行う酒の情報を入力してください．");

        System.out.print("入荷を依頼する酒の銘柄：");
        //Scanner scan = new Scanner(System.in);
        while(true){
            try {
                brand = br.readLine();
                break;
            }
            catch (IOException e){
                System.out.println("------------------------------------------------------------");
                System.out.println("正しい入力が行われませんでした．");
                System.out.println("もう一度入力をお願いします．");
                System.out.println("------------------------------------------------------------");
            }
        }

        System.out.print("入荷依頼本数：");

        while(true){
            try {
                number = Integer.parseInt(br.readLine());
                break;
            }
            catch (IOException e){
                System.out.println("------------------------------------------------------------");
                System.out.println("正しい入力が行われませんでした．");
                System.out.println("もう一度入力をお願いします．");
                System.out.println("------------------------------------------------------------");
            }
        }

        System.out.println("------------------------------------------------------------");

        checkArrive(brand,number);

    }
}
