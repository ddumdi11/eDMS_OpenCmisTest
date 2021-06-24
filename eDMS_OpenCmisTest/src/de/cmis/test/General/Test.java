package de.cmis.test.General;

import java.util.List;

import org.apache.chemistry.opencmis.client.api.Repository;

import de.cmis.test.Tool;

public class Test {

	public static void main(String args[]) {
		//String urlToConnect = "http://localhost:8080/chemistry-opencmis-server-inmemory-1.1.0/atom11";
		String urlToConnect = "http://localhost:8089/chemistry-opencmis-server-inmemory-1.1.0/atom11";

		RepositoryUtil repoUtil = new AtomPubBindingRepositoryUtil("", "", urlToConnect);

		List<Repository> repositories = repoUtil.getAllRepositories();

		for (Repository repository : repositories) {
			Tool.printAndLog(repository.getName());
		}

	}
}