package de.cmis.test.RepositoryCapabilities;

import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.data.RepositoryInfo;

import de.cmis.test.Session.SessionSingleton;

public class IsPrivateWorkingCopyUpdatable {

	public static void main(String args[]) {
		Session session = SessionSingleton.getInstance().getSession("OpenCmisServer", "atom11");

		RepositoryInfo repoInfo = session.getRepositoryInfo();

		Boolean isPWCUpdatable = repoInfo.getCapabilities().isPwcUpdatableSupported();

		if (isPWCUpdatable == null) {
			System.out.println("Repository is not providing this value");
		} else if (isPWCUpdatable) {
			System.out.println("Private working copy can be updatable");
		} else {
			System.out.println("Private working copy updation is not supported");
		}
	}

}