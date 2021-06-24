package de.cmis.test.RepositoryCapabilities;

import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.data.RepositoryInfo;

import de.cmis.test.Tool;
import de.cmis.test.Session.SessionSingleton;

public class CheckMultiFilingCapability {

	public static void main(String args[]) {
		Session session = SessionSingleton.getInstance().getSession("OpenCmisServer", "atom11");

		RepositoryInfo repoInfo = session.getRepositoryInfo();

		Boolean isMultiFilingSupported = repoInfo.getCapabilities().isMultifilingSupported();

		if (isMultiFilingSupported == null) {
			Tool.printAndLog("Repository does not provide this value");
		} else if (isMultiFilingSupported) {
			Tool.printAndLog("Repository is supporting multi filing");
		} else {
			Tool.printAndLog("Repository is not supporting multi filing");
		}
	}

}