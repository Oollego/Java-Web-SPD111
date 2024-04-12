package step.learning.ioc;

import com.google.inject.servlet.ServletModule;
import step.learning.filtres.CharsetFilter;
import step.learning.servlets.*;

public class RouterModule extends ServletModule {

    @Override
    protected void configureServlets() {
        filter("/*").through ( CharsetFilter.class ) ;
        
        serve("/").with( HomeServlet.class ) ;
        serve("/auth").with(AuthServlet.class);
        serve("/cart").with( CartServlet.class );
//        serve("/product").with(PropServlet.class ) ;
        serve("/product").with(PropServlet.class ) ;
        serve("/shop").with(ShopServlet.class) ;
        serve("/item").with(ItemServlet.class) ;

        serve("/shop-api").with( ShopApiServlet.class );


    }

}
