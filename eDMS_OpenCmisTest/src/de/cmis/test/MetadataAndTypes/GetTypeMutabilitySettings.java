package de.cmis.test.MetadataAndTypes;

import java.io.IOException;

import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.ObjectType;
import org.apache.chemistry.opencmis.client.api.Session;

import de.cmis.test.Session.SessionSingleton;

public class GetTypeMutabilitySettings {

	public static void main(String args[]) throws IOException {
		Session session = SessionSingleton.getInstance().getSession("OpenCmisServer", "atom11");

		Folder folder = session.getRootFolder();
		ObjectType objectType = folder.getType();

		boolean canCreate = objectType.getTypeMutability().canCreate();
		boolean canDelete = objectType.getTypeMutability().canDelete();
		boolean canUpdate = objectType.getTypeMutability().canUpdate();

		System.out.println("canCreate : " + canCreate);
		System.out.println("canDelete : " + canDelete);
		System.out.println("canUpdate : " + canUpdate);
	}
}