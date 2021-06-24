package de.cmis.test.ItemsRelationshipsPolicies;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.chemistry.opencmis.client.api.Item;
import org.apache.chemistry.opencmis.client.api.ObjectId;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.PropertyIds;

import de.cmis.test.Session.SessionSingleton;

public class createItemUnfiled {

	public static void main(String args[]) throws IOException {
		Session session = SessionSingleton.getInstance().getSession("OpenCmisServer", "atom11");

		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put(PropertyIds.NAME, "My Demo unfiled item");
		properties.put(PropertyIds.OBJECT_TYPE_ID, "cmis:item");

		ObjectId itemId = session.createItem(properties, null);

		Item item = (Item) session.getObject(itemId);

		System.out.println((PropertyIds.NAME + ": " + item.getPropertyValue(PropertyIds.NAME)));
		System.out.println((PropertyIds.OBJECT_TYPE_ID + ": " + item.getPropertyValue(PropertyIds.OBJECT_TYPE_ID)));

	}

}