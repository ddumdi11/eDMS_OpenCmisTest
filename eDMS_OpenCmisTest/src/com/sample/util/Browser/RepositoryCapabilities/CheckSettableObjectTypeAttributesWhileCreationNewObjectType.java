package com.sample.util.Browser.RepositoryCapabilities;

import java.util.HashMap;
import java.util.Map;

import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.api.SessionFactory;
import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl;
import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.data.NewTypeSettableAttributes;
import org.apache.chemistry.opencmis.commons.data.RepositoryInfo;
import org.apache.chemistry.opencmis.commons.enums.BindingType;

public class CheckSettableObjectTypeAttributesWhileCreationNewObjectType {

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

		NewTypeSettableAttributes newTypeSettableAttributes = repoInfo.getCapabilities().getNewTypeSettableAttributes();

		System.out.println("can set Id : " + newTypeSettableAttributes.canSetId());
		System.out.println("can set local name : " + newTypeSettableAttributes.canSetLocalName());
		System.out.println("can set local name space : " + newTypeSettableAttributes.canSetLocalNamespace());
		System.out.println("can set display name : " + newTypeSettableAttributes.canSetDisplayName());
		System.out.println("can set query name : " + newTypeSettableAttributes.canSetQueryName());
		System.out.println("can set description : " + newTypeSettableAttributes.canSetDescription());
		System.out.println("can set creatable : " + newTypeSettableAttributes.canSetCreatable());
		System.out.println("can set fileable : " + newTypeSettableAttributes.canSetFileable());
		System.out.println("can set queryable : " + newTypeSettableAttributes.canSetQueryable());
		System.out.println("can full text indexed : " + newTypeSettableAttributes.canSetFulltextIndexed());
		System.out.println(
				"can included in super type query : " + newTypeSettableAttributes.canSetIncludedInSupertypeQuery());
		System.out.println("can control policy : " + newTypeSettableAttributes.canSetControllablePolicy());
		System.out.println("can control ACL : " + newTypeSettableAttributes.canSetControllableAcl());

	}

}