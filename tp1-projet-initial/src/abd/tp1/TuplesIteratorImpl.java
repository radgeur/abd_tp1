package abd.tp1;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class TuplesIteratorImpl implements TuplesIterator{

	protected Scanner scan;
	
	public TuplesIteratorImpl(Path path) throws IOException {
		scan = new Scanner(path);
	}
	
	@Override
	public boolean hasNext() throws IOException {
		return scan.hasNext();
	}

	@Override
	public String[] next() throws IOException {
		return scan.next().split(";");
	}

	@Override
	public void close() throws IOException {
		scan.close();		
	}

}
