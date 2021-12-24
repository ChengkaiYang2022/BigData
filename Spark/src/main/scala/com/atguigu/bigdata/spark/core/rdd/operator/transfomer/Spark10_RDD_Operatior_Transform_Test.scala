package com.atguigu.bigdata.spark.core.rdd.operator.transfomer

import org.apache.spark.{SparkConf, SparkContext}

object Spark10_RDD_Operatior_Transform_Test {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(conf)
    val rdd = sc.makeRDD(List(1, 2, 3, 4, 5, 5, 5, 6, 7, 8, 9), 2)
    rdd.coalesce(3).saveAsTextFile("data/Spark10_RDD_Operatior_Transform_Test")
    sc.stop()
  }

}