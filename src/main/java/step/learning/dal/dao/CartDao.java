package step.learning.dal.dao;

import com.google.inject.Inject;
import step.learning.dal.dto.CartItem;
import step.learning.dal.dto.ProductItem;
import step.learning.dal.dto.User;
import step.learning.services.db.DbService;

import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class CartDao {

    private final DbService dbService;


    @Inject
    public CartDao(DbService dbService) {
        this.dbService = dbService;
    }
    public List<CartItem> getCart() {
        return Arrays.asList( new CartItem[]{
                new CartItem(UUID.randomUUID(), UUID.randomUUID(), 1),
                new CartItem(UUID.randomUUID(), UUID.randomUUID(), 2),
                new CartItem(UUID.randomUUID(), UUID.randomUUID(),3),
        });
    }

    public void add(String userId, String productId, int cnt){
        String sql = String.format("SELECT cart_id FROM carts WHERE cart_user='%s' AND cart_status=0",
                userId);
        try(Statement statement = dbService.getConnection().createStatement()){
            String cartId = null;
            ResultSet res = statement.executeQuery(sql);
            if( res.next() ){
                cartId = res.getString(1);
            }
            else{
                cartId = UUID.randomUUID().toString();
                sql= String.format("INSERT INTO carts(cart_id, cart_user, cart_date, cart_status) VALUES ('%s', '%s', CURRENT_TIMESTAMP,0)",
                        cartId, userId);
                statement.executeUpdate(sql);
            }

            sql = String.format("SELECT cart_dt_cnt FROM carts_details WHERE cart_id = '%s' AND product_id = '%s'",
                    cartId, productId);
            res = statement.executeQuery(sql);
            if( res.next() ){
                cnt += res.getInt(1);
                sql = String.format("UPDATE cart_dt_cnt SET cart_dt_cnt = %d WHERE cart_id = '%s' AND product_id = '%s'",
                 cnt, cartId, productId);
            }
            else{
                sql = String.format(
                        "INSERT INTO carts_details(cart_dt_id,cart_id,product_id,cart_dt_cnt ) " +
                                "VALUES( UUID(), '%s', '%s', %d )",
                        cartId, productId, cnt);
            }
            statement.executeUpdate(sql);

        }
         catch (SQLException ex) {
            System.err.println( ex.getMessage() );
            System.out.println( sql );

        }
    }
}
