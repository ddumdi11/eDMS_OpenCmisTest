package de.cmis.test.RepositoryCapabilities;

import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.data.RepositoryInfo;

import de.cmis.test.Session.SessionSingleton;

public class IsPrivateWorkingCopySearchable {

	public static void main(String args[]) {
		Session session = SessionSingleton.getInstance().getSession("OpenCmisServer", "atom11");

		RepositoryInfo repoInfo = session.getRepositoryInfo();

		Boolean isPWCSearchable = repoInfo.getCapabilities().isPwcSearchableSupported();

		if (isPWCSearchable == null) {
			System.out.println("Repository is not providing this value");
		} else if (isPWCSearchable) {
			System.out.println("Private working copy is searchable");
		} else {
			System.out.println("Private working copy is not searchable");
		}
	}

}