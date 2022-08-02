package reporter;


import il.co.topq.difido.ReportDispatcher;
import il.co.topq.difido.ReportManager;
import il.co.topq.difido.model.Enums;
import il.co.topq.difido.model.Enums.Status;

import java.awt.*;
import java.io.File;
import java.time.LocalDateTime;

public class Reporter {
    private static ReportDispatcher reportDispatcher;
    private static int reportStepsCounter = 0;
    private static int reportLevelsCounter = 0;

    private static void getReportDispatcherInstance() {
        if (reportDispatcher == null)
            reportDispatcher = ReportManager.getInstance();
    }

    public static void log(String title, String message) {
        log(title, message, Status.in_progress);
    }

    public static void log(String message, Status status) {
        log(message, message, status);
    }

    public static void log(String message) {
        log(message, "");
    }

    public static void log(String title, String message, Status status) {
        getReportDispatcherInstance();
        reportDispatcher.log(title, message, status);
    }

    public static void addTestProperty(String str) {
        addTestProperty(str, "");
    }

    public static void addTestProperty(String key, String value) {
        getReportDispatcherInstance();
        reportDispatcher.addTestProperty(key, value);
    }

    public static void endTest() {
        log("End of the test");
    }

    public static void step(String str) {
        getReportDispatcherInstance();
        closeAllLevels();
        reportDispatcher.step("Step " + ++reportStepsCounter + ": " + str);
    }

    public static void closeAllLevels() {
        getReportDispatcherInstance();
        for (int i = 0; i < reportLevelsCounter; i++) {
            reportDispatcher.endLevel();
            --reportLevelsCounter;
        }
    }

    public static void startTest(LocalDateTime startTime) {
        log("Starting test " + startTime);
        reportStepsCounter = 0;
    }

    public static void htmlLog(String message, Enums.Status status) {
        getReportDispatcherInstance();
        reportDispatcher.logHtml(message, status);
    }

    public static void addFile(File file, String fileName) {
        getReportDispatcherInstance();
        reportDispatcher.addFile(file, fileName);
    }
}