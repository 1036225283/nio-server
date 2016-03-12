package com.nitian.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UtilThreadPool {

	private static ExecutorService executorService = Executors
			.newScheduledThreadPool(13);

	public void execute(Runnable command) {
		executorService.execute(command);
	}

}
