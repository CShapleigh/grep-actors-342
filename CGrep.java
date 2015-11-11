import java.util.ArrayList;

public class CGrep {
  public static void main(String [] args) {
	  final String pattern = args[0];
	  ArrayList<String> files = new ArrayList<String>();
	  for (int i = 1; i < args.length; i++) {
		  files.add(args[i]);
	  }

	  for (final String file : files) {
	  	// TODO: Start CGREP
	  }
  }
}