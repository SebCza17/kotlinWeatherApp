
import com.beust.klaxon.Klaxon
import dao.WeatherApi
import model.City
import java.net.URL
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "Hello", value = ["/hello"])
class HomeController : HttpServlet() {
    override fun doGet(req: HttpServletRequest, res: HttpServletResponse) {

        var cityList = arrayListOf<String>("Krakow", "Lodz", "Wroclaw", "Gdansk", "Szczecin", "Warszawa", "Zakopane")

        val weatherApi = WeatherApi("http://api.openweathermap.org/data/2.5/", "87e128e248703bd2745770307df31697")



        res.contentType = "text/html"
        res.writer.write("<style>")
        res.writer.write("table {" +
                "background-color: aqua;" +
                "box-shadow: 2px 4px 10px 0px;" +
                "border-collapse: collapse;" +
                "width: 100%;" +
                "}" +
        "tr {" +
                "box-shadow: 1px 1px 1px 0px" +
        "}")
        res.writer.write("</style>")

        res.writer.write("<table><tr><th>Name</th><th>Descritpion</th><th>Temp</th><tr>")
        cityList.forEach {
            val city = weatherApi.getCurrentByCity(it)
            res.writer.write("<tr><td>")
            res.writer.write(city?.name)
            res.writer.write("</td><td>")
            res.writer.write(" " + city?.weather?.first()?.description)
            res.writer.write("</td><td>")
            res.writer.write(" " + city?.main?.temp.toString())
            res.writer.write("</td></tr>")
        }

        res.writer.write("</table>")
    }
}