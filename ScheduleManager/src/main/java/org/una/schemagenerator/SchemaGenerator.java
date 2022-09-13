/**
 * @author ArnoldG6
 */
package org.una.schemagenerator;

import java.io.File;
import java.util.EnumSet;

import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.schema.TargetType;


public final class SchemaGenerator {
    /*
     *  SchemaGenerator class can create or drop a schema from the mapped entities in hibernate-cfg.xml
     *  The script used also can be exported as a ".sql" file.
     */

    /**
     * Filename for output script.
     *
     */
    public static final String filename = "generatedScript.sql";

    /**
     * Enum that defines schema generation output.
     * TargetType.DATABASE - Execute on Database
     * TargetType.SCRIPT - Write Script file.
     * TargetType.STDOUT - Write log to Console.
     * SQL dialect is defined in hibernate-cfg.xml
     */
    public static final EnumSet<TargetType> targetTypes = EnumSet.of(TargetType.DATABASE, TargetType.STDOUT);

    /**
     *
     * @return a SchemaExport object which can be used to create or drop DB.
     */
    private static SchemaExport getSchemaExport() {

        SchemaExport exportedSchema = new SchemaExport();
        File outputFile = new File(filename);
        String outputFilePath = outputFile.getAbsolutePath();
        exportedSchema.setDelimiter(";");//Line delimiter.
        exportedSchema.setOutputFile(outputFilePath);
        exportedSchema.setHaltOnError(false); //If there is an error, exportation does not stop.
        return exportedSchema;
    }
    /**
     *
     * drops the database based on
     * @param exportedSchema and
     * @param metadata object that contains hibernate configuration.
     */
    public static void dropDataBase(SchemaExport exportedSchema, Metadata metadata) {
        exportedSchema.drop(targetTypes, metadata);
    }
    /**
     *
     * Creates the database based on
     * @param exportedSchema and
     * @param metadata object that contains hibernate configuration.
     */
    public static void createDataBase(SchemaExport exportedSchema, Metadata metadata) {
        SchemaExport.Action action = SchemaExport.Action.BOTH;
        exportedSchema.execute(targetTypes, action, metadata);
    }

    public static void main(String[] args) {
        //DB/Hibernate settings file.
        String configFileName = "Hibernate/hibernate-cfg.xml";
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()//
                .configure(configFileName).build();
        // Create a metadata sources using the specified service registry.
        Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();
        SchemaExport exportedSchema = getSchemaExport();
        dropDataBase(exportedSchema, metadata);
        createDataBase(exportedSchema, metadata);
    }

}
