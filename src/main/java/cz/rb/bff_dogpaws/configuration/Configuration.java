package cz.rb.bff_dogpaws.configuration;

import cz.rb.projectcommon.config.CommonConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@org.springframework.context.annotation.Configuration
@ComponentScan("cz.rb.bff_dogpaws")
@EnableWebMvc
@Import({
        CommonConfig.class
})
public class Configuration {


}
