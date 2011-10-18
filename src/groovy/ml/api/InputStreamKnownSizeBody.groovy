package ml.api

import org.apache.http.entity.mime.content.InputStreamBody

public class InputStreamKnownSizeBody extends org.apache.http.entity.mime.content.InputStreamBody {
    def lenght;

    public InputStreamKnownSizeBody(InputStream input, def lenght, String mimeType, String filename) {
        super(input, mimeType, filename);

        this.lenght = lenght;
    }

    @Override
    long getContentLength() {
        return lenght as Long;
    }
}