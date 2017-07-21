import java.io.Serializable;

/**
 * ユーザの役割を表すclass
 * Created by kurenaif on 2017/07/21.
 */
public class User implements Serializable{
    private int role;

    /**
     * コンストラクタ roleを指定する
     * 1. 卸売業者
     * 2. 顧客
     * 3. 販売員
     * @param role 役割
     * @throws InputException roleの値が[1,3]以外の場合
     */
    public User(int role) throws InputException{
        if(role <= 0 || 3 < role) throw new InputException("role number error");
        this.role = role;
    }

    /**
     * @return get 役割
     */
    public int getRole() {
        return role;
    }
}