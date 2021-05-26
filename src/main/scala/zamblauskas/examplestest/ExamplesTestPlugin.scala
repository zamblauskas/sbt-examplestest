package zamblauskas.examplestest

import sbt._
import sbt.Keys._
import sbt.plugins.JvmPlugin

object ExamplesTestPlugin extends AutoPlugin {

  val examplesTestInputFiles = settingKey[Seq[File]]("Files containing code examples.")
  val examplesTestCodeBlockTypes = settingKey[Seq[String]]("Code block types to generate tests for.")
  val examplesTestGenerator = taskKey[Option[TestGenerator]]("Testing library specific code generator.")
  val examplesTestGenTests = taskKey[Seq[File]]("Generates test files for code examples.")

  override def trigger: PluginTrigger = AllRequirements
  override def requires: Plugins = JvmPlugin
  override def projectSettings: Seq[Setting[_]] = Seq(
    examplesTestInputFiles := (baseDirectory.value * "*.md").filter(_.isFile).get,
    examplesTestCodeBlockTypes := Seq("scala"),
    examplesTestGenerator := generatorFromDependencies(allDependencies.value),
    examplesTestGenTests := {
      ((Test / managedSourceDirectories).value.headOption, examplesTestGenerator.value) match {
        case (None, _) =>
          streams.value.log.error("Cannot find managed sources directory.")
          Seq.empty
        case (_, None) =>
          streams.value.log.error("Cannot determine testing library.")
          Seq.empty
        case (Some(dir), Some(generator)) =>
          val writer = new TestWriter(dir, examplesTestCodeBlockTypes.value, FlexMarkCodeExampleExtractor, generator)
          examplesTestInputFiles.value.flatMap(writer.write)
      }
    },
    Test / sourceGenerators += examplesTestGenTests.taskValue,
    watchSources ++= examplesTestInputFiles.value
  )

  private val moduleToGenerator = Map(
    "munit-cats-effect-3" -> MunitCatsEffect3Generator,
    "scalatest" -> ScalaTestGenerator
  )

  def generatorFromDependencies(dependencies: Seq[ModuleID]): Option[TestGenerator] = {
    val modules = dependencies.filter(_.configurations.exists(_ == "test")).map(_.name)
    moduleToGenerator.collectFirst {
      case (module, generator) if modules.contains(module) => generator
    }
  }
}
