package de.culo.lucyo;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SnapPictureStuffTest {

    @Test
    public void name() throws IOException {
        CameraApi.takeAPicture();
    }
}
