package de.cmis.test.RepositoryCapabilities;

import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.data.RepositoryInfo;

import de.cmis.test.Session.SessionSingleton;

public class GetVendorNameProductNameProductVersionOfRepository {

	public static void main(String args[]) {
		Session session = SessionSingleton.getInstance().getSession("OpenCmisServer", "atom11");

		RepositoryInfo repoInfo = session.getRepositoryInfo();

		String vendorName = repoInfo.getVendorName();
		String productName = repoInfo.getProductName();
		String productVersion = repoInfo.getProductVersion();

		System.out.println("vendorName : " + vendorName);
		System.out.println("productName : " + productName);
		System.out.println("productVersion : " + productVersion);
	}

}