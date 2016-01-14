package abd;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import abd.tp1.FileTable;
import abd.tp1.FileTableImpl;

/** This class contains factory methods that provide instances of various interfaces.
 * 
 * @author Iovka Boneva
 *
 */
public class Factory {

	/** Creates a FileTable with given backup file and arity.
	 * The backup file should not exist prior to creation.
	 * 
	 * @param path
	 * @param arity
	 * @return
	 * @throws IOException if the given file exists, or other I/O error occurs
	 */
	public static FileTable createFileTable (Path path, int arity) throws IOException {
		Files.createFile(path);
		return new FileTableImpl(arity, path);
	}
	
	/** Destroys the backup file of a file table.
	 * Before destruction, closes all resources used by the file table.
	 * 
	 * @param fileTable
	 * @throws IOException if the given file exists, or other I/O error occurs
	 */
	public static void destroyFileTable (FileTable fileTable) throws IOException {
		fileTable.close();
		Files.delete(fileTable.getPath());
	}

}
