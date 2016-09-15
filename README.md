## petrol

because maintaining strings of sql leads to :shit:

### use it

```java
Petrol.select("*").from("table").toPlainString();
```

will prodoce:

```sql
SELECT * FROM table;
```

"Oh, that's even longer than writing the SQL! Why would you want that?"

Because

```java
String sql = "select m.* " +
    "from flarbs f " +
    "inner join scrabs s on f.id = s.flarbs_id " +
    "outer join medals m on s.medals_id = m.id " +
    "where f.teacher_id = :teacher_id and s.teacher_type_id = 42";
```

:flushed: :gun:

Here's the same query with petrol:

```Java
Petrol.select("m*")
    .from("flarbs f", joins
        -> joins.inner("scrabs s").on("f.id = s.flarbs.id")
            .outer("medals m").on("s.medals_id = m.id")
    .where(conditions
        -> conditions.apply("f.teacher_id = :teacher_id")
            .and("s.teacher_type_id = 42");
```

Incoming features

* better support for sub-queries
* group by
* count
* distinct
* better documentation
