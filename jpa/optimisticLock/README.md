# A Locking mode example (Optimistic Locking)

This example demonstrates a locking mode example with optimistic locking.
#### In JPA "Optimistic Read Lock" provides Repeatable Read transaction isolation level that prevents the so-called non-repeatable read anomaly.

### Transaction Anomalies

The ANSI/ISO SQL standard defines four levels of transaction isolation in terms of three phenomena that must be prevented between concurrent transactions. 
These undesirable phenomena are:

#### dirty reads
A transaction reads data written by concurrent uncommitted transaction.

#### non-repeatable reads
A transaction re-reads data it has previously read and finds that data has been modified by another transaction (that committed since the initial read).

#### phantom read
A transaction re-executes a query returning a set of rows that satisfy a search condition and finds that the set of rows satisfying the condition has changed due to another recently-committed transaction.

## The four transaction isolation levels and the corresponding behaviors are described in following table:

|Isolation Level|Dirty Read|Non-Repeatable Read|Phantom Read|
|---|:---:|:---:|---:|
|Read uncommitted|Possible|Possible|Possible|
|Read committed|Not possible|Possible|Possible|
|Repeatable read|Not possible|Not possible|Possible|
|Serializable|Not possible|Not possible|Not possible|

#### We should remember that for versioned entities optimistic locking is available by default. (source: baeldung.com/jpa-optimistic-locking)
#### JPA supports Read Committed isolation level by default. 
