package tools;

import java.util.UUID;

public class randomUUIDFactory {
	
	public static String toUUID(){
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replaceAll("-", "").toUpperCase();
	}
	
}
