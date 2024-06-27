# A Jakarta Persistence @Inheritance example (Single Table)

This example demonstrates an @Inheritance example with Single Table strategy.

The SINGLE_TABLE strategy maps records from the same database table to different entity classes of an inheritance hierarchy.

If you want to use this strategy with Jakarta Persistence, your database table needs to have a discriminator column. The value in this column identifies the entity class to which each record shall be mapped. The @DiscriminatorValue annotation is optional. It defines which discriminator value shall be mapped to annotated entity. If you don’t annotate your subclass with a @DiscriminatorValue annotation, Hibernate uses the name of the entity as a default. But this default handling isn’t defined by the JPA specification, and you shouldn’t rely on it.

source: https://thorben-janssen.com/hibernate-tips-single_table-strategy-without-discriminator-column/
