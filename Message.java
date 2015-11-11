import java.util.ArrayList;

public class Message {


  static class Configure {

  	private final CollectionActor act;
  	private final String file;

  	public Configure(CollectionActor act, String file) {
  		this.act = act;
  		this.file = file;
  	}

  	public String getFile() {
  		return file;
  	}

  	public CollectionActor getActor() {
  		return act;
  	}

  }

  static class FileCount {

    private final int filesBeingScanned;

    public FileCount (int files) {
      filesBeingScanned = files;
    }

    public int getFilesBeingScanned() {
      return filesBeingScanned;
    }

  }

  static class Found {

      private final String fileName;
      private final ArrayList<String> matchingLinesInFile;
      private final boolean match;

      public Found(String fileName) {
          this.fileName = fileName;
          this.matchingLinesInFile = new ArrayList<>();
      }

      public String getFileName() {
          return fileName;
      }

      public ArrayList<String> getMatchingLinesInFile() {
          return matchingLinesInFile;
      }


      public void addLine(String line) {
          matchingLinesInFile.add(line);
          match = true;
      }

      public boolean hasMatch() {
        return match;
      }
  }



}
