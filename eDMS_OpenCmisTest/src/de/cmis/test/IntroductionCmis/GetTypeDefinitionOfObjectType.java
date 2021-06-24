package de.cmis.test.IntroductionCmis;

import org.apache.chemistry.opencmis.client.api.ObjectType;
import org.apache.chemistry.opencmis.client.api.Session;

import de.cmis.test.Tool;
import de.cmis.test.Session.SessionSingleton;

public class GetTypeDefinitionOfObjectType {

	public static void main(String args[]) {
		Session session = SessionSingleton.getInstance().getSession("OpenCmisServer", "atom11");

		ObjectType objectType = session.getTypeDefinition("cmis:document");

		Tool.printAndLog(objectType.toString());
	}

}
