package com.exterro.CassandraDemo;
/**
import java.util.concurrent.TimeUnit;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.stargate.grpc.StargateBearerToken;
import io.stargate.proto.QueryOuterClass;
import io.stargate.proto.StargateGrpc;


 * Hello world!
 *

public class App 
{
	private static final String ASTRA_DB_ID = "b5efbf66-620d-4167-a383-9c02d10dba43";
	private static final String ASTRA_DB_REGION = "us-east1";
	private static final String ASTRA_TOKEN = "AstraCS:jTkXcUKZUoSCTXnnemtslZEB:5a630ffd988cb84046294343e1f139227b39be7dfcfbded435f0ad59bb1e424b";
	private static final String ASTRA_KEYSPACE = "demo";

	public static void main(String[] args)
			throws Exception {
			  //-------------------------------------
			  // 1. Initializing Connectivity
			  //-------------------------------------
			  ManagedChannel channel = ManagedChannelBuilder
			          .forAddress(ASTRA_DB_ID + "-" + ASTRA_DB_REGION + ".apps.astra.datastax.com", 443)
			          .useTransportSecurity()
			          .build();

			  // blocking stub version
			  StargateGrpc.StargateBlockingStub blockingStub =
			      StargateGrpc.newBlockingStub(channel)
			          .withDeadlineAfter(10, TimeUnit.SECONDS)
			          .withCallCredentials(new StargateBearerToken(ASTRA_TOKEN));
			  blockingStub.executeBatch(
					  QueryOuterClass.Batch.newBuilder()
					      .addQueries(
					          QueryOuterClass.BatchQuery.newBuilder()
					              .setCql("INSERT INTO " + ASTRA_KEYSPACE + ".users (id,firstname, lastname) VALUES(01234567-0123-0123-0123-0123456789ab,'Jane', 'Doe')")
					              .build())
					      .addQueries(
					          QueryOuterClass.BatchQuery.newBuilder()
					              .setCql("INSERT INTO " + ASTRA_KEYSPACE + ".users (id,firstname, lastname) VALUES(01234567-0123-0123-0123-0123456789ac,'Serge', 'Provencio')")
					              .build())
					      .build());
					System.out.println("2 rows have been inserted in table users.");
	}
}
 */
