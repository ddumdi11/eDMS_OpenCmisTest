package com.sample.util.Browser.Documents;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.api.SessionFactory;
import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl;
import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.enums.BindingType;

public class RenameDocument {

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

	public static void main(String args[]) throws IOException {
		Session session = getSession();

		//Dokumente löschen, falls vorhanden
		try {
			Document toDeleteDoc = (Document) session.getObjectByPath("/a.txt");
			System.out.println("Dokument vorhanden + wird gelöscht.");
			toDeleteDoc.delete();
			System.out.println("Dokument erfolgreich gelöscht.");
		} catch (Exception e) {
			System.out.println("Dokument entweder nicht vorhanden oder ein anderer Fehler ...");
		}
		try {
			Document toDeleteDoc = (Document) session.getObjectByPath("/b.txt");
			System.out.println("Dokument vorhanden + wird gelöscht.");
			toDeleteDoc.delete();
			System.out.println("Dokument erfolgreich gelöscht.");
		} catch (Exception e) {
			System.out.println("Dokument entweder nicht vorhanden oder ein anderer Fehler ...");
		}
		
		// Dokument erstellen, was umbenannt werden soll
		Map<String, String> props = new HashMap<>();
		props.put("cmis:objectTypeId", "cmis:document");
		props.put("cmis:name", "a.txt");

		Document doc2Create = session.getRootFolder().createDocument(props, null, null);

		System.out.println("Name Of the Document " + doc2Create.getName());
		System.out.println("Path Of the Document " + doc2Create.getPaths().get(0));

		// Dokument finden
		Document document = (Document) session.getObjectByPath("/a.txt");

		System.out.println("Before renaming : ");
		System.out.println("name : " + document.getName());
		System.out.println("path : " + document.getPaths().get(0));

		Map<String, String> properties = new HashMap<>();
		properties.put("cmis:name", "b.txt");

		document = (Document) document.updateProperties(properties);

		System.out.println("After renaming : ");
		System.out.println("name : " + document.getName());
		System.out.println("path : " + document.getPaths().get(0));

	}
}