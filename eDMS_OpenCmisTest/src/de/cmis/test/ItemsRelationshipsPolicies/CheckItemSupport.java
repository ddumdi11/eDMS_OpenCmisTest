package de.cmis.test.ItemsRelationshipsPolicies;

import java.io.IOException;

import org.apache.chemistry.opencmis.client.api.ItemIterable;
import org.apache.chemistry.opencmis.client.api.ObjectType;
import org.apache.chemistry.opencmis.client.api.Session;

import de.cmis.test.Session.SessionSingleton;

public class CheckItemSupport {

	
	public static void main(String args[]) throws IOException {
		Session session = SessionSingleton.getInstance().getSession("OpenCmisServer", "atom11");

		ItemIterable<ObjectType> objectTypes = session.getTypeChildren(null, false);

		for (ObjectType objectType : objectTypes) {
			if ("cmis:item".equals(objectType.getId())) {
				System.out.println("Repository supports item type");
				return;
			}
		}

		System.out.println("Repository do not support item type");
	}

}