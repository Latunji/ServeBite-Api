package com.osm.servebite.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import java.io.IOException;
import java.sql.SQLException;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
@ComponentScan(basePackages="com.osm.servebite.api")
public class ServeBiteApplication {
	public static void main(String[] args) throws IOException, SQLException {
		SpringApplication.run(ServeBiteApplication.class, args);

	}


//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder){
//		return builder.sources(WalletSystemApplication.class);
//	}

}
