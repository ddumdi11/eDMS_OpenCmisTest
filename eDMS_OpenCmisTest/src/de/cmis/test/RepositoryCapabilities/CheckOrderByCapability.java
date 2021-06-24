package de.cmis.test.RepositoryCapabilities;

import org.apache.chemistry.opencmis.client.api.CmisObject;
import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.ItemIterable;
import org.apache.chemistry.opencmis.client.api.OperationContext;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.runtime.OperationContextImpl;
import org.apache.chemistry.opencmis.commons.data.RepositoryInfo;
import org.apache.chemistry.opencmis.commons.enums.CapabilityOrderBy;

import de.cmis.test.Session.SessionSingleton;

public class CheckOrderByCapability {

	public static void main(String args[]) {
		Session session = SessionSingleton.getInstance().getSession("OpenCmisServer", "atom11");

		RepositoryInfo repoInfo = session.getRepositoryInfo();

		CapabilityOrderBy capabilityOrderBy = repoInfo.getCapabilities().getOrderByCapability();

		if (capabilityOrderBy == null) {
			System.out.println("Ordering is not supported");
			return;
		}

		System.out.println(capabilityOrderBy);

		Folder rootFolder = session.getRootFolder();

		OperationContext ascContext = new OperationContextImpl();
		ascContext.setOrderBy("cmis:name ASC, cmis:creationDate ASC");

		OperationContext descContext = new OperationContextImpl();
		descContext.setOrderBy("cmis:name DESC, cmis:creationDate DESC");

		printChildren(rootFolder, ascContext);

		System.out.println("\n************************************\n");
		printChildren(rootFolder, descContext);
	}

	private static void printChildren(Folder rootFolder, OperationContext context) {
		ItemIterable<CmisObject> cmisObjects = rootFolder.getChildren(context);

		for (CmisObject cmisObject : cmisObjects) {
			System.out.println(cmisObject.getName());
		}
	}
}