package util;

import java.util.UUID;

public class UniqueIdUtil {
	public UniqueIdUtil() {
		
	}

	public static int generateUniqueId() {      
	    UUID idOne = UUID.randomUUID();
	    String str=""+idOne;        
	    int uid=str.hashCode();
	    String filterStr=""+uid;
	    str=filterStr.replaceAll("-", "");
	    
	    return Integer.parseInt(str);
	}

}
