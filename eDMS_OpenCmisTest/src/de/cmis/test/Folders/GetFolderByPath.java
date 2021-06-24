package de.cmis.test.Folders;

import java.io.IOException;

import org.apache.chemistry.opencmis.client.api.CmisObject;
import org.apache.chemistry.opencmis.client.api.Session;

import de.cmis.test.Session.SessionSingleton;

public class GetFolderByPath {

	
	public static void main(String args[]) throws IOException {
		Session session = SessionSingleton.getInstance().getSession("OpenCmisServer", "atom11");

		CmisObject cmisObject = session.getObjectByPath("/My_Folder-0-0");

		System.out.println("Name : " + cmisObject.getName());
		System.out.println("Type : " + cmisObject.getType());

	}

}