package de.cmis.test.General;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.chemistry.opencmis.client.api.Repository;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.api.SessionFactory;
import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl;
import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.data.AclCapabilities;
import org.apache.chemistry.opencmis.commons.data.RepositoryInfo;
import org.apache.chemistry.opencmis.commons.definitions.PermissionDefinition;
import org.apache.chemistry.opencmis.commons.enums.BindingType;

public class TestCmisGetBasicRepositoryInfo {

	public static List<Repository> getRepositories(String serverURL) {
		Map<String, String> parameters = new HashMap<>();
		parameters.put(SessionParameter.BINDING_TYPE, BindingType.ATOMPUB.value());

		parameters.put(SessionParameter.USER, "");
		parameters.put(SessionParameter.PASSWORD, "");

		parameters.put(SessionParameter.ATOMPUB_URL, serverURL);

		SessionFactory sessionFactory = SessionFactoryImpl.newInstance();
		List<Repository> repositories = sessionFactory.getRepositories(parameters);
		return repositories;
	}

	public static void printRepositoryInformation(RepositoryInfo repositoryInfo) {
		System.out.println("******************************************************");
		System.out.println("Id : " + repositoryInfo.getId());
		System.out.println("Cmis Version Supported : " + repositoryInfo.getCmisVersionSupported());
		System.out.println("Description : " + repositoryInfo.getDescription());
		System.out.println("Latest Change log token : " + repositoryInfo.getLatestChangeLogToken());
		System.out.println("Name : " + repositoryInfo.getName());
		System.out.println("Principal ID of authenticated user : " + repositoryInfo.getPrincipalIdAnonymous());
		System.out.println("Principal ID for unauthenticated user " + repositoryInfo.getPrincipalIdAnyone());
		System.out.println("Product Name : " + repositoryInfo.getProductName());
		System.out.println("Product Version : " + repositoryInfo.getProductVersion());
		System.out.println("Root Folder Id : " + repositoryInfo.getRootFolderId());
		System.out.println("URL of a web interface for this repository : " + repositoryInfo.getThinClientUri());
		System.out.println("Vendor Name : " + repositoryInfo.getVendorName());
		printACLCapabilities(repositoryInfo);
		System.out.println("******************************************************");
	}

	public static void printACLCapabilities(RepositoryInfo repositoryInfo) {
		System.out.println("\n----------------------------------------------------------");
		System.out.println("ACL Capabilities");
		AclCapabilities aclCapabilities = repositoryInfo.getAclCapabilities();

		List<PermissionDefinition> permissionDefinitions = aclCapabilities.getPermissions();

		for (PermissionDefinition permissionDefinition : permissionDefinitions) {
			System.out.println(permissionDefinition.getId() + " " + permissionDefinition.getDescription());
		}
		System.out.println("----------------------------------------------------------");
	}

	public static void main(String args[]) {
		String serverURL = "http://localhost:8089/chemistry-opencmis-server-inmemory-1.1.0/atom11";
		List<Repository> repositories = getRepositories(serverURL);

		for (Repository repository : repositories) {

			Session session = repository.createSession();
			RepositoryInfo repositoryInfo = session.getRepositoryInfo();
			printRepositoryInformation(repositoryInfo);
		}
	}
}