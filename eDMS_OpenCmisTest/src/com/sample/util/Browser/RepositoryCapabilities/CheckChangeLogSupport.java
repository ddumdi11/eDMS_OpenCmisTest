package com.sample.util.Browser.RepositoryCapabilities;

import java.util.HashMap;
import java.util.Map;

import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.api.SessionFactory;
import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl;
import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.data.RepositoryInfo;
import org.apache.chemistry.opencmis.commons.enums.BindingType;
import org.apache.chemistry.opencmis.commons.enums.CapabilityChanges;

public class CheckChangeLogSupport {

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

	public static void main(String args[]) {
		Session session = getSession();

		RepositoryInfo repoInfo = session.getRepositoryInfo();

		CapabilityChanges capabilityChanges = repoInfo.getCapabilities().getChangesCapability();

		if (capabilityChanges == null) {
			System.out.println("Repository is not providing this value");
		} else if (capabilityChanges == CapabilityChanges.NONE) {
			System.out.println("The repository does not support the change log feature");
		} else if (capabilityChanges == CapabilityChanges.OBJECTIDSONLY) {
			System.out.println(
					"The change log can return only the object ids for changed objects in the repository and an indication of the type of change, not details of the actual change.");
		} else if (capabilityChanges == CapabilityChanges.PROPERTIES) {
			System.out.println("The change log can return properties and the object id for the changed objects.");
		} else if (capabilityChanges == CapabilityChanges.ALL) {
			System.out.println(
					"The change log can return the object ids for changed objects in the repository and more information about the actual change.");
		}
	}

}