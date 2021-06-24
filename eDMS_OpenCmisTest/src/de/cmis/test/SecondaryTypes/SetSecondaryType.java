package de.cmis.test.SecondaryTypes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.PropertyIds;

import de.cmis.test.Session.SessionSingleton;

public class SetSecondaryType {

	private static void printDocument(Document document) {
		String contactUs = document.getPropertyValue("abc:contactUs");
		String help = document.getPropertyValue("abc:help");

		System.out.println("name : " + document.getName());
		System.out.println("id : " + document.getId());
		System.out.println("version series id : " + document.getVersionSeriesId());
		System.out.println("Version Label : " + document.getVersionLabel());
		System.out.println("Checked out by : " + document.getVersionSeriesCheckedOutBy());
		System.out.println("Content length : " + document.getContentStreamLength());

		System.out.println("contact us : " + contactUs);
		System.out.println("Help : " + help);
	}

	public static void main(String args[]) throws IOException {
		Session session = SessionSingleton.getInstance().getSession("OpenCmisServer", "atom11");
		Folder rootFolder = session.getRootFolder();

		Map<String, Object> properties = new HashMap<>();
		properties.put("cmis:objectTypeId", "cmis:document");
		properties.put("cmis:name", "emptyDocument.txt");

		List<String> secondaryTypes = new ArrayList<String>();
		secondaryTypes.add("abc:secondaryType");
		properties.put(PropertyIds.SECONDARY_OBJECT_TYPE_IDS, secondaryTypes);

		properties.put("abc:contactUs", "https://self-learning-java-tutorial.blogspot.com");
		properties.put("abc:help", "Java Tutorial blogspot");

		Document document = rootFolder.createDocument(properties, null, null);
		printDocument(document);
	}

}