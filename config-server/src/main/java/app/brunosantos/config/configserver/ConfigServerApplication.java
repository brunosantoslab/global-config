/**
 * Configuration server setup for managing centralized configurations across environments.
 *
 * @author Bruno da Silva Santos <contact@brunosantos.app>
 * @since 0.0.1-alpha
 * @see <a href="https://github.com/brunosantoslab/global-config/wiki">Project Documentation</a>
 */
package app.brunosantos.config.configserver;

 import org.springframework.boot.SpringApplication;
 import org.springframework.boot.autoconfigure.SpringBootApplication;
 import org.springframework.cloud.config.server.EnableConfigServer;
 
	/**
	 * Main class for the Config Server application.
	 * <p>
	 * This server is responsible for providing centralized configuration management
	 * for various microservices and environments. It integrates with
	 * Spring Cloud Config and pulls configurations from a centralized repository.
	 * </p>
	 */
 @SpringBootApplication
 @EnableConfigServer
 public class ConfigServerApplication {
 
	 public static void main(String[] args) {
		 SpringApplication.run(ConfigServerApplication.class, args);
	 }
 }
 