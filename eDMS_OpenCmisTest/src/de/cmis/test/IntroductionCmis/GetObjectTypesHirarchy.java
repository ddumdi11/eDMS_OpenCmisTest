package de.cmis.test.IntroductionCmis;

import java.util.List;

import org.apache.chemistry.opencmis.client.api.ObjectType;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.api.Tree;

import de.cmis.test.Tool;
import de.cmis.test.Session.SessionSingleton;

public class GetObjectTypesHirarchy {

	private static final String NULL_STRING = null;

	public static void printTypeAndParentType(List<Tree<ObjectType>> types) {

		for (Tree<ObjectType> typeTree : types) {
			ObjectType objType = typeTree.getItem();
			ObjectType parentType = objType.getParentType();

			StringBuilder builder = new StringBuilder("type : ").append(objType.getId()).append(" -> ");

			if (parentType == null) {
				builder.append("parentType : ").append(NULL_STRING);
			} else {
				builder.append("parentType : ").append(parentType.getId());
			}

			Tool.printAndLog(builder.toString());
			printTypeAndParentType(typeTree.getChildren());
		}

	}

	public static void main(String args[]) {
		Session session = SessionSingleton.getInstance().getSession("OpenCmisServer", "atom11");

		List<Tree<ObjectType>> typeDescendants = session.getTypeDescendants(null, -1, false);

		printTypeAndParentType(typeDescendants);

	}

}