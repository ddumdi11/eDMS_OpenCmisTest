package com.sample.util.Browser.SecondaryTypes;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.chemistry.opencmis.client.api.CmisObject;
import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.api.SessionFactory;
import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl;
import org.apache.chemistry.opencmis.commons.PropertyIds;
import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.enums.BindingType;

public class RemoveSecondaryType {

	private static String serverURL = "http://localhost:8089/chemistry-opencmis-server-inmemory-1.1.0/browser";
	private static String repositoryId = "A1";

	private static Session getSession() {
		Map<String, String> parameters = new HashMap<>();
		parameters.put(SessionParameter.BINDING_TYPE, BindingType.BROWSER.value());

		parameters.put(SessionParameter.USER, "");
		parameters.put(SessionParameter.PASSWORD, "");

		parameters.put(SessionParameter.REPOSITORY_ID, repositoryId);
		parameters.put(SessionParameter.BROWSER_URL, serverURL);

		SessionFactory sessionFactory = SessionFactoryImpl.newInstance();
		return sessionFactory.createSession(parameters);
	}

	private static void printDocument(CmisObject cmisObject) {
		System.out.println("***********************************************");
		System.out.println("contact us : " + cmisObject.getPropertyValue("abc:contactUs"));
		System.out.println("Help : " + cmisObject.getPropertyValue("abc:help"));
		System.out.println("confidentialValue : " + cmisObject.getPropertyValue("abc:confidentialLevel"));

		List<String> secondaryTypes = cmisObject.getPropertyValue(PropertyIds.SECONDARY_OBJECT_TYPE_IDS);

		System.out.println("Secondary types are");
		for (String secondaryType : secondaryTypes) {
			System.out.println(secondaryType);
		}
	}

	public static void main(String args[]) throws IOException {

		Session session = getSession();

		CmisObject cmisObject = session.getObjectByPath("/emptyDocument.txt");

		System.out.println("Before removing secondary type 'abc:confidentialDocs'");
		printDocument(cmisObject);

		/* Get current secondary types */
		List<String> secondaryTypes = cmisObject.getPropertyValue(PropertyIds.SECONDARY_OBJECT_TYPE_IDS);
		if (secondaryTypes == null) {
			System.out.println("No secondary types associated to this object");
			return;
		}

		Map<String, Object> properties = new HashMap<String, Object>();

		/* Remove the new secondary type */
		secondaryTypes.remove("abc:confidentialDocs");
		properties.put(PropertyIds.SECONDARY_OBJECT_TYPE_IDS, secondaryTypes);

		/* update properties */
		cmisObject.updateProperties(properties);

		System.out.println("\nAfter removing secondary type 'abc:confidentialDocs'");
		printDocument((Document) cmisObject);
	}

}