import com.atlauncher.utils.Utils;
import org.junit.Test;

import javax.swing.text.html.StyleSheet;

public class TestCSS{
    @Test
    public void test(){
        StyleSheet sheet = Utils.createStyleSheet("twitter");
    }
}
