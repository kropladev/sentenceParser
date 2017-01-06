package com.kropla.writer;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.powermock.reflect.Whitebox;

import java.io.File;

import static org.mockito.Mockito.mock;

/**
 * Created by kropla on 14.11.16.
 */
@RunWith(JUnit4.class)
public class CvsHeaderSentenceWriterTest {

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    private static final String HEADER = ",Word 1,Word 2";

    @Mock
    StringBuffer stringHeader;

    @Mock
    private File tempFile;

    @Test
    public void shouldGenerateHeaderTest() throws Exception {

        File csvFile = mock(File.class);

        CsvHeaderSentenceWriter writer = new CsvHeaderSentenceWriter(2,csvFile);
        Whitebox.invokeMethod(writer, "generateHeaderSentence");

        StringBuilder stringHeader = Whitebox.getInternalState(writer,"stringHeader");

        Assert.assertEquals(HEADER, stringHeader.toString());
    }

    @Test
    public void shouldWriteHeaderToFileTest() throws Exception {
        //G
        File csvFile = mock(File.class);
        CsvHeaderSentenceWriter writer = new CsvHeaderSentenceWriter(2,csvFile);

        //W
        Whitebox.invokeMethod(writer, "generateHeaderSentence");
        Whitebox.invokeMethod(writer, "writeHeaderSentenceIntoTempFile");
        File fileWithHeader = new File(CsvHeaderSentenceWriter.TEMP_FILE_NAME);

        //T
        Assert.assertTrue(fileWithHeader.exists());
    }

}
