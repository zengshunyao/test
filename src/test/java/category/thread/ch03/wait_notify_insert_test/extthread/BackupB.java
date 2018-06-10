package category.thread.ch03.wait_notify_insert_test.extthread;

import category.thread.ch03.wait_notify_insert_test.service.DBTools;

public class BackupB extends Thread {

	private DBTools dbtools;

	public BackupB(DBTools dbtools) {
		super();
		this.dbtools = dbtools;
	}

	@Override
	public void run() {
		dbtools.backupB();
	}

}
