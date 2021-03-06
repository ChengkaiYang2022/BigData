package wc

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark02_WordCount {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local").setAppName("wordcount")
    val sc = new SparkContext(conf)
    val value: RDD[String] = sc.textFile("src/main/resources/datas.txt")
    val value1 = value.flatMap(_.split(",")).map(t => {
      (t, 1)
    }).reduceByKey((i, j) => {
      i + j
    })
    value1.collect()
    value1.foreach(println)
    sc.stop()
  }
}
