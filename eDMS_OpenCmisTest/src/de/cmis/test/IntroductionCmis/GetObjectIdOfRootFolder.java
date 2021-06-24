package de.cmis.test.IntroductionCmis;

import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.data.RepositoryInfo;

import de.cmis.test.Tool;
import de.cmis.test.Session.SessionSingleton;

public class GetObjectIdOfRootFolder {

	public static void main(String args[]) {
		Session session = SessionSingleton.getInstance().getSession("OpenCmisServer", "atom11");

		RepositoryInfo repoInfo = session.getRepositoryInfo();

		String rootFolderId = repoInfo.getRootFolderId();

		Tool.printAndLog("rootFolderId : " + rootFolderId);

	}

}