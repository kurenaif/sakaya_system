public class Main {
    public static void main(String[] args)
    {
    	Warehouse warehouse = Warehouse.getInstance();
    	VoucherList voucherlist = VoucherList.getInstance();
    	SelectRole selectrole = new SelectRole();

    	while(true){
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
	        else break;
    	}
        return;
    }
}
