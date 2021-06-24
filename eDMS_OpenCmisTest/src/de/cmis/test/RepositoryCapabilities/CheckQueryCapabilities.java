package de.cmis.test.RepositoryCapabilities;

import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.data.RepositoryInfo;
import org.apache.chemistry.opencmis.commons.enums.CapabilityQuery;

import de.cmis.test.Session.SessionSingleton;

public class CheckQueryCapabilities {

	public static void main(String args[]) {
		Session session = SessionSingleton.getInstance().getSession("OpenCmisServer", "atom11");

		RepositoryInfo repoInfo = session.getRepositoryInfo();

		CapabilityQuery queryCapability = repoInfo.getCapabilities().getQueryCapability();

		if (queryCapability == null) {
			System.out.println("Repository is not providing any value");
		} else if (CapabilityQuery.NONE == queryCapability) {
			System.out.println("Query capability is not supported");
		} else if (CapabilityQuery.METADATAONLY == queryCapability) {
			System.out.println("Support queries that filter based on object properties");
		} else if (CapabilityQuery.FULLTEXTONLY == queryCapability) {
			System.out.println("Support queries that filter based on the full-text content of documents");
		} else if (CapabilityQuery.BOTHSEPARATE == queryCapability) {
			System.out.println(
					"Support queries that filter EITHER on the full-text content of documents OR on their properties, but NOT if both types of filters are included in the same query.");
		} else if (CapabilityQuery.BOTHCOMBINED == queryCapability) {
			System.out.println(
					"Support that filter on both the full-text content of documents and their properties in the same query.");
		}

	}

}