package step.learning.dal.dao;

import com.google.inject.Inject;
import step.learning.dal.dto.ProductItem;
import step.learning.services.db.DbService;

import java.sql.PreparedStatement;
import java.sql.SQLException;
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

    public ProductItem[] GetPropItems(){
        ProductItem PropItem1 = new ProductItem(
                UUID.randomUUID(),
                "УМБ XO Power Bank 20000mAh PR183 Light",
                "336305793.webp",
                3000,
                2999
                );

        ProductItem PropItem2 = new ProductItem(
                UUID.randomUUID(),
                "Фітнес-браслет Xiaomi Smart Band 8 Black",
                "367323203.webp",
                5000,
                4000
        );
        ProductItem PropItem3 = new ProductItem(
                UUID.randomUUID(),
                "Смарт-годинник Huawei Watch GT3 42",
                "368324204.png",
                6000,
                5000
        );

        return new ProductItem[]{
                PropItem1, PropItem2, PropItem3
        };
    }
}
