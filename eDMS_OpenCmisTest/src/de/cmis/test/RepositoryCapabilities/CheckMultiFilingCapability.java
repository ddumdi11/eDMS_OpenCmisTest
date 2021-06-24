package de.cmis.test.RepositoryCapabilities;

import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.data.RepositoryInfo;

import de.cmis.test.Session.SessionSingleton;

public class CheckMultiFilingCapability {

	public static void main(String args[]) {
		Session session = SessionSingleton.getInstance().getSession("OpenCmisServer", "atom11");

		RepositoryInfo repoInfo = session.getRepositoryInfo();

		Boolean isMultiFilingSupported = repoInfo.getCapabilities().isMultifilingSupported();

		if (isMultiFilingSupported == null) {
			System.out.println("Repository does not provide this value");
		} else if (isMultiFilingSupported) {
			System.out.println("Repository is supporting multi filing");
		} else {
			System.out.println("Repository is not supporting multi filing");
		}
	}

}