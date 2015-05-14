package jbogo.modules.l10n.timezone;

import jbogo.jpa.entities.RefNamedEntity;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;

/**
 *
 */
@Entity
public class L10nTimezone extends RefNamedEntity<Short>
{
    @NotEmpty
    String code;

    public String getCode()
    {
        return code;
    }
}
