package de.cmis.test.Properties;

import java.util.List;

import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.Property;
import org.apache.chemistry.opencmis.client.api.Session;

import de.cmis.test.Session.SessionSingleton;

public class TestCmisGetPropertiesOfRootFolder {

	
	public static void main(String args[]) {
		Session session = SessionSingleton.getInstance().getSession("OpenCmisServer", "atom11");

		Folder folder = session.getRootFolder();

		List<Property<?>> properties = folder.getProperties();

		System.out.println("propertyId | displayName | value");
		for (Property<?> property : properties) {
			System.out.println(property.getId() + " | " + property.getDisplayName() + " | " + property.getValue());
		}

	}

}