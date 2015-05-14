package jbogo.jpa.entities;

import jbogo.util.Named;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Named reference entity implementation with manually assigned id.
 */
@MappedSuperclass
abstract public class RefNamedEntity<ID extends Serializable> extends RefEntity<ID> implements Named
{
    protected String name;

    @Override
    public String getName()
    {
        return name;
    }
}
