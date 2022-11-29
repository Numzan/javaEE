package edu.whu.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {
	@Autowired
	private GoodsController controller;

	@Test
	public void contextLoads() throws Exception{
		assertThat(controller).isNotNull();
	}

}
