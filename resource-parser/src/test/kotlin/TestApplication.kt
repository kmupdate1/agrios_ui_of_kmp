import org.b3.agrios.plugin.generator.ColorsKotlinGenerator
import org.b3.agrios.plugin.generator.StringsKotlinGenerator
import org.b3.agrios.plugin.res.parser.xml.BytesFileXmlParser
import org.b3.agrios.plugin.res.parser.xml.DefaultBytesXmlParser
import org.b3.agrios.plugin.res.values.ColorsResourceParser
import org.b3.agrios.plugin.res.values.StringsResourceParser
import org.b3.agrios.plugin.writer.KtFileWriter
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class TestApplication {
    @Test
    fun `en-stringsをパースするテスト`() {
        val file = File(
            javaClass.getResource("/res/values/strings.xml")!!.toURI()
        )

        val parser = StringsResourceParser(
            parser = bytesFileParser,
        )

        val resources = parser.parse(file)

        println("en:\n$resources")

        assertEquals(5, resources.size)
    }

    @Test
    fun `ja-stringsをパースするテスト`() {
        val file = File(
            javaClass.getResource("/res/values-ja/strings.xml")!!.toURI()
        )

        val parser = StringsResourceParser(
            parser = bytesFileParser,
        )

        val resources = parser.parse(file)

        println("ja\n$resources")

        assertEquals(5, resources.size)
    }

    @Test
    fun `colorsをパースするテスト`() {
        val file = File(
            javaClass.getResource("/res/values/colors.xml")!!.toURI()
        )

        val parser = ColorsResourceParser(
            parser = bytesFileParser,
        )

        val resources = parser.parse(file)

        println("colors:\n$resources")

        assertEquals(56, resources.size)
    }

    @Test
    fun `output kotlin file`() {
        val writer = KtFileWriter(
            outputDirectory = File("build/generate")
        )

        val stringsFile = File(
            javaClass.getResource("/res/values/strings.xml")!!.toURI()
        )
        val stringsParser = StringsResourceParser(bytesFileParser)
        val strings = stringsParser.parse(stringsFile)

        writer.write(
            fileName = "Strings",
            source = StringsKotlinGenerator().generate(strings)
        )

        val colorsFile = File(
            javaClass.getResource("/res/values/colors.xml")!!.toURI(),
        )
        val colorsParser = ColorsResourceParser(bytesFileParser)
        val colors = colorsParser.parse(colorsFile)

        writer.write(
            fileName = "Colors",
            source = ColorsKotlinGenerator().generate(colors)
        )
    }

    private val bytesFileParser = BytesFileXmlParser(
        parser = DefaultBytesXmlParser(),
    )
}
