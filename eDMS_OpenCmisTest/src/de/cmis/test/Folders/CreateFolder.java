package de.cmis.test.Folders;

import java.util.HashMap;
import java.util.Map;

import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.api.SessionFactory;
import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl;
import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.enums.BindingType;

import de.cmis.test.Session.SessionSingleton;

public class CreateFolder {

	

	public static void main(String args[]) {
		Session session = SessionSingleton.getInstance().getSession("OpenCmisServer", "atom11");

		Folder rootFolder = session.getRootFolder();

		Map<String, String> properties = new HashMap<>();
		properties.put("cmis:objectTypeId", "cmis:folder");
		properties.put("cmis:name", "My_Folder-0-0");

		Folder folder = rootFolder.createFolder(properties);

		System.out.println("Name Of the Folder " + folder.getName());
		System.out.println("Path Of the Folder " + folder.getPaths().get(0));
	}
}