package de.cmis.test.RepositoryCapabilities;

import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.data.RepositoryInfo;
import org.apache.chemistry.opencmis.commons.enums.CapabilityJoin;

import de.cmis.test.Tool;
import de.cmis.test.Session.SessionSingleton;

public class CheckQueryJoiningCapabilities {

	public static void main(String args[]) {
		Session session = SessionSingleton.getInstance().getSession("OpenCmisServer", "atom11");

		RepositoryInfo repoInfo = session.getRepositoryInfo();

		CapabilityJoin joinCapability = repoInfo.getCapabilities().getJoinCapability();

		if (joinCapability == null) {
			Tool.printAndLog("Repository is not providing any value");
		} else if (CapabilityJoin.NONE == joinCapability) {
			Tool.printAndLog(
					"The repository cannot fulfill any queries that include any JOIN clauses on two primary types. If the Repository supports secondary types, JOINs on secondary types SHOULD be supported, even if the support level is none.");
		} else if (CapabilityJoin.INNERONLY == joinCapability) {
			Tool.printAndLog(
					"The repository can fulfill queries that include an INNER JOIN clause, but cannot fulfill queries that include other types of JOIN clauses.");
		} else if (CapabilityJoin.INNERANDOUTER == joinCapability) {
			Tool.printAndLog(
					"The repository can fulfill queries that include any type of JOIN clause defined by the CMIS query grammar.");
		} else {
			Tool.printAndLog("Feature is not implemented as per specification");
		}

	}

}