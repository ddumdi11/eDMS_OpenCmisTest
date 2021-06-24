package com.sample.util.Browser.RepositoryCapabilities;

import java.util.HashMap;
import java.util.Map;

import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.api.SessionFactory;
import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl;
import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.data.RepositoryInfo;
import org.apache.chemistry.opencmis.commons.enums.BindingType;
import org.apache.chemistry.opencmis.commons.enums.CapabilityQuery;

public class CheckQueryCapabilities {

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

		CapabilityQuery queryCapability = repoInfo.getCapabilities().getQueryCapability();

		if (queryCapability == null) {
			System.out.println("Repository is not providing any value");
		} else if (CapabilityQuery.NONE == queryCapability) {
			System.out.println("Query capability is not supported");
		} else if (CapabilityQuery.METADATAONLY == queryCapability) {
			System.out.println("Support queries that filter based on object properties");
		} else if (CapabilityQuery.FULLTEXTONLY == queryCapability) {
			System.out.println("Support queries that filter based on the full-text content of documents");
		} else if (CapabilityQuery.BOTHSEPARATE == queryCapability) {
			System.out.println(
					"Support queries that filter EITHER on the full-text content of documents OR on their properties, but NOT if both types of filters are included in the same query.");
		} else if (CapabilityQuery.BOTHCOMBINED == queryCapability) {
			System.out.println(
					"Support that filter on both the full-text content of documents and their properties in the same query.");
		}

	}

}