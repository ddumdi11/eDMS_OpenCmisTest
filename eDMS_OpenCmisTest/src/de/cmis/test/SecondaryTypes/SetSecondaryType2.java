package de.cmis.test.SecondaryTypes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.chemistry.opencmis.client.api.CmisObject;
import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.PropertyIds;

import de.cmis.test.Tool;
import de.cmis.test.Session.SessionSingleton;

public class SetSecondaryType2 {

	private static void printDocument(Document document) {
		String contactUs = document.getPropertyValue("abc:contactUs");
		String help = document.getPropertyValue("abc:help");

		Tool.printAndLog("name : " + document.getName());
		Tool.printAndLog("id : " + document.getId());
		Tool.printAndLog("version series id : " + document.getVersionSeriesId());
		Tool.printAndLog("Version Label : " + document.getVersionLabel());
		Tool.printAndLog("Checked out by : " + document.getVersionSeriesCheckedOutBy());
		Tool.printAndLog("Content length : " + document.getContentStreamLength());

		Tool.printAndLog("contact us : " + contactUs);
		Tool.printAndLog("Help : " + help);
		Tool.printAndLog("confidentialValue : " + document.getPropertyValue("abc:confidentialLevel"));
	}

	public static void main(String args[]) throws IOException {
		Session session = SessionSingleton.getInstance().getSession("OpenCmisServer", "atom11");

		CmisObject cmisObject = session.getObjectByPath("/emptyDocument.txt");

		/* Get current secondary types */
		List<String> secondaryTypes = cmisObject.getPropertyValue(PropertyIds.SECONDARY_OBJECT_TYPE_IDS);
		if (secondaryTypes == null) {
			secondaryTypes = new ArrayList<String>();
		}

		Map<String, Object> properties = new HashMap<String, Object>();

		/* Add the new secondary type */
		secondaryTypes.add("abc:confidentialDocs");
		properties.put(PropertyIds.SECONDARY_OBJECT_TYPE_IDS, secondaryTypes);

		/* set secondary type property */
		properties.put("abc:confidentialLevel", 5);

		/* update properties */
		cmisObject.updateProperties(properties);

		printDocument((Document) cmisObject);
	}

}