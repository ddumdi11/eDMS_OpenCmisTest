package de.cmis.test.General;

import java.util.List;

import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.Property;
import org.apache.chemistry.opencmis.client.api.Session;

import de.cmis.test.Session.SessionSingleton;

public class TestCmisGetAllPropertyTypes {

	

	public static void main(String args[]) {
		//Session session = SessionSingleton.getInstance().getSession("OpenCmisServer", "atom11");
		Session session = SessionSingleton.getInstance().getSession("OpenCmisServer", "browser");

		Folder folder = session.getRootFolder();

		List<Property<?>> props = folder.getProperties();

		System.out.println("Display Name|type|value");
		for (Property<?> prop : props) {
			System.out.println(prop.getDisplayName() + "|" + prop.getType() + "|" + prop.getValue());
		}

	}

}