package de.cmis.test.RepositoryCapabilities;

import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.data.RepositoryInfo;
import org.apache.chemistry.opencmis.commons.enums.CapabilityContentStreamUpdates;

import de.cmis.test.Session.SessionSingleton;

public class CheckContentStreamUpdationCapability {

	public static void main(String args[]) {
		Session session = SessionSingleton.getInstance().getSession("OpenCmisServer", "atom11");

		RepositoryInfo repoInfo = session.getRepositoryInfo();

		CapabilityContentStreamUpdates capabilityContentStreamUpdates = repoInfo.getCapabilities()
				.getContentStreamUpdatesCapability();

		if (capabilityContentStreamUpdates == null) {
			System.out.println("Repository is not providing any value");
		} else if (capabilityContentStreamUpdates == CapabilityContentStreamUpdates.NONE) {
			System.out.println("Content stream will not be updated");
		} else if (capabilityContentStreamUpdates == CapabilityContentStreamUpdates.ANYTIME) {
			System.out.println("Content stream can be updated at any time");
		} else if (capabilityContentStreamUpdates == CapabilityContentStreamUpdates.PWCONLY) {
			System.out.println("Content stream can be updated only when the document is checked out");
		}

	}

}