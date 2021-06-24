package de.cmis.test.RepositoryCapabilities;

import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.data.RepositoryInfo;
import org.apache.chemistry.opencmis.commons.enums.CapabilityRenditions;

import de.cmis.test.Tool;
import de.cmis.test.Session.SessionSingleton;

public class CheckRenditionSupport {

	public static void main(String args[]) {
		Session session = SessionSingleton.getInstance().getSession("OpenCmisServer", "atom11");

		RepositoryInfo repoInfo = session.getRepositoryInfo();

		CapabilityRenditions renditionsCapability = repoInfo.getCapabilities().getRenditionsCapability();

		if (renditionsCapability == null) {
			Tool.printAndLog("Repository is not providing this value");
		} else if (CapabilityRenditions.NONE == renditionsCapability) {
			Tool.printAndLog("Repository does not expose renditions at all");
		} else if (CapabilityRenditions.READ == renditionsCapability) {
			Tool.printAndLog("Renditions are provided by the repository and readable by the client");
		} else {
			Tool.printAndLog("Other value is written, which is not supported by repository");
		}
	}

}