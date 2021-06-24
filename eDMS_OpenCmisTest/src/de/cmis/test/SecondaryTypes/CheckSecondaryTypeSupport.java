package de.cmis.test.SecondaryTypes;

import java.io.IOException;

import org.apache.chemistry.opencmis.client.api.ItemIterable;
import org.apache.chemistry.opencmis.client.api.ObjectType;
import org.apache.chemistry.opencmis.client.api.Session;

import de.cmis.test.Tool;
import de.cmis.test.Session.SessionSingleton;

public class CheckSecondaryTypeSupport {

	
	public static void main(String args[]) throws IOException {
		Session session = SessionSingleton.getInstance().getSession("OpenCmisServer", "atom11");

		ItemIterable<ObjectType> objectTypes = session.getTypeChildren(null, false);

		for (ObjectType objectType : objectTypes) {
			if ("cmis:secondary".equals(objectType.getId())) {
				Tool.printAndLog("Secondary types are supported by the repository");
				return;
			}
		}

		Tool.printAndLog("Secondary types are not supported");
	}

}