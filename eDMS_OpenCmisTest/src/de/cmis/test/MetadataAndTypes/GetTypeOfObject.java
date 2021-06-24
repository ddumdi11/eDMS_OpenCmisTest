package de.cmis.test.MetadataAndTypes;

import java.io.IOException;

import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.ObjectType;
import org.apache.chemistry.opencmis.client.api.Session;

import de.cmis.test.Tool;
import de.cmis.test.Session.SessionSingleton;

public class GetTypeOfObject {

	public static void printTypeInformation(ObjectType objectType) {

		String description = objectType.getDescription();
		String displayName = objectType.getDisplayName();
		String id = objectType.getId();
		String localName = objectType.getLocalName();
		String queryName = objectType.getQueryName();

		Tool.printAndLog("description = " + description);
		Tool.printAndLog("displayName = " + displayName);
		Tool.printAndLog("id = " + id);
		Tool.printAndLog("localName = " + localName);
		Tool.printAndLog("queryName = " + queryName);
	}

	public static void main(String args[]) throws IOException {
		Session session = SessionSingleton.getInstance().getSession("OpenCmisServer", "atom11");

		Folder folder = session.getRootFolder();

		ObjectType objectType = folder.getType();

		printTypeInformation(objectType);

	}
}