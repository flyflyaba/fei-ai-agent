package com.fei.feiaiagent;

import org.springframework.ai.autoconfigure.vectorstore.pgvector.PgVectorStoreAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = PgVectorStoreAutoConfiguration.class)
public class FeiAiAgentApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeiAiAgentApplication.class, args);
	}

}
