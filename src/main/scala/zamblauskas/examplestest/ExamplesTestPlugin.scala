package zamblauskas.examplestest

import sbt._
import sbt.Keys._
import sbt.plugins.JvmPlugin

object ExamplesTestPlugin extends AutoPlugin {

  val examplesTestInputFiles = settingKey[Seq[File]]("Files containing code examples.")
  val examplesTestCodeBlockTypes = settingKey[Seq[String]]("Code block types to generate tests for.")
  val examplesTestGenTests = taskKey[Seq[File]]("Generates test files for code examples.")

  override def trigger: PluginTrigger = AllRequirements
  override def requires: Plugins = JvmPlugin
  override def projectSettings: Seq[Setting[_]] = Seq(
    examplesTestInputFiles := (baseDirectory.value * "*.md").filter(_.isFile).get,
    examplesTestCodeBlockTypes := Seq("scala"),
    examplesTestGenTests := {
      (managedSourceDirectories in Test).value.headOption match {
        case None =>
          streams.value.log.error("Cannot find managed sources directory.")
          Seq.empty
        case Some(dir) =>
          val writer = new TestWriter(dir, examplesTestCodeBlockTypes.value, FlexMarkCodeExampleExtractor, ScalaTestGenerator)
          examplesTestInputFiles.value.flatMap(writer.write)
      }
    },
    sourceGenerators in Test += examplesTestGenTests.taskValue,
    watchSources ++= examplesTestInputFiles.value
  )
}
