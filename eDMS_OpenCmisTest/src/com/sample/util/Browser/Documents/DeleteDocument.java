package com.sample.util.Browser.Documents;

import java.io.File;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.api.SessionFactory;
import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl;
import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.data.ContentStream;
import org.apache.chemistry.opencmis.commons.enums.Action;
import org.apache.chemistry.opencmis.commons.enums.BindingType;
import org.apache.chemistry.opencmis.commons.exceptions.CmisObjectNotFoundException;

public class DeleteDocument {

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
	
	public static void createEmptyDocInFolder (Folder folder, String docName) {
			
		Map<String, String> properties = new HashMap<>();
		properties.put("cmis:objectTypeId", "cmis:document");
		properties.put("cmis:name", docName);

		Document document = folder.createDocument(properties, null, null);

		System.out.println("Name Of the Document " + document.getName());
		System.out.println("Path Of the Document " + document.getPaths().get(0));
	}

	public static void main(String args[]) throws IOException {
		Session session = getSession();
		
		Folder root = session.getRootFolder();		
		String docName = "Hello.txt";
		createEmptyDocInFolder(root, docName);

		Document document = (Document) session.getObjectByPath("/Hello.txt");

		System.out.println("Document Name : " + document.getName());

		/* Checking for permissions, whether user has permission to delete or not. */
		if (!document.getAllowableActions().getAllowableActions().contains(Action.CAN_DELETE_OBJECT)) {
			System.out.println("User don't have permission to delete the object");
		}

		document.delete();

		try {
			document.refresh();
		} catch (CmisObjectNotFoundException e) {
			System.out.println("Document is deleted");
			System.out.println(e.getMessage());
		}

	}
}