package de.cmis.test.RepositoryCapabilities;

import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.data.NewTypeSettableAttributes;
import org.apache.chemistry.opencmis.commons.data.RepositoryInfo;

import de.cmis.test.Tool;
import de.cmis.test.Session.SessionSingleton;

public class CheckSettableObjectTypeAttributesWhileCreationNewObjectType {

	public static void main(String args[]) {
		Session session = SessionSingleton.getInstance().getSession("OpenCmisServer", "atom11");

		RepositoryInfo repoInfo = session.getRepositoryInfo();

		NewTypeSettableAttributes newTypeSettableAttributes = repoInfo.getCapabilities().getNewTypeSettableAttributes();

		Tool.printAndLog("can set Id : " + newTypeSettableAttributes.canSetId());
		Tool.printAndLog("can set local name : " + newTypeSettableAttributes.canSetLocalName());
		Tool.printAndLog("can set local name space : " + newTypeSettableAttributes.canSetLocalNamespace());
		Tool.printAndLog("can set display name : " + newTypeSettableAttributes.canSetDisplayName());
		Tool.printAndLog("can set query name : " + newTypeSettableAttributes.canSetQueryName());
		Tool.printAndLog("can set description : " + newTypeSettableAttributes.canSetDescription());
		Tool.printAndLog("can set creatable : " + newTypeSettableAttributes.canSetCreatable());
		Tool.printAndLog("can set fileable : " + newTypeSettableAttributes.canSetFileable());
		Tool.printAndLog("can set queryable : " + newTypeSettableAttributes.canSetQueryable());
		Tool.printAndLog("can full text indexed : " + newTypeSettableAttributes.canSetFulltextIndexed());
		Tool.printAndLog(
				"can included in super type query : " + newTypeSettableAttributes.canSetIncludedInSupertypeQuery());
		Tool.printAndLog("can control policy : " + newTypeSettableAttributes.canSetControllablePolicy());
		Tool.printAndLog("can control ACL : " + newTypeSettableAttributes.canSetControllableAcl());

	}

}