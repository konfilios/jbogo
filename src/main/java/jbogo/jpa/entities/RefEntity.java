package jbogo.jpa.entities;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Reference entity implementation with manually assigned id.
 */
@MappedSuperclass
abstract public class RefEntity<ID extends Serializable> extends AbstractEntity<ID>
{
    @Id
    protected ID id;

    @Override
    public ID getId()
    {
        return id;
    }
}
