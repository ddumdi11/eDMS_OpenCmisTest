package de.cmis.test.General;

import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.Session;

import de.cmis.test.Tool;
import de.cmis.test.Session.SessionSingleton;

public class TestCmisGetIdOfRootFolder {

	
	public static void main(String args[]) {
		Session session = SessionSingleton.getInstance().getSession("OpenCmisServer", "atom11");
		Folder folder = session.getRootFolder();
		Tool.printAndLog("id : " + folder.getId());
	}
}
