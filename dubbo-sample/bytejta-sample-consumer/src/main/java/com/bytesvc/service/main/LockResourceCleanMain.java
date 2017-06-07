package com.bytesvc.service.main;

import java.io.File;

import javax.sql.XAConnection;
import javax.sql.XADataSource;
import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;

import org.bytesoft.common.utils.ByteUtils;

public class LockResourceCleanMain {

	public static void main(String... args) throws Throwable {
		com.mysql.jdbc.jdbc2.optional.MysqlXADataSource xads1 = new com.mysql.jdbc.jdbc2.optional.MysqlXADataSource();
		xads1.setUrl("jdbc:mysql://127.0.0.1:3306/inst01");
		xads1.setUser("root");
		xads1.setPassword("123456");

		// com.mysql.jdbc.jdbc2.optional.MysqlXADataSource xads2 = new com.mysql.jdbc.jdbc2.optional.MysqlXADataSource();
		// xads2.setUrl("jdbc:mysql://127.0.0.1:3306/inst02");
		// xads2.setUser("root");
		// xads2.setPassword("123456");

		cleanup(xads1);
		// cleanup(xads2);

		cleanDir("E:/opensource/gitoschina/ByteJTA-sample/demo-consumer/bytejta");
		cleanDir("E:/opensource/gitoschina/ByteJTA-sample/demo-provider/bytejta");
	}

	public static void cleanDir(String file) throws Throwable {
		cleanDir(new File(file));
	}

	public static void cleanDir(File dir) throws Throwable {
		File[] files = dir.listFiles();
		for (int i = 0; i < files.length; i++) {
			File file = files[i];
			if (file.isDirectory()) {
				cleanDir(file);
			} else {
				file.delete();
			}
		}
		dir.delete();
	}

	public static void cleanup(XADataSource xads) throws Throwable {
		XAConnection xacon = xads.getXAConnection();
		XAResource xares = xacon.getXAResource();
		Xid[] xidArray = xares.recover(XAResource.TMSTARTRSCAN | XAResource.TMENDRSCAN);
		System.out.printf("recover: length= %s%n", xidArray.length);
		for (int i = 0; i < xidArray.length; i++) {
			Xid xid = xidArray[i];
			xares.rollback(xid);
			System.out.printf("%2s. xid= %s-%s%n", i + 1 //
					, ByteUtils.byteArrayToString(xid.getGlobalTransactionId()) //
					, ByteUtils.byteArrayToString(xid.getBranchQualifier()));
		}
	}

}
