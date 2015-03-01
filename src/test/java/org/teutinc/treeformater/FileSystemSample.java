package org.teutinc.treeformater;

import com.teutinc.treeformater.TreeFormaters;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author apeyrard
 */
public class FileSystemSample {
	public static List<Path> getSubPath(Path path) {
		if (Files.isDirectory(path) && Files.isReadable(path)) {
			try {
				return Files.list(path).collect(Collectors.toList());
			} catch (IOException e) {
				throw new RuntimeException();
			}
		} else {
			return new ArrayList<>();
		}
	}

	public static void main(String[] args) {
		TreeFormaters.prettyPrint(
				Paths.get("/tmp"),
				p -> p.getFileName().toString(),
				FileSystemSample::getSubPath
		).forEach(System.out::println);
	}
}
