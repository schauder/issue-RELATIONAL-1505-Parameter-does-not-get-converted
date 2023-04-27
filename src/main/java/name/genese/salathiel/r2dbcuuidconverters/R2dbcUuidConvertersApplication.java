package name.genese.salathiel.r2dbcuuidconverters;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.annotation.Id;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;
import reactor.core.publisher.Flux;

import java.util.UUID;

import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication
public class R2dbcUuidConvertersApplication {
    public static void main(String[] args) {
        final var SOME_UUID_STRING = "e21b62df-12b8-47a1-a083-057f2e279bc3";
        final var context = run(R2dbcUuidConvertersApplication.class, args);
        final var template = context.getBean(R2dbcEntityTemplate.class);
        template.getDatabaseClient()
                .sql("CREATE TABLE IF NOT EXISTS todos(id UUID NOT NULL PRIMARY KEY, next_id UUID)").then()
                .then().block();

        System.out.println("SELECT * FROM todos WHERE id = <UUID>");
        template.select(Todo.class).matching(Query.query(
                Criteria.where("id").is(UUID.fromString(SOME_UUID_STRING))
        )).all().blockLast();

        System.out.println("SELECT * FROM todos WHERE id = <String>");
        template.select(Todo.class).matching(Query.query(
                Criteria.where("id").is(SOME_UUID_STRING)
        )).all().blockLast();

        System.out.println("SELECT * FROM todos WHERE next_id = <UUID>");
        template.select(Todo.class).matching(Query.query(
                Criteria.where("next_id").is(UUID.fromString(SOME_UUID_STRING))
        )).all().blockLast();

        System.out.println("SELECT * FROM todos WHERE next_id = <String>");
        template.select(Todo.class).matching(Query.query(
                Criteria.where("next_id").is(SOME_UUID_STRING)
        )).all().blockLast();
    }

    @Table("todos")
    public record Todo(
            @Id @Column("id") UUID id,
            @Column("parent_id") UUID parentId) {
    }
}
