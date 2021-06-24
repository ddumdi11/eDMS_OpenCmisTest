package de.cmis.test.RepositoryCapabilities;

import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.data.RepositoryInfo;
import org.apache.chemistry.opencmis.commons.enums.CapabilityAcl;

import de.cmis.test.Tool;
import de.cmis.test.Session.SessionSingleton;

public class CheckAclCapabilities {

	public static void main(String args[]) {
		Session session = SessionSingleton.getInstance().getSession("OpenCmisServer", "atom11");

		RepositoryInfo repoInfo = session.getRepositoryInfo();

		CapabilityAcl aclCapability = repoInfo.getCapabilities().getAclCapability();

		if (aclCapability == null) {
			Tool.printAndLog("Repository do not provide any value");
		} else if (CapabilityAcl.NONE == aclCapability) {
			Tool.printAndLog("The repository does not support ACL services.");
		} else if (CapabilityAcl.DISCOVER == aclCapability) {
			Tool.printAndLog("The repository supports discovery of ACLs (getACL and other services)");
		} else if (CapabilityAcl.MANAGE == aclCapability) {
			Tool.printAndLog(
					"The repository supports discovery of ACLs AND applying ACLs (getACL and apply-ACL services).");
		} else {
			Tool.printAndLog("Service is not implemented by the repository");
		}

	}

}