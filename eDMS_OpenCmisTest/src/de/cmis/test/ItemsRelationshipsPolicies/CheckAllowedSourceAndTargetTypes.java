package de.cmis.test.ItemsRelationshipsPolicies;

import java.io.IOException;
import java.util.List;

import org.apache.chemistry.opencmis.client.api.ObjectType;
import org.apache.chemistry.opencmis.client.api.RelationshipType;
import org.apache.chemistry.opencmis.client.api.Session;

import de.cmis.test.Tool;
import de.cmis.test.Session.SessionSingleton;

public class CheckAllowedSourceAndTargetTypes {

	

	private static void printTypes(List<ObjectType> allowedTypes) {
		for (ObjectType objectType : allowedTypes) {
			Tool.printAndLog(objectType.toString());
		}
	}

	public static void main(String args[]) throws IOException {
		Session session = SessionSingleton.getInstance().getSession("OpenCmisServer", "atom11");

		RelationshipType relationShipType = (RelationshipType) session.getTypeDefinition("cmis:relationship");

		List<ObjectType> allowedSourceTypes = relationShipType.getAllowedSourceTypes();
		List<ObjectType> allowedTargetTypes = relationShipType.getAllowedTargetTypes();

		Tool.printAndLog("Allowed source types");
		printTypes(allowedSourceTypes);

		Tool.printAndLog("Allowed target types");
		printTypes(allowedTargetTypes);

	}

}