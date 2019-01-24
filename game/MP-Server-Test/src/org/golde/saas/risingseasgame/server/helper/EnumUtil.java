package org.golde.saas.risingseasgame.server.helper;

import org.golde.saas.risingseasgame.server.MainServer;

public class EnumUtil {
	
	public static final <T extends Enum<?>> T randomEnum(Class<T> clazz){
		int x = MainServer.RANDOM.nextInt(clazz.getEnumConstants().length);
		return clazz.getEnumConstants()[x];
	}

}
