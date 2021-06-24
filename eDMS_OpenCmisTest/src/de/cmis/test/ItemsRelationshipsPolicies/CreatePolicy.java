package de.cmis.test.ItemsRelationshipsPolicies;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.chemistry.opencmis.client.api.ObjectId;
import org.apache.chemistry.opencmis.client.api.Policy;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.PropertyIds;

import de.cmis.test.Tool;
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

		Tool.printAndLog("Created By : " + policy.getCreatedBy());
		Tool.printAndLog("Description : " + policy.getDescription());
		Tool.printAndLog("Name : " + policy.getName());
		Tool.printAndLog("Policy Text : " + policy.getPolicyText());
		Tool.printAndLog("Policy Id : " + policy.getId());

	}

}