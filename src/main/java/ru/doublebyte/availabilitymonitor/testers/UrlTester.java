package ru.doublebyte.availabilitymonitor.testers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.doublebyte.availabilitymonitor.entities.Monitoring;
import ru.doublebyte.availabilitymonitor.entities.TestResult;
import ru.doublebyte.availabilitymonitor.managers.TestResultManager;

public class UrlTester implements Tester {

    private static final Logger logger = LoggerFactory.getLogger(UrlTester.class);

    private TestResultManager testResultManager;
    private Monitoring monitoring;

    private UrlChecker urlChecker;

    ///////////////////////////////////////////////////////////////////////////

    public UrlTester(
            TestResultManager testResultManager,
            Monitoring monitoring
    ) {
        this.testResultManager = testResultManager;
        this.monitoring = monitoring;

        urlChecker = new UrlChecker(monitoring.getUrl(), monitoring.getRespondInterval());
    }

    ///////////////////////////////////////////////////////////////////////////

    @Override
    public void run() {
        logger.debug("Testing {}", monitoring);

        Result result = urlChecker.check();
        TestResult testResult = new TestResult(monitoring.getId(), result);
        testResultManager.add(monitoring, testResult);
    }

}
