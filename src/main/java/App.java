import model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import repository.CustomerRepository;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@EntityScan("model")
@EnableJpaRepositories("repository")
@ComponentScan("controller")
public class App extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(App.class);
    }

    public static final Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        SpringApplication.run(App.class);
    }

    @Bean
    public CommandLineRunner demo(CustomerRepository repository) {
        return (args) -> {
            // save a couple of customers
            repository.save(new Customer("Jack", "Bauer", "1314344553"));
            repository.save(new Customer("Chloe", "O'Brian", "1235351227"));
            repository.save(new Customer("Kim", "Bauer", "1235316612"));
            repository.save(new Customer("David", "Palmer", "1345138754"));
            repository.save(new Customer("Test", "Test", "3809230405"));
            repository.save(new Customer("Firstname", "Lastname", "3802231121"));

        };
    }
}
