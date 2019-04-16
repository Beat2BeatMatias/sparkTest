import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class Test {

    public static void main(String[] args) {

        Scanner reader = new Scanner(System. in);
        System.out.println("Introduzca la url que quieras consultar: " );
        String consulta = reader.nextLine();
        consulta=consulta.replace(" ","");
        try {
            String data = readUrl(consulta);
            Sites[] sites = new Gson().fromJson(data, Sites[].class);
            int i=1;
            for (Sites site : sites) {
                System.out.println(i++ + ") " + site.getName());
            }

            System.out.println("Elija una opción: ");
            String respuesta = reader.nextLine();
            String idSites = sites[Integer.parseInt(respuesta)-1].getId();

            if(consulta.endsWith("/"))
                consulta += idSites + "/categories";
            else
                consulta += "/" + idSites + "/categories";

            String dataRespuesta = readUrl(consulta);
            Category[] categories = new Gson().fromJson(dataRespuesta, Category[].class);

            for (Category category : categories) {
                System.out.println(category.getName());
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Ocurrio un error al traer las categorias.");
        }


        reader.close();

    }


    private static String readUrl(String urlString) throws IOException {

        BufferedReader reader=null;

        try {
            URL url = new URL(urlString);
            URLConnection connection=url.openConnection();
            connection.setRequestProperty("Accept","application/json");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");
            reader=new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));
            int read = 0;
            StringBuffer buffer = new StringBuffer();
            char[] chars = new char[1024];
            while((read = reader.read(chars)) != -1){
                buffer.append(chars,0,read);
            }
            return buffer.toString();
        } finally {
            if(reader != null){
                reader.close();
            }
        }

    }
}
