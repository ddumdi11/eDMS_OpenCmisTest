package de.cmis.test.Queries;

import java.io.IOException;
import java.util.List;

import org.apache.chemistry.opencmis.client.api.ItemIterable;
import org.apache.chemistry.opencmis.client.api.QueryResult;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.data.PropertyData;
import org.apache.chemistry.opencmis.commons.impl.json.parser.JSONParseException;

import de.cmis.test.Tool;
import de.cmis.test.Session.SessionSingleton;

public class QueryTypes {

	public static void main(String args[]) throws IOException, JSONParseException {
		Session session = SessionSingleton.getInstance().getSession("OpenCmisServer", "atom11");

		String query = "SELECT * FROM cmis:document";

		ItemIterable<QueryResult> queryResults = session.query(query, true);

		for (QueryResult queryResult : queryResults) {
			List<PropertyData<?>> propertiesData = queryResult.getProperties();

			Tool.printAndLog("*********************************************************");
			for (PropertyData<?> propData : propertiesData) {
				Tool.printAndLog(propData.getId() + " : " + propData.getValues());
			}
			Tool.printAndLog("*********************************************************\n\n");
		}

	}
}