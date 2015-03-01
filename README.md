# tree-formater

Simple lib to format trees.

The `prettyPrint` method permits to generate trees like the "Ux" `tree` command line tool.
```
.
├── LICENSE
├── README.md
├── pom.xml
├── src
│   ├── main
│   └── test
├── target
│   ├── MANIFEST.MF
│   ├── classes
│   ├── generated-sources
│   ├── generated-test-sources
│   └── test-classes
└── tree-formater.iml
```

A sample using file system is implemented in the test classes: `FileSystemSample.java`, its very easy to generate a tree output:
```
        TreeFormaters.prettyPrint(
				Paths.get("/tmp"),
				p -> p.getFileName().toString(),
				FileSystemSample::getSubPath
		).forEach(System.out::println);
```