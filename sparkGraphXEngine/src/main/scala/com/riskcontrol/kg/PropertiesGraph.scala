package com.riskcontrol.kg

import org.apache.spark.graphx.{Edge, Graph, VertexId}
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object PropertiesGraph {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("graph").setMaster("local[*]")
    val sc = new SparkContext(conf)

    sc.setLogLevel("OFF")
    //定义点
    val user:RDD[(VertexId,(String,String))] = sc.parallelize(
      Array(
        (3L,("hanmeimei","student")),
        (7L,("lilei","postdoc")),
        (5L,("zixuan","prof")),
        (2L,("haoran","prof"))
      )
    )

    val relationship: RDD[Edge[String]] = sc.parallelize(
      Array(
        Edge(3L,7L,"cooperate"),
        Edge(5L,7L,"advisor"),
        Edge(2L,5L,"colleague")
      )
    )

    val defaultUser = ("Jack ma","defaultusUser")
    val graph = Graph(user,relationship,defaultUser)

  }

}
