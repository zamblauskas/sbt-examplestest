organization := "zamblauskas"

name := "sbt-examplestest"

scalaVersion := "2.10.6"

libraryDependencies ++= Seq(
  "com.vladsch.flexmark"  %  "flexmark"    % "0.11.1",
  "com.google.guava"      %  "guava"       % "21.0",
  "commons-io"            %  "commons-io"  % "2.5",
  "org.scalatest"         %% "scalatest"                   % "3.0.1" % Test,
  "org.scalamock"         %% "scalamock-scalatest-support" % "3.4.2" % Test
)

sbtPlugin := true

licenses := ("MIT", url("https://opensource.org/licenses/MIT")) :: Nil
