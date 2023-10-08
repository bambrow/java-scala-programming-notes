package collections

// deprecated
import scala.collection.JavaConversions

object JavaConversionsDemo extends App {

  val list: List[Int] = List(1, 2, 3)
  val javaList: java.util.List[java.lang.Integer] =
    JavaConversions.seqAsJavaList(list).asInstanceOf[java.util.List[java.lang.Integer]]
  println(javaList)

  val map: Map[Int, String] = Map(1 -> "a", 2 -> "b", 3 -> "c")
  val javaMap: java.util.Map[java.lang.Integer, String] =
    JavaConversions.mapAsJavaMap(map).asInstanceOf[java.util.Map[java.lang.Integer, String]]
  println(javaMap)

  val set: Set[String] = Set("A", "B")
  val javaSet: java.util.Set[String] = JavaConversions.setAsJavaSet(set)
  println(javaSet)

  val scalaBuffer: scala.collection.mutable.Buffer[Int] =
    JavaConversions.asScalaBuffer(javaList).asInstanceOf[scala.collection.mutable.Buffer[Int]]
  println(scalaBuffer)

  val scalaMap: scala.collection.mutable.Map[Int, String] =
    JavaConversions.mapAsScalaMap(javaMap).asInstanceOf[scala.collection.mutable.Map[Int, String]]
  println(scalaMap)

  val scalaSet: scala.collection.mutable.Set[String] = JavaConversions.asScalaSet(javaSet)
  println(scalaSet)

}
