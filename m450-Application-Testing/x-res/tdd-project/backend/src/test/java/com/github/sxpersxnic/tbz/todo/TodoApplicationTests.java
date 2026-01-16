package com.github.sxpersxnic.tbz.todo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class TodoApplicationTests {

	@Test
	void contextLoads() {
	}

}
