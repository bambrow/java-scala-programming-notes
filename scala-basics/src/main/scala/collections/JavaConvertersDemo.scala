package collections

import scala.collection.JavaConverters

object JavaConvertersDemo extends App {

  val list: List[Int] = List(1, 2, 3)
  val javaList: java.util.List[java.lang.Integer] =
    JavaConverters.seqAsJavaList(list).asInstanceOf[java.util.List[java.lang.Integer]]
  println(javaList)

  val map: Map[Int, String] = Map(1 -> "a", 2 -> "b", 3 -> "c")
  val javaMap: java.util.Map[java.lang.Integer, String] =
    JavaConverters.mapAsJavaMap(map).asInstanceOf[java.util.Map[java.lang.Integer, String]]
  println(javaMap)

  val set: Set[String] = Set("A", "B")
  val javaSet: java.util.Set[String] = JavaConverters.setAsJavaSet(set)
  println(javaSet)

  val scalaBuffer: scala.collection.mutable.Buffer[Int] =
    JavaConverters.asScalaBuffer(javaList).asInstanceOf[scala.collection.mutable.Buffer[Int]]
  println(scalaBuffer)

  val scalaMap: scala.collection.mutable.Map[Int, String] =
    JavaConverters.mapAsScalaMap(javaMap).asInstanceOf[scala.collection.mutable.Map[Int, String]]
  println(scalaMap)

  val scalaSet: scala.collection.mutable.Set[String] = JavaConverters.asScalaSet(javaSet)
  println(scalaSet)

}
