package de.cmis.test.ItemsRelationshipsPolicies;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.Item;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.PropertyIds;

import de.cmis.test.Tool;
import de.cmis.test.Session.SessionSingleton;

public class CreateItemCustomType {

	public static void main(String args[]) throws IOException {
		Session session = SessionSingleton.getInstance().getSession("OpenCmisServer", "atom11");

		Folder folder = (Folder) session.getObjectByPath("/My_Folder-0-0");

		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put(PropertyIds.NAME, "My custom filed item");
		properties.put(PropertyIds.OBJECT_TYPE_ID, "abc:configurationsItem");
		properties.put("abc:contactUs", "self-learning-java-tutorial.blogspot.in");
		properties.put("abc:help", "Comment on any one of the post");

		Item item = folder.createItem(properties);

		Tool.printAndLog((PropertyIds.NAME + ": " + item.getPropertyValue(PropertyIds.NAME)));
		Tool.printAndLog((PropertyIds.OBJECT_TYPE_ID + ": " + item.getPropertyValue(PropertyIds.OBJECT_TYPE_ID)));

	}

}
