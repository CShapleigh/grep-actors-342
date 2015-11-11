import java.util.ArrayList;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.japi.Creator;

public class CGrep {
  public static void main(String [] args) {
	  final String pattern = args[0];
	  ArrayList<String> files = new ArrayList<String>();
	  for (int i = 1; i < args.length; i++) {
		  files.add(args[i]);
	  }

	  final ActorSystem system = ActorSystem.create("GrepSystem");
	  final ActorRef collectionActor = system.actorOf(Props.create(CollectionActor.class), "Collector");
	  collectionActor.tell(new CollectionActor.FileCount(files.size()), collectionActor);
	  for (final String file : files) {
	  	ActorRef StringSearcher = system.actorOf(Props.create(ScanActor.class), file);
		StringSearcher.tell(new ScanActor.Configure(collectionActor,file,pattern),collectionActor);
	  }
  }
}
