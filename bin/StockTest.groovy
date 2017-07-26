import spock.lang.Specification
import spock.lang.Unroll

class StockTest extends Specification {
	@Unroll
	def "本数が0よりおおきいの値が入力されればそのまま帰す"(){
		setup:
		def stock = new Stock("鬼殺し",1)

		expect:
		stock.brand == "鬼殺し" && stock.number == 1
	}

	@Unroll
	def "本数が0以下の値が入力されれば例外"(){
		when:
		new Stock("鬼殺し",0)

		then:
		thrown(InputException)
	}

	@Unroll
	def "ストックの追加を行う"(){
		setup:
		def stock = new Stock("鬼殺し", 1)
		stock.addNumber(1)

		expect:
		stock.getNumber() == 2
	}

	@Unroll
	def "ストックに追加する数が0以下であれば例外"(){
		when:
		def stock = new Stock("鬼殺し", 1)
		stock.addNumber(0)

		then:
		thrown(InputException)
	}

	@Unroll
	def "ストックの減算を行う(0になってもOK)"(){
		setup:
		def stock = new Stock("鬼殺し", 1)
		stock.substNumber(1)

		expect:
		stock.getNumber() == 0
	}

	@Unroll
	def "ストックに減算する数が0以下であれば例外"(){
		when:
		def stock = new Stock("鬼殺し", 1)
		stock.substNumber(0)

		then:
		thrown(InputException)
	}
}

