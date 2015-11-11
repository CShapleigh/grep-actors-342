public static class CollectionActor extends UntypedActor{
	private int fileCount;
	private int filesReceived = 0;
	private ArrayList<List<String>> lines; 
	
	public void onRecieve(Object message) throws Exception{
		if (message instanceof Found){
			if(fileCount == null){
				//Do nothing if FileCount Message hasn't been received yet
			}
			else{
				lines.append(message.getMatchingLinesInFile);
				filesReceived++;
				if(filesRecieved == fileCount){
					// Print out all the Strings
				}
			
		}
		else if(message instanceof FileCount){
				fileCount = message.getFilesBeingScanned();
		}
		else{
			unhandled(message);
		}
	}
}