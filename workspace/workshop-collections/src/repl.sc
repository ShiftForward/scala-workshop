object repl {

  var x = 5                                       //> x  : Int = 5
  x = 6
  x                                               //> res0: Int = 6

  val y = 5                                       //> y  : Int = 5
  // y = 5

  case class Car(year: Int, model: String)

  val car1 = new Car(1989, "Fiat Punto")          //> car1  : repl.Car = Car(1989,Fiat Punto)
  val car2 = Car(1989, "Fiat Punto")              //> car2  : repl.Car = Car(1989,Fiat Punto)

  car1 match {
    case Car(year, _) if year < 1990 => println("Too old!")
    case Car(_, model) => println(model)
  }                                               //> Too old!

  val list1 = List(1, 3, 4)                       //> list1  : List[Int] = List(1, 3, 4)
  val list2 = List(1, 3, "aa")                    //> list2  : List[Any] = List(1, 3, aa)
  val list3 = List(List(1, 3), Set(2, 4))         //> list3  : List[scala.collection.immutable.Iterable[Int] with Int => AnyVal] =
                                                  //|  List(List(1, 3), Set(2, 4))

  val func = { x: Int => x * 2 }                  //> func  : Int => Int = <function1>
  func                                            //> res1: Int => Int = <function1>
  func(3)                                         //> res2: Int = 6
  func(4)                                         //> res3: Int = 8
  List(1, 2, 3).map(func)                         //> res4: List[Int] = List(2, 4, 6)
  List(1, 2, 3).map { x: Int => x * 2 }           //> res5: List[Int] = List(2, 4, 6)
  List(1, 2, 3).map(x => x * 2)                   //> res6: List[Int] = List(2, 4, 6)
  List(1, 2, 3).map(_ * 2)                        //> res7: List[Int] = List(2, 4, 6)

  val nums = (1 to 10).toList                     //> nums  : List[Int] = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
  nums.filter(_ % 2 != 0)                         //> res8: List[Int] = List(1, 3, 5, 7, 9)
  nums.take(3)                                    //> res9: List[Int] = List(1, 2, 3)
  nums.drop(3)                                    //> res10: List[Int] = List(4, 5, 6, 7, 8, 9, 10)
  nums.sum                                        //> res11: Int = 55
  nums.max                                        //> res12: Int = 10
  nums.map(_ + 100).filter(_ % 2 != 0)            //> res13: List[Int] = List(101, 103, 105, 107, 109)
  nums.map(_ + 100).filter(_ % 2 != 0)            //> res14: List[Int] = List(101, 103, 105, 107, 109)
  nums.foreach(println)                           //> 1
                                                  //| 2
                                                  //| 3
                                                  //| 4
                                                  //| 5
                                                  //| 6
                                                  //| 7
                                                  //| 8
                                                  //| 9
                                                  //| 10
  nums.foreach { i => println("a imprimir " + i) }//> a imprimir 1
                                                  //| a imprimir 2
                                                  //| a imprimir 3
                                                  //| a imprimir 4
                                                  //| a imprimir 5
                                                  //| a imprimir 6
                                                  //| a imprimir 7
                                                  //| a imprimir 8
                                                  //| a imprimir 9
                                                  //| a imprimir 10

  (1 to 10).foreach { i => println("a imprimir " + i) }
                                                  //> a imprimir 1
                                                  //| a imprimir 2
                                                  //| a imprimir 3
                                                  //| a imprimir 4
                                                  //| a imprimir 5
                                                  //| a imprimir 6
                                                  //| a imprimir 7
                                                  //| a imprimir 8
                                                  //| a imprimir 9
                                                  //| a imprimir 10
  import scala.concurrent.ExecutionContext.Implicits.global
  (1 to 10).par.foreach { i => println("a imprimir " + i) }
                                                  //> a imprimir 1
                                                  //| a imprimir 6
                                                  //| a imprimir 8
                                                  //| a imprimir 7
                                                  //| a imprimir 3
                                                  //| a imprimir 9
                                                  //| a imprimir 10
                                                  //| a imprimir 2
                                                  //| a imprimir 5
                                                  //| a imprimir 4
}