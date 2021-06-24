package de.cmis.test.Documents;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.enums.Action;
import org.apache.chemistry.opencmis.commons.exceptions.CmisObjectNotFoundException;

import de.cmis.test.Session.SessionSingleton;

public class DeleteDocument {

	public static void createEmptyDocInFolder(Folder folder, String docName) {

		Map<String, String> properties = new HashMap<>();
		properties.put("cmis:objectTypeId", "cmis:document");
		properties.put("cmis:name", docName);

		Document document = folder.createDocument(properties, null, null);

		System.out.println("Name Of the Document " + document.getName());
		System.out.println("Path Of the Document " + document.getPaths().get(0));
	}

	public static void main(String args[]) throws IOException {
		Session session = SessionSingleton.getInstance().getSession("OpenCmisServer", "atom11");

		Folder root = session.getRootFolder();
		String docName = "Hello.txt";
		createEmptyDocInFolder(root, docName);

		Document document = (Document) session.getObjectByPath("/Hello.txt");

		System.out.println("Document Name : " + document.getName());

		/* Checking for permissions, whether user has permission to delete or not. */
		if (!document.getAllowableActions().getAllowableActions().contains(Action.CAN_DELETE_OBJECT)) {
			System.out.println("User don't have permission to delete the object");
		}

		document.delete();

		try {
			document.refresh();
		} catch (CmisObjectNotFoundException e) {
			System.out.println("Document is deleted");
			System.out.println(e.getMessage());
		}

	}
}