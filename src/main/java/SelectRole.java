import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SelectRole {
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
	}

	public User getUser() {
		return user;
	}
}
