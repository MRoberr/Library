package edu.msg.library_client.desktop;

import java.util.List;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.sound.sampled.LineListener;

import edu.msg.library_common.model.Author;
import edu.msg.library_common.model.Entity;
import edu.msg.library_common.model.User;

public class AuthorService  {
public boolean newAuthorCreate(String name){
	Author author=new Author();
	author.setName(name);
	
	try {
		return RmiRegistry.authorServiceRmi.insertAuthor(author);
	} catch (RemoteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return false;
}
public boolean authorUpdate(String actualName,String newName) {
	try {
		List<Entity> authors =RmiRegistry.authorServiceRmi.getAllAuthors();
		for(Entity e: authors){
			Author a=(Author)e;
			if (a.getName().equals(actualName)){
				a.setName(newName);
				return RmiRegistry.authorServiceRmi.updateAuthor(a);
			}
		}
	} catch (RemoteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return false;	
}

public boolean deleteAuthor(String name){
	try {
		List<Entity> authors=RmiRegistry.authorServiceRmi.getAllAuthors();
		for(Entity e: authors) {
			Author a=(Author)e;
			if (a.getName().equals(name)){
				return RmiRegistry.authorServiceRmi.deleteAuthor(a);
			}
		}
	} catch (RemoteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
	return false;
}

public List<Entity> getAllAuthors(){
	try {
		return RmiRegistry.authorServiceRmi.getAllAuthors();
	} catch (RemoteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
}
}
