package com.riskcontrol.kg

import com.google.common.annotations.VisibleForTesting
import org.apache.spark.{SparkConf, SparkContext}


class RDDTest {
  var sc: SparkContext = _

  @Before
  def initialize(): Unit ={
    val conf = new SparkConf()
      .setAppName("First Application")
      .setMaster("local[*]") //本地多线程，指定所有可用内核

    sc = new SparkContext(conf)
    sc.setLogLevel("OFF")
  }
  @Test
  def test():Unit={

  }
}
