package com.nixsolutions;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.zip.DataFormatException;

public class Main {

	public static void main(String[] args) throws Exception {
		System.out.println("Task 1:");

		MyBean mb = new MyBean("qwertyuiop");
		byte[] serialized = Utils.serialize(mb);

		MyBean mb1 = Utils.deserialize(serialized);

		printHash(mb, mb1);

		System.out.println("Task 2:");
		String file = "out.dat";

		Account acc = new Account(22L, "New name", "New role");
		try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(file))) {
			os.writeObject(acc);
		} catch (Exception ex) {
			throw ex;
		}

		Account newAcc = null;
		try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(file))) {
			newAcc = (Account) is.readObject();
		} catch (Exception ex) {
			throw ex;
		}

		printHash(acc, newAcc);
	}

	public static void printHash(Object obj1, Object obj2) {
		System.out.println("Initial hash: " + obj1.hashCode());
		System.out.println("Deserialized hash: " + obj2.hashCode());
		System.out.println("==: " + (obj1 == obj2));
		System.out.println("Equals: " + (obj1.equals(obj2)));
	}
}
