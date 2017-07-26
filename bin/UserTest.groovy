import spock.lang.Specification
import spock.lang.Unroll

class UserTest extends Specification {

	@Unroll
	def "1,2,3が入力された時はその値をそのまま帰す"(){
		setup:
		def user1 = new User(1)
		def user2 = new User(2)
		def user3 = new User(3)

		expect:
		user1.getRole() == 1 && user2.getRole() == 2 && user3.getRole() == 3
	}

	@Unroll
	def "0以下の数字が入力されればInputExceptionを吐く"(){
		when:
		new User(0)

		then:
		thrown(InputException)
	}

	@Unroll
	def "4以上の数字が入力されればInputExceptionを吐く"(){
		when:
		new User(4)

		then:
		thrown(InputException)
	}
}
