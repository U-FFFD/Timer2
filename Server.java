import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Scanner;

public class Server{

  static String sharedResponse = "";
  static MainDirectory theDirectory = new MainDirectory();

  static String htmltop = "<!DOCTYPE html>\n"
          + "<head>\n"
          + "<title>U+FFFD Corp's Super Private Employee Directory</title>\n"
          + "<link rel='stylesheet' type='text/css' href='style.css'>\n"
          + "</head>\n"
          + "<body>\n"
          + "<table>\n"
          + "<thead>\n"
          + "<tr>\n"
          + "<th>Place</th>\n"
          + "<th>Bib Num</th>\n"
          + "<th>Name</th>\n"
          + "<th>Time</th>\n"
          + "<th>Race Type</th>\n"
          + "</tr>\n"
          + "<tbody>\n";

  static String htmlbottom = "</tbody>\n"
          + "</table>\n"
          + "</body\n"
          + "</html>\n";

  public static void main(String[] args) throws Exception{
    HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

    // creates contexts for displaying and posting data
    server.createContext("/results", new DisplayHandler());
    server.createContext("/sendresults", new PostHandler());
    server.createContext("/style.css", new CssHandler());

    server.setExecutor(null);

    System.out.println("Server starting");
    server.start();
  }

  static class MainDirectory {
    private ArrayList<RacerData> emplDir;

    public MainDirectory(){
      emplDir = new ArrayList<RacerData>();
    }


    public void add(String input){
      Gson g = new Gson();
      RacerData imported;
      imported = g.fromJson(input, RacerData.class);
      emplDir.add(imported);
    }

    public void print(){
      for(RacerData x : emplDir)
      {
        System.out.print(x.toString());
        System.out.println();
      }
      System.out.println();
    }

    public void clear(){

      emplDir = new ArrayList<>();

    }

    public String toString(){
      String ret = "";
      for (RacerData x : emplDir){
        ret += x.toString();
        ret += "\n";
      }

      return ret;
    }

      public String toTable(){
          String ret = "";
          for (RacerData x : emplDir){
              ret += x.toTable();
          }
          return ret;
      }

  }

  static class PostHandler implements HttpHandler {
    public void handle(HttpExchange transmission) throws IOException {
      //  shared data that is used with other handlers
      sharedResponse = "";

      // set up a stream to read the body of the request
      InputStream inputStr = transmission.getRequestBody();

      // set up a stream to write out the body of the response
      OutputStream outputStream = transmission.getResponseBody();

      // string to hold the result of reading in the request
      StringBuilder sb = new StringBuilder();

      // read the characters from the request byte by byte and build up the sharedResponse
      int nextChar = inputStr.read();
      while (nextChar > -1) {
        sb=sb.append((char)nextChar);
        nextChar=inputStr.read();
      }

      // create our response String to use in other handler
      sharedResponse = sharedResponse+sb.toString();

      String[] splitIn = sharedResponse.split(" ", 2);
      System.out.println("Command: " + splitIn[0]);

      switch(splitIn[0]){
        case "ADD":
          System.out.println("Adding " + splitIn[1]);
          theDirectory.add(splitIn[1]);
          System.out.println("Added " + splitIn[1]);
          break;
        case "PRINT":
          System.out.println("Printing:");
          theDirectory.print();
          break;
        case "CLEAR":
          System.out.println("Clearing...");
          theDirectory.clear();
          break;
        default:
          break;
      }

      // respond to the POST with ROGER
      String postResponse = "ROGER JSON RECEIVED";

      // assume that stuff works all the time
      transmission.sendResponseHeaders(200, postResponse.length());

      // write it and return it
      outputStream.write(postResponse.getBytes());

      outputStream.close();
    }
  }
    static class DisplayHandler implements HttpHandler {
        public void handle(HttpExchange t) throws IOException {

            String response = "";
            response += htmltop;
            Gson g = new Gson();
            // set up the header

            response += theDirectory.toTable();

            response += htmlbottom;

            // write out the response
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    static class CssHandler implements HttpHandler {
        public void handle(HttpExchange t) throws IOException {
            String css = new Scanner(new File("style.css")).useDelimiter("\\Z").next();

            t.sendResponseHeaders(200, css.length());
            OutputStream os = t.getResponseBody();
            os.write(css.getBytes());
            os.close();
        }
    }
}