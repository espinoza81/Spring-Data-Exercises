package gameshop.core;

import gameshop.exceptions.ValidationException;
import gameshop.messages.OutputMessages;
import gameshop.service.ExecutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {
    private final ExecutorService executorService;

    private final String END = "End";

    @Autowired
    public CommandLineRunnerImpl(
            ExecutorService executorService) {
        this.executorService = executorService;
    }

    @Override
    public void run(String... args){

        while (true) {
            String result;
            try {
                result = executorService.executeCommand();

                if (result.equals(END)) {
                    System.out.println(OutputMessages.EXIT_PROGRAM);
                    break;
                }

            } catch (IllegalArgumentException | NullPointerException | ValidationException e) {
                result = e.getMessage();
            }

            System.out.println(result);
        }
    }
}