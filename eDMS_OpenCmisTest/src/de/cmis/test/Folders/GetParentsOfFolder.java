package de.cmis.test.Folders;

import java.io.IOException;
import java.util.List;

import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.Session;

import de.cmis.test.Tool;
import de.cmis.test.Session.SessionSingleton;

public class GetParentsOfFolder {

	
	public static void main(String args[]) throws IOException {
		Session session = SessionSingleton.getInstance().getSession("OpenCmisServer", "atom11");

		Folder document = (Folder) session.getObjectByPath("/My_Folder-0-0");

		List<Folder> folders = document.getParents();

		for (Folder folder : folders) {
			Tool.printAndLog(folder.getPath());
		}

	}

}