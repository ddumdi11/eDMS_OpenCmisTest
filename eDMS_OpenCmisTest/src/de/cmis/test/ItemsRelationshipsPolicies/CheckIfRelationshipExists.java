package de.cmis.test.ItemsRelationshipsPolicies;

import java.io.IOException;
import java.util.List;

import org.apache.chemistry.opencmis.client.api.CmisObject;
import org.apache.chemistry.opencmis.client.api.Relationship;
import org.apache.chemistry.opencmis.client.api.Session;

import de.cmis.test.Tool;
import de.cmis.test.Session.SessionSingleton;

public class CheckIfRelationshipExists {

	

	public static void main(String args[]) throws IOException {
		Session session = SessionSingleton.getInstance().getSession("OpenCmisServer", "atom11");

		CmisObject cmisObject = session.getObjectByPath("/My_Document-0-0");

		List<Relationship> relationShips = cmisObject.getRelationships();
		if (relationShips == null) {
			Tool.printAndLog("No relation ships exist");
			return;
		}

		for (Relationship relationship : relationShips) {
			Tool.printAndLog(relationship.toString());
		}

	}

}