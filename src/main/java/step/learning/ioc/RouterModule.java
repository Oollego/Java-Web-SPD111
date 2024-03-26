package step.learning.ioc;

import com.google.inject.servlet.ServletModule;
import step.learning.servlets.CartServlet;
import step.learning.servlets.HomeServlet;
import step.learning.servlets.PropServlet;
import step.learning.servlets.SignupServlet;
import step.learning.servlets.ItemServlet;
public class RouterModule extends ServletModule {

    @Override
    protected void configureServlets() {
        serve("/").with( HomeServlet.class ) ;
        serve("/cart").with( CartServlet.class );
        serve("/prop").with(PropServlet.class ) ;
        serve("/signup").with(SignupServlet.class) ;
        serve("/item").with(ItemServlet.class) ;


    }

}
