package com.sample.util.Browser.RepositoryCapabilities;

import java.util.HashMap;
import java.util.Map;

import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.api.SessionFactory;
import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl;
import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.data.RepositoryInfo;
import org.apache.chemistry.opencmis.commons.enums.BindingType;

public class GetVendorNameProductNameProductVersionOfRepository {

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

		String vendorName = repoInfo.getVendorName();
		String productName = repoInfo.getProductName();
		String productVersion = repoInfo.getProductVersion();

		System.out.println("vendorName : " + vendorName);
		System.out.println("productName : " + productName);
		System.out.println("productVersion : " + productVersion);
	}

}