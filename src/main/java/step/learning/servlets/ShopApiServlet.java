package step.learning.servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.apache.commons.fileupload.FileItem;
import step.learning.dal.dao.ProductDao;
import step.learning.dal.dao.UserDao;
import step.learning.dal.dto.ProductItem;
import step.learning.dal.dto.User;
import step.learning.services.form.FormParseResult;
import step.learning.services.form.FormParseService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Singleton
public class ShopApiServlet extends HttpServlet {
    private final FormParseService formParseService;
    private final ProductDao productDao;
    private final UserDao userDao;

    @Inject
    public ShopApiServlet(FormParseService formParseService, ProductDao productDao, UserDao userDao) {
        this.formParseService = formParseService;
        this.productDao = productDao;
        this.userDao = userDao;
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FormParseResult parseResult = formParseService.parse(req);
        Map<String,String> fields = parseResult.getFields() ;
        Map<String, FileItem> files = parseResult.getFiles() ;

        String token = req.getParameter("token");
        boolean isTokenValid = userDao.isTokenValid(token);
        if(!isTokenValid){
            sendRest( resp, "error", "403", "Error 403 forbidden", null ) ;
            return;
        }

        String name = fields.get("name");
        if( name == null || name.isEmpty() || name.matches("\\w{4}" )) {
            sendRest( resp, "error", "name", "Property 'name' required", null ) ;
            return ;
        }

        String price = fields.get("price");
        double priceDouble = 0;
        try{
            priceDouble = Double.parseDouble(price);
        }
       catch (Exception ex){
           System.err.println( ex.getMessage() );
       }

        if( price.isEmpty() || priceDouble <= 0) {
            sendRest( resp, "error", "price", "Property 'price' required", null ) ;
            return ;
        }
        String description = fields.get("description");
        if( description == null || description.isEmpty() || name.matches("\\w{4}" ) ) {
            sendRest( resp, "error", "description", "Property 'description' required", null ) ;
            return ;
        }
        ProductItem product = new ProductItem();
        product.setId(UUID.randomUUID());
        product.setName(name);
        product.setPrice(priceDouble);
        product.setDescription(description);

        FileItem image = files.get("image");



        if( image != null ) {

            String path = req.getServletContext().getRealPath("/") +
                    "img" + File.separator + "products" + File.separator;

            int dotPosition = image.getName().lastIndexOf('.');
            if( dotPosition < 0 ) {
                sendRest( resp, "error", "file", "Image file must have extension", null ) ;
                return ;
            }

            boolean isExtWrong = false ;

            String reg = ".+\\.(jpg|png|jpeg|webp)$";
                if (image.getName().matches(reg)) {
                    isExtWrong = true;
                }

            if(!isExtWrong){
                sendRest(resp, "error", "file", "Image file has wrong extension", null);
                return;
            }

            String ext = image.getName().substring( dotPosition );

            String savedName ;
            File savedFile ;
            do {
                savedName = UUID.randomUUID() + ext ;
                savedFile =  new File( path, savedName ) ;
            } while( savedFile.exists() ) ;

            try {
                image.write( savedFile );
                product.setImgPath( savedName );
            }
            catch (Exception ex) {
                System.err.println( ex.getMessage() );
            }
        }

        if( productDao.add(product) ) {
            sendRest(resp, "success", null,"Product added", product.getId().toString() );
        }
        else {
            sendRest( resp, "error", null,"Internal error, look at server's logs", null ) ;
        }
    }


    private void sendRest(HttpServletResponse resp, String status, String target, String message, Object data) throws IOException {
        JsonObject rest = new JsonObject();
        JsonObject meta = new JsonObject();
        meta.addProperty("service", "auth");
        meta.addProperty("status", status);
        if (target == null){
            meta.addProperty("messageTarget", "");
        }else{
            meta.addProperty("messageTarget", target);
        }
        meta.addProperty("message", message);
        meta.addProperty("time", Instant.now().getEpochSecond() );
        rest.add("meta", meta);
        Gson gson = new GsonBuilder().serializeNulls().create();
        rest.add("data", gson.toJsonTree(data));
        resp.getWriter().print(gson.toJson(rest));

    }
}
