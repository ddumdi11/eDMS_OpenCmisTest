package de.cmis.test.ItemsRelationshipsPolicies;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.ObjectId;
import org.apache.chemistry.opencmis.client.api.Policy;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.runtime.ObjectIdImpl;
import org.apache.chemistry.opencmis.commons.PropertyIds;

import de.cmis.test.Session.SessionSingleton;

public class ApplyPolicyToObject {

	

	private static void printPolicy(Policy policy) {
		System.out.println("Created By : " + policy.getCreatedBy());
		System.out.println("Description : " + policy.getDescription());
		System.out.println("Name : " + policy.getName());
		System.out.println("Policy Text : " + policy.getPolicyText());
		System.out.println("Policy Id : " + policy.getId());

	}

	public static void main(String args[]) throws IOException {
		Session session = SessionSingleton.getInstance().getSession("OpenCmisServer", "atom11");

		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put(PropertyIds.NAME, "a new unfiled policy");
		properties.put(PropertyIds.OBJECT_TYPE_ID, "AuditPolicy");
		properties.put(PropertyIds.POLICY_TEXT, "my un policy description");

		ObjectId policyId = session.createPolicy(properties, null);

		Policy policy = (Policy) session.getObject(policyId);

		System.out.println("Applying policy to the folder : '/My_Folder-0-0'");
		Folder folder = (Folder) session.getObjectByPath("/My_Folder-0-0");
		session.applyPolicy(new ObjectIdImpl(folder.getId()), new ObjectIdImpl(policy.getId()));

		List<Policy> policies = folder.getPolicies();

		if (policies == null) {
			System.out.println("No policies applied on this object");
			return;
		}

		for (Policy policyTemp : policies) {
			printPolicy(policyTemp);
		}

	}

}