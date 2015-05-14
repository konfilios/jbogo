package jbogo.modules.l10n.continent;


import jbogo.jpa.entities.RefNamedEntity;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;

/**
 *
 */
@Entity
@AttributeOverride(name = "id", column = @Column(columnDefinition = "char"))
public class L10nContinent extends RefNamedEntity<String>
{
}
