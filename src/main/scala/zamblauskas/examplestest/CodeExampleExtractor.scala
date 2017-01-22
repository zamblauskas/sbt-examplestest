package zamblauskas.examplestest

import com.vladsch.flexmark.ast._
import com.vladsch.flexmark.parser.Parser

import scala.collection.mutable

case class CodeExample(`type`: String, block: String)

trait CodeExampleExtractor {
  def extract(doc: String): Seq[CodeExample]
}

object FlexMarkCodeExampleExtractor extends CodeExampleExtractor {
  def extract(doc: String): Seq[CodeExample] = {
    val document = Parser.builder.build.parse(doc)
    val examples = mutable.ListBuffer.empty[CodeExample]

    val visitor = new NodeVisitor(new VisitHandler[FencedCodeBlock](classOf[FencedCodeBlock], new Visitor[FencedCodeBlock] {
      override def visit(node: FencedCodeBlock): Unit = {
        examples += CodeExample(node.getInfo.toString, node.getContentChars.toString)
      }
    }))
    visitor.visitChildren(document)

    examples
  }
}
