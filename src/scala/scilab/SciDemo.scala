package scala.scilab
/**
 * Copyright 2016 Mingdong.
 * Licensed under the LGPL.
 * A demo of SciContext in Scala.
 * email: mdwkmail@yeah.net
 * @author Mingdong
 * @version 0.0.1
 **/
object SciDemo extends App {
  val sci=SciContext()
  sci.open()
  sci.exec("foo = [ 2, 4, 6; 4, 0, 10; 6, 10, 12 ]")
  var foo = sci.getDoubleReal("foo")
  println("Representation of: \n" + foo.map { x => x.mkString(",") }.mkString("\n"))
  
  val cmd="""mtx=[1,2,3;4,5,6];
                     disp(mtx);
                     disp(foo*mtx');
                """
                .split("\n").filter { x => x.nonEmpty}
                
  sci.execException(cmd)
  sci.close()
}