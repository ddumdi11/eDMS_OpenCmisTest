package de.cmis.test.RepositoryCapabilities;

import java.util.List;

import org.apache.chemistry.opencmis.client.api.FileableCmisObject;
import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.api.Tree;
import org.apache.chemistry.opencmis.commons.data.RepositoryInfo;

import de.cmis.test.Tool;
import de.cmis.test.Session.SessionSingleton;

public class CheckFolderTreeSupport {

	public static void printHierarchy(List<Tree<FileableCmisObject>> objects, String space) {

		for (Tree<FileableCmisObject> obj : objects) {
			FileableCmisObject fileableObj = obj.getItem();

			Tool.printAndLog(space + fileableObj.getName());
			Folder folder = (Folder) fileableObj;

			printHierarchy(folder.getFolderTree(-1), " " + space);

		}
	}

	public static void main(String args[]) {
		Session session = SessionSingleton.getInstance().getSession("OpenCmisServer", "atom11");

		RepositoryInfo repoInfo = session.getRepositoryInfo();

		boolean isFolderTreeSupported = repoInfo.getCapabilities().isGetFolderTreeSupported();

		if (!isFolderTreeSupported) {
			Tool.printAndLog("Folder tree capability is not supported");
			return;
		}

		Folder rootFolder = session.getRootFolder();

		List<Tree<FileableCmisObject>> fileableCmisObjects = rootFolder.getFolderTree(2);

		printHierarchy(fileableCmisObjects, "");

	}
}