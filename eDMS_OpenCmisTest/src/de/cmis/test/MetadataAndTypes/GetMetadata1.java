package de.cmis.test.MetadataAndTypes;

import java.io.IOException;
import java.util.List;

import org.apache.chemistry.opencmis.client.api.ObjectType;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.api.Tree;

import de.cmis.test.Tool;
import de.cmis.test.Session.SessionSingleton;

public class GetMetadata1 {

	public static void printType(Tree<ObjectType> types, String space) {

		ObjectType objType = types.getItem();

		Tool.printAndLog(space + "[id : " + objType.getId() + "], [Display Name : " + objType.getDisplayName()
				+ "], [Description : " + objType.getDescription() + "]");

		List<Tree<ObjectType>> objTypes = types.getChildren();

		for (Tree<ObjectType> objTypeTemp : objTypes) {
			printType(objTypeTemp, space + " ");
		}
	}

	public static void main(String args[]) throws IOException {
		Session session = SessionSingleton.getInstance().getSession("OpenCmisServer", "atom11");

		List<Tree<ObjectType>> types = session.getTypeDescendants(null, -1, true);

		for (Tree<ObjectType> typeTemp : types) {
			printType(typeTemp, "");
		}

	}
}