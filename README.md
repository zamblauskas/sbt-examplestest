[![Build Status](https://travis-ci.org/zamblauskas/sbt-examplestest.svg?branch=master)](https://travis-ci.org/zamblauskas/sbt-examplestest)

About
==============================
SBT plugin to generate unit tests for code examples in markdown (e.g. README) files.

Usage
==============================

Add to your `project/plugins.sbt`:
```
resolvers += Resolver.bintrayRepo("zamblauskas", "maven")

addSbtPlugin("zamblauskas" % "sbt-examplestest" % "0.1.0")
```

Plugin will be enabled by default.  
It will find all `.md` files in the base directory, generate unit test code for every Scala block (marked as \`\`\` scala) and put it in `target/scala-<version>/src_managed/test`.  
Tests will be run during `sbt test`.

Dependencies
==============================

Your project must have a `ScalaTest` library on path. E.g.:
```
"org.scalatest" %% "scalatest" % "3.0.0" % Test
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
```
// path to markdown files
examplesTestInputFiles := (baseDirectory.value * "*.md").filter(_.isFile).get

// code block types the plugin generates unit tests for
examplesTestCodeBlockTypes := Seq("scala")
```
