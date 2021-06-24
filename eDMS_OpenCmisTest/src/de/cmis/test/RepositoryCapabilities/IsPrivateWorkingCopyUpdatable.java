package de.cmis.test.RepositoryCapabilities;

import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.data.RepositoryInfo;

import de.cmis.test.Tool;
import de.cmis.test.Session.SessionSingleton;

public class IsPrivateWorkingCopyUpdatable {

	public static void main(String args[]) {
		Session session = SessionSingleton.getInstance().getSession("OpenCmisServer", "atom11");

		RepositoryInfo repoInfo = session.getRepositoryInfo();

		Boolean isPWCUpdatable = repoInfo.getCapabilities().isPwcUpdatableSupported();

		if (isPWCUpdatable == null) {
			Tool.printAndLog("Repository is not providing this value");
		} else if (isPWCUpdatable) {
			Tool.printAndLog("Private working copy can be updatable");
		} else {
			Tool.printAndLog("Private working copy updation is not supported");
		}
	}

}