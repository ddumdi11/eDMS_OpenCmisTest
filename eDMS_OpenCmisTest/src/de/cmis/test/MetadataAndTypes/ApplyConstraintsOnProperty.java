package de.cmis.test.MetadataAndTypes;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.ObjectType;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.definitions.PropertyDefinition;

import de.cmis.test.Tool;
import de.cmis.test.Session.SessionSingleton;

public class ApplyConstraintsOnProperty {

	public static void printPropertyDefinitions(Map<String, PropertyDefinition<?>> propertyDefinitions) {

		for (String key : propertyDefinitions.keySet()) {
			Tool.printAndLog("\n\nGeneric Constraints for the property : " + key);
			Tool.printAndLog("************************************************");

			PropertyDefinition<?> propertyDefinition = propertyDefinitions.get(key);

			boolean isRequired = propertyDefinition.isRequired();
			List<?> choices = propertyDefinition.getChoices();

			Tool.printAndLog("isRequired : " + isRequired);
			Tool.printAndLog("isOpenChoice : " + propertyDefinition.isOpenChoice());
			Tool.printAndLog("choices : " + choices);
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