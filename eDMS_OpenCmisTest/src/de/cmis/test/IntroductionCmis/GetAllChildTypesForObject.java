package de.cmis.test.IntroductionCmis;

import org.apache.chemistry.opencmis.client.api.ItemIterable;
import org.apache.chemistry.opencmis.client.api.ObjectType;
import org.apache.chemistry.opencmis.client.api.Session;

import de.cmis.test.Tool;
import de.cmis.test.Session.SessionSingleton;

public class GetAllChildTypesForObject {

	public static void main(String args[]) {
		Session session = SessionSingleton.getInstance().getSession("OpenCmisServer", "atom11");

		ItemIterable<ObjectType> baseObjectTypes = session.getTypeChildren("cmis:document", false);

		for (ObjectType objType : baseObjectTypes) {
			Tool.printAndLog(objType.toString());
		}

	}

}