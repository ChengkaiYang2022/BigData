package com.atguigu.bigdata.spark.core.rdd.operator.transfomer

import org.apache.spark.{SparkConf, SparkContext}

/**
 *求出单词最多的
 */
object Spark18_RDD_Operatior_Transform_Test {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(conf)

    val rdd = sc.makeRDD(List(

      ("a", 1), ("a", 2), ("b", 3),
      ("b", 4), ("b", 5), ("a", 6)
    ))
    rdd.aggregateByKey((0, 0))(
      (t, v) =>{
        (t._1 + v,t._2 + 1)
      },
      (t1, t2) => {
        (t1._1 + t2._1,t2._2 + t1._2)
      }
    ).mapValues{
      case (t,v) => t/v
    }.collect().foreach(println)
    sc.stop()

  }

}


