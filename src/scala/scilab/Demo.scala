package scala.scilab

/**
 * Copyright 2016 Mingdong.
 * Licensed under the LGPL.
 * A demo of Scilab in Scala.
 * email: mdwkmail@yeah.net
 * @author Mingdong
 * @version 0.0.1
 **/

import scala.collection._
import scala.collection.Iterable._
import scala.collection.TraversableOnce._
import scala.annotation._
import scala.collection.JavaConverters._
import scala.collection.JavaConversions._
import scala.sys._
import scala.util.control._
import scala.util._
import scala.util.matching._
import scala.concurrent._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.control.Breaks._
import org.scilab.modules.javasci.Scilab
import org.scilab.modules.types._


object Demo extends App {
  try {

            val sci:Scilab = new Scilab()

            if (sci.open()) {
                /* Send a Scilab instruction */
                sci.exec("foo = [ 2, 4, 6; 4, 0, 10; 6, 10, 12 ]")

                /* Retrieve the variable foo */
                var foo = sci.get("foo")

                /* Display the variable */
                println("Representation of:\n" + foo)
                  
                var aReal = (foo.asInstanceOf[ScilabDouble]).getRealPart()
                println("foo[1,1] = " + aReal(1)(1))

                aReal(1)(1) = Math.PI

                var bar = new ScilabDouble(aReal)

                sci.put("bar", bar)
                
                sci.exec("disp(bar);")
                val cmd="""mtx=[1,2,3;4,5,6];
                                    disp(mtx);
                                    disp(bar*mtx');
                """
                .split("\n").filter { x => x.nonEmpty}
                
                sci.execException(cmd)
              
                sci.close()
            } else {
                println("Could not start Scilab ")
            }

        } catch {
          case e:Throwable => println("An exception occurred: " + e.getLocalizedMessage())
        }
}