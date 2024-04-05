package step.learning.ioc;

import com.google.inject.servlet.ServletModule;
import step.learning.servlets.*;

public class RouterModule extends ServletModule {

    @Override
    protected void configureServlets() {
        serve("/").with( HomeServlet.class ) ;
        serve("/auth").with(AuthServlet.class);
        serve("/cart").with( CartServlet.class );
        serve("/prop").with(PropServlet.class ) ;
        serve("/signup").with(SignupServlet.class) ;
        serve("/item").with(ItemServlet.class) ;


    }

}
