package de.cmis.test.ChangeLogSupport;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.chemistry.opencmis.client.api.ChangeEvent;
import org.apache.chemistry.opencmis.client.api.ChangeEvents;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.enums.CapabilityChanges;

import de.cmis.test.Tool;
import de.cmis.test.Session.SessionSingleton;

public class CheckChangeLogChangeEvents {

	public static void main(String args[]) throws IOException {
		Session session = SessionSingleton.getInstance().getSession("OpenCmisServer", "atom11");

		CapabilityChanges capabilityChanges = session.getRepositoryInfo().getCapabilities().getChangesCapability();

		if (capabilityChanges == null) {
			Tool.printAndLog("Repository is not providing any value");
			return;
		}

		if (CapabilityChanges.NONE == capabilityChanges) {
			Tool.printAndLog("Repository is not supporitng change log");
			return;
		}

		Tool.printAndLog("capabilityChanges : " + capabilityChanges);
		ChangeEvents oldChangeEvents = session.getContentChanges(null, false, 1000);
		String previousChangeLogToken = oldChangeEvents.getLatestChangeLogToken();
		Tool.printAndLog("Change log token " + previousChangeLogToken);

		Tool.printAndLog("Press Enter to get latest change events");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br.readLine();

		ChangeEvents changeEvents = session.getContentChanges(previousChangeLogToken, false, 1000);

		String latestChangeLogToken = changeEvents.getLatestChangeLogToken();
		Tool.printAndLog("Change log token " + latestChangeLogToken);

		for (ChangeEvent changeEvent : changeEvents.getChangeEvents()) {
			Tool.printAndLog(changeEvent.getObjectId() + ", " + changeEvent.getChangeType());
		}

	}

}