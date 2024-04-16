package step.learning.dal.dao;

import com.google.inject.Inject;
import step.learning.dal.dto.ProductItem;
import step.learning.services.db.DbService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProductDao {

    private final DbService dbService;


    @Inject
    public ProductDao(DbService dbService) {
        this.dbService = dbService;
    }
    public boolean add( ProductItem product ) {
        if( product == null ) return false ;
        if( product.getId() == null ) product.setId( UUID.randomUUID() );

        String sql = "INSERT INTO Products" +
                "(product_id,product_name,product_price,product_description,product_image ) " +
                "VALUES(?,?,?,?,?)";
        try( PreparedStatement prep = dbService.getConnection().prepareStatement(sql) ) {
            prep.setString( 1, product.getId().toString() );   // у JDBC відлік від 1
            prep.setString( 2, product.getName() );
            prep.setDouble( 3, product.getPrice() );
            prep.setString( 4, product.getDescription() );
            prep.setString( 5, product.getImgPath() );
            prep.executeUpdate();
            return true;
        }
        catch (SQLException ex) {
            System.err.println( ex.getMessage() );
            System.out.println( sql );
            return false ;
        }
    }
    public List<ProductItem> getList(int skip, int take){
            String sql = String.format("SELECT * FROM Products LIMIT %d, %d", skip, take);

            List<ProductItem> result = new ArrayList<>();

            try( Statement statement = dbService.getConnection().createStatement()){
                ResultSet resultSet = statement.executeQuery(sql);
                while(resultSet.next()){
                    result.add( new ProductItem(resultSet) ) ;
                }
            }
            catch (SQLException ex) {
                System.err.println( ex.getMessage() );
                System.out.println( sql );

            }
            return result;
    }

    public ProductItem[] GetPropItems(){
        ProductItem PropItem1 = new ProductItem(
                UUID.randomUUID(),
                "УМБ XO Power Bank 20000mAh PR183 Light",
                "336305793.webp",
                "image",
                3000.0

                );

        ProductItem PropItem2 = new ProductItem(
                UUID.randomUUID(),
                "Фітнес-браслет Xiaomi Smart Band 8 Black",
                "367323203.webp",
                "image",
                5000.0
        );
        ProductItem PropItem3 = new ProductItem(
                UUID.randomUUID(),
                "Смарт-годинник Huawei Watch GT3 42",
                "368324204.png",
                "image",
                4000.0
        );

        return new ProductItem[]{
                PropItem1, PropItem2, PropItem3
        };
    }
}
