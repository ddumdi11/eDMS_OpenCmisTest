package de.cmis.test.RepositoryCapabilities;

import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.data.RepositoryInfo;

import de.cmis.test.Session.SessionSingleton;

public class CheckUnfilingCapability {

	public static void main(String args[]) {
		Session session = SessionSingleton.getInstance().getSession("OpenCmisServer", "atom11");

		RepositoryInfo repoInfo = session.getRepositoryInfo();

		Boolean isUnfilingSupported = repoInfo.getCapabilities().isUnfilingSupported();

		if (isUnfilingSupported == null) {
			System.out.println("Repository does not provide this value");
		} else if (isUnfilingSupported) {
			System.out.println("Repository is supporting unfiling");
		} else {
			System.out.println("Repository is not supporting unfiling");
		}
	}

}