package nvt.android.gplxquiz.db

import android.content.Context
import android.util.Xml
import nvt.android.gplxquiz.dto.QuizItem
import nvt.android.gplxquiz.dto.QuizList
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import java.io.IOException
import java.io.InputStream

class XmlReader(private val context: Context) {
    companion object {
        private const val XML_FILE_NAME = "data.xml"
    }

    private val ns: String? = null

    @Throws(XmlPullParserException::class, IOException::class)
    private fun parse(inputStream: InputStream): List<QuizList> {
        inputStream.use { inputStream ->
            val parser: XmlPullParser = Xml.newPullParser()
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false)
            parser.setInput(inputStream, null)
            parser.nextTag()
            return readQuiz(parser)
        }
    }

    @Throws(XmlPullParserException::class, IOException::class)
    private fun readQuiz(parser: XmlPullParser): List<QuizList> {
        val quizList = mutableListOf<QuizList>()

        parser.require(XmlPullParser.START_TAG, ns, "data")
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.eventType != XmlPullParser.START_TAG) {
                continue
            }
            // Starts by looking for the entry tag
            if (parser.name == "list") {
                quizList.add(readQuizList(parser))
            } else {
                skip(parser)
            }
        }
        return quizList
    }

    @Throws(XmlPullParserException::class, IOException::class)
    private fun readQuizList(parser: XmlPullParser): QuizList {
        parser.require(XmlPullParser.START_TAG, ns, "list")
        var id = ""
        var title = ""
        var description = ""
        var score = 0
        var count = 0
        var items: List<QuizItem> = arrayListOf()

        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.eventType != XmlPullParser.START_TAG) {
                continue
            }
            when (parser.name) {
                "id" -> id = readText(parser)
                "title" -> title = readText(parser)
                "description" -> description = readText(parser)
                "score" -> score = readText(parser).toInt()
                "count" -> count = readText(parser).toInt()
                "detail" -> items = readQuizItemList(parser)
                else -> skip(parser)
            }
        }
        return QuizList(id, title, description, score, count, items)
    }

    @Throws(XmlPullParserException::class, IOException::class)
    private fun readQuizItemList(parser: XmlPullParser): List<QuizItem> {
        val quizList = mutableListOf<QuizItem>()

        parser.require(XmlPullParser.START_TAG, ns, "detail")
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.eventType != XmlPullParser.START_TAG) {
                continue
            }
            // Starts by looking for the entry tag
            if (parser.name == "quiz") {
                quizList.add(readQuizItem(parser))
            } else {
                skip(parser)
            }
        }
        return quizList
    }

    @Throws(XmlPullParserException::class, IOException::class)
    private fun readQuizItem(parser: XmlPullParser): QuizItem {
        parser.require(XmlPullParser.START_TAG, ns, "quiz")
        var id = ""
        var title = ""
        var answer = listOf<String>()
        var multiAnswer = false
        var result = 0
        var tip = ""

        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.eventType != XmlPullParser.START_TAG) {
                continue
            }
            when (parser.name) {
                "id" -> id = readText(parser)
                "title" -> title = readText(parser)
                "answer" -> answer = readAnswer(parser)
                "result" -> result = readText(parser).toInt()
                "multiAnswer" -> multiAnswer = readText(parser).toBoolean()
                "tip" -> tip = readText(parser)
                else -> skip(parser)
            }
        }
        return QuizItem(id, title, answer, result, tip, multiAnswer)
    }

    @Throws(XmlPullParserException::class, IOException::class)
    private fun readAnswer(parser: XmlPullParser): List<String> {
        parser.require(XmlPullParser.START_TAG, ns, "answer")
        var answer = mutableListOf<String>()

        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.eventType != XmlPullParser.START_TAG) {
                continue
            }
            if (parser.name == "answer_detail") {
                answer.add(readText(parser))
            } else {
                skip(parser)
            }
        }
        return answer.toList()
    }

    @Throws(XmlPullParserException::class, IOException::class)
    private fun skip(parser: XmlPullParser) {
        if (parser.eventType != XmlPullParser.START_TAG) {
            throw IllegalStateException()
        }
        var depth = 1
        while (depth != 0) {
            when (parser.next()) {
                XmlPullParser.END_TAG -> depth--
                XmlPullParser.START_TAG -> depth++
            }
        }
    }

    @Throws(IOException::class, XmlPullParserException::class)
    private fun readText(parser: XmlPullParser): String {
        var result = ""
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.text
            parser.nextTag()
        }
        return result
    }

    @Throws(IOException::class, XmlPullParserException::class)
    fun readXml(): List<QuizList> {
        return parse(context.assets.open(XML_FILE_NAME))
    }
}