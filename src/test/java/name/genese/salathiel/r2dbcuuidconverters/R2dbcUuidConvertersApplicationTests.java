package name.genese.salathiel.r2dbcuuidconverters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;

import java.util.UUID;

@SpringBootTest
class R2dbcUuidConvertersApplicationTests {
	static final String SOME_UUID_STRING = "e21b62df-12b8-47a1-a083-057f2e279bc3";

	@Autowired
	R2dbcEntityTemplate template;

	@BeforeEach
	void setup() {
		template.getDatabaseClient()
				.sql("CREATE TABLE IF NOT EXISTS todos(id UUID NOT NULL PRIMARY KEY, next_id UUID)").then()
				.then().block();
	}

	@Test
	void byIdUuid() {


		System.out.println("SELECT * FROM todos WHERE id = <UUID>");
		template.select(Todo.class).matching(Query.query(
				Criteria.where("id").is(UUID.fromString(SOME_UUID_STRING))
		)).all().blockLast();


	}

	@Test
	void byIdString() {

		System.out.println("SELECT * FROM todos WHERE id = <String>");
		template.select(Todo.class).matching(Query.query(
				Criteria.where("id").is(SOME_UUID_STRING)
		)).all().blockLast();


	}

	@Test
	void byNextIdUuid() {

		System.out.println("SELECT * FROM todos WHERE next_id = <UUID>");
		template.select(Todo.class).matching(Query.query(
				Criteria.where("next_id").is(UUID.fromString(SOME_UUID_STRING))
		)).all().blockLast();


	}

	@Test
	void byNextIdString() {


		System.out.println("SELECT * FROM todos WHERE next_id = <String>");
		template.select(Todo.class).matching(Query.query(
				Criteria.where("next_id").is(SOME_UUID_STRING)
		)).all().blockLast();

	}

}
