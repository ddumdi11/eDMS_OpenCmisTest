package com.sample.util.Browser.RepositoryCapabilities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.chemistry.opencmis.client.api.FileableCmisObject;
import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.api.SessionFactory;
import org.apache.chemistry.opencmis.client.api.Tree;
import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl;
import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.data.RepositoryInfo;
import org.apache.chemistry.opencmis.commons.enums.BindingType;

public class CheckDescendantsSupport {

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

	public static void printHierarchy(List<Tree<FileableCmisObject>> objects, String space) {

		for (Tree<FileableCmisObject> obj : objects) {
			FileableCmisObject fileableObj = obj.getItem();

			System.out.println(space + fileableObj.getName());
			printHierarchy(obj.getChildren(), " " + space);
		}
	}

	public static void main(String args[]) {
		Session session = getSession();

		RepositoryInfo repoInfo = session.getRepositoryInfo();

		boolean isGetDescendantsSupported = repoInfo.getCapabilities().isGetDescendantsSupported();

		if (!isGetDescendantsSupported) {
			System.out.println("Repository do not support descendants");
			return;
		}

		Folder rootFolder = session.getRootFolder();

		List<Tree<FileableCmisObject>> fileableCmisObjects = rootFolder.getDescendants(-1);

		System.out.println(rootFolder.getName());
		printHierarchy(fileableCmisObjects, " ");

	}
}