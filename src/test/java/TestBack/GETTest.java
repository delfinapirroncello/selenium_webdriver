package TestBack;

import com.aventstack.extentreports.util.Assert;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GETTest {
    @Test
    public void Get_Test01() {
        io.restassured.response.Response resGet = RestAssured.get("https://reqres.in/api/users?page=2");
        System.out.println(resGet.getBody().asString());
        System.out.println(resGet.statusCode());
        System.out.println(resGet.getHeader("Date"));
        System.out.println(resGet.getTime());
    }

    @Test
    public void Get_Test02() {
        Response response = RestAssured.get("https://reqres.in/api/users?page=2");

        int statusCode = response.statusCode();
        JsonPath body = response.jsonPath();
        String nombre_0 = body.getString("data.first_name[0]");
        String mail_0 = body.getString("data.email[0]");
        String nombre_2 = body.getString("data.first_name[2]");
        String mail_2 = body.getString("data.email[2]");

        assertEquals(statusCode, 200);
        assertEquals(nombre_0, "Michael");
        assertEquals(mail_0, "michael.lawson@reqres.in");
        assertEquals(nombre_2, "Tobias");
        assertEquals(mail_2, "tobias.funke@reqres.in");

        System.out.println("El código de status es: " + statusCode);
        System.out.println("El nombre del primer registro es: " + nombre_0);
        System.out.println("El correo del primer registro es: " + mail_0);
        System.out.println("El nombre del tercer registro es: " + nombre_2);
        System.out.println("El correo del tercer registro es: " + mail_2);
    }

    @Test
    public void Get_Test03() {
        given()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200)
                .log().body(false);
    }
}