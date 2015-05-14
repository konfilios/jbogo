package jbogo.jpa.strategies;

import org.hibernate.AssertionFailure;
import org.hibernate.cfg.EJB3NamingStrategy;
import org.hibernate.internal.util.StringHelper;

/**
 *
 */
public class CamelCaseNamingStrategy extends EJB3NamingStrategy
{

//    @Override
//    public String collectionTableName(String ownerEntity, String ownerEntityTable, String associatedEntity, String associatedEntityTable, String propertyName) {
//        return this.tableName(ownerEntityTable + "_" + (associatedEntityTable != null?associatedEntityTable: StringHelper.unqualify(
//            propertyName)));
//    }

    @Override
    public String foreignKeyColumnName(String propertyName, String propertyEntityName, String propertyTableName, String referencedColumnName) {
        String header = propertyName != null? StringHelper.unqualify(propertyName):propertyTableName;
        if(header == null) {
            throw new AssertionFailure("NamingStrategy not properly filled");
        } else {
            return this.columnName(header + ucFirst(referencedColumnName));
        }
    }

//    @Override
//    public String logicalCollectionTableName(String tableName, String ownerEntityTable, String associatedEntityTable, String propertyName) {
//        return tableName != null?tableName:ownerEntityTable + "_" + (associatedEntityTable != null?associatedEntityTable:StringHelper.unqualify(propertyName));
//    }

//    @Override
//    public String logicalCollectionColumnName(String columnName, String propertyName, String referencedColumn) {
//        return StringHelper.isNotEmpty(columnName)?columnName:StringHelper.unqualify(propertyName) + "_" + referencedColumn;
//    }

    /**
     * Convert first letter to upper case.
     * @return
     */
    private String ucFirst(String str)
    {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
