import spock.lang.Specification
import spock.lang.Unroll

class VoucherTest extends Specification {
	@Unroll
	def "伝票を作成する"(){
		setup:
		Voucher voucher = new Voucher(1, "勝浦巧", "鬼殺し", 1)

		expect:
		voucher.getOrderNumber() == 1 && voucher.getCustomerName() == "勝浦巧" && voucher.getBrand() == "鬼殺し" && voucher.getNumber() == 1
	}

	def "伝票を作成する(numberが負の時に例外)"(){
		when:
		new Voucher(1, "勝浦巧", "鬼殺し", -1)

		then:
		thrown(InputException)
	}
}
