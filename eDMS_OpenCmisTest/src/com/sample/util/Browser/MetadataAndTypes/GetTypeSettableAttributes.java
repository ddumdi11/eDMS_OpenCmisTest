package com.sample.util.Browser.MetadataAndTypes;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.api.SessionFactory;
import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl;
import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.data.NewTypeSettableAttributes;
import org.apache.chemistry.opencmis.commons.enums.BindingType;

public class GetTypeSettableAttributes {

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

		NewTypeSettableAttributes settableAttributes = session.getRepositoryInfo().getCapabilities()
				.getNewTypeSettableAttributes();

		boolean canSetControllableAcl = settableAttributes.canSetControllableAcl();
		boolean canSetControllablePolicy = settableAttributes.canSetControllablePolicy();
		boolean canSetCreatable = settableAttributes.canSetCreatable();
		boolean canSetDescription = settableAttributes.canSetDescription();
		boolean canSetDisplayName = settableAttributes.canSetDisplayName();
		boolean canSetFileable = settableAttributes.canSetFileable();
		boolean canSetFulltextIndexed = settableAttributes.canSetFulltextIndexed();
		boolean canSetId = settableAttributes.canSetId();
		boolean canSetIncludedInSupertypeQuery = settableAttributes.canSetIncludedInSupertypeQuery();
		boolean canSetLocalName = settableAttributes.canSetLocalName();
		boolean canSetLocalNamespace = settableAttributes.canSetLocalNamespace();
		boolean canSetQueryable = settableAttributes.canSetQueryable();
		boolean canSetQueryName = settableAttributes.canSetQueryName();

		System.out.println("canSetControllableAcl : " + canSetControllableAcl);
		System.out.println("canSetControllablePolicy : " + canSetControllablePolicy);
		System.out.println("canSetCreatable : " + canSetCreatable);
		System.out.println("canSetDescription : " + canSetDescription);
		System.out.println("canSetDisplayName : " + canSetDisplayName);
		System.out.println("canSetFileable : " + canSetFileable);
		System.out.println("canSetFulltextIndexed : " + canSetFulltextIndexed);
		System.out.println("canSetId : " + canSetId);
		System.out.println("canSetIncludedInSupertypeQuery : " + canSetIncludedInSupertypeQuery);
		System.out.println("canSetLocalName : " + canSetLocalName);
		System.out.println("canSetLocalNamespace : " + canSetLocalNamespace);
		System.out.println("canSetQueryable : " + canSetQueryable);
		System.out.println("canSetQueryName : " + canSetQueryName);
	}
}