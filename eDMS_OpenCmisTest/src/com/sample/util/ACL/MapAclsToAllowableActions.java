package com.sample.util.ACL;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.chemistry.opencmis.client.api.OperationContext;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.api.SessionFactory;
import org.apache.chemistry.opencmis.client.runtime.OperationContextImpl;
import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl;
import org.apache.chemistry.opencmis.commons.PropertyIds;
import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.data.PermissionMapping;
import org.apache.chemistry.opencmis.commons.enums.BindingType;

public class MapAclsToAllowableActions {

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

	public static OperationContext getDefaultContext() {
		OperationContextImpl operationalContext = new OperationContextImpl();

		Set<String> filterSet = new HashSet<String>();

		operationalContext.setIncludeAcls(true);
		operationalContext.setIncludeAllowableActions(true);
		operationalContext.setIncludePolicies(false);

		filterSet.add(PropertyIds.VERSION_SERIES_ID);
		filterSet.add(PropertyIds.VERSION_SERIES_CHECKED_OUT_ID);
		filterSet.add(PropertyIds.IS_VERSION_SERIES_CHECKED_OUT);
		filterSet.add(PropertyIds.IS_PRIVATE_WORKING_COPY);
		filterSet.add(PropertyIds.IS_LATEST_VERSION);
		filterSet.add(PropertyIds.OBJECT_ID);
		filterSet.add(PropertyIds.NAME);
		filterSet.add(PropertyIds.CONTENT_STREAM_LENGTH);
		filterSet.add(PropertyIds.CHANGE_TOKEN);
		filterSet.add(PropertyIds.LAST_MODIFICATION_DATE);
		filterSet.add(PropertyIds.LAST_MODIFIED_BY);
		filterSet.add(PropertyIds.SECONDARY_OBJECT_TYPE_IDS);
		filterSet.add(PropertyIds.CONTENT_STREAM_MIME_TYPE);

		operationalContext.setFilter(filterSet);

		return operationalContext;
	}

	public static void main(String args[]) {
		Session session = getSession();
		session.setDefaultContext(getDefaultContext());

		Map<String, PermissionMapping> permissionMappings = session.getRepositoryInfo().getAclCapabilities()
				.getPermissionMapping();

		for (String key : permissionMappings.keySet()) {

			PermissionMapping permMapping = permissionMappings.get(key);

			System.out.println(key + " : " + permMapping.getPermissions());

		}

	}
}