package com.sample.util.Browser.MetadataAndTypes;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.ObjectType;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.api.SessionFactory;
import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl;
import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.enums.BindingType;

public class GetTypeOfObject {

	private static String serverURL = "http://localhost:8089/chemistry-opencmis-server-inmemory-1.1.0/browser";
	private static String repositoryId = "A1";

	public static Session getSession() {
		Map<String, String> parameters = new HashMap<>();
		parameters.put(SessionParameter.BINDING_TYPE, BindingType.BROWSER.value());

		parameters.put(SessionParameter.USER, "");
		parameters.put(SessionParameter.PASSWORD, "");

		parameters.put(SessionParameter.REPOSITORY_ID, repositoryId);
		parameters.put(SessionParameter.BROWSER_URL, serverURL);

		SessionFactory sessionFactory = SessionFactoryImpl.newInstance();
		return sessionFactory.createSession(parameters);
	}

	public static void printTypeInformation(ObjectType objectType) {

		String description = objectType.getDescription();
		String displayName = objectType.getDisplayName();
		String id = objectType.getId();
		String localName = objectType.getLocalName();
		String queryName = objectType.getQueryName();

		System.out.println("description = " + description);
		System.out.println("displayName = " + displayName);
		System.out.println("id = " + id);
		System.out.println("localName = " + localName);
		System.out.println("queryName = " + queryName);
	}

	public static void main(String args[]) throws IOException {
		Session session = getSession();

		Folder folder = session.getRootFolder();

		ObjectType objectType = folder.getType();

		printTypeInformation(objectType);

	}
}