package com.sample.util.Browser.Documents;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.chemistry.opencmis.client.api.CmisObject;
import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.ItemIterable;
import org.apache.chemistry.opencmis.client.api.Repository;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.api.SessionFactory;
import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl;
import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.data.ContentStream;
import org.apache.chemistry.opencmis.commons.enums.BindingType;

public class GetContentsOfFirstDocument {

	public static List<Repository> getRepositories(String serverURL) {
		Map<String, String> parameters = new HashMap<>();
		parameters.put(SessionParameter.BINDING_TYPE, BindingType.BROWSER.value());

		parameters.put(SessionParameter.USER, "");
		parameters.put(SessionParameter.PASSWORD, "");

		parameters.put(SessionParameter.BROWSER_URL, serverURL);

		SessionFactory sessionFactory = SessionFactoryImpl.newInstance();
		List<Repository> repositories = sessionFactory.getRepositories(parameters);
		return repositories;
	}

	public static void printFirstDocumentContent(Folder folder) {

		ItemIterable<CmisObject> cmisObjects = folder.getChildren();

		for (CmisObject cmisObject : cmisObjects) {
			if (!(cmisObject instanceof Document)) {
				continue;
			}

			Document document = (Document) cmisObject;

			String mimeType = document.getContentStreamMimeType();

			if (mimeType == null)
				continue;

			if (!mimeType.startsWith("text"))
				continue;

			long contentStreamLength = document.getContentStreamLength();

			if (contentStreamLength == 0)
				continue;

			System.out.println("Name Of the Document : " + document.getName());
			printDocumentContent(document);
			return;
		}

	}

	private static void printDocumentContent(Document document) {
		ContentStream contentStream = document.getContentStream();
		String fileNameOfTheStream = contentStream.getFileName();
		System.out.println("file name of the stream : " + fileNameOfTheStream);
		try (InputStream inputStream = contentStream.getStream()) {
			int data;

			while ((data = inputStream.read()) != -1) {
				System.out.print((char) data);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String args[]) {
		String serverURL = "http://localhost:8089/chemistry-opencmis-server-inmemory-1.1.0/browser";
		List<Repository> repositories = getRepositories(serverURL);

		for (Repository repository : repositories) {
			Session session = repository.createSession();
			Folder rootFolder = session.getRootFolder();
			printFirstDocumentContent(rootFolder);

		}
	}
}