organization := "zamblauskas"

name := "sbt-examplestest"

scalaVersion := "2.12.4"

libraryDependencies ++= Seq(
  "com.vladsch.flexmark"  %  "flexmark"    % "0.28.32",
  "com.google.guava"      %  "guava"       % "23.6-jre",
  "commons-io"            %  "commons-io"  % "2.6",
  "org.scalatest"         %% "scalatest"                   % "3.0.4" % Test,
  "org.scalamock"         %% "scalamock-scalatest-support" % "3.6.0" % Test
)

sbtPlugin := true

licenses := ("MIT", url("https://opensource.org/licenses/MIT")) :: Nil
