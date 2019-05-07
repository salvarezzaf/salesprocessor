package com.jpmchase.salesprocessor.input;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileMessageReader implements MessageReader {

    private String inputFilename;
    private List<String> saleNofitications;

    FileMessageReader(String filename) {
        this.inputFilename = filename;
    }

    public List<String> read() {

        try {
            Path salesFilePath = Paths.get(Objects.requireNonNull(getClass().getClassLoader().getResource(this.inputFilename), "A valid input file must exist").toURI());
            Stream<String> lines = Files.lines(salesFilePath);
            saleNofitications = lines.collect(Collectors.toList());
            lines.close();

        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }

        return saleNofitications;
    }
}
