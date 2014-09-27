name := "Pilgrim explorer"

version := "3.0"

scalaVersion := "2.10.3"

resolvers += "swt-repo" at "https://swt-repo.googlecode.com/svn/repo/"

libraryDependencies ++= Seq(
  "log4j" % "log4j" % "1.2.16",
//  "org.eclipse.swt" % "org.eclipse.swt.win32.win32.x86_64" % "4.4",
  "junit" % "junit" % "4.11" % "test",
  "com.google.guava" % "guava" % "18.0",
  "com.jayway.jsonpath" % "json-path-assert" % "0.8.1" % "test"
)

libraryDependencies += {
  val os = (sys.props("os.name"), sys.props("os.arch")) match {
    case ("Linux", _) => "gtk.linux.x86"
    case ("Mac OS X", "amd64" | "x86_64") => "cocoa.macosx.x86_64"
    case ("Mac OS X", _) => "cocoa.macosx.x86"
    case (os, "amd64") if os.startsWith("Windows") => "win32.win32.x86_64"
    case (os, _) if os.startsWith("Windows") => "win32.win32.x86"
    case (os, arch) => sys.error("Cannot obtain lib for OS '" + os + "' and architecture '" + arch + "'")
  }
  val artifact = "org.eclipse.swt." + os
  "org.eclipse.swt" % artifact % "4.4"
}