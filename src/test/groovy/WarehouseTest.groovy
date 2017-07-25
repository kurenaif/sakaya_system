import spock.lang.Specification
import spock.lang.Unroll

class WarehouseTest extends Specification {
	@Unroll
	def "在庫の生成を行う"(){
		setup:
		def wareHouse = new Warehouse()
		wareHouse.add(new Stock("鬼殺し", 1))
		wareHouse.add(new Stock("xyz", 5))

		expect:
		wareHouse.getStockNumber("鬼殺し") == 1 &&
		wareHouse.getStockNumber("xyz") == 5
	}

	def "在庫の保存，読み込みを行う"(){
		setup:
		def wareHouse = new Warehouse()
		wareHouse.add(new Stock("鬼殺し", 1))
		wareHouse.add(new Stock("xyz", 5))
		wareHouse.saveFile()
		def wareHouse2 = new Warehouse()
		wareHouse2.loadFile()

		expect:
		wareHouse2.getStockNumber("鬼殺し") == 1 &&
				wareHouse2.getStockNumber("xyz") == 5
	}

	def "在庫の追加を行う"(){
		setup:
		def wareHouse = new Warehouse()
		wareHouse.add(new Stock("鬼殺し", 1))
		wareHouse.add(new Stock("鬼殺し", 1))

		expect:
		wareHouse.getStockNumber("鬼殺し") == 2
	}

	def "在庫の減算を行う"(){
		setup:
		def wareHouse = new Warehouse()
		wareHouse.add(new Stock("鬼殺し", 1))
		wareHouse.subst(new Stock("鬼殺し", 1))

		expect:
		wareHouse.getStockNumber("鬼殺し") == 0
	}

	def "0以下の数字をaddするとInputException"(){
		when:
		def wareHouse = new Warehouse()
		wareHouse.add(new Stock("鬼殺し", 0))

		then:
		thrown(InputException)
	}

	def "0以下の数字をsubstするとInputException"(){
		when:
		def wareHouse = new Warehouse()
		wareHouse.subst(new Stock("鬼殺し", 0))

		then:
		thrown(InputException)
	}

	def "在庫が負の数になるような数を入力するとInputException"(){
		when:
		def wareHouse = new Warehouse()
		wareHouse.add(new Stock("鬼殺し", 1))
		def stock = new Stock("鬼殺し", 2)
		wareHouse.subst(stock)

		then:
		thrown(InputException)
	}
}
