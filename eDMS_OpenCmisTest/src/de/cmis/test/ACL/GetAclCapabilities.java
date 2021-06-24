package de.cmis.test.ACL;

import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.enums.CapabilityAcl;

import de.cmis.test.Session.SessionSingleton;

public class GetAclCapabilities {

	public static void main(String args[]) {
		Session session = SessionSingleton.getInstance().getSession("OpenCmisServer", "atom11");

		CapabilityAcl aclCapabilities = session.getRepositoryInfo().getCapabilities().getAclCapability();

		if (aclCapabilities == null) {
			System.out.println("Reposiotry do not support acls");
		} else if (aclCapabilities == CapabilityAcl.NONE) {
			System.out.println("Reposiotry do not support acls");
		} else if (aclCapabilities == CapabilityAcl.DISCOVER) {
			System.out.println("Repository support discovery of ACLs");
		} else {
			System.out.println("Repository support discovery of ACLs and applying ACLs.");
		}

	}
}