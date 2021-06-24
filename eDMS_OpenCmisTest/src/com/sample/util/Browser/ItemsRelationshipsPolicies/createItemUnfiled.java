package com.sample.util.Browser.ItemsRelationshipsPolicies;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.chemistry.opencmis.client.api.Item;
import org.apache.chemistry.opencmis.client.api.ObjectId;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.api.SessionFactory;
import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl;
import org.apache.chemistry.opencmis.commons.PropertyIds;
import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.enums.BindingType;

public class createItemUnfiled {

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

	public static void main(String args[]) throws IOException {

		Session session = getSession();

		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put(PropertyIds.NAME, "My Demo unfiled item");
		properties.put(PropertyIds.OBJECT_TYPE_ID, "cmis:item");

		ObjectId itemId = session.createItem(properties, null);

		Item item = (Item) session.getObject(itemId);

		System.out.println((PropertyIds.NAME + ": " + item.getPropertyValue(PropertyIds.NAME)));
		System.out.println((PropertyIds.OBJECT_TYPE_ID + ": " + item.getPropertyValue(PropertyIds.OBJECT_TYPE_ID)));

	}

}