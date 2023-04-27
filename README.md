# Minimum Viable Example: `r2dbc-uuid-converters`

This is a MVE showing an issue with UUID R2DBC converter in a Spring application, using PostgreSQL drivers.

Whe can have criteria on UUID columns. In the Spring `Criteria` objects, we can have values as `String` or `UUID`.

**OBSERVATION:** When the `Criteria` apply to an `@Id` column, it works just fine, whether the valus of the `Criteria`
is `String` or `UUID`. But any other columns?! Nope. Bad grammar.

> In this MVE, I enabled logging of generated SQL query.
> Here, the query is always `SELECT todos.* FROM todos WHERE todos.<column> = $1`
> although in my real project, it is `SELECT todos.* FROM todos WHERE UPPER(todos.<column>) = UPPER($1)`.
> But `UPPER` only apply to strings/varying characters, so the underlying issue remains the same:
> 
> `operator does not exist: uuid = character varying`

The question is how to address this issue (without hacking my way around it).
