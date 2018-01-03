package persistence;

import model.Image;

import java.io.*;
import model.Image;

public class FileImageLoader implements ImageLoader {
    private File[] files;
    private int index = 0;

    public FileImageLoader(File folder) {
        this.files = folder.listFiles(imageType());
    }

    private FileFilter imageType() {
        return new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.getPath().endsWith(".jpg");
            }
        };
    }

    @Override
    public Image load() {
        return imageAt(index);
    }

    private Image imageAt(int i) {
        return new Image() {
            @Override
            public Image next() {
                return imageAt(i+1 >= files.length ? 0 : i+1);
            }

            @Override
            public Image prev() {
                return imageAt(i-1 < 0 ? files.length-1 : i-1);
            }

            @Override
            public InputStream stream() {
                try {
                    return new BufferedInputStream(new FileInputStream(files[i]));
                }catch(FileNotFoundException e) {
                    return null;
                }
            }

            @Override
            public String name() {
                return files[i].getName();
            }
        };
    }
}
