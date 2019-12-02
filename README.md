[![Build Status](https://travis-ci.org/zamblauskas/sbt-examplestest.svg?branch=master)](https://travis-ci.org/zamblauskas/sbt-examplestest)
[![Bintray Download](https://api.bintray.com/packages/zamblauskas/sbt-plugins/sbt-examplestest/images/download.svg)](https://bintray.com/zamblauskas/sbt-plugins/sbt-examplestest/_latestVersion)

About
==============================
SBT plugin to generate unit tests for code examples in markdown (e.g. README) files.

Usage
==============================

Package is available at [Bintray](https://bintray.com/zamblauskas/sbt-plugins/sbt-examplestest).
Check for the latest version and add to your `project/plugins.sbt`:
```scala
resolvers += Resolver.bintrayIvyRepo("zamblauskas", "sbt-plugins")

addSbtPlugin("zamblauskas" % "sbt-examplestest" % "<latest_version>")
```

| version  | SBT  | scalatest |
|----------|------|-----------|
| 0.1.1    | 0.13 | 3.0.4     |
| 0.1.2    | 1.x  | 3.0.4     |
| 0.2.0+   | 1.x  | 3.1.0     |

Plugin will be enabled by default.
It will find all `.md` files in the base directory, generate unit test code for every Scala block (marked as \`\`\` scala) and put it in `target/scala-<version>/src_managed/test`.
Tests will be run during `sbt test`.

Dependencies
==============================

Your project must have a `ScalaTest` library on path. E.g.:
```scala
"org.scalatest" %% "scalatest" % "3.1.0" % Test
```

Example
==============================

If you have `README.md`:

> \`\`\` scala
> val foo = "bar"
> foo shouldBe "bar"
> \`\`\`

plugin will create `target/scala-2.11/src_managed/test/READMETest.scala`:
``` scala
import org.scalatest.{FunSpec, Matchers}

class READMETest extends FunSpec with Matchers {
  describe("README") {
    it("code block #0") {
val foo = "bar"
foo shouldBe "bar"
    }
  }
}
```

Configuration
==============================

Defaults should work for most of the projects, but you can change a few SBT keys if needed.
```scala
// path to markdown files
examplesTestInputFiles := (baseDirectory.value * "*.md").filter(_.isFile).get

// code block types the plugin generates unit tests for
examplesTestCodeBlockTypes := Seq("scala")
```
