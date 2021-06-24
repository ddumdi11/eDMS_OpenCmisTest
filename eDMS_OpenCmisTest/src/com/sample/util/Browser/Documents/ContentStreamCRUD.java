package com.sample.util.Browser.Documents;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.api.SessionFactory;
import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl;
import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.enums.BindingType;
import org.apache.chemistry.opencmis.commons.enums.CapabilityContentStreamUpdates;

public class ContentStreamCRUD {

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

	public static void main(String args[]) throws IOException {

		Session session = getSession();

		CapabilityContentStreamUpdates capabilityContentStreamUpdates = session.getRepositoryInfo().getCapabilities()
				.getContentStreamUpdatesCapability();

		if (capabilityContentStreamUpdates == null) {
			System.out.println("Repository do not provide this value");
		} else if (CapabilityContentStreamUpdates.NONE == capabilityContentStreamUpdates) {
			System.out.println("Content stream may never be updated.");
		} else if (CapabilityContentStreamUpdates.ANYTIME == capabilityContentStreamUpdates) {
			System.out.println("Content stream can be updated any time.");
		} else if (CapabilityContentStreamUpdates.PWCONLY == capabilityContentStreamUpdates) {
			System.out.println(
					"Content stream is updated only when the document is checked out. PWC stands for Private Working Copy.");
		} else {
			System.out.println("It is not implemented as per the CMIS Specification");
		}
	}

}