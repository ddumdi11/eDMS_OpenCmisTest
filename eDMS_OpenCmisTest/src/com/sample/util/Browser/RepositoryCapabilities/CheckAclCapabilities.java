package com.sample.util.Browser.RepositoryCapabilities;

import java.util.HashMap;
import java.util.Map;

import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.api.SessionFactory;
import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl;
import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.data.RepositoryInfo;
import org.apache.chemistry.opencmis.commons.enums.BindingType;
import org.apache.chemistry.opencmis.commons.enums.CapabilityAcl;

public class CheckAclCapabilities {

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

		CapabilityAcl aclCapability = repoInfo.getCapabilities().getAclCapability();

		if (aclCapability == null) {
			System.out.println("Repository do not provide any value");
		} else if (CapabilityAcl.NONE == aclCapability) {
			System.out.println("The repository does not support ACL services.");
		} else if (CapabilityAcl.DISCOVER == aclCapability) {
			System.out.println("The repository supports discovery of ACLs (getACL and other services)");
		} else if (CapabilityAcl.MANAGE == aclCapability) {
			System.out.println(
					"The repository supports discovery of ACLs AND applying ACLs (getACL and apply-ACL services).");
		} else {
			System.out.println("Service is not implemented by the repository");
		}

	}

}