package de.cmis.test.MetadataAndTypes;

import java.io.IOException;
import java.util.Map;

import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.ObjectType;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.definitions.PropertyDefinition;
import org.apache.chemistry.opencmis.commons.enums.PropertyType;

import de.cmis.test.Tool;
import de.cmis.test.Session.SessionSingleton;

public class GetPropertyDefinitionsFromProperty {

	public static void printPropertyDefinitions(Map<String, PropertyDefinition<?>> propertyDefinitions) {

		for (String key : propertyDefinitions.keySet()) {
			Tool.printAndLog("Property definitions for the property : " + key);
			Tool.printAndLog("************************************************");

			PropertyDefinition<?> propertyDefinition = propertyDefinitions.get(key);

			String description = propertyDefinition.getDescription();
			String displayName = propertyDefinition.getDisplayName();
			String id = propertyDefinition.getId();
			String localName = propertyDefinition.getLocalName();
			String queryName = propertyDefinition.getQueryName();
			PropertyType propertyType = propertyDefinition.getPropertyType();

			Tool.printAndLog("description = " + description);
			Tool.printAndLog("displayName = " + displayName);
			Tool.printAndLog("id = " + id);
			Tool.printAndLog("localName = " + localName);
			Tool.printAndLog("queryName = " + queryName);
			Tool.printAndLog("propertyType = " + propertyType);

		}
	}

	public static void main(String args[]) throws IOException {
		Session session = SessionSingleton.getInstance().getSession("OpenCmisServer", "atom11");

		Folder folder = session.getRootFolder();
		ObjectType objectType = folder.getType();
		Map<String, PropertyDefinition<?>> propertyDefinitions = objectType.getPropertyDefinitions();

		printPropertyDefinitions(propertyDefinitions);
	}
}