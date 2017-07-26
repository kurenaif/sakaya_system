import spock.lang.Specification
import spock.lang.Unroll

class ArrivalTest extends Specification {
	def "正常な入荷を行う(生成)"(){
		setup:
		Warehouse warehouse = Warehouse.getInstance()
		warehouse.clear()
		Arrival arrival = new Arrival(warehouse)
		arrival.arrive("鬼殺し", 1)

		expect:
		warehouse.getStockNumber("鬼殺し") == 1
	}

	def "正常な入荷を行う(追加)"(){
		setup:
		Warehouse warehouse = Warehouse.getInstance()
		warehouse.clear()
		Arrival arrival = new Arrival(warehouse)
		arrival.arrive("鬼殺し", 1)
		arrival.arrive("鬼殺し", 5)

		expect:
		warehouse.getStockNumber("鬼殺し") == 6
	}

	def "異常な入荷を行う(0例外)"(){
		when:
		Warehouse warehouse = Warehouse.getInstance()
		warehouse.clear()
		Arrival arrival = new Arrival(warehouse)
		arrival.arrive("鬼殺し", 0)

		then:
		thrown(InputException)
	}
}
