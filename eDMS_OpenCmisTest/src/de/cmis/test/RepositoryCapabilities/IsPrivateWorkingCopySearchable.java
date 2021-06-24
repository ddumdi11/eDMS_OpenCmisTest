package de.cmis.test.RepositoryCapabilities;

import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.data.RepositoryInfo;

import de.cmis.test.Tool;
import de.cmis.test.Session.SessionSingleton;

public class IsPrivateWorkingCopySearchable {

	public static void main(String args[]) {
		Session session = SessionSingleton.getInstance().getSession("OpenCmisServer", "atom11");

		RepositoryInfo repoInfo = session.getRepositoryInfo();

		Boolean isPWCSearchable = repoInfo.getCapabilities().isPwcSearchableSupported();

		if (isPWCSearchable == null) {
			Tool.printAndLog("Repository is not providing this value");
		} else if (isPWCSearchable) {
			Tool.printAndLog("Private working copy is searchable");
		} else {
			Tool.printAndLog("Private working copy is not searchable");
		}
	}

}