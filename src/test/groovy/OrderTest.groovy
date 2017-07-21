import spock.lang.Specification
import spock.lang.Unroll

class OrderTest extends Specification {
    @Unroll
    def "注文を行う"(){
        setup:
        Warehouse wh = Warehouse.getInstance()
        VoucherList voul = VoucherList.getInstance()
        Order order = new Order(Warehouse.getInstance(), VoucherList.getInstance())
        order.order("勝浦巧", "鬼殺し", 1)
        order.order("藤原巧", "xyz", 2)
        ArrayList<Stock> sl = wh.GetStockList()
        Stock s1 = sl.get(0)
        Stock s2 = sl.get(1)
        ArrayList<Voucher> vl = voul.getVoucherList()
        Voucher v1 = vl.get(0)
        Voucher v2 = vl.get(1)

        expect:
        s1.getNumber() == 1
        s1.getBrand() == "鬼殺し"
        v1.getNumber() == 1
        v1.getBrand() == "鬼殺し"
        v1.getCustomerName() == "勝浦巧"
        v1.getOrderNumber() == 1
        s2.getNumber() == 2
        s2.getBrand() == "xyz"
        v2.getNumber() == 2
        v2.getBrand() == "xyz"
        v2.getCustomerName() == "藤原巧"
        v2.getOrderNumber() == 2
    }

    @Unroll
    def "異常な注文を行う(酒の注文数が0以下)"(){
        when:
        Order order = new Order(Warehouse.getInstance(), VoucherList.getInstance())
        order.order("勝浦巧", "鬼殺し", 0)

        then:
        thrown(InputException)
    }
}