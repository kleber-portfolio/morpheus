package io.github.mrspock182.repository;

import io.github.mrspock182.entity.Person;
import io.github.mrspock182.repository.adapter.PersonRepositoryAdapter;
import io.github.mrspock182.repository.dto.PersonOrmDynamoDB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;

@Repository
public class PersonRepositoryImpl implements PersonRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonRepositoryImpl.class);

    private final DynamoDbTable<PersonOrmDynamoDB> personTable;

    public PersonRepositoryImpl(DynamoDbTable<PersonOrmDynamoDB> personTable) {
        this.personTable = personTable;
    }

    @Override
    public Person save(final Person person) {
        try {
            final PersonOrmDynamoDB orm = PersonRepositoryAdapter.cast(person);
            personTable.putItem(orm);
            return PersonRepositoryAdapter.cast(orm);
        } catch (Exception ex) {
            LOGGER.error("Error to save person", ex);
            throw new RuntimeException("Error to save person", ex);
        }
    }

}
