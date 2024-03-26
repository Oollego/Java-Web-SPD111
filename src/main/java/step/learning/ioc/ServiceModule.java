package step.learning.ioc;

import com.google.inject.AbstractModule;
import step.learning.services.db.DbService;
import step.learning.services.db.MySqlService;
import step.learning.services.form.FormParseService;
import step.learning.services.form.HybridFormParser;
import step.learning.services.hash.HashService;
import step.learning.services.hash.Md5HashService;
import step.learning.services.kdf.HashKdfService;
import step.learning.services.kdf.KdfService;

public class ServiceModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(HashService.class).to(Md5HashService.class);
        bind(DbService.class).to(MySqlService.class);
        bind(FormParseService.class).to(HybridFormParser.class);
        bind(KdfService.class).to(HashKdfService.class);
    }
}
