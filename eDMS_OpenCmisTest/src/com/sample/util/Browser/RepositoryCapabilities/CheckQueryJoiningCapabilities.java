package com.sample.util.Browser.RepositoryCapabilities;

import java.util.HashMap;
import java.util.Map;

import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.api.SessionFactory;
import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl;
import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.data.RepositoryInfo;
import org.apache.chemistry.opencmis.commons.enums.BindingType;
import org.apache.chemistry.opencmis.commons.enums.CapabilityJoin;

public class CheckQueryJoiningCapabilities {

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

		CapabilityJoin joinCapability = repoInfo.getCapabilities().getJoinCapability();

		if (joinCapability == null) {
			System.out.println("Repository is not providing any value");
		} else if (CapabilityJoin.NONE == joinCapability) {
			System.out.println(
					"The repository cannot fulfill any queries that include any JOIN clauses on two primary types. If the Repository supports secondary types, JOINs on secondary types SHOULD be supported, even if the support level is none.");
		} else if (CapabilityJoin.INNERONLY == joinCapability) {
			System.out.println(
					"The repository can fulfill queries that include an INNER JOIN clause, but cannot fulfill queries that include other types of JOIN clauses.");
		} else if (CapabilityJoin.INNERANDOUTER == joinCapability) {
			System.out.println(
					"The repository can fulfill queries that include any type of JOIN clause defined by the CMIS query grammar.");
		} else {
			System.out.println("Feature is not implemented as per specification");
		}

	}

}