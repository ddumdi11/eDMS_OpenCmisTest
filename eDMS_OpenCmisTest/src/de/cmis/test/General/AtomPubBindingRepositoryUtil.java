package de.cmis.test.General;

import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.enums.BindingType;

public class AtomPubBindingRepositoryUtil extends RepositoryUtil {

	public AtomPubBindingRepositoryUtil(String userName, String password, String urlToConnect) {
		super(userName, password, urlToConnect);
	}

	@Override
	public void populateProperties() {
		// user credentials
		parameters.put(SessionParameter.USER, this.getUserName());
		parameters.put(SessionParameter.PASSWORD, this.getPassword());

		// connection settings
		parameters.put(SessionParameter.ATOMPUB_URL, this.getUrlToConnect());
		parameters.put(SessionParameter.BINDING_TYPE, BindingType.ATOMPUB.value());

	}

}
