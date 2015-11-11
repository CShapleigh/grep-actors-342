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
