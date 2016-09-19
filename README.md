# Petrol

Because maintaining strings of SQL leads to :shit:

[![](https://jitpack.io/v/jguest/petrol.svg)](https://jitpack.io/#jguest/petrol)

**Warning**: this library is under active development and is not ready for production use.

## Install

Maven + Jitpack:

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
<dependencies>
    <dependency>
        <groupId>com.github.jguest</groupId>
        <artifactId>petrol</artifactId>
        <version>v0.1.0</version>
        <exclusions>
            <exclusion>
                <artifactId>hibernate-jpa-2.0-api</artifactId>
                <groupId>org.hibernate.javax.persistence</groupId>
            </exclusion>
        </exclusions>
    </dependency>
</dependencies>
```

## Use It

### As a query builder

```java
new Query().select("*").from("table").toPlainString();
```

will produce the following query:

```sql
SELECT * FROM table
```

*"Oh, that's even longer than writing the SQL! Why would you want that?"* 

Oh, because:

```java
String sql = "select s.* " +
   "from flarbs f " + // <-- eww multiline concatenation
   "inner join scrabs s on f.id = s.flarbs_id " + // <-- accidentally miss a space and you die
   "where f.teacher_type_id = 42";
```

:flushed: :gun:

And that was the neatest string of SQL I could find.

Here's the same query built with petrol:

```Java
String sql = new Query()
   .select("s.*")
   .from("flarbs f", table ->
      table.innerJoin("scrabs s").on("f.id = s.flarbs_id"))
   .where("f.teacher_type_id = 42")
   .toPlainString();
```

*"That's nice, but why not use an ORM?"* I like ORMs for writes but prefer plain ol' SQL for reads.

### As a JPA interface

Instantiate `Petrol`, bringing your own `javax.persistence.EntityManager`. Petrol's `#stream` method accepts a class (entity) and query function and will return a `java.util.stream.Stream`. Use built in Java 8 methods to act on the result. Here's a complete example:

```Java
// instantiate petrol
Petrol db = new Petrol(myEntityManager);

// build the query and execute it
db.stream(Sloth.class, query ->
   query.select("*")
      .from("sloths")
      .where("s.name = 'Kristen Bell'")
   )
   .findFirst() // Optional<Sloth>
```

## Incoming Features

* parameter substitution
* better support for sub-queries
* group by
* count
* distinct
* better documentation
