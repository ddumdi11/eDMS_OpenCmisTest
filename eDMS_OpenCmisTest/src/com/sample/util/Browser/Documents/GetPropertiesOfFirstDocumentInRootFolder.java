package com.sample.util.Browser.Documents;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.chemistry.opencmis.client.api.CmisObject;
import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.ItemIterable;
import org.apache.chemistry.opencmis.client.api.Property;
import org.apache.chemistry.opencmis.client.api.Repository;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.api.SessionFactory;
import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl;
import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.enums.BindingType;

public class GetPropertiesOfFirstDocumentInRootFolder {

	public static List<Repository> getRepositories(String serverURL) {
		Map<String, String> parameters = new HashMap<>();
		parameters.put(SessionParameter.BINDING_TYPE, BindingType.BROWSER.value());

		parameters.put(SessionParameter.USER, "");
		parameters.put(SessionParameter.PASSWORD, "");

		parameters.put(SessionParameter.BROWSER_URL, serverURL);

		SessionFactory sessionFactory = SessionFactoryImpl.newInstance();
		List<Repository> repositories = sessionFactory.getRepositories(parameters);
		return repositories;
	}

	public static void printFirstDocumentProperties(Folder folder) {

		ItemIterable<CmisObject> cmisObjects = folder.getChildren();

		for (CmisObject cmisObject : cmisObjects) {
			if (!(cmisObject instanceof Document)) {
				continue;
			}

			Document document = (Document) cmisObject;
			System.out.println("Name des zuerst gefundenen Dokuments: " + document.getName() + "\n");
			
			List<Property<?>> properties = document.getProperties();

			for (Property property : properties) {
				if (property.getId().startsWith("cmis:"))
					System.out.println(property.getId() + " " + property.getValueAsString());
			}
			return; // Return bewirkt, daß die Schleife nach dem ersten Fund abbricht!
		}

	}

	public static void main(String args[]) {
		String serverURL = "http://localhost:8089/chemistry-opencmis-server-inmemory-1.1.0/browser";
		List<Repository> repositories = getRepositories(serverURL);

		for (Repository repository : repositories) {
			Session session = repository.createSession();
			Folder rootFolder = session.getRootFolder();
			printFirstDocumentProperties(rootFolder);

		}
	}
}