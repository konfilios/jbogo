package jbogo.modules.l10n.language;


import jbogo.jpa.entities.RefNamedEntity;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;

/**
 *
 */
@Entity
@AttributeOverride(name = "id", column = @Column(columnDefinition = "char"))
public class L10nLanguage extends RefNamedEntity<String>
{
}
