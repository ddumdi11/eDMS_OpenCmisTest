package de.cmis.test.General;

import java.util.List;

import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.data.Ace;
import org.apache.chemistry.opencmis.commons.data.Acl;

import de.cmis.test.Tool;
import de.cmis.test.Session.SessionSingleton;

public class TestCmisGetAclOfRootFolder {

	public static void main(String args[]) {		
		Session session = SessionSingleton.getInstance().getSession("OpenCmisServer", "atom11");

		Folder folder = session.getRootFolder();

		Acl acl = folder.getAcl();

		if (acl == null) {
			Tool.printAndLog("No acl is associated with root folder");
			return;
		}

		List<Ace> aces = acl.getAces();

		for (Ace ace : aces) {
			Tool.printAndLog(ace.getPermissions().toString());
		}

	}

}