package com.carreservation.catalogservice;

import com.carreservation.catalogservice.controller.CatalogController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CatalogServiceApplicationTests {

	@Autowired
	private CatalogController catalogController;

	@Test
	public void contextLoads() throws Exception {
		assertThat(catalogController).isNotNull();
	}
}