package com.sample.util.Browser.Documents;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.api.SessionFactory;
import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl;
import org.apache.chemistry.opencmis.client.runtime.repository.ObjectFactoryImpl;
import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.data.ContentStream;
import org.apache.chemistry.opencmis.commons.enums.BindingType;

public class GetContentStreamOfDocument {

	private static String serverURL = "http://localhost:8089/chemistry-opencmis-server-inmemory-1.1.0/browser";
	private static String repositoryId = "A1";
	private static String localFilePath = "./src/com/sample/util/Files/test.json";

	private static Session getSession() {
		Map<String, String> parameters = new HashMap<>();
		parameters.put(SessionParameter.BINDING_TYPE, BindingType.BROWSER.value());

		parameters.put(SessionParameter.USER, "");
		parameters.put(SessionParameter.PASSWORD, "");

		parameters.put(SessionParameter.REPOSITORY_ID, repositoryId);
		parameters.put(SessionParameter.BROWSER_URL, serverURL);

		SessionFactory sessionFactory = SessionFactoryImpl.newInstance();
		return sessionFactory.createSession(parameters);
	}

	private static void setTheContentStream(Document document) {
		/* Creating content stream */
		ObjectFactoryImpl objectFactory = new ObjectFactoryImpl();
		File file = new File(localFilePath);

		try (InputStream stream = new FileInputStream(file)) {
			String mimetype = Files.probeContentType(file.toPath());
			ContentStream contentStream = objectFactory.createContentStream(file.getName(), file.length(), mimetype,
					stream);
			document.setContentStream(contentStream, true, true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void readTheContentsOfTheDocument(Document document) {
		ContentStream contentStream = document.getContentStream();

		try (InputStream inputStream = contentStream.getStream();
				BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream)) {

			byte[] buffer = new byte[65535];
			int bytesRead = -1;

			while ((bytesRead = bufferedInputStream.read(buffer)) != -1) {
				String str = new String(buffer, 0, bytesRead);
				System.out.print(str);
			}
		} catch (IOException e) {
			System.out.println("Error occurred while processing the file content");
			System.out.println(e.getMessage());
		}

	}

	public static void main(String args[]) throws IOException {

		Session session = getSession();

		/* Create the document 'sampleDoc.json' */
		Map<String, String> properties = new HashMap<>();
		properties.put("cmis:objectTypeId", "cmis:document");
		properties.put("cmis:name", "sampleDoc.json");

		Folder rootFolder = session.getRootFolder();

		Document document = rootFolder.createDocument(properties, null, null);

		setTheContentStream(document);
		readTheContentsOfTheDocument(document);

	}

}