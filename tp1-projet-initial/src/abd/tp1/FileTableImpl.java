package abd.tp1;

import java.util.List;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class FileTableImpl implements FileTable{

	protected int arity;
	protected Path path;
	
	public FileTableImpl(int arity, Path path) throws IOException{
		if (arity <= 0) 
			throw new IOException();
		this.arity = arity;
		this.path = path;
	}
	
	@Override
	public int getArity() {
		return arity;
	}

	@Override
	public Path getPath() {
		return path;
	}

	@Override
	public void addTuple(String... tuple) throws IllegalArgumentException, IOException {
		BufferedWriter writer = Files.newBufferedWriter(path, Charset.defaultCharset(), StandardOpenOption.APPEND);
		if(tuple.length == arity)
			for (int i = 0; i<tuple.length; i++)
				writer.write(tuple[i] + ";");
		else
			System.out.println("L'arité du tuple est différente de celle de la table");
		
	}

	@Override
	public TuplesIterator tuplesIterator() throws IOException {
		return new TuplesIteratorImpl(path);
	}

	@Override
	public FileTable select(Path resultPath, String contained, int columnRank) throws IllegalArgumentException, IOException {
		FileTable tmp = new FileTableImpl(arity, resultPath);
		TuplesIterator it = this.tuplesIterator();
		List<String[]> toWrite = new ArrayList<String[]>();
		//compare all the element in the table to know who have the same text than contained
		while (it.hasNext()) {
			String[] toCompare = it.next();
			if (toCompare[columnRank].equals(contained))
				toWrite.add(toCompare);
		}
		
		//write all the find element in the new table
		for (String[] tuple : toWrite) {
			tmp.addTuple(tuple);
		}
		return tmp;
	}

	@Override
	public FileTable project(Path resultPath, int... columnRanks) throws IllegalArgumentException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		
	}

}
