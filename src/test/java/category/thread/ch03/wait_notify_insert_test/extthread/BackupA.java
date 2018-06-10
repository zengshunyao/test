package category.thread.ch03.wait_notify_insert_test.extthread;

import category.thread.ch03.wait_notify_insert_test.service.DBTools;

public class BackupA extends Thread {

	private DBTools dbtools;

	public BackupA(DBTools dbtools) {
		super();
		this.dbtools = dbtools;
	}

	@Override
	public void run() {
		dbtools.backupA();
	}

}
