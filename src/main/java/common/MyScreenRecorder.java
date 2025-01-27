package common;

import LOGGER.LogManager;
import org.monte.media.Format;
import org.monte.media.FormatKeys.MediaType;
import org.monte.media.Registry;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.monte.media.AudioFormatKeys.EncodingKey;
import static org.monte.media.AudioFormatKeys.FrameRateKey;
import static org.monte.media.AudioFormatKeys.KeyFrameIntervalKey;
import static org.monte.media.AudioFormatKeys.MIME_AVI;
import static org.monte.media.AudioFormatKeys.MediaTypeKey;
import static org.monte.media.AudioFormatKeys.MimeTypeKey;
import static org.monte.media.VideoFormatKeys.*;

public class MyScreenRecorder extends ScreenRecorder {
    public static ScreenRecorder screenRecorder;
    public String name;
    public static final LogManager LOGGER = LogManager.getInstance();


    /*
  TODO: The `MyScreenRecorder` constructor initializes a new instance of the `MyScreenRecorder` class.
         It performs the following steps:
      1. Calls the superclass `ScreenRecorder` constructor with the provided parameters.
      2. Sets the `name` field to the provided `name` parameter.
  */
    public MyScreenRecorder(GraphicsConfiguration cfg,
                            Rectangle captureArea,
                            Format fileFormat,
                            Format screenFormat,
                            Format mouseFormat,
                            Format audioFormat,
                            File movieFolder,
                            String name)
            throws IOException, AWTException {

        super(cfg, captureArea, fileFormat, screenFormat, mouseFormat, audioFormat, movieFolder);
        this.name = name;
    }


    /*
    TODO: The `createMovieFile` method is an overridden method from the `ScreenRecorder` class.
           It is responsible for creating a new movie file in the specified format. The method performs the following steps:
        1. Checks if the `movieFolder` directory exists. If not, it creates the directory.
        2. If `movieFolder` exists but is not a directory, it throws an `IOException`.
        3. Uses `SimpleDateFormat` to generate a timestamp for the file name.
        4. Constructs a new `File` object with the `movieFolder` path, a name that includes the provided `name`
        and the current timestamp, and the appropriate file extension based on the `fileFormat`.
        5. Returns the created `File` object.
    */
    @Override
    protected File createMovieFile(Format fileFormat) throws IOException {
        if (!movieFolder.exists()) {
            movieFolder.mkdirs();
        } else if (!movieFolder.isDirectory()) {
            throw new IOException("\"" + movieFolder + "\" is not a directory.");
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH.mm.ss");
        return new File(movieFolder, name + "-" + dateFormat.format(new Date()) + "." + Registry.getInstance().getExtension(fileFormat));
    }

    /*
    TODO: The `startRecording` method initializes and begins screen recording.
           It is responsible for setting up the recording environment and starting the recording process.
           The method performs the following steps:
        1. Creates a `File` object that points to the directory where recordings will be saved.
        2. Retrieves the screen dimensions to determine the area to capture.
        3. Sets up a `Rectangle` object representing the capture area.
        4. Gets the default graphics configuration for the current screen device.
        5. Creates an instance of the `MyScreenRecorder` class with the following settings:
           - File format: AVI (set for screen recording).
           - Video encoding: Specifies compression, frame rate, and quality settings.
           - Mouse and audio formats: Configurable (set to `null` for audio in this example).
           - Target directory and file name include the provided method name.
        6. Starts the screen recording session using the `start` method of `MyScreenRecorder`.
        7. Logs the start of the recording process for debugging or tracking purposes.
    */
    public static void startRecording(String methodName) throws Exception {
        File file = new File("./recordings/");

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;

        Rectangle captureSize = new Rectangle(0, 0, width, height);

        GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment()
                .getDefaultScreenDevice()
                .getDefaultConfiguration();

        LOGGER.info("Starting recording for method: " + methodName);
        screenRecorder = new MyScreenRecorder(gc, captureSize,
                new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI),
                new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                        CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE, DepthKey, 24, FrameRateKey,
                        Rational.valueOf(15), QualityKey, 1.0f, KeyFrameIntervalKey, 15 * 60),
                new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black", FrameRateKey, Rational.valueOf(30)),
                null, file, methodName);

        screenRecorder.start();
        LOGGER.info("Recording started for method: " + methodName);
    }


    public static void stopRecording() throws Exception {
        screenRecorder.stop();
    }
}
