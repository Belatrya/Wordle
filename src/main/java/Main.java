import base.WordleFactory;
import org.springframework.context.annotation.Configuration;
import view.UserInterface;

@Configuration
public class Main {
    public static void main(String[] args) {
        WordleFactory wordleFactory = new WordleFactory();
        UserInterface userInterface = wordleFactory.createApp();
        userInterface.runGame();
    }
}
