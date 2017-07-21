/**
 * Warehouseのストックでエラーが発生した時に使う
 * Created by kurenaif on 2017/07/21.
 */
class DataBaseException extends Exception{
    private static final long serialVersionUID = 1L;

    /**
     * ただエラーメッセージを吐くだけ
     * @param message エラーメッセージ
     */
    DataBaseException(String message){
        super(message);
    }
}