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

import de.cmis.test.Tool;

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
		Tool.printAndLog("******************************************************");
		Tool.printAndLog("Id : " + repositoryInfo.getId());
		Tool.printAndLog("Cmis Version Supported : " + repositoryInfo.getCmisVersionSupported());
		Tool.printAndLog("Description : " + repositoryInfo.getDescription());
		Tool.printAndLog("Latest Change log token : " + repositoryInfo.getLatestChangeLogToken());
		Tool.printAndLog("Name : " + repositoryInfo.getName());
		Tool.printAndLog("Principal ID of authenticated user : " + repositoryInfo.getPrincipalIdAnonymous());
		Tool.printAndLog("Principal ID for unauthenticated user " + repositoryInfo.getPrincipalIdAnyone());
		Tool.printAndLog("Product Name : " + repositoryInfo.getProductName());
		Tool.printAndLog("Product Version : " + repositoryInfo.getProductVersion());
		Tool.printAndLog("Root Folder Id : " + repositoryInfo.getRootFolderId());
		Tool.printAndLog("URL of a web interface for this repository : " + repositoryInfo.getThinClientUri());
		Tool.printAndLog("Vendor Name : " + repositoryInfo.getVendorName());
		printACLCapabilities(repositoryInfo);
		Tool.printAndLog("******************************************************");
	}

	public static void printACLCapabilities(RepositoryInfo repositoryInfo) {
		Tool.printAndLog("\n----------------------------------------------------------");
		Tool.printAndLog("ACL Capabilities");
		AclCapabilities aclCapabilities = repositoryInfo.getAclCapabilities();

		List<PermissionDefinition> permissionDefinitions = aclCapabilities.getPermissions();

		for (PermissionDefinition permissionDefinition : permissionDefinitions) {
			Tool.printAndLog(permissionDefinition.getId() + " " + permissionDefinition.getDescription());
		}
		Tool.printAndLog("----------------------------------------------------------");
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