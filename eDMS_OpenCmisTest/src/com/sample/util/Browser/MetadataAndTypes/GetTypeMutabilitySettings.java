package com.sample.util.Browser.MetadataAndTypes;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.ObjectType;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.api.SessionFactory;
import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl;
import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.enums.BindingType;

public class GetTypeMutabilitySettings {

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

	public static void main(String args[]) throws IOException {
		Session session = getSession();

		Folder folder = session.getRootFolder();
		ObjectType objectType = folder.getType();

		boolean canCreate = objectType.getTypeMutability().canCreate();
		boolean canDelete = objectType.getTypeMutability().canDelete();
		boolean canUpdate = objectType.getTypeMutability().canUpdate();

		System.out.println("canCreate : " + canCreate);
		System.out.println("canDelete : " + canDelete);
		System.out.println("canUpdate : " + canUpdate);
	}
}