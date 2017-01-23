package com.ts.util;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LogUtil {
	private static final Logger logger;

	static {
		logger = Logger.getLogger("jcomplyPU");
		logger.setLevel(Level.ALL);
	}

	public static void log(String info, Level level, Throwable ex) {
		logger.log(level, info, ex);
	}

	public static Logger getLogger() {
		return logger;
	}
}
