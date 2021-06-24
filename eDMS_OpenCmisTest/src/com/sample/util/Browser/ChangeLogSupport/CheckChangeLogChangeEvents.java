package com.sample.util.Browser.ChangeLogSupport;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.chemistry.opencmis.client.api.ChangeEvent;
import org.apache.chemistry.opencmis.client.api.ChangeEvents;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.api.SessionFactory;
import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl;
import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.enums.BindingType;
import org.apache.chemistry.opencmis.commons.enums.CapabilityChanges;

public class CheckChangeLogChangeEvents {

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

		CapabilityChanges capabilityChanges = session.getRepositoryInfo().getCapabilities().getChangesCapability();

		if (capabilityChanges == null) {
			System.out.println("Repository is not providing any value");
			return;
		}

		if (CapabilityChanges.NONE == capabilityChanges) {
			System.out.println("Repository is not supporitng change log");
			return;
		}

		System.out.println("capabilityChanges : " + capabilityChanges);
		ChangeEvents oldChangeEvents = session.getContentChanges(null, false, 1000);
		String previousChangeLogToken = oldChangeEvents.getLatestChangeLogToken();
		System.out.println("Change log token " + previousChangeLogToken);

		System.out.println("Press Enter to get latest change events");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br.readLine();

		ChangeEvents changeEvents = session.getContentChanges(previousChangeLogToken, false, 1000);

		String latestChangeLogToken = changeEvents.getLatestChangeLogToken();
		System.out.println("Change log token " + latestChangeLogToken);

		for (ChangeEvent changeEvent : changeEvents.getChangeEvents()) {
			System.out.println(changeEvent.getObjectId() + ", " + changeEvent.getChangeType());
		}

	}

}