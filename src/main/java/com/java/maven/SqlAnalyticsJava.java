package com.java.maven;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.SparkSession;

import com.datastax.spark.connector.japi.CassandraJavaUtil;

public class SqlAnalyticsJava {

	private JavaSparkContext sparkContext;
	private SQLContext sqlContext;
	private SparkSession spark;
	private SparkConf conf;

	private Dataset<ApacheLog> logDS1;
	private Dataset<ApacheLog> logDS2;

	public static void main(String[] args) {

		SqlAnalyticsJava sqlAnalytics = new SqlAnalyticsJava();

		sqlAnalytics.initJavaSparkContextParams();
		sqlAnalytics.initLogDataSet();

		sqlAnalytics.logAnalytics1();
		sqlAnalytics.logAnalytics2();
		sqlAnalytics.logAnalytics3();

		sqlAnalytics.stop();

	}

	private void stop() {
		spark.stop();
	}

	private void initJavaSparkContextParams() {
		conf = getConf();
		spark = SparkSession.builder().config(conf).getOrCreate();

		sparkContext = new JavaSparkContext(conf);
		sqlContext = new SQLContext(spark);
	}

	private SparkConf getConf() {
		return new SparkConf().set("spark.cassandra.connection.host", "127.0.0.1").setAppName("SqlAnalyticsJava");
	}

	private void initLogDataSet() {

		JavaRDD<ApacheLog> logJavaRDD = CassandraJavaUtil.javaFunctions(sparkContext).cassandraTable("loganalytics",
				"apachelogs", CassandraJavaUtil.mapRowTo(ApacheLog.class));

		logDS1 = sqlContext.createDataset(logJavaRDD.rdd(), Encoders.bean(ApacheLog.class));
		logDS1.cache();
		logDS2 = logDS1;

	}

	// 1st Problem SQL Query - (excluding images - only JPG and JPEG) - case
	// insensitive
	private void logAnalytics1() {

		logDS2.filter(log -> {

			String iUrl = log.getUrl().toLowerCase();
			return !(iUrl.endsWith(".jpg") || iUrl.endsWith(".jpeg"));

		}).createOrReplaceTempView("log_2");

		// Windowing for 24 hours
		Dataset<Temp1> logAnalytics1_1 = spark
				.sql("SELECT date, url, count(1) AS count FROM log_2 GROUP BY date, url ORDER BY date, count DESC")
				.as(Encoders.bean(Temp1.class));

		logAnalytics1_1.createOrReplaceTempView("logAnalytics1_1_View");

		Dataset<Row> logAnalytics1_1_Result = spark.sql(
				"SELECT * from (SELECT date, url, count, DENSE_RANK() OVER (PARTITION BY date ORDER BY count DESC) AS rank FROM logAnalytics1_1_View) tmp WHERE rank <= 3 ORDER BY date, count DESC")
				.coalesce(1).cache();

		// logAnalytics1_1_Result.show()
		logAnalytics1_1_Result.write().format("com.databricks.spark.csv").option("header", "true")
				.save("file:///home/maria_dev/logAnalytics1_24_Java");

		// ------------------------------------------------------------------------------------------

		// Windowing for 6 hours
		Dataset<Temp2> logAnalytics1_2 = spark.sql(
				"SELECT date, url, six_hour_slot, count(1) AS count FROM log GROUP BY date, url, six_hour_slot ORDER BY date, six_hour_slot, count DESC")
				.as(Encoders.bean(Temp2.class));

		logAnalytics1_2.createOrReplaceTempView("logAnalytics1_2_view");

		Dataset<Row> logAnalytics1_2_Result = spark.sql(
				"SELECT * from (SELECT date, url, six_hour_slot, count, DENSE_RANK() OVER (PARTITION BY date, six_hour_slot ORDER BY count DESC) AS rank FROM logAnalytics1_2_view) tmp WHERE rank <= 3 ORDER BY date, six_hour_slot, count DESC")
				.coalesce(1).cache();

		// logAnalytics1_2_Result.show()
		logAnalytics1_2_Result.write().format("com.databricks.spark.csv").option("header", "true")
				.save("file:///home/maria_dev/logAnalytics1_6_Java");
	}

	// 2nd Problem SQL Query
	private void logAnalytics2() {

		// Windowing for 24 hours
		Dataset<Temp3> logAnalytics2_1 = spark.sql(
				"SELECT ip_host, date, url, count(1) AS count FROM log GROUP BY ip_host, date, url ORDER BY date, count DESC")
				.as(Encoders.bean(Temp3.class));

		logAnalytics2_1.createOrReplaceTempView("logAnalytics2_1_View");

		Dataset<Row> logAnalytics2_1_Result = spark.sql(
				"SELECT * from (SELECT ip_host, date, url, count, DENSE_RANK() OVER (PARTITION BY date ORDER BY count DESC) AS rank FROM logAnalytics2_1_View) tmp WHERE rank <= 3 ORDER BY date, count DESC")
				.coalesce(1).cache();

		// logAnalytics2_1_Result.show()
		logAnalytics2_1_Result.write().format("com.databricks.spark.csv").option("header", "true")
				.save("file:///home/maria_dev/logAnalytics2_24_Java");

		// ------------------------------------------------------------------------------------------

		// Windowing for 6 hours
		Dataset<Temp4> logAnalytics2_2 = spark.sql(
				"SELECT ip_host, date, url, six_hour_slot, count(1) AS count FROM log GROUP BY ip_host, date, url, six_hour_slot ORDER BY date, six_hour_slot, count DESC")
				.as(Encoders.bean(Temp4.class));

		logAnalytics2_2.createOrReplaceTempView("logAnalytics2_2_view");

		Dataset<Row> logAnalytics2_2_Result = spark.sql(
				"SELECT * from (SELECT ip_host, date, url, six_hour_slot, count, DENSE_RANK() OVER (PARTITION BY date, six_hour_slot ORDER BY count DESC) AS rank FROM logAnalytics2_2_view) tmp WHERE rank <= 2 ORDER BY date, six_hour_slot, count DESC")
				.coalesce(1).cache();

		// logAnalytics2_2_Result.show()
		logAnalytics2_2_Result.write().format("com.databricks.spark.csv").option("header", "true")
				.save("file:///home/maria_dev/logAnalytics2_6_Java");
	}

	// 3rd Problem SQL Query
	private void logAnalytics3() {

		// Test
		Dataset<Row> logAnalytics3 = spark.sql(
				"SELECT version, code, count(*) AS count FROM log WHERE version IN ('1.0','1.1') GROUP BY code, version ORDER BY count DESC")
				.coalesce(1).cache();

		// Production - remove coalesce(n)

		// logAnalytics3.show()
		logAnalytics3.write().format("com.databricks.spark.csv").option("header", "true")
				.save("file:///home/maria_dev/log_analytics_3_Java");
	}
}
