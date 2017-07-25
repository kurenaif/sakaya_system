import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Customer {
	Order order;
	
	public Customer(Warehouse warehouse, VoucherList voucherlist){
    	order = new Order(warehouse, voucherlist);
	}
	
	/**
	 * 注文・出荷をするメソッド
	 */
	public void doOrder(){
		InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
    	String numbuf, selectbuf;
    	int number, select, orderNum;
    	String name, brand;

   
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
			if(Warehouse.getInstance().getStockNumber(brand) == 0){//在庫が0の場合
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
}
