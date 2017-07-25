import spock.lang.Specification
import spock.lang.Unroll

class OrderTest extends Specification {
    @Unroll
    def "注文を行う"(){
        setup:
        Warehouse wh = Warehouse.getInstance()
        VoucherList voul = VoucherList.getInstance()
        Order order = new Order(Warehouse.getInstance(), VoucherList.getInstance())
		wh.add(new Stock("鬼ごろし", 1))
		order.order("勝浦巧", "鬼ごろし", 1)

        expect:
		wh.getStockNumber("鬼ごろし") == 0
		voul.getVoucherList()[0].getCustomerName() == "勝浦巧"
		voul.getVoucherList()[0].getBrand() == "鬼ごろし" 
		voul.getVoucherList()[0].getNumber() == 1
    }

    @Unroll
    def "異常な注文を行う(酒の注文数が0以下)"(){
        when:
        Order order = new Order(Warehouse.getInstance(), VoucherList.getInstance())
        order.order("勝浦巧", "鬼殺し", 0)

        then:
        thrown(InputException)
    }
	
	@Unroll
	def "異常な注文を行う(在庫切れ)"(){
		when:
		Warehouse wh = Warehouse.getInstance()
		Order order = new Order(Warehouse.getInstance(), VoucherList.getInstance())
		wh.add(new Stock("鬼殺し", 1))
		order.order("勝浦巧", "鬼殺し", 2)
		then:
		thrown(InputException)
	}

	@Unroll
	def "異常な注文を行う(在庫が存在しない)"(){
		when:
		Warehouse wh = Warehouse.getInstance()
		Order order = new Order(Warehouse.getInstance(), VoucherList.getInstance())
		wh.add(new Stock("鬼殺し", 1))
		order.order("勝浦巧", "おにごろし", 2)
		then:
		thrown(InputException)
	}
}