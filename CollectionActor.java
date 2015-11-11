import akka.actor.UntypedActor;
import java.util.ArrayList;




public class CollectionActor extends UntypedActor{
	private int fileCount;
	private boolean configured;
	private int filesReceived = 0;
	private ArrayList<ArrayList<String>> lines = new ArrayList<ArrayList<String>>();
	
	public void onReceive(Object message) throws Exception{
		if (message instanceof Found){
			if(!configured){
				//Do nothing if FileCount Message hasn't been received yet
			}
			else {
				lines.add( ((Found)message).getMatchingLinesInFile() );
				filesReceived++;
				if (filesReceived == fileCount) {
					// Print out all the Strings
				}
			}
			
		}
		else if(message instanceof FileCount){
				fileCount = ((FileCount)message).getFilesBeingScanned();
				configured = true;
		}
		else{
			unhandled(message);
		}
	}
	static public class FileCount {

		private final int filesBeingScanned;

		public FileCount(int files) {
			filesBeingScanned = files;
		}

		public int getFilesBeingScanned() {
			return filesBeingScanned;
		}
	}
	static public class Found {

		private final String fileName;
		private final ArrayList<String> matchingLinesInFile;

		public Found(String fileName, ArrayList<String> results) {
			this.fileName = fileName;
			this.matchingLinesInFile = results;
		}

		public String getFileName() {
			return fileName;
		}

		public ArrayList<String> getMatchingLinesInFile() {
			return matchingLinesInFile;
		}
	}

}