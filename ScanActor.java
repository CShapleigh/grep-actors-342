import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import java.util.regex.*;
import java.io.*;
import java.util.ArrayList;



public class ScanActor extends UntypedActor {
  private ActorRef reportTo;
  private String regexPattern;
  private ArrayList<String> foundLines = new ArrayList<String>();

  public void onReceive(Object message) throws Exception {
  	if(message instanceof Configure){
      reportTo = ((Configure)message).getActor();
      regexPattern = ((Configure)message).getSearchTerm();
      readFile(((Configure)message).getFile());
      CollectionActor.Found foundInFile = new CollectionActor.Found(((Configure)message).getFile(), foundLines);
      reportTo.tell(foundInFile,getSelf());

    }
    else{
      unhandled(message);
    }
  }

  private void readFile(String fileName) {
    BufferedReader br;
    try {
      br = new BufferedReader(new java.io.FileReader(fileName));
      String line;
      int i = 0;
      while ((line = br.readLine()) != null) {
        matchLine(line,i);
      }
      br.close();
    } catch (Exception e)  {
      System.err.println("Error in readfile");
    }
  }
  private void matchLine(String line, int lineNum) {
    Pattern pattern = Pattern.compile(regexPattern);
    Matcher matcher = pattern.matcher(line);
    if (matcher.find()) {
      foundLines.add(lineNum + " " + line);
    }
  }
  static public class Configure {

    private final ActorRef act;
    private final String file;
    private final String searchterm;


    public Configure(ActorRef act, String file, String searchterm) {
      this.act = act;
      this.file = file;
      this.searchterm = searchterm;
    }

    public String getFile() {
      return file;
    }

    public ActorRef getActor() {
      return act;
    }

    public String getSearchTerm(){return searchterm;}

  }

}