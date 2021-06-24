package de.cmis.test.RepositoryCapabilities;

import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.data.RepositoryInfo;

import de.cmis.test.Session.SessionSingleton;

public class CheckVersioningCapabilities {

	public static void main(String args[]) {
		Session session = SessionSingleton.getInstance().getSession("OpenCmisServer", "atom11");

		RepositoryInfo repoInfo = session.getRepositoryInfo();

		Boolean isVersionSpecificFilingSupported = repoInfo.getCapabilities().isVersionSpecificFilingSupported();

		if (isVersionSpecificFilingSupported == null) {
			System.out.println("Repository does not provide this value");
		} else if (isVersionSpecificFilingSupported) {
			System.out.println("Repository is supporting version specific filing");
		} else {
			System.out.println("Repository is not supporting version specific filing");
		}
	}

}