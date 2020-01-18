organization := "zamblauskas"

name := "sbt-examplestest"

scalaVersion := "2.12.10"

libraryDependencies ++= Seq(
  "com.vladsch.flexmark"  %  "flexmark"    % "0.50.48",
  "com.google.guava"      %  "guava"       % "28.2-jre",
  "commons-io"            %  "commons-io"  % "2.6",
  "org.scalatest"         %% "scalatest"                   % "3.1.0" % Test,
  "org.scalamock"         %% "scalamock-scalatest-support" % "3.6.0" % Test
)

sbtPlugin := true

licenses := ("MIT", url("https://opensource.org/licenses/MIT")) :: Nil
