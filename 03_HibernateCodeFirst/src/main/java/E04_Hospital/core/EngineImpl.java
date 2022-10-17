package E04_Hospital.core;

import E04_Hospital.common.OutputMessages;
import E04_Hospital.core.interfaces.Controller;
import E04_Hospital.core.interfaces.Engine;
import E04_Hospital.io.ConsoleReader;
import E04_Hospital.io.ConsoleWriter;

import java.io.IOException;

public class EngineImpl implements Engine {
    private ConsoleReader reader;

    private ConsoleWriter writer;
    private final Controller controller;


    public EngineImpl(ConsoleReader reader, ConsoleWriter writer, Controller controller) {
        this.reader = reader;
        this.writer = writer;
        this.controller = controller;
    }

    public void run() {
        while (true) {
            String result = null;
            try {
                result = processInput();

                if (result.equals("END")) {
                    break;
                }

            } catch (IOException | IllegalArgumentException | NullPointerException e) {
                result = e.getMessage();
            }

            this.writer.writeLine(result);
        }

    }

    private String processInput() throws IOException {
        printMainMenu();
        int mainMenu = this.reader.readInt();


        String result = null;

        switch (mainMenu) {

            case 1:
                result = controller.addPatient();
                break;
            case 2:
                result = controller.addVisitation();
                break;
            case 3:
                result = controller.addDiagnose();
                break;
            case 4:
                result = controller.addMedicament();
                break;
            case 5:
                result = controller.searchPatientById().toString();
                break;
            case 6:
                result = controller.searchVisitationForDate();
                break;
            case 0:
                result = "END";
                break;
            default:
                result = OutputMessages.NO_SUCH_MENU;
        }
        return result.trim();
    }

    private void printMainMenu() {
        StringBuilder mainMenu = new StringBuilder().append(System.lineSeparator()).
                append("Софтуера е в процес на изработка. Някои функционалности може да не работят правилно, за което моля да ни извините").append(System.lineSeparator()).
                append("Формат за дата YYYY-MM-DD").append(System.lineSeparator()).
                append("--------- Основно меню ----------").append(System.lineSeparator()).
                append("1. Въведи пациент").append(System.lineSeparator()).
                append("2. Въведи преглед").append(System.lineSeparator()).
                append("3. Въведи диагноза").append(System.lineSeparator()).
                append("4. Въведи медикамент").append(System.lineSeparator()).
                append("5. Търси пациент").append(System.lineSeparator()).
                append("6. Търси прегледи за дата").append(System.lineSeparator()).
                append("0. Изход").append(System.lineSeparator()).
                append("Моля въведете вашият избор за подменю:");

        System.out.println(mainMenu);
    }
}