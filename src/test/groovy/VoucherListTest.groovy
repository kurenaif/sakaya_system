import spock.lang.Specification
import spock.lang.Unroll

class VoucherListTest extends Specification {
	@Unroll
	def "伝票の追加を,clean,file save, file loadのテスト"(){
		setup:
		VoucherList vl = VoucherList.getInstance()
		vl.clear()
		vl.add("勝浦巧","鬼殺し",1)
		vl.add("勝浦巧","xyz",5)
		Voucher v1 = vl.getVoucherList().get(0)
		Voucher v2 = vl.getVoucherList().get(1)
		vl.saveFile()
		vl.clear()
		ArrayList<Voucher> va = (ArrayList<Voucher>)vl.getVoucherList().clone()
		vl.loadFile()
		Voucher v3 = vl.getVoucherList().get(0)
		Voucher v4 = vl.getVoucherList().get(1)

		expect:
		v1.orderNumber == 1 &&
		v1.getCustomerName() == "勝浦巧"
				v1.getBrand() == "鬼殺し"
				v1.getNumber() == 1
				v2.orderNumber == 2
				v2.getCustomerName() == "勝浦巧"
				v2.getBrand() == "xyz"
				v2.getNumber() == 5
				va.isEmpty()
		v3.orderNumber == 1
				v3.getCustomerName() == "勝浦巧"
				v3.getBrand() == "鬼殺し"
				v3.getNumber() == 1
				v4.orderNumber == 2
				v4.getCustomerName() == "勝浦巧"
				v4.getBrand() == "xyz"
				v4.getNumber() == 5
	}

	@Unroll
	def "酒の数が0以下でInputException"(){
		when:
		VoucherList vl = VoucherList.getInstance()
		vl.add("勝浦巧","鬼殺し",0)

		then:
		thrown(InputException)
	}
}

