package jbogo.jpa.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Standard entity implementation with auto generated id.
 */
@MappedSuperclass
abstract public class StdEntity<ID extends Serializable> extends AbstractEntity<ID>
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected ID id;

    @Override
    public ID getId()
    {
        return id;
    }
}
