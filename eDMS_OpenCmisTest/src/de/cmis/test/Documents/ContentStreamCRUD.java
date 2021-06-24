package de.cmis.test.Documents;

import java.io.IOException;

import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.enums.CapabilityContentStreamUpdates;

import de.cmis.test.Tool;
import de.cmis.test.Session.SessionSingleton;

public class ContentStreamCRUD {

	public static void main(String args[]) throws IOException {
		Session session = SessionSingleton.getInstance().getSession("OpenCmisServer", "atom11");

		CapabilityContentStreamUpdates capabilityContentStreamUpdates = session.getRepositoryInfo().getCapabilities()
				.getContentStreamUpdatesCapability();

		if (capabilityContentStreamUpdates == null) {
			Tool.printAndLog("Repository do not provide this value");
		} else if (CapabilityContentStreamUpdates.NONE == capabilityContentStreamUpdates) {
			Tool.printAndLog("Content stream may never be updated.");
		} else if (CapabilityContentStreamUpdates.ANYTIME == capabilityContentStreamUpdates) {
			Tool.printAndLog("Content stream can be updated any time.");
		} else if (CapabilityContentStreamUpdates.PWCONLY == capabilityContentStreamUpdates) {
			Tool.printAndLog(
					"Content stream is updated only when the document is checked out. PWC stands for Private Working Copy.");
		} else {
			Tool.printAndLog("It is not implemented as per the CMIS Specification");
		}
	}

}