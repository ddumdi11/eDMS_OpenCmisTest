package de.cmis.test.RepositoryCapabilities;

import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.data.RepositoryInfo;
import org.apache.chemistry.opencmis.commons.enums.CapabilityAcl;

import de.cmis.test.Session.SessionSingleton;

public class CheckAclCapabilities {

	public static void main(String args[]) {
		Session session = SessionSingleton.getInstance().getSession("OpenCmisServer", "atom11");

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