package de.cmis.test.RepositoryCapabilities;

import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.data.NewTypeSettableAttributes;
import org.apache.chemistry.opencmis.commons.data.RepositoryInfo;

import de.cmis.test.Session.SessionSingleton;

public class CheckSettableObjectTypeAttributesWhileCreationNewObjectType {

	public static void main(String args[]) {
		Session session = SessionSingleton.getInstance().getSession("OpenCmisServer", "atom11");

		RepositoryInfo repoInfo = session.getRepositoryInfo();

		NewTypeSettableAttributes newTypeSettableAttributes = repoInfo.getCapabilities().getNewTypeSettableAttributes();

		System.out.println("can set Id : " + newTypeSettableAttributes.canSetId());
		System.out.println("can set local name : " + newTypeSettableAttributes.canSetLocalName());
		System.out.println("can set local name space : " + newTypeSettableAttributes.canSetLocalNamespace());
		System.out.println("can set display name : " + newTypeSettableAttributes.canSetDisplayName());
		System.out.println("can set query name : " + newTypeSettableAttributes.canSetQueryName());
		System.out.println("can set description : " + newTypeSettableAttributes.canSetDescription());
		System.out.println("can set creatable : " + newTypeSettableAttributes.canSetCreatable());
		System.out.println("can set fileable : " + newTypeSettableAttributes.canSetFileable());
		System.out.println("can set queryable : " + newTypeSettableAttributes.canSetQueryable());
		System.out.println("can full text indexed : " + newTypeSettableAttributes.canSetFulltextIndexed());
		System.out.println(
				"can included in super type query : " + newTypeSettableAttributes.canSetIncludedInSupertypeQuery());
		System.out.println("can control policy : " + newTypeSettableAttributes.canSetControllablePolicy());
		System.out.println("can control ACL : " + newTypeSettableAttributes.canSetControllableAcl());

	}

}