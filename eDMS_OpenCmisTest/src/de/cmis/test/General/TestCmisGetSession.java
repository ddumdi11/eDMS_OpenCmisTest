package de.cmis.test.General;

import java.util.HashMap;
import java.util.Map;

import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.api.SessionFactory;
import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl;
import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.enums.BindingType;

import de.cmis.test.Tool;

public class TestCmisGetSession {

	public static Session getSession(String repositoryId, String serverURL) {
		Map<String, String> parameters = new HashMap<>();
		parameters.put(SessionParameter.BINDING_TYPE, BindingType.ATOMPUB.value());

		parameters.put(SessionParameter.USER, "");
		parameters.put(SessionParameter.PASSWORD, "");

		parameters.put(SessionParameter.REPOSITORY_ID, repositoryId);
		parameters.put(SessionParameter.ATOMPUB_URL, serverURL);

		SessionFactory sessionFactory = SessionFactoryImpl.newInstance();
		return sessionFactory.createSession(parameters);
	}

	public static void main(String args[]) {
		String repositoryId = "A1";
		String serverURL = "http://localhost:8089/chemistry-opencmis-server-inmemory-1.1.0/atom11";
		Session session = getSession(repositoryId, serverURL);

		Tool.printAndLog(session.getRepositoryInfo().getName());
	}
}