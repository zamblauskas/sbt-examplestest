organization := "io.github.zamblauskas"
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
startYear := Some(2017)
organizationName := "github.com/zamblauskas/sbt-examplestest/graphs/contributors"
homepage := Some(url("https://github.com/zamblauskas/sbt-examplestest"))
developers := List(
  Developer(
    "contributors",
    "Contributors",
    "https://github.com/zamblauskas/sbt-examplestest/graphs/contributors",
    url("https://github.com/zamblauskas/sbt-examplestest/graphs/contributors")
  )
)
