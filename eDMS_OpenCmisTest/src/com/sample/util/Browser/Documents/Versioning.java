package com.sample.util.Browser.Documents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.ObjectId;
import org.apache.chemistry.opencmis.client.api.Repository;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.api.SessionFactory;
import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl;
import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.data.ContentStream;
import org.apache.chemistry.opencmis.commons.enums.BindingType;
import org.apache.chemistry.opencmis.commons.exceptions.CmisConnectionException;

public class Versioning {

	private static String serverURL = "http://localhost:8080/alfresco/api/-default-/public/cmis/versions/1.1/atom";

	private static String inputFile1 = "./src/com/sample/util/Files/07_S_Verweis.docx";
	private static String inputFile2 = "./src/com/sample/util/Files/07_S_Verweis.docx";
	private static String documentName = "sample1.txt";
	private static Session session = null;

	public static Session getSession() {
		if (session != null) {
			return session;
		}
		
		SessionFactory sessionFactory = SessionFactoryImpl.newInstance();
		
		Map<String, String> parameters = new HashMap<>();
		parameters.put(SessionParameter.BINDING_TYPE, BindingType.ATOMPUB.value());

		parameters.put(SessionParameter.USER, "admin");
		parameters.put(SessionParameter.PASSWORD, "Lanuv1111");

		parameters.put(SessionParameter.ATOMPUB_URL, serverURL);
		parameters.put(SessionParameter.COMPRESSION, "true");
		parameters.put(SessionParameter.CACHE_TTL_OBJECTS, "0");		
		

		// If there is only one repository exposed (e.g. Alfresco),
		// these lines will help detect it and its ID
		List<Repository> repositories = sessionFactory.getRepositories(parameters);
		Repository alfrescoRepository = null;
		if (repositories != null && repositories.size() > 0) {
			System.out.println("Found (" + repositories.size() + ") Alfresco repositories");
			alfrescoRepository = repositories.get(0);
			System.out.println("Info about the first Alfresco repo [ID=" + alfrescoRepository.getId() + "][name="
					+ alfrescoRepository.getName() + "][CMIS ver supported="
					+ alfrescoRepository.getCmisVersionSupported() + "]");
		} else {
			throw new CmisConnectionException("Could not connect to the Alfresco Server, " + "no repository found!");
		}

		// Create a new session with the -default- Alfresco repository
		session = alfrescoRepository.createSession();

		return session;
	}

	private static Document createDocument() throws IOException {
		File inputFile = new File(inputFile1);

		String mimeType = Files.probeContentType(inputFile.toPath());

		Session session = getSession();
		try (InputStream inputStream = new FileInputStream(inputFile)) {
			ContentStream contentStream = session.getObjectFactory().createContentStream(inputFile.getName(),
					inputFile.length(), mimeType, inputStream);

			Map<String, String> properties = new HashMap<>();
			properties.put("cmis:objectTypeId", "cmis:document");
			properties.put("cmis:name", documentName);

			Folder rootFolder = session.getRootFolder();
			Document document = rootFolder.createDocument(properties, contentStream, null);

			return document;

		}
	}

	private static void printDocumentDetails(Document document) {
		System.out.println("name : " + document.getName());
		System.out.println("id : " + document.getId());
		System.out.println("version series id : " + document.getVersionSeriesId());
		System.out.println("Version Label : " + document.getVersionLabel());
		System.out.println("Checked out by : " + document.getVersionSeriesCheckedOutBy());
		System.out.println("Content length : " + document.getContentStreamLength());
	}

	private static Document updateAndCheckInDocument(ObjectId objectId) throws IOException {
		Session session = getSession();
		Document document = (Document) session.getObject(objectId);

		File inputFile = new File(inputFile2);
		String mimeType = Files.probeContentType(inputFile.toPath());

		try (InputStream inputStream = new FileInputStream(inputFile)) {
			ContentStream contentStream = session.getObjectFactory().createContentStream(inputFile.getName(),
					inputFile.length(), mimeType, inputStream);

			document.setContentStream(contentStream, true, true);
			ObjectId objId = document.checkIn(true, null, contentStream, "Checking in document");
			return (Document) session.getObject(objId);
		}

	}

	public static void main(String args[]) throws IOException {

		Document document = createDocument();

		System.out.println("*****************************************");
		System.out.println("Before checking in the document");
		System.out.println("*****************************************");

		printDocumentDetails(document);

		boolean isVersionable = document.isVersionable();

		if (!isVersionable) {
			System.out.println("Document is not versionable");
			return;
		}

		ObjectId objectId = document.checkOut();
		Document checkdInDocument = updateAndCheckInDocument(objectId);

		System.out.println("*****************************************");
		System.out.println("After checking in the document");
		System.out.println("*****************************************");
		printDocumentDetails(checkdInDocument);

	}
}