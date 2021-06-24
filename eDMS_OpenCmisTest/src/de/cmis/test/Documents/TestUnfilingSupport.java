package de.cmis.test.Documents;

import java.io.IOException;

import org.apache.chemistry.opencmis.client.api.Session;

import de.cmis.test.Session.SessionSingleton;

public class TestUnfilingSupport {

	

	public static void main(String args[]) throws IOException {
		Session session = SessionSingleton.getInstance().getSession("OpenCmisServer", "atom11");

		boolean isUnfilingSupprted = session.getRepositoryInfo().getCapabilities().isUnfilingSupported();

		if (!isUnfilingSupprted) {
			System.out.println("unfiling documents are not supported");
			return;
		}

		System.out.println("unfiling doucments are supported by the repository");

	}
}