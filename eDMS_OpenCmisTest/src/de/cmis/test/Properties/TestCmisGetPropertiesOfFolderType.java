package de.cmis.test.Properties;

import java.util.Map;

import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.FolderType;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.definitions.PropertyDefinition;

import de.cmis.test.Tool;
import de.cmis.test.Session.SessionSingleton;

public class TestCmisGetPropertiesOfFolderType {

	

	public static void main(String args[]) {
		Session session = SessionSingleton.getInstance().getSession("OpenCmisServer", "atom11");

		Folder folder = session.getRootFolder();

		FolderType folderType = folder.getFolderType();

		Map<String, PropertyDefinition<?>> propertyDefintions = folderType.getPropertyDefinitions();

		for (String key : propertyDefintions.keySet()) {
			PropertyDefinition propDefinition = propertyDefintions.get(key);
			Tool.printAndLog(propDefinition.toString());
		}

	}

}