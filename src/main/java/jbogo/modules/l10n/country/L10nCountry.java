package jbogo.modules.l10n.country;


import jbogo.jpa.entities.RefNamedEntity;
import jbogo.modules.l10n.continent.L10nContinent;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 *
 */
@Entity
@AttributeOverride(name = "id", column = @Column(columnDefinition = "char"))
public class L10nCountry extends RefNamedEntity<String>
{
    @NotNull
    @ManyToOne
    private L10nContinent continent;

    public L10nContinent getContinent()
    {
        return continent;
    }
}
