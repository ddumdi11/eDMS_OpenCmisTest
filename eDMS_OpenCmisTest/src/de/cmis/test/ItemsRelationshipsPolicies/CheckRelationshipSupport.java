package de.cmis.test.ItemsRelationshipsPolicies;

import java.io.IOException;

import org.apache.chemistry.opencmis.client.api.ItemIterable;
import org.apache.chemistry.opencmis.client.api.ObjectType;
import org.apache.chemistry.opencmis.client.api.Session;

import de.cmis.test.Session.SessionSingleton;

public class CheckRelationshipSupport {

	public static void main(String args[]) throws IOException {
		Session session = SessionSingleton.getInstance().getSession("OpenCmisServer", "atom11");

		ItemIterable<ObjectType> objectTypes = session.getTypeChildren(null, false);

		for (ObjectType objectType : objectTypes) {
			System.out.println(objectType.getId());
		}

	}

}