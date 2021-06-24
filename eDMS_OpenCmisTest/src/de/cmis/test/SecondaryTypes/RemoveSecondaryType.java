package de.cmis.test.SecondaryTypes;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.chemistry.opencmis.client.api.CmisObject;
import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.PropertyIds;

import de.cmis.test.Session.SessionSingleton;

public class RemoveSecondaryType {

	private static void printDocument(CmisObject cmisObject) {
		System.out.println("***********************************************");
		System.out.println("contact us : " + cmisObject.getPropertyValue("abc:contactUs"));
		System.out.println("Help : " + cmisObject.getPropertyValue("abc:help"));
		System.out.println("confidentialValue : " + cmisObject.getPropertyValue("abc:confidentialLevel"));

		List<String> secondaryTypes = cmisObject.getPropertyValue(PropertyIds.SECONDARY_OBJECT_TYPE_IDS);

		System.out.println("Secondary types are");
		for (String secondaryType : secondaryTypes) {
			System.out.println(secondaryType);
		}
	}

	public static void main(String args[]) throws IOException {
		Session session = SessionSingleton.getInstance().getSession("OpenCmisServer", "atom11");

		CmisObject cmisObject = session.getObjectByPath("/emptyDocument.txt");

		System.out.println("Before removing secondary type 'abc:confidentialDocs'");
		printDocument(cmisObject);

		/* Get current secondary types */
		List<String> secondaryTypes = cmisObject.getPropertyValue(PropertyIds.SECONDARY_OBJECT_TYPE_IDS);
		if (secondaryTypes == null) {
			System.out.println("No secondary types associated to this object");
			return;
		}

		Map<String, Object> properties = new HashMap<String, Object>();

		/* Remove the new secondary type */
		secondaryTypes.remove("abc:confidentialDocs");
		properties.put(PropertyIds.SECONDARY_OBJECT_TYPE_IDS, secondaryTypes);

		/* update properties */
		cmisObject.updateProperties(properties);

		System.out.println("\nAfter removing secondary type 'abc:confidentialDocs'");
		printDocument((Document) cmisObject);
	}

}