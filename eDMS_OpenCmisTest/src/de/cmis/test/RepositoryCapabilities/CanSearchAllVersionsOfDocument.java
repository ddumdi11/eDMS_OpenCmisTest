package de.cmis.test.RepositoryCapabilities;

import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.data.RepositoryInfo;

import de.cmis.test.Tool;
import de.cmis.test.Session.SessionSingleton;

public class CanSearchAllVersionsOfDocument {

	public static void main(String args[]) {
		Session session = SessionSingleton.getInstance().getSession("OpenCmisServer", "atom11");

		RepositoryInfo repoInfo = session.getRepositoryInfo();

		Boolean isAllVersionsSearchable = repoInfo.getCapabilities().isAllVersionsSearchableSupported();

		if (isAllVersionsSearchable == null) {
			Tool.printAndLog("Repository is not providing this value");
		} else if (isAllVersionsSearchable) {
			Tool.printAndLog("You can search all the versions of a document");
		} else {
			Tool.printAndLog("You can't search all the versions");
		}
	}

}