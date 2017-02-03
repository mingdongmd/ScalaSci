# ScalaSci

This is framework calling Scilab code from Scala.

一个从Scala程序调用Scilab代码的架构。

##SciContext definition(SciContext定义)

```scala
case class SciContext()extends Serializable{
	val sci:Scilab = new Scilab()
	...
}
```


###Usage(用法)：

1)Set environment variable "PATH"(设置环境变量"PATH")
export PATH=$PATH:/path/to/scilab/bin

2)Install Scala(安装Scala)

3)Demonstration code(演示代码)
```scala
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
```

##Link(连接)：

* http://www.scilab.org
* https://atoms.scilab.org
