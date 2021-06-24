package de.cmis.test.RepositoryCapabilities;

import java.util.List;

import org.apache.chemistry.opencmis.client.api.FileableCmisObject;
import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.api.Tree;
import org.apache.chemistry.opencmis.commons.data.RepositoryInfo;

import de.cmis.test.Tool;
import de.cmis.test.Session.SessionSingleton;

public class CheckDescendantsSupport {

	public static void printHierarchy(List<Tree<FileableCmisObject>> objects, String space) {

		for (Tree<FileableCmisObject> obj : objects) {
			FileableCmisObject fileableObj = obj.getItem();

			Tool.printAndLog(space + fileableObj.getName());
			printHierarchy(obj.getChildren(), " " + space);
		}
	}

	public static void main(String args[]) {
		Session session = SessionSingleton.getInstance().getSession("OpenCmisServer", "atom11");

		RepositoryInfo repoInfo = session.getRepositoryInfo();

		boolean isGetDescendantsSupported = repoInfo.getCapabilities().isGetDescendantsSupported();

		if (!isGetDescendantsSupported) {
			Tool.printAndLog("Repository do not support descendants");
			return;
		}

		Folder rootFolder = session.getRootFolder();

		List<Tree<FileableCmisObject>> fileableCmisObjects = rootFolder.getDescendants(-1);

		Tool.printAndLog(rootFolder.getName());
		printHierarchy(fileableCmisObjects, " ");

	}
}