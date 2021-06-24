package de.cmis.test.ItemsRelationshipsPolicies;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.chemistry.opencmis.client.api.CmisObject;
import org.apache.chemistry.opencmis.client.api.ObjectId;
import org.apache.chemistry.opencmis.client.api.Policy;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.runtime.ObjectIdImpl;
import org.apache.chemistry.opencmis.commons.PropertyIds;

import de.cmis.test.Tool;
import de.cmis.test.Session.SessionSingleton;

public class GetPoliciesAppliedToObject {

	private static void printPolicy(Policy policy) {
		Tool.printAndLog("Created By : " + policy.getCreatedBy());
		Tool.printAndLog("Description : " + policy.getDescription());
		Tool.printAndLog("Name : " + policy.getName());
		Tool.printAndLog("Policy Text : " + policy.getPolicyText());
		Tool.printAndLog("Policy Id : " + policy.getId());

	}

	public static void main(String args[]) throws IOException {
		Session session = SessionSingleton.getInstance().getSession("OpenCmisServer", "atom11");

		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put(PropertyIds.NAME, "a new filed policy");
		properties.put(PropertyIds.OBJECT_TYPE_ID, "AuditPolicy");
		properties.put(PropertyIds.POLICY_TEXT, "my policy description");

		ObjectId policyId = session.createPolicy(properties, null);

		Policy policy = (Policy) session.getObject(policyId);

		Tool.printAndLog("Applying policy to the folder : '/My_Folder-0-0'");
		CmisObject cmisObject = session.getObjectByPath("/My_Folder-0-0");
		session.applyPolicy(new ObjectIdImpl(cmisObject.getId()), new ObjectIdImpl(policy.getId()));

		List<Policy> policies = cmisObject.getPolicies();

		if (policies == null) {
			Tool.printAndLog("No policies applied on this object");
			return;
		}

		for (Policy policyTemp : policies) {
			printPolicy(policyTemp);
		}

	}

}