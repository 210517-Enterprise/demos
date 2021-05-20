package com.revature.K.serialization;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class PetStore {
	
	private List<Pet> petDB = new ArrayList<Pet>(); // we can also add Puppy objects because Puppy extends Pet

	public static void main(String[] args) {
		
		PetStore ps = new PetStore();
		
//		ps.petDB.add(new Pet("Fluffy", 85, "Cerebus", 4, Color.BROWN));
//		Pet p2 = new Pet("Finn", 2, "Cat", "Bob", 3, Color.GRAY);
//		
//		ps.petDB.add(p2);
		
//		ps.serialize();
		
//		ps.deserialize();
//		
//		for(Pet p : ps.petDB) {
//			System.out.println(p);
//		}

		
	}
	
	// responsible for persisting an object to somewhere in our disk
	public void serialize() {
		
		
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("files/pet.db"))) {
			
			// ObjectOutput stream is a special class within the java.io package that
			// allows use to write objects that can be read with ObjectInputStream
			
			oos.writeObject(this.petDB);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	// From file -> Java
	@SuppressWarnings("unchecked")
	public void deserialize() {
		
		// It allows you to instantiate an object that implements the AutoClosable interface
		// This will cause the object to automatically close at the end of the block
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("files/pet.db"))) {
			
			// ObjectOutput stream is a special class within the java.io package that
			// allows use to write objects that can be read with ObjectInputStream
			
			this.petDB = (List<Pet>) ois.readObject(); 
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}

}
