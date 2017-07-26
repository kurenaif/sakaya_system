import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SelectRole  {
	private User user;
	
	private int role() throws InputException, IOException, NumberFormatException {
		System.out.println("-------------------------------------------------");
    	System.out.println("☆️酒屋在庫管理システム");
    	System.out.println("１．卸売業者");
    	System.out.println("２．顧客");
    	System.out.println("３．販売員");
    	System.out.println("４．終了");
        System.out.print("あなたの役割の数字を入力をしてください：");
        
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
    	String buf = null;
		buf = br.readLine();
    	int num = Integer.parseInt(buf);	
    	
    	if(num < 1 || 4 < num) throw new InputException("Input Number Error");
    	
    	return num;

	}
	
	private boolean check(int num) throws InputException, NumberFormatException, IOException{
    	String arr[] = {"卸売業者", "顧客", "販売員", "終了"};
    	
    	System.out.println("-------------------------------------------------");
    	if(1 <= num && num <= 4) System.out.println("本当に "+arr[num-1]+"で良いですか?");
    	else throw new InputException("Input Number Error");
    	
    	System.out.println("１．はい");
    	System.out.println("２．いいえ");
    	System.out.print("数字を入力してください：");
    	
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
    	String buf = null;
    	buf = br.readLine();
		
		int isOk = Integer.parseInt(buf);
		if(isOk == 1) return true;
		else if(isOk == 2) return false;
		else throw new InputException("Yes No Input Error");
	}

	public boolean selectRole(){
		int num;
		while(true){
			while(true){
				try{
					num = role();
					break;
				} catch (NumberFormatException | InputException | IOException e) {
					System.out.println("正しい入力が行われませんでした.");
					System.out.println("もう一度入力をお願いします.");

				}
			}
		
			while(true){
				try {	
					boolean isOk = check(num);
					if(!isOk) break;
					if(num == 4 && isOk) return false;
					// numが確定
					user = new User(num);
					return true;
				} catch (NumberFormatException | InputException | IOException e) {
					System.out.println("正しい入力が行われませんでした.");
					System.out.println("もう一度入力をお願いします.");

				}
			}
		}
	}

	public User getUser() {
		return user;
	}
}
