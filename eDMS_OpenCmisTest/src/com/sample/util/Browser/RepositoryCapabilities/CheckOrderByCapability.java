package com.sample.util.Browser.RepositoryCapabilities;

import java.util.HashMap;
import java.util.Map;

import org.apache.chemistry.opencmis.client.api.CmisObject;
import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.ItemIterable;
import org.apache.chemistry.opencmis.client.api.OperationContext;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.api.SessionFactory;
import org.apache.chemistry.opencmis.client.runtime.OperationContextImpl;
import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl;
import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.data.RepositoryInfo;
import org.apache.chemistry.opencmis.commons.enums.BindingType;
import org.apache.chemistry.opencmis.commons.enums.CapabilityOrderBy;

public class CheckOrderByCapability {

	private static String serverURL = "http://localhost:8089/chemistry-opencmis-server-inmemory-1.1.0/browser";
	private static String repositoryId = "A1";

	public static Session getSession() {
		Map<String, String> parameters = new HashMap<>();
		parameters.put(SessionParameter.BINDING_TYPE, BindingType.BROWSER.value());

		parameters.put(SessionParameter.USER, "");
		parameters.put(SessionParameter.PASSWORD, "");

		parameters.put(SessionParameter.REPOSITORY_ID, repositoryId);
		parameters.put(SessionParameter.BROWSER_URL, serverURL);

		SessionFactory sessionFactory = SessionFactoryImpl.newInstance();
		return sessionFactory.createSession(parameters);
	}

	public static void main(String args[]) {
		Session session = getSession();

		RepositoryInfo repoInfo = session.getRepositoryInfo();

		CapabilityOrderBy capabilityOrderBy = repoInfo.getCapabilities().getOrderByCapability();

		if (capabilityOrderBy == null) {
			System.out.println("Ordering is not supported");
			return;
		}

		System.out.println(capabilityOrderBy);

		Folder rootFolder = session.getRootFolder();

		OperationContext ascContext = new OperationContextImpl();
		ascContext.setOrderBy("cmis:name ASC, cmis:creationDate ASC");

		OperationContext descContext = new OperationContextImpl();
		descContext.setOrderBy("cmis:name DESC, cmis:creationDate DESC");

		printChildren(rootFolder, ascContext);

		System.out.println("\n************************************\n");
		printChildren(rootFolder, descContext);
	}

	private static void printChildren(Folder rootFolder, OperationContext context) {
		ItemIterable<CmisObject> cmisObjects = rootFolder.getChildren(context);

		for (CmisObject cmisObject : cmisObjects) {
			System.out.println(cmisObject.getName());
		}
	}
}