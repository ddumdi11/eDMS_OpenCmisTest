package de.cmis.test.Queries;

import java.io.IOException;

import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.enums.CapabilityQuery;
import org.apache.chemistry.opencmis.commons.impl.json.parser.JSONParseException;

import de.cmis.test.Session.SessionSingleton;

public class GetQuerySupport {

	public static void main(String args[]) throws IOException, JSONParseException {
		Session session = SessionSingleton.getInstance().getSession("OpenCmisServer", "atom11");

		CapabilityQuery queryCapability = session.getRepositoryInfo().getCapabilities().getQueryCapability();

		if (queryCapability == null) {
			System.out.println("Repository don't support querys");
			return;
		}
		System.out.println("Query capability supported");
		System.out.println("Query capability : " + queryCapability);
	}
}