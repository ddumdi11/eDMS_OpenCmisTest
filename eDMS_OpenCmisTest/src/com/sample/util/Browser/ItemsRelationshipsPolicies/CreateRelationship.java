package com.sample.util.Browser.ItemsRelationshipsPolicies;

import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import org.apache.chemistry.opencmis.client.api.CmisObject;
import org.apache.chemistry.opencmis.client.api.ObjectId;
import org.apache.chemistry.opencmis.client.api.Relationship;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.api.SessionFactory;
import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl;
import org.apache.chemistry.opencmis.commons.PropertyIds;
import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.enums.BindingType;

public class CreateRelationship {

	private static String serverURL = "http://localhost:8089/chemistry-opencmis-server-inmemory-1.1.0/browser";
	private static String repositoryId = "A1";

	private static Session getSession() {
		Map<String, String> parameters = new HashMap<>();
		parameters.put(SessionParameter.BINDING_TYPE, BindingType.BROWSER.value());

		parameters.put(SessionParameter.USER, "");
		parameters.put(SessionParameter.PASSWORD, "");

		parameters.put(SessionParameter.REPOSITORY_ID, repositoryId);
		parameters.put(SessionParameter.BROWSER_URL, serverURL);

		SessionFactory sessionFactory = SessionFactoryImpl.newInstance();
		return sessionFactory.createSession(parameters);
	}

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

		System.out.println("createdBy : " + createdBy);
		System.out.println("creationDate : " + creationDate);
		System.out.println("description : " + description);
		System.out.println("sourceName : " + sourceName);
		System.out.println("targetName : " + targetName);

	}

	public static void main(String args[]) throws IOException {

		Session session = getSession();

		CmisObject folder1 = session.getObjectByPath("/My_Folder-0-0");
		CmisObject folder2 = session.getObjectByPath("/My_Folder-0-1");

		ObjectId relationShipId = createRelationShip(session, folder1.getId(), folder2.getId());

		Relationship relationShip = (Relationship) session.getObject(relationShipId);

		printRelationShip(relationShip);

	}

}