package de.cmis.test.Folders;

import java.io.IOException;

import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.exceptions.CmisObjectNotFoundException;

import de.cmis.test.Session.SessionSingleton;

public class DeleteFolder {

	

	public static void main(String args[]) throws IOException {
		Session session = SessionSingleton.getInstance().getSession("OpenCmisServer", "atom11");

		Folder folder = (Folder) session.getObjectByPath("/My_Folder-0-0");

		System.out.println("Name Of the Folder : " + folder.getName());
		System.out.println("Deleting the folder : " + folder.getName());

		folder.deleteTree(true, null, true);

		try {
			folder.refresh();
		} catch (CmisObjectNotFoundException e) {
			System.out.println("Folder is deleted : " + folder.getName());
			System.out.println(e.getMessage());
		}

	}
}