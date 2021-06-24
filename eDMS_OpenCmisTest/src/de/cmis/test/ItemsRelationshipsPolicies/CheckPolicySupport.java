package de.cmis.test.ItemsRelationshipsPolicies;

import java.io.IOException;

import org.apache.chemistry.opencmis.client.api.ItemIterable;
import org.apache.chemistry.opencmis.client.api.ObjectType;
import org.apache.chemistry.opencmis.client.api.Session;

import de.cmis.test.Tool;
import de.cmis.test.Session.SessionSingleton;

public class CheckPolicySupport {

	public static void main(String args[]) throws IOException {
		Session session = SessionSingleton.getInstance().getSession("OpenCmisServer", "atom11");

		ItemIterable<ObjectType> objTypes = session.getTypeChildren(null, false);

		for (ObjectType objType : objTypes) {
			if ("cmis:policy".equals(objType.getId())) {
				Tool.printAndLog("Policy objects are supported by the repository");
			}
		}

	}

}