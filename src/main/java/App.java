import model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import repository.CustomerRepository;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@EntityScan("model")
@EnableJpaRepositories("repository")
@ComponentScan("controller")
public class App {

    public static final Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        SpringApplication.run(App.class);
    }

    @Bean
    public CommandLineRunner demo(CustomerRepository repository) {
        return (args) -> {
            // save a couple of customers
            repository.save(new Customer("Jack", "Bauer", "131 434 45 53 "));
            repository.save(new Customer("Chloe", "O'Brian", "123 535 12 27"));
            repository.save(new Customer("Kim", "Bauer", "123 531 66 12"));
            repository.save(new Customer("David", "Palmer", "134 513 87 54"));
            repository.save(new Customer("Michelle", "Dessler", "147 361 86 14"));

            // fetch all customers
//            log.info("Customers found with findAll():");
//            log.info("-------------------------------");
//            for (Customer customer : repository.findAll()) {
//                log.info(customer.toString());
//            }
//            log.info("");

            // fetch an individual customer by ID
//            repository.findById(1L)
//                    .ifPresent(customer -> {
//                        log.info("Customer found with findById(1L):");
//                        log.info("--------------------------------");
//                        log.info(customer.toString());
//                        log.info("");
//                    });

            // fetch customers by last name
//            repository.findByLastName("Bauer").forEach(bauer -> {
//                log.info(bauer.toString());
//            });
            // for (Customer bauer : repository.findByLastName("Bauer")) {
            // 	log.info(bauer.toString());
            // }
        };
    }

}
