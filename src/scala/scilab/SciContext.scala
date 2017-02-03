package scala.scilab

/**
 * Copyright 2016 Mingdong.
 * Licensed under the LGPL.
 * A Scilab in Scala.
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

case class SciContext() extends Serializable{
  val sci:Scilab = new Scilab()
  
  def open() = sci.open()
  
  def close() = sci.close()
  
  def exec(cmds:String) = sci.exec(cmds)
  
  def execException(cmds: Array[String]) =sci.execException(cmds)
  
  def get(variable:String) = sci.get(variable)
  
  def getDouble(variable:String) = sci.get(variable).asInstanceOf[ScilabDouble]
  
  def getDoubleReal(variable:String) = sci.get(variable).asInstanceOf[ScilabDouble].getRealPart
  
  def getString(variable:String) = sci.get(variable).asInstanceOf[ScilabString]
  
  def getBoolean(variable:String) = sci.get(variable).asInstanceOf[ScilabBoolean]
  
  def getList(variable:String) = sci.get(variable).asInstanceOf[ScilabList]

  def put(varname:String,matrix: Array[Array[String]])= sci.put(varname, new ScilabString(matrix))
    
  def put(varname:String,matrix: Array[Array[Double]])= sci.put(varname, new ScilabDouble(matrix))
  
  def put(varname:String,matrix: Array[Array[Int]])= sci.put(varname, new ScilabInteger(matrix,false))
  
  def put(varname:String,matrix: Array[Array[Long]])= sci.put(varname, new ScilabInteger(matrix,false))
  
  def put(varname:String,matrix: Array[Array[Boolean]])= sci.put(varname, new ScilabBoolean(matrix))
    
  def put(varname:String,list: List[Any])= {//Double,Int,Long,String,Boolean
    val data = new ScilabList()
    list.foreach { x => {
      x match{
        case s:String=>data.add(new ScilabString(s))
        case d:Double=>data.add(new ScilabDouble(d))
        case i:Int=>data.add(new ScilabInteger(i,false))
        case l:Long=>data.add(new ScilabInteger(l,false))
        case b:Boolean=>data.add(new ScilabBoolean(b))
      }
    }}
    sci.put(varname, data)
  }
  
}


