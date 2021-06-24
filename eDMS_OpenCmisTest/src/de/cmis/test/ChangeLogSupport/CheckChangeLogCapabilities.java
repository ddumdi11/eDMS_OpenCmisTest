package de.cmis.test.ChangeLogSupport;

import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.data.RepositoryInfo;
import org.apache.chemistry.opencmis.commons.enums.CapabilityChanges;

import de.cmis.test.Tool;
import de.cmis.test.Session.SessionSingleton;

public class CheckChangeLogCapabilities {

	public static void main(String args[]) {
		Session session = SessionSingleton.getInstance().getSession("OpenCmisServer", "atom11");

		RepositoryInfo repoInfo = session.getRepositoryInfo();

		CapabilityChanges capabilityChanges = repoInfo.getCapabilities().getChangesCapability();

		if (capabilityChanges == null) {
			Tool.printAndLog("Repository is not providing this value");
		} else if (capabilityChanges == CapabilityChanges.NONE) {
			Tool.printAndLog("The repository does not support the change log feature");
		} else if (capabilityChanges == CapabilityChanges.OBJECTIDSONLY) {
			Tool.printAndLog(
					"The change log can return only the object ids for changed objects in the repository and an indication of the type of change, not details of the actual change.");
		} else if (capabilityChanges == CapabilityChanges.PROPERTIES) {
			Tool.printAndLog("The change log can return properties and the object id for the changed objects.");
		} else if (capabilityChanges == CapabilityChanges.ALL) {
			Tool.printAndLog(
					"The change log can return the object ids for changed objects in the repository and more information about the actual change.");
		}
	}

}