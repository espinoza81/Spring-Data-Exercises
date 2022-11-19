package productshop.core;

import productshop.constant.OutputMessages;
import productshop.service.ExecutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import productshop.service.SeedService;

import java.io.IOException;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {
    private final ExecutorService executorService;
    private final SeedService seedService;

    @Autowired
    public CommandLineRunnerImpl(
            ExecutorService executorService,
            SeedService seedService) {
        this.executorService = executorService;
        this.seedService = seedService;
    }

    @Override
    public void run(String... args) throws IOException {
        seedService.seedData();

        while (true) {
            String result;
            try {
                result = executorService.executeCommand();

                if (result.equals(OutputMessages.END_MENU)) {
                    System.out.println(OutputMessages.EXIT_PROGRAM);
                    break;
                }

            } catch (IllegalArgumentException | NullPointerException e) {
                result = e.getMessage();
            }

            System.out.println(result);
        }
    }
}