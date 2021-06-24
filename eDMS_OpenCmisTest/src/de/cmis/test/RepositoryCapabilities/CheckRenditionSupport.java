package de.cmis.test.RepositoryCapabilities;

import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.data.RepositoryInfo;
import org.apache.chemistry.opencmis.commons.enums.CapabilityRenditions;

import de.cmis.test.Session.SessionSingleton;

public class CheckRenditionSupport {

	public static void main(String args[]) {
		Session session = SessionSingleton.getInstance().getSession("OpenCmisServer", "atom11");

		RepositoryInfo repoInfo = session.getRepositoryInfo();

		CapabilityRenditions renditionsCapability = repoInfo.getCapabilities().getRenditionsCapability();

		if (renditionsCapability == null) {
			System.out.println("Repository is not providing this value");
		} else if (CapabilityRenditions.NONE == renditionsCapability) {
			System.out.println("Repository does not expose renditions at all");
		} else if (CapabilityRenditions.READ == renditionsCapability) {
			System.out.println("Renditions are provided by the repository and readable by the client");
		} else {
			System.out.println("Other value is written, which is not supported by repository");
		}
	}

}