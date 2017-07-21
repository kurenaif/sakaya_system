/**
 * 正しい入力が行われなかった時に吐く例外
 * Created by kurenaif on 2017/07/21.
 */
class InputException extends Exception{
    private static final long serialVersionUID = 1L;

    /**
     * ただエラーメッセージを吐くだけ
     * @param message エラーメッセージ
     */
    InputException(String message){
        super(message);
    }
}