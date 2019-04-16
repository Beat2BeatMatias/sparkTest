import static spark.Spark.*;

public class HolaMundoSpark {

    public static void main(String[] args) {

        get("/hola",(req,res)-> "Hola Mundo IT");

        get("/hola/:nombre",(req,res)->{
            return  "Hola "+req.params(":nombre");
        });
    }
}
