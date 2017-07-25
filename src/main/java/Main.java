import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {

    	User user;
    	Warehouse warehouse = Warehouse.getInstance();
		VoucherList voucherlist = VoucherList.getInstance();
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
    	String buf, numbuf, selectbuf;
    	int num;

    	System.out.println("-------------------------------------------------");
        System.out.println("役割を選択してください．　1:卸売業者，2:顧客，3:販売員");
	        while(true){//役割選択
		        try{
		        	buf = br.readLine();
		        	num = Integer.parseInt(buf);
		        	user = new User(num);
		        	break;
		        } catch(IOException e){//入力がおかしい
		        	System.out.println("入力が規定外です．1,2,3のどれかを入力してください．");
		        } catch(NumberFormatException e){//入力が数字でない
					System.out.println("入力が規定外です．1,2,3のどれかを入力してください．");
				} catch(InputException e) {//入力が規定外（1,2,3）ではない
					System.out.println("入力が規定外です．1,2,3のどれかを入力してください．");
				}
	        }
        System.out.println("-------------------------------------------------");



        if(user.getRole() == 1){//卸売業者の場合
        	ArriveControl arv = new ArriveControl(warehouse);
        	arv.doArrive();
		}

        /**
         * 顧客の操作
         * Created by matsuzaki on 2017/07/22.
         */
        else if(user.getRole() == 2){//顧客の場合
        	Order order = new Order(warehouse, voucherlist);
        	String name, brand;
        	int number, select, orderNum;

		        System.out.println("注文情報を入力してください．");
		        System.out.print("あなたの名前：");
		        while(true){
			        try{
			        	name = br.readLine();
			        	break;
			        } catch(IOException e){
			        	System.out.println("入力が規定外です．正しい入力をしてください．");
			        }
		        }

		        System.out.print("注文する酒の銘柄：");
		        while(true){
		        	try{
		        		brand = br.readLine();
		        		break;
		        	} catch(IOException e){

		        	}
		        }

		        System.out.print("本数：");
		        while(true){
			        try{
			        	numbuf = br.readLine();
			        	number = Integer.parseInt(numbuf);
			        	break;
			        } catch(IOException | NumberFormatException e){
			        	System.out.println("入力が規定外です．正しい入力をしてください．");
			        	System.out.print("本数：");
			        }
		        }


		        System.out.println("-------------------------------------------------");
		        System.out.println(number + "本の" + brand + "の注文を行いますか？");
		        System.out.println("1．はい");
		        System.out.println("2．いいえ");
		        System.out.print("数字を入力してください：");
		        while(true){
			        try{
			        	selectbuf = br.readLine();
			        	select = Integer.parseInt(selectbuf);
			        	if(select < 1 || select > 2) System.out.println("入力が規定外です．1,2のどちらかを入力をしてください．");
			        	else break;
			        } catch(IOException | NumberFormatException e){
			        	System.out.println("入力が規定外です．1,2のどちらかを入力をしてください．");
			        }
		        }

		        if(select == 2){
		        	System.out.println("-------------------------------------------------");
		        	System.out.println("注文がキャンセルされました．");
		        	System.out.println("-------------------------------------------------");
		        	return;
		       	}
		        System.out.println("-------------------------------------------------");

		        try {
					if(warehouse.getStockNumber(brand) == 0){//在庫が0の場合
						System.out.println("大変申し訳ありませんが，倉庫内に在庫がございませんでした．");
						System.out.println("そのため出荷できませんでした．");
						System.out.println("-------------------------------------------------");
						return;
					}
					orderNum = order.order(name, brand, number); //在庫がある場合
				} catch(InputException e){//在庫に銘柄が存在しない場合
		        	System.out.println("大変申し訳ありませんが，倉庫内に在庫がございませんでした．");
	        		System.out.println("そのため出荷できませんでした．");
	        		System.out.println("-------------------------------------------------");
	        		return;
		        } catch(DataBaseException e){
		        	System.out.println("データベースが正しく読み込まれませんでした．");
		        	System.out.println("-------------------------------------------------");
		        	return;
		        }


        	System.out.println("出荷が完了しました．");
        	System.out.println("注文番号:" + orderNum);
        	System.out.println("あなたの名前:" + name);
        	System.out.println("注文した酒の銘柄:" + brand);
        	System.out.println("注文本数:" + number);
        	System.out.println("-------------------------------------------------");
        }
        else{//販売員の場合
			SalesPerson sales = new SalesPerson(warehouse,voucherlist);
			sales.selection();
		}
    }
}
