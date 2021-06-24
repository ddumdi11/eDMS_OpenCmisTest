package de.cmis.test.ItemsRelationshipsPolicies;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.chemistry.opencmis.client.api.ObjectId;
import org.apache.chemistry.opencmis.client.api.Policy;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.PropertyIds;

import de.cmis.test.Session.SessionSingleton;

public class CreatePolicy {

	public static void main(String args[]) throws IOException {
		Session session = SessionSingleton.getInstance().getSession("OpenCmisServer", "atom11");

		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put(PropertyIds.NAME, "a new unfiled policy");
		properties.put(PropertyIds.OBJECT_TYPE_ID, "AuditPolicy");
		properties.put(PropertyIds.POLICY_TEXT, "my un policy description");

		ObjectId policyId = session.createPolicy(properties, null);

		Policy policy = (Policy) session.getObject(policyId);

		System.out.println("Created By : " + policy.getCreatedBy());
		System.out.println("Description : " + policy.getDescription());
		System.out.println("Name : " + policy.getName());
		System.out.println("Policy Text : " + policy.getPolicyText());
		System.out.println("Policy Id : " + policy.getId());

	}

}