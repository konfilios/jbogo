package jbogo.jpa.entities;

import jbogo.util.Named;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Standard named entity implementation with auto generated id.
 */
@MappedSuperclass
abstract public class StdUuNamedEntity<ID extends Serializable> extends StdUuEntity<ID> implements Named
{
    protected String name;

    @Override
    public String getName()
    {
        return name;
    }
}
