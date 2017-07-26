import java.io.IOException;

public class Main {
    public static void main(String[] args)
    {
    	Warehouse warehouse = Warehouse.getInstance();
    	VoucherList voucherlist = VoucherList.getInstance();
    	
    	try {
			warehouse.loadFile();
			voucherlist.loadFile();
		} catch (IOException | InputException | DataBaseException e) {
			e.printStackTrace();
		}
    	
    	while(true){
    		SelectRole selectrole = new SelectRole();
    		if(! selectrole.selectRole()) break;
	        if(selectrole.getUser().getRole() == 1){//卸売業者の場合
	        	ArriveControl arv = new ArriveControl(warehouse);
	        	arv.doArrive();
			} else if(selectrole.getUser().getRole() == 2){//顧客の場合
	        	Customer customer = new Customer(warehouse, voucherlist);
	        	customer.doOrder();
	        } else if(selectrole.getUser().getRole() == 3){//販売員の場合
				SalesPerson sales = new SalesPerson(warehouse,voucherlist);
				sales.selection();
			}
    	}
    	try {
			warehouse.saveFile();
			voucherlist.saveFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
        return;
    }
}
