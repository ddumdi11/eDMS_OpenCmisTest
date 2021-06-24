package de.cmis.test.ItemsRelationshipsPolicies;

import java.io.IOException;

import org.apache.chemistry.opencmis.client.api.CmisObject;
import org.apache.chemistry.opencmis.client.api.Session;

import de.cmis.test.Session.SessionSingleton;

public class CheckObjectControllabilityOfObject {

	

	public static void main(String args[]) throws IOException {
		Session session = SessionSingleton.getInstance().getSession("OpenCmisServer", "atom11");

		//CmisObject cmisObject = session.getRootFolder();
		CmisObject cmisObject = session.getObjectByPath("/My_Document-0-0");
		Boolean controllablePolicy = cmisObject.getType().isControllablePolicy();

		if (controllablePolicy == null) {
			System.out.println("Unknown (noncompliant repository)");
		} else if (controllablePolicy) {
			System.out.println("Object is controlled by the policy");
		} else {
			System.out.println("Object is not controlled by the policy");
		}

	}

}