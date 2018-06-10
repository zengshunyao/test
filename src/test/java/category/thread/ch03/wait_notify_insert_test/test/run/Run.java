package category.thread.ch03.wait_notify_insert_test.test.run;

import category.thread.ch03.wait_notify_insert_test.service.DBTools;
import category.thread.ch03.wait_notify_insert_test.extthread.BackupA;
import category.thread.ch03.wait_notify_insert_test.extthread.BackupB;

public class Run {

	public static void main(String[] args) {
		DBTools dbtools = new DBTools();
		for (int i = 0; i < 20; i++) {
			BackupB output = new BackupB(dbtools);
			output.start();
			BackupA input = new BackupA(dbtools);
			input.start();
		}
	}

}
