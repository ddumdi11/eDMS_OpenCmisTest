package com.sample.util.Browser.ItemsRelationshipsPolicies;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.chemistry.opencmis.client.api.CmisObject;
import org.apache.chemistry.opencmis.client.api.ObjectId;
import org.apache.chemistry.opencmis.client.api.Policy;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.api.SessionFactory;
import org.apache.chemistry.opencmis.client.runtime.ObjectIdImpl;
import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl;
import org.apache.chemistry.opencmis.commons.PropertyIds;
import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.enums.BindingType;

public class GetPoliciesAppliedToObject {

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

	private static void printPolicy(Policy policy) {
		System.out.println("Created By : " + policy.getCreatedBy());
		System.out.println("Description : " + policy.getDescription());
		System.out.println("Name : " + policy.getName());
		System.out.println("Policy Text : " + policy.getPolicyText());
		System.out.println("Policy Id : " + policy.getId());

	}

	public static void main(String args[]) throws IOException {

		Session session = getSession();

		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put(PropertyIds.NAME, "a new filed policy");
		properties.put(PropertyIds.OBJECT_TYPE_ID, "AuditPolicy");
		properties.put(PropertyIds.POLICY_TEXT, "my policy description");

		ObjectId policyId = session.createPolicy(properties, null);

		Policy policy = (Policy) session.getObject(policyId);

		System.out.println("Applying policy to the folder : '/My_Folder-0-0'");
		CmisObject cmisObject = session.getObjectByPath("/My_Folder-0-0");
		session.applyPolicy(new ObjectIdImpl(cmisObject.getId()), new ObjectIdImpl(policy.getId()));

		List<Policy> policies = cmisObject.getPolicies();

		if (policies == null) {
			System.out.println("No policies applied on this object");
			return;
		}

		for (Policy policyTemp : policies) {
			printPolicy(policyTemp);
		}

	}

}