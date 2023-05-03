package name.genese.salathiel.r2dbcuuidconverters;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table("todos")
public record Todo(
		@Id @Column("id") UUID id,
		@Column("parent_id") UUID parentId) {
}