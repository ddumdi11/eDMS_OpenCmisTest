package de.cmis.test.MetadataAndTypes;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.ObjectType;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.api.SessionFactory;
import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl;
import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.enums.BindingType;

import de.cmis.test.Session.SessionSingleton;

public class GetTypeOfObject {

	public static void printTypeInformation(ObjectType objectType) {

		String description = objectType.getDescription();
		String displayName = objectType.getDisplayName();
		String id = objectType.getId();
		String localName = objectType.getLocalName();
		String queryName = objectType.getQueryName();

		System.out.println("description = " + description);
		System.out.println("displayName = " + displayName);
		System.out.println("id = " + id);
		System.out.println("localName = " + localName);
		System.out.println("queryName = " + queryName);
	}

	public static void main(String args[]) throws IOException {
		Session session = SessionSingleton.getInstance().getSession("OpenCmisServer", "atom11");

		Folder folder = session.getRootFolder();

		ObjectType objectType = folder.getType();

		printTypeInformation(objectType);

	}
}