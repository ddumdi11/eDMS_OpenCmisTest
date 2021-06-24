package com.sample.util.Browser.IntroductionCmis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.chemistry.opencmis.client.api.ObjectType;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.api.SessionFactory;
import org.apache.chemistry.opencmis.client.api.Tree;
import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl;
import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.enums.BindingType;

public class GetTypeDescendantsOfType {

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

	public static void printTypes(List<Tree<ObjectType>> objectTypes, String space) {

		for (Tree<ObjectType> objType : objectTypes) {
			ObjectType objectType = objType.getItem();
			System.out.println(space + objectType.getDisplayName());

			printTypes(objType.getChildren(), "  " + space);
		}
	}

	public static void main(String args[]) {
		Session session = getSession();

		List<Tree<ObjectType>> objectTypes = session.getTypeDescendants(null, -1, false);

		printTypes(objectTypes, "");

	}

}