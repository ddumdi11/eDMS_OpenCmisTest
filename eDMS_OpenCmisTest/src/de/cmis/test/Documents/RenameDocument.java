package de.cmis.test.Documents;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.Session;

import de.cmis.test.Tool;
import de.cmis.test.Session.SessionSingleton;

public class RenameDocument {

	public static void main(String args[]) throws IOException {
		Session session = SessionSingleton.getInstance().getSession("OpenCmisServer", "atom11");

		// Dokumente löschen, falls vorhanden
		try {
			Document toDeleteDoc = (Document) session.getObjectByPath("/a.txt");
			Tool.printAndLog("Dokument vorhanden + wird gelöscht.");
			toDeleteDoc.delete();
			Tool.printAndLog("Dokument erfolgreich gelöscht.");
		} catch (Exception e) {
			Tool.printAndLog("Dokument entweder nicht vorhanden oder ein anderer Fehler ...");
		}
		try {
			Document toDeleteDoc = (Document) session.getObjectByPath("/b.txt");
			Tool.printAndLog("Dokument vorhanden + wird gelöscht.");
			toDeleteDoc.delete();
			Tool.printAndLog("Dokument erfolgreich gelöscht.");
		} catch (Exception e) {
			Tool.printAndLog("Dokument entweder nicht vorhanden oder ein anderer Fehler ...");
		}

		// Dokument erstellen, was umbenannt werden soll
		Map<String, String> props = new HashMap<>();
		props.put("cmis:objectTypeId", "cmis:document");
		props.put("cmis:name", "a.txt");

		Document doc2Create = session.getRootFolder().createDocument(props, null, null);

		Tool.printAndLog("Name Of the Document " + doc2Create.getName());
		Tool.printAndLog("Path Of the Document " + doc2Create.getPaths().get(0));

		// Dokument finden
		Document document = (Document) session.getObjectByPath("/a.txt");

		Tool.printAndLog("Before renaming : ");
		Tool.printAndLog("name : " + document.getName());
		Tool.printAndLog("path : " + document.getPaths().get(0));

		Map<String, String> properties = new HashMap<>();
		properties.put("cmis:name", "b.txt");

		document = (Document) document.updateProperties(properties);

		Tool.printAndLog("After renaming : ");
		Tool.printAndLog("name : " + document.getName());
		Tool.printAndLog("path : " + document.getPaths().get(0));

	}
}