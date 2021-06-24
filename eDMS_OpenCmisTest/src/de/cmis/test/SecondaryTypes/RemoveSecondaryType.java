package de.cmis.test.SecondaryTypes;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.chemistry.opencmis.client.api.CmisObject;
import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.PropertyIds;

import de.cmis.test.Tool;
import de.cmis.test.Session.SessionSingleton;

public class RemoveSecondaryType {

	private static void printDocument(CmisObject cmisObject) {
		Tool.printAndLog("***********************************************");
		Tool.printAndLog("contact us : " + cmisObject.getPropertyValue("abc:contactUs"));
		Tool.printAndLog("Help : " + cmisObject.getPropertyValue("abc:help"));
		Tool.printAndLog("confidentialValue : " + cmisObject.getPropertyValue("abc:confidentialLevel"));

		List<String> secondaryTypes = cmisObject.getPropertyValue(PropertyIds.SECONDARY_OBJECT_TYPE_IDS);

		Tool.printAndLog("Secondary types are");
		for (String secondaryType : secondaryTypes) {
			Tool.printAndLog(secondaryType);
		}
	}

	public static void main(String args[]) throws IOException {
		Session session = SessionSingleton.getInstance().getSession("OpenCmisServer", "atom11");

		CmisObject cmisObject = session.getObjectByPath("/emptyDocument.txt");

		Tool.printAndLog("Before removing secondary type 'abc:confidentialDocs'");
		printDocument(cmisObject);

		/* Get current secondary types */
		List<String> secondaryTypes = cmisObject.getPropertyValue(PropertyIds.SECONDARY_OBJECT_TYPE_IDS);
		if (secondaryTypes == null) {
			Tool.printAndLog("No secondary types associated to this object");
			return;
		}

		Map<String, Object> properties = new HashMap<String, Object>();

		/* Remove the new secondary type */
		secondaryTypes.remove("abc:confidentialDocs");
		properties.put(PropertyIds.SECONDARY_OBJECT_TYPE_IDS, secondaryTypes);

		/* update properties */
		cmisObject.updateProperties(properties);

		Tool.printAndLog("\nAfter removing secondary type 'abc:confidentialDocs'");
		printDocument((Document) cmisObject);
	}

}