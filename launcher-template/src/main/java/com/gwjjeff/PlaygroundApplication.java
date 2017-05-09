package com.gwjjeff;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class PlaygroundApplication {

	public static void main(String[] args) {
//		ApplicationContext ctx =  SpringApplication.run(PlaygroundApplication.class, args);

//		SpringApplicationBuilder sb =  new SpringApplicationBuilder(PlaygroundApplication.class);
//		sb.application().setWebEnvironment(false);

		// 法一：
//		SpringApplication app =  new SpringApplication(PlaygroundApplication.class);
//		app.setWebEnvironment(false);
//		app.run(args);

		// 法二：
		new SpringApplicationBuilder(PlaygroundApplication.class).web(false).run(args);

//		for (String name : ctx.getBeanDefinitionNames() ) {
//			System.out.println("- " + name);
//		}
//		System.out.println( ((R) ctx.getBean("r")).getLaunchers().size() );
//		System.exit(0);
	}
}
