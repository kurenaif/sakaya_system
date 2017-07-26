import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SelectRole  {
	private User user;

	/**
	 * コンストラクタ 役割をキーボードからの入力で決定する
	 */
	public SelectRole(){
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
    	String buf;
    	int num;
    	System.out.println("-------------------------------------------------");
    	System.out.print("☆️酒屋在庫管理システム");
    	System.out.println("１．卸売業者");
    	System.out.println("２．顧客");
    	System.out.println("３．販売員");
    	System.out.println("４．終了");
        System.out.print("あなたの役割の数字を入力をしてください：");
	        while(true){//役割選択
		        try{
		        	
		        	while(true){
			        	try{
				        	buf = br.readLine();
				        	num = Integer.parseInt(buf);	
				        	if(num == 1){
				        		System.out.println("本当に卸売業者でよいですか？");
				        	} else if(num == 2){
				        		System.out.println("本当に顧客でよいですか？");
				        	} else if(num == 3){
				        		System.out.println("本当に販売員でよいですか？");
				        	} else if(num == 4){
				        		System.out.println("本当に終了でよいですか？");
				        	} else{
				        		throw new InputException("str");
				        	}
				        	System.out.println("１．はい");
				        	System.out.println("２．いいえ");
				        	System.out.print("数字を入力してください：");
				        	buf = br.readLine();
				        	num = Integer.parseInt(buf);
				        	if(num == 1) break;
				        	else System.out.println("もう一度入力をお願いします.");
			        	} catch(IOException | NumberFormatException | InputException e){
			        		System.out.println("正しい入力が行われませんでした.");
			        		System.out.println("もう一度入力をお願いします.");
			        	}		    
			        	
		        	}
		        	user = new User(num);
		        	break;
		        } catch(InputException e){//入力が規定外
		        	System.out.println("入力が規定外です．1,2,3のどれかを入力してください．");
				}
	        }
	        
	        try {
				user = new User(num);
			} catch (InputException e) {
				e.printStackTrace();
			}
        System.out.println("-------------------------------------------------");	
	}

	public User getUser() {
		return user;
	}
}
