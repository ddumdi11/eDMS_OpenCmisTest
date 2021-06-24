package de.cmis.test.ItemsRelationshipsPolicies;

import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import org.apache.chemistry.opencmis.client.api.CmisObject;
import org.apache.chemistry.opencmis.client.api.ObjectId;
import org.apache.chemistry.opencmis.client.api.Relationship;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.PropertyIds;

import de.cmis.test.Tool;
import de.cmis.test.Session.SessionSingleton;

public class CreateRelationship {

	private static ObjectId createRelationShip(Session session, String sourceId, String targetId) {
		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put(PropertyIds.NAME, "a new relationship");
		properties.put(PropertyIds.OBJECT_TYPE_ID, "CrossReferenceType");
		properties.put(PropertyIds.SOURCE_ID, sourceId);
		properties.put(PropertyIds.TARGET_ID, targetId);

		ObjectId newRelId = session.createRelationship(properties);

		return newRelId;
	}

	private static void printRelationShip(Relationship relationship) {
		String createdBy = relationship.getCreatedBy();
		GregorianCalendar creationDate = relationship.getCreationDate();
		String description = relationship.getDescription();
		String sourceName = relationship.getSource().getName();
		String targetName = relationship.getTarget().getName();

		Tool.printAndLog("createdBy : " + createdBy);
		Tool.printAndLog("creationDate : " + creationDate);
		Tool.printAndLog("description : " + description);
		Tool.printAndLog("sourceName : " + sourceName);
		Tool.printAndLog("targetName : " + targetName);

	}

	public static void main(String args[]) throws IOException {
		Session session = SessionSingleton.getInstance().getSession("OpenCmisServer", "atom11");

		CmisObject folder1 = session.getObjectByPath("/My_Folder-0-0");
		CmisObject folder2 = session.getObjectByPath("/My_Folder-0-1");

		ObjectId relationShipId = createRelationShip(session, folder1.getId(), folder2.getId());

		Relationship relationShip = (Relationship) session.getObject(relationShipId);

		printRelationShip(relationShip);

	}

}