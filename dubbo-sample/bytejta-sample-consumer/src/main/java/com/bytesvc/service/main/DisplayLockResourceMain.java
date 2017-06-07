package com.bytesvc.service.main;

import javax.sql.XAConnection;
import javax.sql.XADataSource;
import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;

import org.bytesoft.common.utils.ByteUtils;

public class DisplayLockResourceMain {

	public static void main(String... args) throws Throwable {
		com.mysql.jdbc.jdbc2.optional.MysqlXADataSource xads = new com.mysql.jdbc.jdbc2.optional.MysqlXADataSource();
		xads.setUrl("jdbc:mysql://127.0.0.1:3306/inst01");
		xads.setUser("root");
		xads.setPassword("123456");

		cleanup(xads);
	}

	public static void cleanup(XADataSource xads) throws Throwable {
		XAConnection xacon = xads.getXAConnection();
		XAResource xares = xacon.getXAResource();
		Xid[] xidArray = xares.recover(XAResource.TMSTARTRSCAN | XAResource.TMENDRSCAN);
		System.out.printf("recover: length= %s%n", xidArray.length);
		for (int i = 0; i < xidArray.length; i++) {
			Xid xid = xidArray[i];
			System.out.printf("%2s. xid= %s-%s%n", i + 1 //
					, ByteUtils.byteArrayToString(xid.getGlobalTransactionId()) //
					, ByteUtils.byteArrayToString(xid.getBranchQualifier()));
		}
	}

}
