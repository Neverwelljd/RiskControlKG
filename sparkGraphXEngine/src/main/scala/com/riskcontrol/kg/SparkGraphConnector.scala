package com.riskcontrol.kg

import org.apache.spark.SparkConf
import org.apache.spark.api.java.JavaSparkContext
import org.apache.spark.graphx.Graph
import org.neo4j.driver.internal.InternalNode
import org.neo4j.spark.Neo4j

object SparkGraphConnector {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setAppName("neo4j")
      .setMaster("local[*]")
      .set("spark.neo4j.bolt.url","bolt://192.168.3.10")
      .set("spark.neo4j.bolt.user","neo4j")
      .set("spark.neo4j.bolt.password","123456")

    val sc = new JavaSparkContext(conf)

    sc.setLogLevel("OFF")
    val neo4j = Neo4j(sc)

    val rdd = neo4j.cypher("match (n:Person) return n").loadRowRdd

    val personRDD = rdd.map(row =>{
      val map = row.get(0).asInstanceOf[InternalNode]
      new Person(map.get("home").asString(),
        map.get("name").asString(),
        map.get("personId").asString()
      )
    })

    personRDD.foreach(println)

    val graphQuery = "match (p:Person)=[r]-(a:Person) return id(p) as source, id(a) as target, type(r) as value"

    val graph:Graph[String,String] = neo4j.rels(graphQuery).loadGraph
    graph.edges.foreach(println(_))

  }

  case class Person(val home: String, val name: String, val personId: String)

}
