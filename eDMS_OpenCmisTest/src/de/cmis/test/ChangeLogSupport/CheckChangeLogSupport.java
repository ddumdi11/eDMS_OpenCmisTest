package de.cmis.test.ChangeLogSupport;

import org.apache.chemistry.opencmis.client.api.Session;

import de.cmis.test.Tool;
import de.cmis.test.Session.SessionSingleton;

public class CheckChangeLogSupport {

	public static void main(String args[]) {
		Session session = SessionSingleton.getInstance().getSession("OpenCmisServer", "atom11");

		Boolean isChangesInComplete = session.getRepositoryInfo().getChangesIncomplete();

		if (isChangesInComplete) {
			Tool.printAndLog("Changes in the changelog are incomplete");
			return;
		}

		Tool.printAndLog("Changes in the change log are complete");
	}

}