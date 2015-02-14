import javax.swing.filechooser.FileFilter;
import java.io.File;

/**
 * Created by ME on 2/14/2015.
 */
public class PersonFileFilter extends FileFilter {
    @Override
    public boolean accept(File file) {

        if(file.isDirectory())
            return true;
        String name = file.getName();
        int index = name.lastIndexOf(".");

        if(index == -1)
            return false;
        else if(index == name.length() - 1)
            return false;
        else if(name.substring(index + 1, name.length()) != "per")
            return false;
        else
            return true;
    }

    @Override
    public String getDescription() {
        return "Person database files (*.per)";
    }
}
