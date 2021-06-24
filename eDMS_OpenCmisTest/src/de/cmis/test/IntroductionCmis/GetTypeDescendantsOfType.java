package de.cmis.test.IntroductionCmis;

import java.util.List;

import org.apache.chemistry.opencmis.client.api.ObjectType;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.api.Tree;

import de.cmis.test.Tool;
import de.cmis.test.Session.SessionSingleton;

public class GetTypeDescendantsOfType {

	public static void printTypes(List<Tree<ObjectType>> objectTypes, String space) {

		for (Tree<ObjectType> objType : objectTypes) {
			ObjectType objectType = objType.getItem();
			Tool.printAndLog(space + objectType.getDisplayName());

			printTypes(objType.getChildren(), "  " + space);
		}
	}

	public static void main(String args[]) {
		Session session = SessionSingleton.getInstance().getSession("OpenCmisServer", "atom11");

		List<Tree<ObjectType>> objectTypes = session.getTypeDescendants(null, -1, false);

		printTypes(objectTypes, "");

	}

}