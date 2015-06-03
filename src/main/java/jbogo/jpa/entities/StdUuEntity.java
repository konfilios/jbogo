package jbogo.jpa.entities;

import jbogo.util.UuIdentifiable;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

/**
 * Standard entity which is also UuIdentifiable
 */
@MappedSuperclass
abstract public class StdUuEntity<ID extends Serializable> extends StdEntity<ID> implements UuIdentifiable
{
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    @NotNull
    protected UUID uuid;

    @Override
    public UUID getUuid()
    {
        return uuid;
    }
}
