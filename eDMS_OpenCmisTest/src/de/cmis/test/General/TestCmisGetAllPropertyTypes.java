package de.cmis.test.General;

import java.util.List;

import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.Property;
import org.apache.chemistry.opencmis.client.api.Session;

import de.cmis.test.Tool;
import de.cmis.test.Session.SessionSingleton;

public class TestCmisGetAllPropertyTypes {

	

	public static void main(String args[]) {
		//Session session = SessionSingleton.getInstance().getSession("OpenCmisServer", "atom11");
		Session session = SessionSingleton.getInstance().getSession("OpenCmisServer", "browser");

		Folder folder = session.getRootFolder();

		List<Property<?>> props = folder.getProperties();

		Tool.printAndLog("Display Name|type|value");
		for (Property<?> prop : props) {
			Tool.printAndLog(prop.getDisplayName() + "|" + prop.getType() + "|" + prop.getValue());
		}

	}

}